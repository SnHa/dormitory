package com.atsun.dormitory.service;

import com.atsun.dormitory.dto.RepairPageDTO;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.Repair;
import com.atsun.dormitory.vo.RepairVO;
import com.github.pagehelper.PageInfo;

/**
 * @author: SH
 * @create: 2021-12-01 14:38
 **/
public interface RepairService {


    int deleteByPrimaryKey(String id) throws TransException;

    int insert(Repair record) throws TransException;

    int insertSelective(Repair record) throws TransException;

    Repair selectByPrimaryKey(String id) throws TransException;

    int updateByPrimaryKeySelective(Repair record) throws TransException;

    int updateByPrimaryKey(Repair record) throws TransException;

    /**
     * 查询权限下所有的维修记录
     *
     * @param repairPageDTO 维修表单
     * @param token         token
     * @return 分页查询
     */
    PageInfo<RepairVO> list(RepairPageDTO repairPageDTO, String token) throws TransException;

    /**
     * 根据id判断是添加还是修改维修
     *
     * @param repairPageDTO 维修表单
     * @throws TransException 异常
     */
    void saveOrUpdate(RepairPageDTO repairPageDTO) throws TransException;

    /**
     * 查询单个维修信息
     *
     * @param id 维修id
     * @return RepairVO
     * @throws TransException 异常
     */
    RepairVO query(String id) throws TransException;

    /**
     * ]
     * 确认维修状态
     *
     * @param rid 维修id
     * @throws TransException 异常
     */
    void updateStatus(String rid) throws TransException;

    /**
     * 删除维修记录
     *
     * @param rid 维修id
     * @throws TransException 异常
     */
    void delete(String rid) throws TransException;
}
