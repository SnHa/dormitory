package com.atsun.dormitory.dto;

import com.atsun.dormitory.po.Building;
import com.atsun.dormitory.po.User;
import com.atsun.dormitory.vo.RoleVO;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-17 11:21
 **/
@Data
public class UserDTO {
    private String id;

    private String realName;

    private String loginName;

    private String icon;

    private String cellphone;

    private String email;

    private Date createTime;

    private String leaderId;

    private List<String> userRoleId;


    private String buildingId;
}
