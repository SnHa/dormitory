package com.atsun.dormitory.vo;

import com.atsun.dormitory.po.Permission;
import lombok.Data;


import java.util.Date;
import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-06 11:05
 **/
@Data
public class PermissionVO {
    /**
     * id
     */
    private String id;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 父权限
     */
    private String pid;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 路径
     */
    private String path;

    /**
     * 组件
     */
    private String component;

    /**
     * 类型（M菜单M目录F按钮）
     */
    private String type;

    /**
     * 权限
     */
    private String sn;

    /**
     * 图标
     */
    private String icon;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 跟新时间
     */
    private Date updateTime;

    /**
     * 子权限
     */
    private List<PermissionVO> children;


}
