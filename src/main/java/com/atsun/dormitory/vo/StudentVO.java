package com.atsun.dormitory.vo;

import com.atsun.dormitory.po.Faculty;
import com.atsun.dormitory.po.Room;
import lombok.Data;

import java.util.Date;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author SH
 * @Date 2022/1/12 13:49
 */
@Data
public class StudentVO {

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

    /**
     * 宿舍
     */
    private Room room;

    /**
     * 学院
     */
    private Faculty faculty;
}
