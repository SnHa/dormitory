package com.atsun.dormitory.controller;

import com.atsun.dormitory.convert.Impl.UserConvert;
import com.atsun.dormitory.dto.BasicUserDTO;
import com.atsun.dormitory.dto.PasswordDTO;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.service.UserService;
import com.atsun.dormitory.vo.DataResponse;
import com.atsun.dormitory.vo.NoDataResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: SH
 * @create: 2021-12-08 16:02
 **/
@Api(tags = "个人中心")
@RequestMapping("/person")
@RestController
public class PersonController extends BaseController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    // TODO id, real_name, icon, cellphone, email

    @ApiOperation(value = "用户的基本信息")
    @GetMapping("/info")
    public DataResponse<?> info(@RequestHeader("_ut") String token) throws TransException {
        return ok(UserConvert.INSTANCE.poToVo(userService.findUser(token)));
    }

    //TODO 修改基本信息

    @ApiOperation(value = "修改用户的基本信息")
    @PostMapping("/update")
    public DataResponse<?> update(@RequestBody BasicUserDTO basicUserDTO) throws TransException {
        userService.updateByBasicUser(basicUserDTO);
        return ok(basicUserDTO);
    }

    @ApiOperation(value = "图片上传")
    @PostMapping("/img/upload")
    public DataResponse<?> imgUpload(@RequestParam("file") MultipartFile file, @RequestHeader("_ut") String token) throws TransException {
        return ok(userService.imgUpload(file, token));
    }

    @ApiOperation(value = "修改密码")
    @PostMapping("/changePassword")
    public NoDataResponse changePassword(@RequestHeader("_ut") String token, @RequestBody PasswordDTO passwordDTO) throws TransException{
        userService.changePassword(token,passwordDTO);
        return ok();
    }
}
