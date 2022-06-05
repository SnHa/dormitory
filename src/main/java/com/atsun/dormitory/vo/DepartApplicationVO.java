package com.atsun.dormitory.vo;

import com.atsun.dormitory.po.DepartApplicationUser;
import com.atsun.dormitory.po.Student;
import com.atsun.dormitory.po.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author SH
 * @Date 2022/2/3 14:19
 */
@Data
public class DepartApplicationVO {
    private String id;

    private String applyUserId;

    private User applyUser;

    private String reason;

    private Date time;

    private Long studentId;

    private Student student;

    private Boolean isFinished;

    private Boolean isPass;

    private List<DepartApplicationUser> operateList;
}
