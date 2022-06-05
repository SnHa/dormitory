package com.atsun.dormitory.controller;

import com.atsun.dormitory.dto.PermissionDTO;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.service.PermissionService;
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
 * @create: 2021-12-09 18:57
 **/
@Api(tags = "菜单-按钮-操作权限")
@RequestMapping("/permission")
@RestController
public class PermissionController extends BaseController {

    private PermissionService permissionService;

    @Autowired
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @ApiOperation(value = "全部权限")
    @RequiresPermissions("system:function:list")
    @GetMapping("/list")
    public DataResponse<?> listPermission() throws TransException {
        return ok(permissionService.listPermission());
    }

    @ApiOperation(value = "详情信息")
    @RequiresPermissions("system:function:query")
    @GetMapping("/query")
    public DataResponse<?> queryPermission(@RequestParam("id") String id) throws TransException {
        return ok(permissionService.queryPermission(id));
    }

    @ApiOperation(value = "添加-修改权限")
    @RequiresPermissions(value = {"system:function:save", "system:function:update"},logical = Logical.OR)
    @PostMapping("/saveOrUpdate")
    public NoDataResponse saveOrUpdate(@RequestBody PermissionDTO permissionDTO) throws TransException{
        permissionService.saveOrUpdate(permissionDTO);
        return ok();
    }

    @ApiOperation(value = "删除权限")
    @RequiresPermissions("system:function:delete")
    @GetMapping("/delete")
    public NoDataResponse deletePermission(@RequestParam("id") String id) throws TransException {
        permissionService.deletePermission(id);
        return  ok();
    }
}
