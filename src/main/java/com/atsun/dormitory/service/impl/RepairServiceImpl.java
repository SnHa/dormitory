package com.atsun.dormitory.service.impl;

import com.atsun.dormitory.convert.Impl.RepairConvent;
import com.atsun.dormitory.dao.RepairMapper;
import com.atsun.dormitory.dto.RepairPageDTO;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.Repair;
import com.atsun.dormitory.service.BuildingService;
import com.atsun.dormitory.service.ImageService;
import com.atsun.dormitory.service.RepairService;
import com.atsun.dormitory.service.UserService;
import com.atsun.dormitory.utils.SnowFlake;
import com.atsun.dormitory.vo.RepairVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-01 14:38
 **/
@Log4j2
@Transactional(readOnly = true, rollbackFor = Exception.class)
@Service
public class RepairServiceImpl implements RepairService {

    private ImageService imageService;
    private BuildingService buildingService;
    private UserService userService;

    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

    @Autowired
    public void setBuildingService(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Resource
    private RepairMapper repairMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(String id) throws TransException {
        return repairMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(Repair record) throws TransException {
        return repairMapper.insert(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertSelective(Repair record) throws TransException {
        return repairMapper.insertSelective(record);
    }

    @Override
    public Repair selectByPrimaryKey(String id) throws TransException {
        return repairMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKeySelective(Repair record) throws TransException {
        return repairMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKey(Repair record) throws TransException {
        return repairMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<RepairVO> list(RepairPageDTO repairPageDTO, String token) throws TransException {
        List<String> bids = buildingService.getIdsByParentId(userService.findUser(token).getBuildingId());
        PageHelper.startPage(repairPageDTO.getPage(), repairPageDTO.getRows());

        List<RepairVO> list = repairMapper.list(repairPageDTO.getRoomId(), repairPageDTO.getStatus(), repairPageDTO.getCreateDate(), bids);
        // TODO 测试图片显示
        // TODO 字符串分割
        list.forEach(repairVO -> {
            if (repairVO.getImg() != null) {
                List<String> strings = Arrays.asList(repairVO.getImg().split(","));
                repairVO.setPicture(strings);
            }

        });
        return PageInfo.of(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(RepairPageDTO repairPageDTO) throws TransException {
        repairPageDTO.setImg(null);
        repairPageDTO.getPicture().forEach(s -> {
            repairPageDTO.setImg(repairPageDTO.getImg() != null ? repairPageDTO.getImg() + "," + s : s);
        });
        // TODO DTO->PO
        Repair repair = RepairConvent.INSTANCE.dtoToPo(repairPageDTO);

        // TODO 判断id是否存在
        if (StringUtils.isBlank(repairPageDTO.getId())) {
            repair.setStatus(false);
            repair.setCreateDate(new Date());
            repair.setId(String.valueOf(SnowFlake.nextId()));
            repairMapper.insert(repair);
        } else {
            repairMapper.updateByPrimaryKey(repair);
        }
    }

    @Override
    public RepairVO query(String rid) throws TransException {
        RepairVO query = repairMapper.query(rid);
        // TODO 测试图片显示
        // TODO 字符串分割
        if (query.getImg() != null) {
            List<String> strings = Arrays.asList(query.getImg().split(","));
            query.setPicture(strings);
        }
        return query;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStatus(String rid) throws TransException {
        // TODO 先查询维修数据
        Repair repair = repairMapper.selectByPrimaryKey(rid);
        //TODO 修改维修状态 维修时间
        repair.setStatus(true);
        repair.setFinishDate(new Date());
        // TODO 修改
        repairMapper.updateByPrimaryKeySelective(repair);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String rid) throws TransException {
        // TODO 删除维修图片
        Repair repair = repairMapper.selectByPrimaryKey(rid);
        if (repair.getImg() != null) {
            List<String> strings = Arrays.asList(repair.getImg().split(","));
            imageService.deleteBySaveName(strings);
        }
        // TODO 删除维修记录
        repairMapper.deleteByPrimaryKey(rid);
    }

}
