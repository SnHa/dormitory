package com.atsun.dormitory.vo;

import com.atsun.dormitory.po.DepartApplication;
import com.atsun.dormitory.po.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author SH
 * @Date 2022/2/3 13:56
 */
@Data
public class DepartApplicationUserVO {

    private String id;

    private String operateUserId;

    private User operateUser;

    private Boolean isAgree;


    private Date operateTime;

    private String reason;

    private String applicationId;

    private DepartApplicationVO application;
}
