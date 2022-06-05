package com.atsun.dormitory.dao;

import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.Pay;
import com.atsun.dormitory.vo.PayVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author SH
 * @Date 2022/3/3 14:09
 */
@Mapper
public interface PayMapper {
    int deleteByPrimaryKey(String id) throws TransException;

    int insert(Pay record) throws TransException;

    int insertSelective(Pay record) throws TransException;

    Pay selectByPrimaryKey(String id) throws TransException;

    int updateByPrimaryKeySelective(Pay record) throws TransException;

    int updateByPrimaryKey(Pay record) throws TransException;

    /**
     * 查询所以的支付订单
     *
     * @return list
     * @throws TransException 异常
     */
    List<PayVO> list() throws TransException;


    /**
     * 查询所以的支付订单
     *
     * @return list
     * @throws TransException 异常
     */
    List<PayVO> query(String roomId) throws TransException;
}