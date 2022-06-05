package com.atsun.dormitory.dao;

import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.Leave;
import com.atsun.dormitory.vo.LeaveVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-01 14:38
 **/
@Mapper
public interface LeaveMapper {
    void deleteByPrimaryKey(String id) throws TransException;

    int insert(Leave record) throws TransException;

    int insertSelective(Leave record) throws TransException;

    Leave selectByPrimaryKey(String id) throws TransException;

    int updateByPrimaryKeySelective(Leave record) throws TransException;

    int updateByPrimaryKey(Leave record) throws TransException;

    /**
     * 根据条件查询请假
     *
     * @param studentId 学生id
     * @param isBack    是否在校
     * @param bids      建筑id
     * @return list
     * @throws TransException 异常
     */
    List<LeaveVO> list(@Param("studentId") String studentId, @Param("isBack") Boolean isBack, @Param("bids") List<String> bids) throws TransException;

    void save(Leave leave) throws TransException;

    /**
     * 查询单个请假
     *
     * @param lid 请假id
     * @return LeaveVO
     * @throws TransException 异常
     */
    LeaveVO query(String lid) throws TransException;

    /**
     * 跟新返校
     *
     * @param lid 请假id
     * @throws TransException 异常
     */
    void update(String lid) throws TransException;
}
