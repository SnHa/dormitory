package com.atsun.dormitory.dao;

import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.BackLate;
import com.atsun.dormitory.vo.BackLateVO;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-01 14:39
 **/
@Mapper
public interface BackLateMapper {
    int deleteByPrimaryKey(String id) throws TransException;

    int insert(BackLate record) throws TransException;

    int insertSelective(BackLate record) throws TransException;

    BackLate selectByPrimaryKey(String id) throws TransException;

    int updateByPrimaryKeySelective(BackLate record) throws TransException;

    int updateByPrimaryKey(BackLate record) throws TransException;

    /**
     * 根据条件查询晚归记录
     *
     * @param studentId 学生id
     * @param bids      建筑id
     * @return 分页晚归
     * @throws TransException 异常
     */
    List<BackLateVO> list(@Param("studentId") String studentId, @Param("bids") List<String> bids) throws TransException;

    /**
     * 单个查询 晚归记录
     *
     * @param blid 晚归id
     * @return BackLateVO
     * @throws TransException 异常
     */
    BackLateVO query(String blid) throws TransException;
}
