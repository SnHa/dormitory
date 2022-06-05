package com.atsun.dormitory.service.impl;

import com.atsun.dormitory.convert.Impl.DepartApplicationUserConvent;
import com.atsun.dormitory.dto.DepartApplicationPageDTO;
import com.atsun.dormitory.dto.DepartApplicationUserDTO;
import com.atsun.dormitory.dto.Message;
import com.atsun.dormitory.enums.TransCode;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.DepartApplication;
import com.atsun.dormitory.po.User;
import com.atsun.dormitory.service.DepartApplicationService;
import com.atsun.dormitory.service.StudentService;
import com.atsun.dormitory.service.UserService;
import com.atsun.dormitory.socket.WebSocket;
import com.atsun.dormitory.utils.JwtUtil;
import com.atsun.dormitory.utils.SnowFlake;
import com.atsun.dormitory.vo.DepartApplicationUserVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.atsun.dormitory.po.DepartApplicationUser;
import com.atsun.dormitory.dao.DepartApplicationUserMapper;
import com.atsun.dormitory.service.DepartApplicationUserService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-01 14:39
 **/
@Log4j2
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class DepartApplicationUserServiceImpl implements DepartApplicationUserService {

    private UserService userService;
    private StudentService studentService;
    private DepartApplicationService departApplicationService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @Autowired
    public void setDepartApplicationService(DepartApplicationService departApplicationService) {
        this.departApplicationService = departApplicationService;
    }

    @Resource
    private DepartApplicationUserMapper departApplicationUserMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(String id) throws TransException {
        return departApplicationUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(DepartApplicationUser record) throws TransException {
        return departApplicationUserMapper.insert(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertSelective(DepartApplicationUser record) throws TransException {
        return departApplicationUserMapper.insertSelective(record);
    }

    @Override
    public DepartApplicationUser selectByPrimaryKey(String id) throws TransException {
        return departApplicationUserMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKeySelective(DepartApplicationUser record) throws TransException {
        return departApplicationUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKey(DepartApplicationUser record) throws TransException {
        return departApplicationUserMapper.updateByPrimaryKey(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveApplication(String id, String userId, String applicationId) throws TransException {
        departApplicationUserMapper.saveApplication(id, userId, applicationId);
    }

    @Override
    public PageInfo<DepartApplicationUserVO> listMyFlow(DepartApplicationPageDTO departApplicationPageDTO, String token) throws TransException {

        PageHelper.startPage(departApplicationPageDTO.getPage(), departApplicationPageDTO.getRows());
        List<DepartApplicationUserVO> list = departApplicationUserMapper.listMyFlow(JwtUtil.decode(token));
        return new PageInfo<>(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String update(DepartApplicationUserDTO departApplicationUserDTO, String token) throws TransException {
        String msg = "成功，";
        User user = userService.findUser(token);
        // TODO  根据退舍消息id查询 通知上级的消息
        DepartApplicationUser departApplicationUser = departApplicationUserMapper.
                selectByPrimaryKey(departApplicationUserDTO.getId());

        if (!user.getId().equals(departApplicationUser.getOperateUserId())) {
            throw new TransException(TransCode.CUSTOM_EXCEPTION_MSG, "无法审核");
        }

        if (departApplicationUser.getIsAgree() != null) {
            throw new TransException(TransCode.CUSTOM_EXCEPTION_MSG, "已审核过，无法修改");
        }
        // TODO 更新 信息
        DepartApplicationUser depart = DepartApplicationUserConvent.INSTANCE.dtoToPo(departApplicationUserDTO);
        depart.setOperateTime(new Date());
        departApplicationUserMapper.updateByPrimaryKeySelective(depart);

        // TODO 获取发起的退审申请
        DepartApplication departApplication = departApplicationService.
                getByDepartApplicationUserId(departApplicationUserDTO.getId());

        // TODO 判断是否通过审核
        if (departApplicationUserDTO.getIsAgree()) {
            if (user.getPId() == null) {
                // TODO 当前用户最高级别 直接删除
                studentService.deleteByPrimaryKey(departApplication.getStudentId());
                departApplicationService.deleteByPrimaryKey(departApplication.getId());
                departApplicationUserMapper.deleteBydepartApplication(departApplication.getId());
                msg += "该学生已成功退宿";
            } else {
                // todo 继续发送消息给上级，让上级审核
                this.saveApplication(String.valueOf(SnowFlake.nextId()), user.getPId(), departApplication.getId());
                WebSocket.sendMessage(user.getPId(),
                        new Message().setTitle("新的退宿申请").setType(2).setMessageBody(departApplication.getReason()),
                        userService);
                msg += "等待上一级审核";
            }
            return msg;

        } else {
            msg += "审核未通过";
            return msg;
        }

    }

    @Override
    public int countFlowAgreeIsNotNull(String applicationId) throws TransException {
        return departApplicationUserMapper.countFlowAgreeIsNotNull(applicationId);
    }

    @Override
    public DepartApplicationUser getLastFlow(String applicationId) throws TransException {
        return departApplicationUserMapper.getLastFlow(applicationId);
    }

    @Override
    public List<DepartApplicationUserVO> listFlow(String applicationId) throws TransException {
        return departApplicationUserMapper.listFlow(applicationId);
    }

    @Override
    public int countFlowAgreeIsNull(String departApplicationId) throws TransException {
        return departApplicationUserMapper.countFlow(departApplicationId);
    }

    @Override
    public void deleteFlow(String departApplicationId) throws TransException {
        departApplicationUserMapper.deleteFlow(departApplicationId);
    }

    @Override
    public int countFlowAgreeNull(String operateUserId) throws TransException {
        return departApplicationUserMapper.countFlowAgreeNull(operateUserId);
    }

}
