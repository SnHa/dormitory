package com.atsun.dormitory.vo;

import com.atsun.dormitory.po.Building;
import com.atsun.dormitory.po.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author: SH
 * @create: 2021-11-24 16:46
 **/
@Data
public class UserVO {

    private String id;

    private String realName;

    private String loginName;

    private String password;

    private String icon;

    private String cellphone;

    private String email;

    private Date createTime;

    private String leaderId;

    private List<String> userRoleId;

    private List<RoleVO> userRole;

    private User leader;

    private String buildingId;

    private Building building;

    private Boolean isOnLine;
}
