package com.atsun.dormitory.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author: SH
 * @create: 2021-12-13 12:52
 **/
@Data
public class RolePageDTO {

    private String id;

    private String name;
    /**
     * 当前页
     */
    private Integer page;
    /**
     * 每页最大记录数
     */
    private Integer rows;
}
