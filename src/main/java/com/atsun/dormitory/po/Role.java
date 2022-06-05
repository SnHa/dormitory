package com.atsun.dormitory.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
* @author: SH
* @create: 2021-12-01 14:38
**/
@Data
public class Role implements Serializable {
    /**
    * id
    */
    private String id;

    /**
    * 角色名称
    */
    private String name;

    /**
    * 创建时间
    */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}
