package com.atsun.dormitory.controller;

import com.atsun.dormitory.dto.BuildingDTO;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.service.BuildingService;
import com.atsun.dormitory.vo.DataResponse;
import com.atsun.dormitory.vo.NoDataResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-16 14:26
 **/
@Api(tags = "宿舍")
@RequestMapping("/building")
@RestController
public class BuildingController extends BaseController {

    private BuildingService buildingService;

    @Autowired
    public void setBuildingService(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @ApiOperation(value = "列表查询")
    @RequiresPermissions("manage:building:list")
    @GetMapping("/listInAll")
    public DataResponse<?> listInAll() throws TransException {
        return ok(buildingService.listInAll());
    }

    @ApiOperation(value = "tree列表查询")
    @RequiresPermissions("manage:building:list")
    @GetMapping("/list")
    public DataResponse<?> list() throws TransException {
        return ok(buildingService.finds());
    }

    @ApiOperation(value = "查询单个信息")
    @RequiresPermissions("manage:building:query")
    @GetMapping("/query")
    public DataResponse<?> query(@RequestParam("id") String id) throws TransException{
        return ok(buildingService.query(id));
    }

    @ApiOperation(value = "添加-修改")
    @RequiresPermissions(value = {"manage:building:save","manage:building:update"},logical = Logical.OR)
    @PostMapping("/saveOrUpdate")
    public NoDataResponse saveOrUpdate(@RequestBody BuildingDTO buildingDTO) throws TransException{
        buildingService.saveOrUpdate(buildingDTO);
        return ok();
    }

    @ApiOperation(value = "删除建筑")
    @RequiresPermissions("manage:building:delete")
    @GetMapping("/delete")
    public NoDataResponse delete(@RequestParam("id") String id) throws TransException{
        buildingService.delete(id);
        return ok();
    }
}
