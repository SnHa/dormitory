package com.atsun.dormitory.service.impl;

import com.atsun.dormitory.convert.Impl.BackLateConvent;
import com.atsun.dormitory.dto.BackLatePageDTO;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.service.BuildingService;
import com.atsun.dormitory.service.UserService;
import com.atsun.dormitory.utils.SnowFlake;
import com.atsun.dormitory.vo.BackLateVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.atsun.dormitory.dao.BackLateMapper;
import com.atsun.dormitory.po.BackLate;
import com.atsun.dormitory.service.BackLateService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-01 14:39
 **/
@Log4j2
@Transactional(readOnly = true, rollbackFor = Exception.class)
@Service
public class BackLateServiceImpl implements BackLateService {

    private UserService userService;
    private BuildingService buildingService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setBuildingService(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @Resource
    private BackLateMapper backLateMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(String id) throws TransException {
        return backLateMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(BackLate record) throws TransException {
        return backLateMapper.insert(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertSelective(BackLate record) throws TransException {
        return backLateMapper.insertSelective(record);
    }

    @Override
    public BackLate selectByPrimaryKey(String id) throws TransException {
        return backLateMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKeySelective(BackLate record) throws TransException {
        return backLateMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKey(BackLate record) throws TransException {
        return backLateMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<BackLateVO> list(BackLatePageDTO backLatePageDTO, String token) throws TransException {
        List<String> bids = buildingService.getIdsByParentId(userService.findUser(token).getBuildingId());
        PageHelper.startPage(backLatePageDTO.getPage(), backLatePageDTO.getRows());
        return new PageInfo<>(backLateMapper.list(backLatePageDTO.getStudentId(), bids));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(BackLatePageDTO backLatePageDTO) throws TransException {

        BackLate backLate = BackLateConvent.INSTANCE.dtoToPo(backLatePageDTO);
        if (StringUtils.isBlank(backLate.getId())) {
            backLate.setId(String.valueOf(SnowFlake.nextId()));
            backLate.setCreateTime(new Date());
            backLateMapper.insert(backLate);
        } else {
            backLate.setUpdateTime(new Date());
            backLateMapper.updateByPrimaryKeySelective(backLate);
        }

    }

    @Override
    public BackLateVO query(String blid) throws TransException {
        return backLateMapper.query(blid);
    }

}
