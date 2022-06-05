package com.atsun.dormitory.dao;

import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.Faculty;
import com.atsun.dormitory.vo.FacultyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-01 14:38
 **/
@Mapper
public interface FacultyMapper {
    int deleteByPrimaryKey(String id) throws TransException;

    int insert(Faculty record) throws TransException;

    int insertSelective(Faculty record) throws TransException;

    Faculty selectByPrimaryKey(String id)  throws TransException;

    int updateByPrimaryKeySelective(Faculty record) throws TransException;

    int updateByPrimaryKey(Faculty record) throws TransException;

    /**
     * 查询id下的子id
     *
     * @param facultyId 学院id
     * @return list
     * @throws TransException 异常
     */
    List<String> listByParentId(String facultyId) throws TransException;

    /**
     * 查询全部的学院
     *
     * @return List<FacultyVO>
     * @throws TransException 异常
     */
    List<FacultyVO> list(@Param("parentId") String parentId) throws TransException;

    /**
     *  查询单个学院信息
     * @param facultyId 学院id
     * @return FacultyVO
     * @throws TransException 异常
     */
    FacultyVO query(String facultyId) throws TransException;

    /**
     * 列表查询全部学院
     * @return  List<FacultyVO>
     * @throws TransException 异常
     */
    List<FacultyVO> listAll() throws TransException;

}
