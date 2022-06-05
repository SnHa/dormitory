package com.atsun.dormitory.service;

import com.atsun.dormitory.dto.DepartApplicationPageDTO;
import com.atsun.dormitory.dto.DepartApplicationUserDTO;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.DepartApplicationUser;
import com.atsun.dormitory.vo.DepartApplicationUserVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-01 14:39
 **/
public interface DepartApplicationUserService {


    int deleteByPrimaryKey(String id) throws TransException;

    int insert(DepartApplicationUser record) throws TransException;

    int insertSelective(DepartApplicationUser record) throws TransException;

    DepartApplicationUser selectByPrimaryKey(String id) throws TransException;

    int updateByPrimaryKeySelective(DepartApplicationUser record) throws TransException;

    int updateByPrimaryKey(DepartApplicationUser record) throws TransException;

    /**
     * 插入审核用户和退宿申请管理
     *
     * @param userId        审核人
     * @param applicationId 申请人
     * @throws TransException 异常
     */
    void saveApplication(String id, String userId, String applicationId) throws TransException;

    /**
     * 列出我审核的退舍申请
     *
     * @param departApplicationPageDTO
     * @param token
     * @return
     * @throws TransException
     */
    PageInfo<DepartApplicationUserVO> listMyFlow(DepartApplicationPageDTO departApplicationPageDTO, String token) throws TransException;

    /**
     * 审核操作（是否通过）
     *
     * @param departApplicationUserDTO 审核表单
     * @param token                    token
     * @throws TransException 异常
     */
    String update(DepartApplicationUserDTO departApplicationUserDTO, String token) throws TransException;

    /**
     * 获取流转中未审核的数量
     *
     * @param applicationId 审核id
     * @return int
     * @throws TransException 异常
     */
    int countFlowAgreeIsNotNull(String applicationId) throws TransException;

    /**
     * 获取最后一个审核记录
     *
     * @param applicationId 审核id
     * @return DepartApplicationUser
     * @throws TransException 异常
     */
    DepartApplicationUser getLastFlow(String applicationId) throws TransException;

    /**
     * 获取申请退舍列表
     *
     * @param applicationId 申请id
     * @return list
     * @throws TransException 异常
     */
    List<DepartApplicationUserVO> listFlow(String applicationId) throws TransException;

    /**
     * 上级申请数是否判断个数
     *
     * @param departApplicationId 申请id
     * @return int
     * @throws TransException 异常
     */
    int countFlowAgreeIsNull(String departApplicationId) throws TransException;

    /**
     * 删除审核流转
     *
     * @param departApplicationId 审核id
     * @throws TransException 异常
     */
    void deleteFlow(String departApplicationId) throws TransException;

    /**记录转流同意为null的数
     * @param operateUserId 申请人id
     * @return int
     * @throws TransException 异常
     */
    int countFlowAgreeNull(String operateUserId) throws TransException;
}
