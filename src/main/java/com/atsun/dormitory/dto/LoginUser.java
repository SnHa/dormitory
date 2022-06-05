package com.atsun.dormitory.dto;

import lombok.Data;

/**
 * @author: SH
 * @create: 2021-11-24 15:08
 **/
@Data
public class LoginUser {

    private String loginName;
    private String password;
    private String redirectUrl;
}
