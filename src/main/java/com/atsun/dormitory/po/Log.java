package com.atsun.dormitory.po;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
* @author: SH
* @create: 2021-12-01 14:38
**/
@Data
public class Log implements Serializable {
    /**
    * id
    */
    private String id;

    /**
    * 操作时间
    */
    private Date operateTim;

    /**
    * 操作id
    */
    private String userId;

    /**
    * 操作层面
    */
    private String clas;

    /**
    * 操作的方法
    */
    private String method;

    /**
    * 操作的ip地址
    */
    private String ip;

    /**
    * 操作参数
    */
    private String param;

    /**
    * 操作路径
    */
    private String url;

    /**
    * 操作描述
    */
    private String description;

    /**
    * 操作结果
    */
    private String result;

    private static final long serialVersionUID = 1L;
}