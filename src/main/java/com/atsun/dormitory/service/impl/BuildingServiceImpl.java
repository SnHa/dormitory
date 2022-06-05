package com.atsun.dormitory.service.impl;

import com.atsun.dormitory.convert.Impl.BuildingConvent;
import com.atsun.dormitory.dao.RoomMapper;
import com.atsun.dormitory.dto.BuildingDTO;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.Room;
import com.atsun.dormitory.service.RoomService;
import com.atsun.dormitory.utils.SnowFlake;
import com.atsun.dormitory.vo.BuildingListVo;
import com.atsun.dormitory.vo.BuildingVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.atsun.dormitory.po.Building;
import com.atsun.dormitory.dao.BuildingMapper;
import com.atsun.dormitory.service.BuildingService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.atsun.dormitory.enums.TransCode.CUSTOM_EXCEPTION_MSG;

/**
 * @author: SH
 * @create: 2021-12-01 14:39
 **/
@Transactional(readOnly = true, rollbackFor = Exception.class)
@Service
public class BuildingServiceImpl implements BuildingService {

    @Resource
    private RoomMapper roomMapper;

    @Resource
    private BuildingMapper buildingMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(String id) throws TransException {
        return buildingMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(Building record) throws TransException {
        return buildingMapper.insert(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertSelective(Building record) throws TransException {
        return buildingMapper.insertSelective(record);
    }

    @Override
    public Building selectByPrimaryKey(String id) throws TransException {
        return buildingMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKeySelective(Building record) throws TransException {
        return buildingMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKey(Building record) throws TransException {
        return buildingMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<BuildingListVo> listInAll() throws TransException {
        return buildingMapper.findAll();
    }

    @Override
    public List<BuildingVo> finds() throws TransException {
        List<BuildingVo> finds = buildingMapper.finds();
        finds.forEach(buildingVo -> {
            try {
                List<String> ids = getIdsByParentId(buildingVo.getId());
                buildingVo.setStudentNum(buildingMapper.countStudentByBuildingIds(ids));
                buildingVo.setRoomNum(buildingMapper.countRoomByBuildingIds(ids));
            } catch (TransException e) {
                e.printStackTrace();
            }
        });
        return this.buildTree(finds);
    }

    @Override
    public List<String> getIdsByParentId(String pId) throws TransException {
        List<String> res = new ArrayList<>(16);
        res.add(pId);
        getIdsByParentId(pId, res);
        return res;
    }

    @Override
    public Building query(String buildingId) throws TransException {
        return buildingMapper.selectByPrimaryKey(buildingId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(BuildingDTO buildingDTO) throws TransException {

        // TODO dto转换成po
        Building building = BuildingConvent.INSTANCE.dtoToPo(buildingDTO);

        if (StringUtils.isBlank(building.getId())) {
            // TODO 添加
            building.setId(String.valueOf(SnowFlake.nextId()));
            buildingMapper.insert(building);
        } else {
            // TODO 修改
            buildingMapper.updateByPrimaryKeySelective(building);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String buildingId) throws TransException {
        List<String> bids = this.getIdsByParentId(buildingId);
        List<Room> list = roomMapper.listByBuildingId(bids);
        if (list.size() > 0) {
            throw new TransException(CUSTOM_EXCEPTION_MSG, "该节点下或子节点还有寝室，无法删除");
        }
        buildingMapper.deleteByPrimaryKey(buildingId);
    }

    private void getIdsByParentId(String bId, List<String> result) throws TransException {
        List<String> childrenIds = buildingMapper.listByParentId(bId);
        result.addAll(childrenIds);
        childrenIds.forEach(l -> {
            try {
                getIdsByParentId(l, result);
            } catch (TransException e) {
                e.printStackTrace();
            }
        });
    }

    // TODO Tree
    public List<BuildingVo> buildTree(List<BuildingVo> treeNodes) {
        //遍历list
        for (BuildingVo treeNode : treeNodes) {
            //如果元素不是顶级目录
            if (treeNode.getPid() != null && !"0".equals(treeNode.getPid())) {
                //再次遍历List
                for (BuildingVo node : treeNodes) {
                    //在整个list中查找元素的父级
                    if (treeNode.getPid().equals(node.getId())) {
                        //将元素放入父级中
                        if (node.getChildren() == null) {
                            node.setChildren(new ArrayList<>());
                        }
                        node.getChildren().add(treeNode);
                    }
                }
            }
        }
        //删除所有不为顶级目录的元素
        treeNodes.removeIf(treeNode -> treeNode.getPid() != null && !"0".equals(treeNode.getPid()));
        System.out.println("树结构的长度为：" + treeNodes.size());
        return treeNodes;
    }


    private void traverseTree(List<BuildingVo> list) {

        list.forEach(buildingVo -> {
            List<String> result = new ArrayList<>(16);
            result.add(buildingVo.getId());
            try {
                getIdsByParentId(buildingVo.getId(), result);
            } catch (TransException e) {
                System.out.println("===================存在异常");
            }
            int studentNum = buildingMapper.countStudentByBuildingIds(result);
            int roomNum = buildingMapper.countRoomByBuildingIds(result);
            buildingVo.setStudentNum(studentNum);
            buildingVo.setRoomNum(roomNum);
            if (buildingVo.getChildren() != null) {
                traverseTree(buildingVo.getChildren());
            }
        });

    }

}
