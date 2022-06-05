package com.atsun.dormitory.service;

import com.atsun.dormitory.dto.LeavePageDTO;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.Leave;
import com.atsun.dormitory.vo.LeaveVO;
import com.github.pagehelper.PageInfo;

/**
 * @author: SH
 * @create: 2021-12-01 14:38
 **/
public interface LeaveService {


    void deleteByPrimaryKey(String id) throws TransException;

    int insert(Leave record) throws TransException;

    int insertSelective(Leave record) throws TransException;

    Leave selectByPrimaryKey(String id) throws TransException;

    int updateByPrimaryKeySelective(Leave record) throws TransException;

    int updateByPrimaryKey(Leave record) throws TransException;

    /**
     * 条件查询所有请假
     *
     * @param leavePageDTO 请教表单
     * @param token        token
     * @return page
     * @throws TransException 异常
     */
    PageInfo<LeaveVO> list(LeavePageDTO leavePageDTO, String token) throws TransException;

    /**
     * 添加请假
     *
     * @param leavePageDTO 请假列表
     * @throws TransException 异常
     */
    void save(LeavePageDTO leavePageDTO) throws TransException;

    /**
     * 查询单个请假
     *
     * @param lid 请假id
     * @return LeaveVO
     * @throws TransException 异常
     */
    LeaveVO query(String lid) throws TransException;

    /**
     * 更新返校
     *
     * @param lid 请假id
     * @throws TransException 异常
     */
    void update(String lid) throws TransException;

    /**
     * 删除请假
     *
     * @param lid 请假id
     * @throws TransException 异常
     */
    void delete(String lid) throws TransException;
}
