package com.atsun.dormitory.dto;

import lombok.Data;

import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-15 17:13
 **/
@Data
public class UserPageDTO {

    /**
     * Pid
     */
    private String leaderId;

    /**
     * building
     */
    private String buildingId;

    /**
     * name
     */
    private String realName;

    /**
     * 角色id
     */
    private List<String> userRoleId;

    /**
     * 当前页
     */
    private Integer page;

    /**
     * 每页最大记录数
     */
    private Integer rows;
}
