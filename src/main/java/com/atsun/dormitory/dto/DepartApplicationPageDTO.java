package com.atsun.dormitory.dto;

import com.atsun.dormitory.po.DepartApplicationUser;
import com.atsun.dormitory.po.Student;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description: TODO()
 * @Author SH
 * @Date 2022/2/3 13:49
 */
@Data
public class DepartApplicationPageDTO {
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

    private Student student;

    /**
     * 是否完成
     */
    private Boolean isFinished;

    /**
     * 是否通过
     */
    private Boolean isPass;

    private List<DepartApplicationUser> operateList;

    /**
     * 当前页
     */
    private Integer page;
    /**
     * 每页最大记录数
     */
    private Integer rows;
}
