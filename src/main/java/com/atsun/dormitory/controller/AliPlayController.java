package com.atsun.dormitory.controller;


import com.atsun.dormitory.dto.PayDTO;
import com.atsun.dormitory.dto.PayPageDTO;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.service.PayService;

import com.atsun.dormitory.vo.DataResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author SH
 * @Date 2022/3/2 13:09
 */
@Log4j2
@Api(tags = "阿里")
@RequestMapping("/ali")
@RestController
public class AliPlayController extends BaseController {

    private PayService payService;

    @Autowired
    public void setPayService(PayService payService) {
        this.payService = payService;
    }

    @PostMapping(value = "/play")
    public DataResponse<?> pay(@RequestBody PayDTO payDTO) throws TransException {
        return ok(payService.pay(payDTO));
    }

    @ApiOperation(value = "异步请求")
    @PostMapping("/notinfy")
    public DataResponse<?> notify(HttpServletRequest request) throws TransException {
        return ok(payService.notifyByPay(request));
    }

    @ApiOperation(value = "缴费订单查询")
    @PostMapping("/list")
    public DataResponse<?> list(@RequestBody PayPageDTO payPageDTO,@RequestHeader("_ut") String token) throws TransException {
        return ok(payService.list(payPageDTO,token));
    }

}
