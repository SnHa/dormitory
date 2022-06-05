package com.atsun.dormitory.controller;

import com.atsun.dormitory.dto.DepartApplicationPageDTO;
import com.atsun.dormitory.dto.DepartApplicationUserDTO;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.service.DepartApplicationService;
import com.atsun.dormitory.service.DepartApplicationUserService;
import com.atsun.dormitory.vo.DataResponse;
import com.atsun.dormitory.vo.NoDataResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @Description: TODO(depart)
 * @Author SH
 * @Date 2022/2/3 13:42
 */
@Api(tags = "退舍消息")
@RequestMapping("/depart")
@RestController
public class DepartController extends BaseController {

    private DepartApplicationService departApplicationService;
    private DepartApplicationUserService departApplicationUserService;

    @Autowired
    public void setDepartApplicationService(DepartApplicationService departApplicationService) {
        this.departApplicationService = departApplicationService;
    }

    @Autowired
    public void setDepartApplicationUserService(DepartApplicationUserService departApplicationUserService) {
        this.departApplicationUserService = departApplicationUserService;
    }

    @ApiOperation(value = "自己审核")
    @PostMapping("/listMyFlow")
    public DataResponse<?> listMyFlow(@RequestBody DepartApplicationPageDTO departApplicationPageDTO,
                                      @RequestHeader("_ut") String token) throws TransException {
        return ok(departApplicationUserService.listMyFlow(departApplicationPageDTO, token));
    }

    @ApiOperation(value = "审核操作")
    @PostMapping("/update")
    public NoDataResponse update(@RequestBody DepartApplicationUserDTO departApplicationUserDTO,
                                 @RequestHeader("_ut") String token) throws TransException {
        String msg = departApplicationUserService.update(departApplicationUserDTO, token);
        return Ok(msg);
    }

    @ApiOperation(value = "自己发送的审核")
    @PostMapping("/listMy")
    public DataResponse<?> listMy(@RequestBody DepartApplicationPageDTO departApplicationPageDTO,
                                  @RequestHeader("_ut") String token) throws TransException {
        return ok(departApplicationService.listMy(departApplicationPageDTO, token));
    }

    @ApiOperation(value = "查询单个审核记录")
    @GetMapping("/listFlow/{id}")
    public DataResponse<?> listFlow(@PathVariable("id") String id) throws TransException {
        return ok(departApplicationUserService.listFlow(id));
    }

    @ApiOperation(value = "删除自己提交的记录")
    @GetMapping("/delete/{id}")
    public NoDataResponse delete(@PathVariable("id") String id) throws TransException {
        departApplicationService.delete(id);
        return ok();
    }
}
