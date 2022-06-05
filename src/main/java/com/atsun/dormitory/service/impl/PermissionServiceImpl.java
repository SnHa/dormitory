package com.atsun.dormitory.service.impl;

import com.atsun.dormitory.convert.Impl.PermissionConvent;
import com.atsun.dormitory.dao.RolePermissionMapper;
import com.atsun.dormitory.dto.PermissionDTO;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.service.RolePermissionService;
import com.atsun.dormitory.utils.SnowFlake;
import com.atsun.dormitory.vo.PermissionVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.atsun.dormitory.po.Permission;
import com.atsun.dormitory.dao.PermissionMapper;
import com.atsun.dormitory.service.PermissionService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-01 14:38
 **/
@Log4j2
@Transactional(readOnly = true, rollbackFor = Exception.class)
@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private PermissionMapper permissionMapper;

    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(String id) throws TransException {
        return permissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(Permission record) throws TransException {
        return permissionMapper.insert(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertSelective(Permission record) throws TransException {
        return permissionMapper.insertSelective(record);
    }

    @Override
    public Permission selectByPrimaryKey(String id) throws TransException {
        return permissionMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKeySelective(Permission record) throws TransException {
        return permissionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKey(Permission record) throws TransException {
        return permissionMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<PermissionVO> findMenu(List<String> permissionIds) throws TransException {
        List<Permission> menu = permissionMapper.findMenu(permissionIds);
        List<PermissionVO> menuVO = new ArrayList<>();
        for (Permission p : menu) {
            menuVO.add(PermissionConvent.INSTANCE.permissionToPermissionVO(p));
        }
        return this.buildTree(menuVO);
    }

    @Override
    public List<String> findPermiss(List<String> permissionIds) {
        return permissionMapper.findPermiss(permissionIds);
    }

    @Override
    public List<PermissionVO> listPermission() throws TransException {
        return this.buildTree(permissionMapper.find());
    }

    @Override
    public Permission queryPermission(String permissionId) throws TransException {
        return permissionMapper.selectByPrimaryKey(permissionId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(PermissionDTO permissionDTO) throws TransException {
        System.out.println(permissionDTO.getId());
        if (permissionDTO.getId() != null && !permissionDTO.getId().equals("")) {
            if (permissionMapper.selectByPrimaryKey(permissionDTO.getId()) != null) {
                Permission updatePermission = PermissionConvent.INSTANCE.permissionDtoToPermission(permissionDTO);
                updatePermission.setUpdateTime(new Date());
                permissionMapper.updateByPrimaryKeySelective(updatePermission);
            }
        } else {
            Permission savePermission = PermissionConvent.INSTANCE.permissionDtoToPermission(permissionDTO);
            savePermission.setCreateTime(new Date());
            savePermission.setId(String.valueOf(SnowFlake.nextId()));
            permissionMapper.insert(savePermission);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePermission(String permissionId) throws TransException {
        // TODO 找到id下的子id
        List<Permission> childIdS = permissionMapper.findChildIdS(permissionId);
        childIdS.forEach(permission -> {
            try {
                deletePermission(permission.getId());
            } catch (TransException e) {
                e.printStackTrace();
            }
        });
        // TODO 删除表role_permission 的permission  Id 数据
        log.info("删除表role_permission 的permission  Id 数据" + permissionId);
        rolePermissionMapper.deleteByPermissionId(permissionId);
        // TODO 删除表permission id和子id 的数据
        log.info("TODO 删除表permission id的数据" + permissionId);
        permissionMapper.deleteByPrimaryKey(permissionId);
    }

    // TODO 给菜单构建树形结构

    public List<PermissionVO> buildTree(List<PermissionVO> treeNodes) {
        //遍历list
        for (PermissionVO treeNode : treeNodes) {
            //如果元素不是顶级目录
            if (treeNode.getPid() != null && !"0".equals(treeNode.getPid())) {
                //再次遍历List
                for (PermissionVO node : treeNodes) {
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
}
