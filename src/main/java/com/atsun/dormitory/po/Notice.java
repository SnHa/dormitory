package com.atsun.dormitory.po;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
* @author: SH
* @create: 2021-12-01 14:38
**/
@Data
public class Notice implements Serializable {
    /**
    * id
    */
    private String id;

    /**
    * 消息
    */
    private String msg;

    /**
    * 创建人
    */
    private String userId;

    /**
    * 创建时间
    */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}