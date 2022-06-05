package com.atsun.dormitory.po;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
* @author: SH
* @create: 2021-12-01 14:38
**/
@Data
public class Leave implements Serializable {
    /**
    * id
    */
    private String id;

    /**
    * 申请时间
    */
    private Date time;

    /**
    * 原因
    */
    private String reason;

    /**
    * 是否返校
    */
    private Boolean isBack;

    /**
    * 学生id
    */
    private String studentId;

    /**
    * 目的
    */
    private String target;

    /**
    * 返校日期
    */
    private Date backDate;

    private Student student;

    private static final long serialVersionUID = 1L;
}