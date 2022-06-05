package com.atsun.dormitory.controller;

import com.atsun.dormitory.annotation.Log;
import com.atsun.dormitory.dto.RoomDTO;
import com.atsun.dormitory.dto.RoomPageDTO;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.service.RoomService;
import com.atsun.dormitory.vo.DataResponse;
import com.atsun.dormitory.vo.NoDataResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: TODO(宿舍管理)
 * @Author SH
 * @Date 2022/1/11 13:35
 */
@Api(tags = "宿舍管理")
@RequestMapping("/room")
@RestController
public class RoomController extends BaseController {

    private RoomService roomService;

    @Autowired

    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }

    @ApiOperation(value = "账号下管理的全部宿舍")
    @RequiresPermissions("room:list")
    @PostMapping("/list")
    public DataResponse<?> list(@RequestBody RoomPageDTO roomPageDTO, @RequestHeader("_ut") String token) throws TransException {
        return ok(roomService.list(roomPageDTO, token));
    }

    @ApiOperation(value = "查询单个宿舍信息")
    @RequiresPermissions("room:query")
    @GetMapping("/query/{id}")
    public DataResponse<?> query(@PathVariable("id") String id) throws TransException {
        return ok(roomService.query(id));
    }

    @Log
    @ApiOperation(value = "删除宿舍")
    @RequiresPermissions("room:delete")
    @GetMapping("/delete/{id}")
    public NoDataResponse delete(@PathVariable("id") String id) throws TransException {
        roomService.delete(id);
        return ok();
    }

    @Log
    @ApiOperation(value = "添加-修改宿舍")
    @RequiresPermissions(value = {"room:save", "room:update"},logical = Logical.OR)
    @PostMapping("/saveOrUpdate")
    public NoDataResponse saveOrUpdate(@RequestBody RoomDTO roomDTO) throws TransException {
        roomService.saveOrUpdate(roomDTO);
        return ok();
    }

    @ApiOperation(value = "列表查询全部宿舍")
    @RequiresPermissions("room:list")
    @GetMapping("/listAll")
    public DataResponse<?> listAll(@RequestHeader("_ut") String token) throws TransException {
        return ok(roomService.listAll(token));
    }
}

