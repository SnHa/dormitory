package com.atsun.dormitory.service.impl;

import com.atsun.dormitory.convert.Impl.LeaveConvent;
import com.atsun.dormitory.dto.LeavePageDTO;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.service.BuildingService;
import com.atsun.dormitory.service.StudentService;
import com.atsun.dormitory.service.UserService;
import com.atsun.dormitory.utils.SnowFlake;
import com.atsun.dormitory.vo.LeaveVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.atsun.dormitory.dao.LeaveMapper;
import com.atsun.dormitory.po.Leave;
import com.atsun.dormitory.service.LeaveService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-01 14:38
 **/
@Log4j2
@Transactional(readOnly = true, rollbackFor = Exception.class)
@Service
public class LeaveServiceImpl implements LeaveService {

    private UserService userService;
    private StudentService studentService;
    private BuildingService buildingService;

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setBuildingService(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @Resource
    private LeaveMapper leaveMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByPrimaryKey(String id) throws TransException {
         leaveMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(Leave record) throws TransException {
        return leaveMapper.insert(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertSelective(Leave record) throws TransException {
        return leaveMapper.insertSelective(record);
    }

    @Override
    public Leave selectByPrimaryKey(String id) throws TransException {
        return leaveMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKeySelective(Leave record) throws TransException {
        return leaveMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKey(Leave record) throws TransException {
        return leaveMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<LeaveVO> list(LeavePageDTO leavePageDTO, String token) throws TransException {

        List<String> bids = buildingService.getIdsByParentId(userService.findUser(token).getBuildingId());
        PageHelper.startPage(leavePageDTO.getPage(), leavePageDTO.getRows());
        return new PageInfo<>(leaveMapper.list(leavePageDTO.getStudentId(), leavePageDTO.getIsBack(), bids));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(LeavePageDTO leavePageDTO) throws TransException {
        Leave leave = LeaveConvent.INSTANCE.dtoToPo(leavePageDTO);
        leave.setId(String.valueOf(SnowFlake.nextId()));
        leave.setTime(new Date());
        leaveMapper.save(leave);
        studentService.updateLeave(true, leavePageDTO.getStudentId());
    }

    @Override
    public LeaveVO query(String lid) throws TransException {
        return leaveMapper.query(lid);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(String lid) throws TransException {
        leaveMapper.update(lid);
        LeaveVO query = leaveMapper.query(lid);
        studentService.updateLeave(false, query.getStudentId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String lid) throws TransException {
        LeaveVO query = leaveMapper.query(lid);
        if (query!=null){
            leaveMapper.deleteByPrimaryKey(lid);
            studentService.updateLeave(false,query.getStudentId());
        }
    }

}
