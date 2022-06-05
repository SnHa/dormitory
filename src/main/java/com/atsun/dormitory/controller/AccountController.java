package com.atsun.dormitory.controller;


import com.atsun.dormitory.dto.LoginUser;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.service.UserService;
import com.atsun.dormitory.vo.DataResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: SH
 * @create: 2021-11-24 15:00
 **/
@Api(tags = "登出")
@RequestMapping("/account")
@RestController
public class AccountController extends BaseController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public DataResponse<String> login(LoginUser loginUser) throws TransException {

        String token = userService.login(loginUser);

        return ok(token);
    }

    //TODO 退出功能
}
