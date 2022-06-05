package com.atsun.dormitory.dto;

import lombok.Data;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author SH
 * @Date 2022/1/11 13:41
 */
@Data
public class RoomPageDTO {

    /**
     * 编号
     */
    private String number;
    /**
     * 宿舍楼id
     */
    private String buildingId;

    /**
     * 是否已满
     */
    private Boolean isFull;
    /**
     * 当前页
     */
    private Integer page;
    /**
     * 每页最大记录数
     */
    private Integer rows;
}
