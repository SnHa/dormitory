package com.atsun.dormitory.dto;

import lombok.Data;

/**
 * @Description: TODO(room添加修改表单)
 * @Author SH
 * @Date 2022/1/14 12:13
 */
@Data
public class RoomDTO {

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
     * 是否已满
     */
    private  Boolean isFull;
}
