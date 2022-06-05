package com.atsun.dormitory.po;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
* @author: SH
* @create: 2021-12-01 14:39
**/
@Data
public class DepartApplicationUser implements Serializable {
    /**
    * id
    */
    private String id;

    /**
    * 操作者id
    */
    private String operateUserId;

    /**
    * 是否同意
    */
    private Boolean isAgree;

    /**
    * 操作时间
    */
    private Date operateTime;

    /**
    * 原因
    */
    private String reason;

    /**
    * 发起者id
    */
    private String applicationId;

    private static final long serialVersionUID = 1L;
}