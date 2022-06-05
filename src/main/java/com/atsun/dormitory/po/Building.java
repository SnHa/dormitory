package com.atsun.dormitory.po;

import java.io.Serializable;
import lombok.Data;

/**
* @author: SH
* @create: 2021-12-01 14:39
**/
@Data
public class Building implements Serializable {
    /**
    * id
    */
    private String id;

    /**
    * 楼栋名称
    */
    private String name;

    /**
    * 父id
    */
    private String pId;

    private static final long serialVersionUID = 1L;
}