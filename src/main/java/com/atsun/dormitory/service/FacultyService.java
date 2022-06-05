package com.atsun.dormitory.service;

import com.atsun.dormitory.dto.FacultyDTO;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.Faculty;
import com.atsun.dormitory.vo.DataResponse;
import com.atsun.dormitory.vo.FacultyVO;

import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-01 14:38
 **/
public interface FacultyService {


    int deleteByPrimaryKey(String id) throws TransException;

    int insert(Faculty record) throws TransException;

    int insertSelective(Faculty record) throws TransException;

    Faculty selectByPrimaryKey(String id) throws TransException;

    int updateByPrimaryKeySelective(Faculty record) throws TransException;

    int updateByPrimaryKey(Faculty record) throws TransException;

    /**
     * 查询该id下所有的院校
     *
     * @param facultyId 学院id
     * @return String
     * @throws TransException 异常
     */
    List<String> getIdsByParentId(String facultyId) throws TransException;

    /**
     * 查询全部学院信息
     *
     * @return List<FacultyVO>
     * @throws TransException 异常
     */
    List<FacultyVO> list() throws TransException;

    /**
     * @param facultyId 学院id
     * @return FacultyVO
     * @throws TransException 异常
     */
    FacultyVO query(String facultyId) throws TransException;

    /**
     * 根据id是否存在进行添加-修改
     *
     * @param facultyDTO 学院表单
     * @throws TransException 异常
     */
    void saveOrUpdate(FacultyDTO facultyDTO) throws TransException;

    /**
     * 删除学院
     *
     * @param facultyId 学院id
     * @throws TransException 异常
     */
    void delete(String facultyId) throws TransException;

    /**
     * 列表查询全部的学院
      * @return   List<FacultyVO>
     * @throws TransException 异常
     */
    List<FacultyVO> listAll() throws TransException;

    /**
     * 图表查询每个学院的人数
     * @return
     * @throws TransException 异常
     */
    Object FaculyByNumber() throws TransException;
}
