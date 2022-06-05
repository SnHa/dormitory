package com.atsun.dormitory.controller;

import com.atsun.dormitory.annotation.Log;
import com.atsun.dormitory.dto.RoleDTO;
import com.atsun.dormitory.dto.RolePageDTO;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.service.RoleService;
import com.atsun.dormitory.vo.DataResponse;
import com.atsun.dormitory.vo.NoDataResponse;
import com.atsun.dormitory.vo.RoleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author: SH
 * @create: 2021-12-13 12:10
 **/
@Api(tags = "角色")
@RequestMapping("/role")
@RestController
public class RoleController extends BaseController {

    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @ApiOperation(value = "查询全部角色")
    @RequiresPermissions("system:role:list")
    @PostMapping("/list")
    public DataResponse<?> listPage(@RequestBody RolePageDTO rolePageDTO) throws TransException {
        return ok(roleService.listPage(rolePageDTO));
    }

    @ApiOperation(value = "查询单个角色")
    @RequiresPermissions("system:role:query")
    @GetMapping("/query")
    public DataResponse<?> query(@RequestParam("id") String id) throws TransException {
        return ok(roleService.findByPrimaryKey(id));
    }

    @Log("添加修改角色")
    @ApiOperation(value = "添加--修改")
    @RequiresPermissions(value = {"system:role:save", "system:role:update"},logical = Logical.OR)
    @PostMapping("/saveOrUpdate")
    public NoDataResponse saveOrUpdate(@RequestBody RoleDTO roleDTO) throws TransException {

        roleService.saveOrUpdate(roleDTO);
        return ok();
    }

    @Log("删除角色")
    @ApiOperation(value = "删除角色")
    @RequiresPermissions("system:role:delete")
    @GetMapping("/delete")
    public NoDataResponse delete(@RequestParam("id") String id) throws TransException {

        roleService.delete(id);
        return ok();
    }

    @ApiOperation(value = "列表查询查询")
    @RequiresPermissions("system:role:list")
    @GetMapping("/listInSelect")
    public DataResponse<?> listInSelect() throws TransException {
        return ok(roleService.listInSelect());
    }
}
