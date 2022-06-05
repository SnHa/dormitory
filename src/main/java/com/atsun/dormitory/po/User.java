package com.atsun.dormitory.po;

import java.io.Serializable;
import java.util.Date;

import com.atsun.dormitory.utils.SnowFlake;
import lombok.Data;

/**
* @author: SH
* @create: 2021-12-01 14:36
**/
@Data
public class User implements Serializable {
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
    * 登录密码
    */
    private String password;

    /**
    * 移动电话
    */
    private String cellphone;

    /**
    * 邮箱
    */
    private String email;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 跟新时间
    */
    private Date updateTime;

    /**
    * 父id
    */
    private String pId;

    /**
    * 头像
    */
    private String icon;

    /**
    * 宿舍
    */
    private String buildingId;

    private static final long serialVersionUID = 1L;


}
