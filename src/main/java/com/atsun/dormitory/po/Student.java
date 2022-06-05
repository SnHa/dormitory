package com.atsun.dormitory.po;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
* @author: SH
* @create: 2021-12-01 14:37
**/
@Data
public class Student implements Serializable {
    /**
    * id
    */
    private String id;

    /**
     * 性别
     */

    private String sex;

    /**
    * 名字
    */
    private String name;

    /**
    * 房间id
    */
    private String roomId;

    /**
    * 院系id
    */
    private String facultyId;

    /**
    * 学号
    */
    private String number;

    /**
    * 登记注册日期
    */
    private Date registrationDate;

    /**
    * 电话
    */
    private String phone;

    /**
    * 请假
    */
    private Boolean isLeave;

    private static final long serialVersionUID = 1L;
}