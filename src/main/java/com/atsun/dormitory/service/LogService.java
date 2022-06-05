package com.atsun.dormitory.service;

import com.atsun.dormitory.dto.LogPageDTO;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.Log;
import com.atsun.dormitory.vo.LogVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-01 14:38
 **/
public interface LogService {


    int deleteByPrimaryKey(String id) throws TransException;

    int insert(Log record) throws TransException;

    int insertSelective(Log record) throws TransException;

    Log selectByPrimaryKey(String id) throws TransException;

    int updateByPrimaryKeySelective(Log record) throws TransException;

    int updateByPrimaryKey(Log record) throws TransException;

    /**
     * 条件分页查询日志操作
     *
     * @param logPageDTO 查询日志操作参数
     * @return PageInfo<List < LogVO>>
     * @throws TransException 异常
     */
    PageInfo<LogVO> list(LogPageDTO logPageDTO) throws TransException;

    /**
     * 查询单个日志信息
     *
     * @param logId 日志id
     * @return LogVO
     * @throws TransException 异常
     */
    LogVO query(String logId) throws TransException;

    /**
     * 根据用户id删除该用户日志
     *
     * @param userId 用户id
     * @throws TransException 异常
     */
    void deleteByUserId(String userId) throws TransException;
}
