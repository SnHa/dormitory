package com.atsun.dormitory.po;

import java.io.Serializable;

import lombok.Data;

/**
 * @author: SH
 * @create: 2021-12-01 14:38
 **/
@Data
public class Room implements Serializable {
    /**
     * id
     */
    private String id;

    /**
     * 编号
     */
    private String number;

    /**
     * 宿舍楼id
     */
    private String buildingId;

    /**
     * 最大床位数量
     */
    private Integer maxCapacity;

    /**
     * 电费
     */

    private String electricity;

    private static final long serialVersionUID = 1L;
}