package com.atsun.dormitory.vo;

import lombok.Data;

import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-06 10:48
 **/
@Data
public class CommonVO {

    /**
     * id
     */
    private String id;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 用户登录账号
     */
    private String loginName;

    /**
     * 头像
     */
    private String icon;

    /**
     * 菜单
     */
    List<PermissionVO> menu;

    /**
     * 操作权限
     */
    List<String> permissions;
}
