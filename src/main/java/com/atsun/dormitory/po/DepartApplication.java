package com.atsun.dormitory.po;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
* @author: SH
* @create: 2021-12-01 14:39
**/
@Data
public class DepartApplication implements Serializable {
    /**
    * id
    */
    private String id;

    /**
    * 发起人id
    */
    private String applyUserId;

    /**
    * 退宿舍原因
    */
    private String reason;

    /**
    * 发起时间
    */
    private Date time;

    /**
    * 学生id
    */
    private String studentId;

    private static final long serialVersionUID = 1L;
}