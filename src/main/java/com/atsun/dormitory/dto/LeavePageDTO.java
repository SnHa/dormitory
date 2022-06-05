package com.atsun.dormitory.dto;

import com.atsun.dormitory.po.Student;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author SH
 * @Date 2022/2/16 10:53
 */
@Data
public class LeavePageDTO {
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
    @DateTimeFormat(pattern = "yyyy-MM-dd ")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date backDate;

    private Student student;

    /**
     * 当前页
     */
    private Integer page;
    /**
     * 每页最大记录数
     */
    private Integer rows;

}
