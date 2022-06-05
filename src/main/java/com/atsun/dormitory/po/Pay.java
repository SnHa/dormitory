package com.atsun.dormitory.po;

import java.io.Serializable;
import lombok.Data;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author SH
 * @Date 2022/3/3 14:09
 */
@Data
public class Pay implements Serializable {
    /**
     * 支付订单id
     */
    private String id;

    /**
     * 宿舍id
     */
    private String roomId;

    /**
     * 支付金额
     */
    private String payAmount;

    /**
     * 订单名称
     */
    private String subject;

    /**
     * 商品描述
     */
    private String body;

    /**
     * 支付状态
     */
    private String status;

    private static final long serialVersionUID = 1L;
}