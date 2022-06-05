package com.atsun.dormitory.dao;

import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.Building;
import com.atsun.dormitory.vo.BuildingListVo;
import com.atsun.dormitory.vo.BuildingVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-01 14:39
 **/
@Mapper
public interface BuildingMapper {
    int deleteByPrimaryKey(String id) throws TransException;

    int insert(Building record) throws TransException;

    int insertSelective(Building record) throws TransException;

    Building selectByPrimaryKey(String id) throws TransException;

    int updateByPrimaryKeySelective(Building record) throws TransException;

    int updateByPrimaryKey(Building record) throws TransException;

    /**
     * 列表查询 id name
     *
     * @return list
     * @throws TransException 异常
     */
    List<BuildingListVo> findAll() throws TransException;

    /**
     * 全部宿舍Tree
     * @return list
     * @throws TransException 异常
     */
    List<BuildingVo> finds() throws TransException;

    /**
     * 根据宿舍的父id查询下面所有的字id
     * @param pId 父id
     * @return List
     * @throws TransException 异常
     */
    List<String> listByParentId(String pId) throws TransException;

    Integer countStudentByBuildingIds(@Param("buildingIds") List<String> ids);

    Integer countRoomByBuildingIds(@Param("buildingIds")List<String> ids);
}
