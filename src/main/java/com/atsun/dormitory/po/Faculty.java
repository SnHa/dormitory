package com.atsun.dormitory.po;

import java.io.Serializable;
import lombok.Data;

/**
* @author: SH
* @create: 2021-12-01 14:38
**/
@Data
public class Faculty implements Serializable {
    /**
    * id
    */
    private String id;

    /**
    * 名称
    */
    private String name;

    /**
    * 父id
    */
    private String pId;

    /**
    * 排序
    */
    private Integer orderNum;

    private static final long serialVersionUID = 1L;
}