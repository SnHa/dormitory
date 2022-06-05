package com.atsun.dormitory.service;

import com.atsun.dormitory.dto.BuildingDTO;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.Building;
import com.atsun.dormitory.vo.BuildingListVo;
import com.atsun.dormitory.vo.BuildingVo;

import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-01 14:39
 **/
public interface BuildingService {


    int deleteByPrimaryKey(String id) throws TransException;

    int insert(Building record) throws TransException;

    int insertSelective(Building record) throws TransException;

    Building selectByPrimaryKey(String id) throws TransException;

    int updateByPrimaryKeySelective(Building record) throws TransException;

    int updateByPrimaryKey(Building record) throws TransException;

    /**
     * 列表查询
     *
     * @return list
     * @throws TransException 异常
     */
    List<BuildingListVo> listInAll() throws TransException;

    /**
     * 查询全部的领导
     *
     * @return
     * @throws TransException
     */
    List<BuildingVo> finds() throws TransException;

    /**
     * 查询账号下所有管理宿舍
     *
     * @param pId 父id
     * @return List
     * @throws TransException 异常
     */
    List<String> getIdsByParentId(String pId) throws TransException;

    /**
     * 根据buildingId查询单个信息
     *
     * @param buildingId 建筑id
     * @return Building
     * @throws TransException 异常
     */
    Building query(String buildingId) throws TransException;

    /**
     * 根据id是否存在添加或修改建筑
     *
     * @param buildingDTO building表单信息
     * @throws TransException 异常
     */
    void saveOrUpdate(BuildingDTO buildingDTO) throws TransException;

    /**
     * 删除建筑
     *
     * @param buildingId 建筑id
     * @throws TransException 异常
     */
    void delete(String buildingId) throws TransException;

}
