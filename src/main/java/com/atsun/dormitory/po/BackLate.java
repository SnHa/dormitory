package com.atsun.dormitory.po;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
* @author: SH
* @create: 2021-12-01 14:39
**/
@Data
public class BackLate implements Serializable {
    /**
    * id
    */
    private String id;

    /**
    * 学生id
    */
    private String studentId;

    /**
    * 归寝时间
    */
    private Date backDate;

    /**
    * 晚归原因
    */
    private String reason;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 更新时间
    */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}