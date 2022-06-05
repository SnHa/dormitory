package com.atsun.dormitory.dao;

import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.Student;
import com.atsun.dormitory.vo.StudentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-01 14:37
 **/
@Mapper
public interface StudentMapper {
    int deleteByPrimaryKey(String id) throws TransException;

    int insert(Student record) throws TransException;

    int insertSelective(Student record) throws TransException;

    Student selectByPrimaryKey(String id) throws TransException;

    int updateByPrimaryKeySelective(Student record) throws TransException;

    int updateByPrimaryKey(Student record) throws TransException;

    /**
     * 条件查询全部学生
     *
     * @param name
     * @param roomId
     * @param number
     * @param isLeave
     * @param facultyIds
     * @param buildingIds
     * @return
     * @throws TransException
     */
    List<StudentVO> list(@Param("name") String name, @Param("roomId") String roomId, @Param("number") String number,
                         @Param("isLeave") Boolean isLeave, @Param("facultyIds") List<String> facultyIds, @Param("buildingIds") List<String> buildingIds) throws TransException;

    /**
     * 查询宿舍下还存在几个学生
     *
     * @param roomId 宿舍id
     * @return int
     * @throws TransException 异常
     */
    int countByRoomId(String roomId) throws TransException;

    /**
     * 根据faculty id 获取学生列表
     *
     * @param facultyIds 学院id
     * @return INT
     * @throws TransException 异常
     */
    Integer countByFacultyId(@Param("facultyId") List<String> facultyIds) throws TransException;

    /**
     * 查询单个学生信息
     *
     * @param studentId 学生id
     * @return StudentVO
     * @throws TransException 异常
     */
    StudentVO query(String studentId) throws TransException;

    /**
     * 根据名字查询
     *
     * @param name        名字
     * @param buildingIds 建筑id
     * @return list
     */
    List<StudentVO> listByName(@Param("name") String name, @Param("buildingIds") List<String> buildingIds) throws TransException;


    /**
     * 更新在寝状态
     *
     * @param isLeave   是否离校
     * @param studentId 学生id
     */
    void updateLeave(@Param("isLeave") boolean isLeave, @Param("studentId") String studentId) throws TransException;

    /**
     * 根据学号查找学生
     *
     * @param number 学号
     * @return string
     * @throws TransException 异常
     */
    Integer selectByNumber(String number) throws TransException;

    /**
     * 根据性别查询人数
     *
     * @param sex 性别
     * @return int
     */
    Integer selectBySex(String sex) throws TransException;

    /**
     * 查询学生寝室id
     * @param number
     * @return
     * @throws TransException
     */
    String selectNumber(String number) throws TransException;
}
