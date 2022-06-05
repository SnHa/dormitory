package com.atsun.dormitory.po;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
* @author: SH
* @create: 2021-12-01 14:37
**/
@Data
public class UserRole implements Serializable {
    /**
    * id
    */
    private String id;

    /**
    * 用户id
    */
    private String userId;

    /**
    * 角色id
    */
    private String roleId;

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