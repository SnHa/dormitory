package com.atsun.dormitory.controller;

import com.atsun.dormitory.dto.RepairPageDTO;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.service.RepairService;
import com.atsun.dormitory.vo.DataResponse;
import com.atsun.dormitory.vo.NoDataResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: TODO(维修记录)
 * @Author SH
 * @Date 2022/2/13 16:45
 */
@Api(tags = "维修记录")
@RequestMapping("/repair")
@RestController
public class RepairController extends BaseController {

    private RepairService repairService;

    @Autowired
    public void setRepairService(RepairService repairService) {
        this.repairService = repairService;
    }

    @ApiOperation(value = "查询该权限所有的维修")
    @RequiresPermissions("repair:list")
    @PostMapping("/list")
    public DataResponse<?> list(@RequestBody RepairPageDTO repairPageDTO, @RequestHeader("_ut") String token) throws TransException {
        return ok(repairService.list(repairPageDTO, token));
    }

    @ApiOperation(value = "添加-修改维修信息")
    @RequiresPermissions(value = {"repair:update", "repair:save"},logical = Logical.OR)
    @PostMapping("/saveOrUpdate")
    public NoDataResponse saveOrUpdate(@RequestBody RepairPageDTO repairPageDTO) throws TransException {
        repairService.saveOrUpdate(repairPageDTO);
        return ok();
    }

    @ApiOperation(value = "查询单个维修")
    @RequiresPermissions("repair:query")
    @GetMapping("/query/{id}")
    public DataResponse<?> query(@PathVariable("id") String id) throws TransException {
        return ok(repairService.query(id));
    }

    @ApiOperation(value = "确定状态完成")
    @RequiresPermissions("repair:update")
    @GetMapping("/updateStatus/{id}")
    public NoDataResponse updateStatus(@PathVariable("id") String id) throws TransException {
        repairService.updateStatus(id);
        return ok();
    }

    @ApiOperation(value = "删除维修记录")
    @RequiresPermissions("repair:delete")
    @GetMapping("/delete/{id}")
    public NoDataResponse delete(@PathVariable("id") String id) throws TransException{
        repairService.delete(id);
        return ok();
    }


}
