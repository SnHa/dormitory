package com.atsun.dormitory.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author SH
 * @Date 2022/1/12 13:46
 */
@Data
public class StudentPageDTO {


    /**
     * id
     */
    private String id;

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

    /**
     * 当前页
     */
    private Integer page;
    /**
     * 每页最大记录数
     */
    private Integer rows;
}
