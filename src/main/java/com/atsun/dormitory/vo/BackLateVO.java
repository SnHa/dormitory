package com.atsun.dormitory.vo;

import com.atsun.dormitory.po.Student;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Description: TODO(晚归)
 * @Author SH
 * @Date 2022/2/17 15:03
 */
@Data
public class BackLateVO {
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date backDate;

    /**
     * 晚归原因
     */
    private String reason;

    /**
     * 学生
     */
    private Student student;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
