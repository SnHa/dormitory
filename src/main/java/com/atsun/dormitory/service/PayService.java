package com.atsun.dormitory.service;

import com.alibaba.fastjson.JSONObject;
import com.atsun.dormitory.dto.PayDTO;
import com.atsun.dormitory.dto.PayPageDTO;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.Pay;
import com.atsun.dormitory.vo.PayVO;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author SH
 * @Date 2022/3/3 14:09
 */
public interface PayService {


    int deleteByPrimaryKey(String id) throws TransException;

    int insert(Pay record) throws TransException;

    int insertSelective(Pay record) throws TransException;

    Pay selectByPrimaryKey(String id) throws TransException;

    int updateByPrimaryKeySelective(Pay record) throws TransException;

    int updateByPrimaryKey(Pay record) throws TransException;

    String notifyByPay(HttpServletRequest request) throws TransException;

    /**
     * 支付电费
     *
     * @return JSONObject
     * @throws TransException 异常
     */
    JSONObject pay(PayDTO payDTO) throws TransException;

    /**
     * 查询所以订单
     *
     * @param payPageDTO 订单分页
     * @return page
     * @throws TransException 异常
     */
    PageInfo<PayVO> list(PayPageDTO payPageDTO,String token) throws TransException;
}
