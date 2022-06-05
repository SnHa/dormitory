package com.atsun.dormitory.controller;

import com.atsun.dormitory.dto.BackLatePageDTO;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.BackLate;
import com.atsun.dormitory.service.BackLateService;
import com.atsun.dormitory.vo.DataResponse;
import com.atsun.dormitory.vo.NoDataResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: TODO(晚归)
 * @Author SH
 * @Date 2022/2/17 14:54
 */
@Api(tags = "晚归")
@RequestMapping("/backlate")
@RestController
public class BackController extends BaseController {

    private BackLateService backLateService;

    @Autowired
    public void setBackLateService(BackLateService backLateService) {
        this.backLateService = backLateService;
    }

    @ApiOperation(value = "根据条件查询全部晚归记录")
    @RequiresPermissions("back:list")
    @PostMapping("/list")
    public DataResponse<?> list(@RequestBody BackLatePageDTO backLatePageDTO, @RequestHeader("_ut") String token) throws TransException {
        return ok(backLateService.list(backLatePageDTO, token));
    }

    @ApiOperation("添加——修改晚归记录")
    @RequiresPermissions(value = {"back:save","back:update"}, logical = Logical.OR)
    @PostMapping("/saveOrUpdate")
    public NoDataResponse saveOrUpdate(@RequestBody BackLatePageDTO backLatePageDTO) throws TransException {
        backLateService.saveOrUpdate(backLatePageDTO);
        return ok();
    }

    @ApiOperation(value = "查询单个晚归记录")
    @RequiresPermissions("back:query")
    @GetMapping("/query/{id}")
    public DataResponse<?> query(@PathVariable("id") String id) throws TransException {
        return ok(backLateService.query(id));
    }

    @ApiOperation(value = "删除晚归记录")
    @RequiresPermissions("back:delete")
    @GetMapping("/delete/{id}")
    public NoDataResponse delete(@PathVariable("id") String id) throws TransException {
        backLateService.deleteByPrimaryKey(id);
        return ok();
    }
}
