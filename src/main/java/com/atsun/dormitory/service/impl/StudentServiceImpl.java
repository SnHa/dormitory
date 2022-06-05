package com.atsun.dormitory.service.impl;

import com.atsun.dormitory.convert.Impl.DepartApplicationConvent;
import com.atsun.dormitory.convert.Impl.StudentConvent;
import com.atsun.dormitory.dao.UserMapper;
import com.atsun.dormitory.dto.DepartApplicationDTO;
import com.atsun.dormitory.dto.Message;
import com.atsun.dormitory.dto.StudentDTO;
import com.atsun.dormitory.dto.StudentPageDTO;
import com.atsun.dormitory.enums.TransCode;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.DepartApplication;
import com.atsun.dormitory.po.User;
import com.atsun.dormitory.service.*;
import com.atsun.dormitory.socket.WebSocket;
import com.atsun.dormitory.utils.JwtUtil;
import com.atsun.dormitory.utils.SnowFlake;
import com.atsun.dormitory.vo.StudentVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.atsun.dormitory.po.Student;
import com.atsun.dormitory.dao.StudentMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-01 14:37
 **/
@Log4j2
@Transactional(readOnly = true, rollbackFor = Exception.class)
@Service
public class StudentServiceImpl implements StudentService {

    private FacultyService facultyService;
    private BuildingService buildingService;
    private RoomService roomService;
    private DepartApplicationService departApplicationService;
    private UserService userService;
    private DepartApplicationUserService departApplicationUserService;


    @Autowired
    public void setFacultyService(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Autowired
    public void setBuildingService(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @Autowired
    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }

    @Autowired
    public void setDepartApplicationService(DepartApplicationService departApplicationService) {
        this.departApplicationService = departApplicationService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setDepartApplicationUserService(DepartApplicationUserService departApplicationUserService) {
        this.departApplicationUserService = departApplicationUserService;
    }

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(String id) throws TransException {
        return studentMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(Student record) throws TransException {
        return studentMapper.insert(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertSelective(Student record) throws TransException {
        return studentMapper.insertSelective(record);
    }

    @Override
    public Student selectByPrimaryKey(String id) throws TransException {
        return studentMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKeySelective(Student record) throws TransException {
        return studentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKey(Student record) throws TransException {
        return studentMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<StudentVO> list(StudentPageDTO studentPageDTO, String token) throws TransException {

        String buildingId = userMapper.findBuilding(JwtUtil.decode(token));

        List<String> facultyIds = null;

        if (!StringUtils.isBlank(studentPageDTO.getFacultyId())) {
            facultyIds = facultyService.getIdsByParentId(studentPageDTO.getFacultyId());
        }

        List<String> buildingIds = buildingService.getIdsByParentId(buildingId);
        PageHelper.startPage(studentPageDTO.getPage(), studentPageDTO.getRows());
        List<StudentVO> studentList = studentMapper.list(studentPageDTO.getName(), studentPageDTO.getRoomId(), studentPageDTO.getNumber(),
                studentPageDTO.getIsLeave(), facultyIds, buildingIds);

        return new PageInfo<>(studentList);
    }

    @Override
    public int countByRoomId(String roomId) throws TransException {
        return studentMapper.countByRoomId(roomId);
    }

    @Override
    public Integer countByFacultyId(List<String> facultyIds) throws TransException {
        return studentMapper.countByFacultyId(facultyIds);
    }

    @Override
    public StudentVO query(String studentId) throws TransException {
        return studentMapper.query(studentId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(StudentDTO studentDTO) throws TransException {

        //TODO dto-->po
        Student student = StudentConvent.INSTANCE.dtoToPo(studentDTO);
        student.setSex(studentDTO.getSex());
        //  TODO 判断该学生是否是现在寝室
        boolean noRoom = true; //不再寝室

        if (!StringUtils.isBlank(studentDTO.getId())) {
            if (studentDTO.getRoomId().equals(roomService.
                    selectByPrimaryKey(studentMapper.selectByPrimaryKey(studentDTO.getId()).getRoomId()).getId())) {
                noRoom = false; //在寝室
            }
        }
        // TODO 判断寝室是否已满
        int maxCapacity = roomService.getMaxCapacity(student.getRoomId());
        int count = studentMapper.countByRoomId(student.getRoomId());
        if (count >= maxCapacity && noRoom) {
            throw new TransException(TransCode.CUSTOM_EXCEPTION_MSG, "寝室已满");
        } else {
            // TODO 判断id是否存在
            if (StringUtils.isBlank(student.getId())) {
                // TODO 判断学号是否存在
                if ((studentMapper.selectByNumber(studentDTO.getNumber()) == null ?
                        0 : studentMapper.selectByNumber(studentDTO.getNumber())) >= 1) {
                    throw new TransException(TransCode.CUSTOM_EXCEPTION_MSG, "学号存在");
                }
                student.setId(String.valueOf(SnowFlake.nextId()));
                student.setRegistrationDate(new Date());
                student.setIsLeave(false);

                studentMapper.insert(student);
            } else {
                // TODO 判断学号是否修改
                if (!studentDTO.getNumber().equals(student.getNumber())){
                    // TODO 判断学号是否存在
                    if ((studentMapper.selectByNumber(studentDTO.getNumber()) == null ?
                            0 : studentMapper.selectByNumber(studentDTO.getNumber())) >= 1) {
                        throw new TransException(TransCode.CUSTOM_EXCEPTION_MSG, "学号存在");
                    }
                }
                studentMapper.updateByPrimaryKeySelective(student);
            }
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(DepartApplicationDTO departApplicationDTO, String token) throws TransException {
        // TODO 判断 该管理者是否有直接删除的权力
        User user = userMapper.selectByPrimaryKey(JwtUtil.decode(token));
        departApplicationDTO.setApplyUserId(user.getId());
        departApplicationDTO.setTime(new Date());
        String departId = String.valueOf(SnowFlake.nextId());
        departApplicationDTO.setId(departId);
        //  TODO departApplication的dto->po
        DepartApplication departApplication = DepartApplicationConvent.INSTANCE.dtoToPo(departApplicationDTO);
        // TODO 添加在申请
        departApplicationService.saveApplication(departApplication);
        if (StringUtils.isBlank(user.getPId())) {
            // TODO 删除添加消息
            departApplicationService.deleteByPrimaryKey(departId);
            studentMapper.deleteByPrimaryKey(departApplicationDTO.getStudentId());
        } else {
            departApplicationUserService.saveApplication(String.valueOf(SnowFlake.nextId()), user.getPId(), departApplication.getId());
            WebSocket.sendMessage(user.getPId(),
                    new Message().setTitle("新的退宿申请").setType(2).setMessageBody(departApplication.getReason()),
                    userService);
        }
    }

    @Override
    public List<StudentVO> list(String name, String token) throws TransException {
        // 查询用户管理的所有宿舍楼
        List<String> bis = buildingService.getIdsByParentId(userService.findUser(token).getBuildingId());
        return studentMapper.listByName(name, bis);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateLeave(boolean isLeave, String studentId) throws TransException {
        studentMapper.updateLeave(isLeave, studentId);
    }

    @Override
    public HashMap<String, Integer> sexNumber() throws TransException {
       Integer man= studentMapper.selectBySex("男");
        Integer girl= studentMapper.selectBySex("女");
        HashMap<String, Integer> sex = new HashMap<>(10);
        sex.put("man",man);
        sex.put("girl",girl);
        return sex;
    }

    @Override
    public String selectByNumber(String number) throws TransException {
      return   studentMapper.selectNumber(number);
    }


}
