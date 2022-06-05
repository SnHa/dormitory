package com.atsun.dormitory.vo;

import com.atsun.dormitory.po.Building;
import lombok.Data;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author SH
 * @Date 2022/1/11 13:46
 */
@Data
public class RoomVO {

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


    private Integer currentNum;

    private Building building;

    /**
     * 电费
     */

    private String electricity;

    /**
     * 是否已满
     */
    private Boolean isFull;
}
