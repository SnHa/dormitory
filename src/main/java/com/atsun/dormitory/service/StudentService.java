package com.atsun.dormitory.service;

import com.atsun.dormitory.dto.DepartApplicationDTO;
import com.atsun.dormitory.dto.StudentDTO;
import com.atsun.dormitory.dto.StudentPageDTO;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.Student;
import com.atsun.dormitory.vo.StudentVO;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-01 14:37
 **/
public interface StudentService {


    int deleteByPrimaryKey(String id) throws TransException;

    int insert(Student record) throws TransException;

    int insertSelective(Student record) throws TransException;

    Student selectByPrimaryKey(String id) throws TransException;

    int updateByPrimaryKeySelective(Student record) throws TransException;

    int updateByPrimaryKey(Student record) throws TransException;

    PageInfo<StudentVO> list(StudentPageDTO studentPageDTO, String token) throws TransException;

    /**
     * 查询该宿舍下是否存在学生
     *
     * @param roomId 宿舍id
     * @return int
     * @throws TransException 异常
     */
    int countByRoomId(String roomId) throws TransException;

    /**
     * 根据faculty id 获取学生列表
     *
     * @param facultyIds
     * @return
     * @throws TransException
     */
    Integer countByFacultyId(List<String> facultyIds) throws TransException;

    /**
     * 查询单个学生信息
     *
     * @param studentId 学生id
     * @return StudentVO
     * @throws TransException 异常
     */
    StudentVO query(String studentId) throws TransException;

    /**
     * 根据id是否存在进行添加--修改
     *
     * @param studentDTO 学生信息表单
     * @throws TransException 异常
     */
    void saveOrUpdate(StudentDTO studentDTO) throws TransException;

    /**
     * 退舍申请
     *
     * @param departApplicationDTO 退舍表单
     * @param token                token
     * @throws TransException 异常
     */
    void delete(DepartApplicationDTO departApplicationDTO, String token) throws TransException;

    /**
     * 根据名字列出
     *
     * @param name  名字
     * @param token token
     * @return list
     * @throws TransException 异常
     */
    List<StudentVO> list(String name, String token) throws TransException;

    /**
     * 修改学生是否在校
     *
     * @param isLeave   是否在校
     * @param studentId 学生id
     */
    void updateLeave(boolean isLeave, String studentId) throws TransException;


    /**
     * 查询男女生人数
     *
     * @return map
     * @throws TransException 异常
     */
    HashMap<String, Integer> sexNumber() throws TransException;

    /**
     * 根据学号查询学生寝室房间号
     *
     * @param number 学号
     * @return 房间号id
     * @throws TransException 异常
     */
    String selectByNumber(String number) throws TransException;
}
