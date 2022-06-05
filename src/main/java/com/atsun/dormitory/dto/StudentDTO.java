package com.atsun.dormitory.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Description: TODO(学生信息表单)
 * @Author SH
 * @Date 2022/1/30 17:30
 */
@Data
public class StudentDTO {

    /**
     * id
     */
    private String id;

    /**
     * 名字
     */
    private String name;
    /**
     * 性别
     */
    private String sex;

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
}
