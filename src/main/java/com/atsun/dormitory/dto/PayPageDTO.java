package com.atsun.dormitory.dto;

import lombok.Data;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author SH
 * @Date 2022/3/4 11:20
 */
@Data
public class PayPageDTO {
    /**
     * 当前页
     */
    private Integer page;
    /**
     * 每页最大记录数
     */
    private Integer rows;
}
