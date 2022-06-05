package com.atsun.dormitory.vo;

import com.atsun.dormitory.po.Room;
import lombok.Data;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author SH
 * @Date 2022/3/4 11:22
 */
@Data
public class PayVO {
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

    /**
     * 查询宿舍
     */
    private Room room;
}
