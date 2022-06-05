package com.atsun.dormitory.vo;

import com.atsun.dormitory.po.Student;
import lombok.Data;

import java.util.Date;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author SH
 * @Date 2022/2/16 10:53
 */
@Data
public class LeaveVO {
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

}
