package com.atsun.dormitory.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Description: TODO(申请离舍表单)
 * @Author SH
 * @Date 2022/2/2 13:23
 */
@Data
public class DepartApplicationDTO {
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
}
