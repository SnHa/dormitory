package com.atsun.dormitory.po;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
* @author: SH
* @create: 2021-12-01 14:38
**/
@Data
@AllArgsConstructor
public class RolePermission implements Serializable {
    /**
    * id
    */
    private String id;

    /**
    * 角色id
    */
    private String roleId;

    /**
    * 权限id
    */
    private String permissionId;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 更新人
    */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}
