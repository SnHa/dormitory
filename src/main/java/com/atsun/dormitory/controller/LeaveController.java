package com.atsun.dormitory.controller;

import com.atsun.dormitory.dto.LeavePageDTO;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.service.LeaveService;
import com.atsun.dormitory.vo.DataResponse;
import com.atsun.dormitory.vo.NoDataResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: TODO(请假)
 * @Author SH
 * @Date 2022/2/16 10:47
 */
@Api(tags = "请假")
@RequestMapping("/leave")
@RestController
public class LeaveController extends BaseController {

    private LeaveService leaveService;

    @Autowired
    public void setLeaveService(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @ApiOperation(value = "条件查询所有数据")
    @RequiresPermissions("leave:list")
    @PostMapping("/list")
    public DataResponse<?> list(@RequestBody LeavePageDTO leavePageDTO, @RequestHeader("_ut") String token) throws TransException {
        return ok(leaveService.list(leavePageDTO, token));
    }

    @ApiOperation("添加请假")
    @RequiresPermissions("leave:save")
    @PostMapping("/save")
    public NoDataResponse save(@RequestBody LeavePageDTO leavePageDTO) throws TransException {
        leaveService.save(leavePageDTO);
        return ok();
    }

    @ApiOperation(value = "单个查询请假")
    @RequiresPermissions("leave:query")
    @GetMapping("/query/{id}")
    public DataResponse<?> query(@PathVariable("id") String id) throws TransException {
        return ok(leaveService.query(id));
    }

    @ApiOperation(value = "更新返校")
    @RequiresPermissions("leave:update")
    @GetMapping("/update/{id}")
    public NoDataResponse update(@PathVariable("id") String id) throws TransException {
        leaveService.update(id);
        return ok();
    }

    @ApiOperation(value = "删除请假")
    @RequiresPermissions("leave:delete")
    @GetMapping("/delete/{id}")
    public NoDataResponse delete(@PathVariable("id") String id) throws TransException {
        leaveService.delete(id);
        return ok();
    }
}
