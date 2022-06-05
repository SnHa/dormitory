package com.atsun.dormitory.dao;

import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.DepartApplicationUser;
import com.atsun.dormitory.vo.DepartApplicationUserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-01 14:39
 **/
@Mapper
public interface DepartApplicationUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(DepartApplicationUser record);

    int insertSelective(DepartApplicationUser record);

    DepartApplicationUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DepartApplicationUser record);

    int updateByPrimaryKey(DepartApplicationUser record);

    /**
     * 插入审核用户和退宿申请管理
     *
     * @param userId        审核人
     * @param applicationId 申请人
     * @throws TransException 异常
     */
    void saveApplication(String id, String userId, String applicationId) throws TransException;

    /**
     * 获取我的审核列表
     *
     * @param userId 用户id
     * @return 列表
     * @throws TransException 异常
     */
    List<DepartApplicationUserVO> listMyFlow(String userId) throws TransException;

    /**
     * 删除 applicationId下的消息
     *
     * @param applicationId 退舍id
     * @throws TransException 异常
     */
    void deleteBydepartApplication(String applicationId) throws TransException;

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
     * 计数流同意为空
     *
     * @param departApplicationId 申请id
     * @return int
     * @throws TransException 异常
     */
    int countFlow(String departApplicationId) throws TransException;

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
