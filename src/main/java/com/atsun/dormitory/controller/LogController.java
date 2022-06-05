package com.atsun.dormitory.controller;

import com.atsun.dormitory.dto.LogPageDTO;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.service.LogService;
import com.atsun.dormitory.vo.DataResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;

/**
 * @Description: 日志
 * @Author SH
 * @Date 2022/1/7 11:36
 */
@Api(tags = "操作日志")
@RequestMapping("/log")
@RestController
public class LogController extends BaseController {

    private LogService logService;

    @Autowired
    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    @ApiOperation(value = "查询所有操作日志")
    @RequiresPermissions("system:log:list")
    @PostMapping("/list")
    public DataResponse<?> list(@RequestBody LogPageDTO logPageDTO) throws TransException {
        return ok(logService.list(logPageDTO));
    }

    @ApiOperation(value = "查询单个日志操作")
    @RequiresPermissions("system:log:query")
    @GetMapping("/query")
    public DataResponse<?> query(@RequestParam("id") String id) throws TransException{
        return ok(logService.query(id));
    }
}
