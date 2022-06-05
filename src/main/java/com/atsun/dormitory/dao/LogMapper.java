package com.atsun.dormitory.dao;

import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.Log;
import com.atsun.dormitory.vo.LogVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-01 14:38
 **/
@Mapper
public interface LogMapper {
    int deleteByPrimaryKey(String id) throws TransException;

    int insert(Log record) throws TransException;

    int insertSelective(Log record) throws TransException;

    Log selectByPrimaryKey(String id) throws TransException;

    int updateByPrimaryKeySelective(Log record) throws TransException;

    int updateByPrimaryKey(Log record) throws TransException;

    /**
     * 条件查询日志
     *
     * @param clas
     * @param method
     * @param ip
     * @param userId
     * @param url
     * @param operateTimeEnd
     * @return List<LogVO>
     */
    List<LogVO> list(@Param("clas") String clas, @Param("method") String method, @Param("ip") String ip, @Param("userId") String userId,
                     @Param("url") String url, @Param("operateTimeStart") Date operateTimeStart, @Param("operateTimeEnd") Date operateTimeEnd) throws TransException;

    /**
     * 查询日志记录总数
     *
     * @return int
     * @throws TransException 异常
     */
    int count() throws TransException;

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
