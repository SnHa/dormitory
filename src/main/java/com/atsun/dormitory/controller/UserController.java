package com.atsun.dormitory.controller;

import com.atsun.dormitory.dto.UserDTO;
import com.atsun.dormitory.dto.UserPageDTO;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.service.UserService;
import com.atsun.dormitory.vo.DataResponse;
import com.atsun.dormitory.vo.NoDataResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author: SH
 * @create: 2021-12-15 11:47
 **/
@Api(tags = "用户管理")
@RequestMapping("/user")
@RestController
public class UserController extends BaseController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "用户信息")
    @RequiresPermissions("system:user:list")
    @PostMapping("/list")
    public DataResponse<?> list(@RequestBody UserPageDTO userPageDTO) throws TransException {
        return ok(userService.findByCondition(userPageDTO));
    }

    @ApiOperation(value = "列表查询")
    @RequiresPermissions("system:user:list")
    @GetMapping("/listInAll")
    public DataResponse<?> listInAll() throws TransException {
        return ok(userService.listInAll());
    }

    @ApiOperation(value = "单条用户查询")
    @RequiresPermissions("system:user:query")
    @GetMapping("/query")
    public DataResponse<?> query(@RequestParam("id") String id) throws TransException {
        return ok(userService.findByPrimaryKey(id));
    }

    @ApiOperation(value = "修改-添加")
    @RequiresPermissions(value = {"system:user:save", "system:user:update"},logical = Logical.OR)
    @PostMapping("/saveOrUpdate")
    public NoDataResponse saveOrUpdate(@RequestBody UserDTO userDTO) throws TransException {
        userService.saveOrUpdate(userDTO);
        return ok();
    }

    @ApiOperation(value = "删除")
    @RequiresPermissions("system:user:delete")
    @GetMapping("/delete")
    public NoDataResponse delete(@RequestParam("id") String id) throws TransException{
        userService.delete(id);
        return ok();
    }
    @ApiOperation(value = "重置密码")
    @RequiresPermissions("system:user:update")
    @GetMapping("/resetPassword")
    public NoDataResponse resetPassword(@RequestParam("id") String id) throws TransException{
        userService.resetPassword(id);
        return Ok("密码重置成功");
    }
}
