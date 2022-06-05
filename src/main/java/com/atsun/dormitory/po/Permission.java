package com.atsun.dormitory.po;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
* @author: SH
* @create: 2021-12-01 14:38
**/
@Data
public class Permission implements Serializable {
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

    private static final long serialVersionUID = 1L;
}