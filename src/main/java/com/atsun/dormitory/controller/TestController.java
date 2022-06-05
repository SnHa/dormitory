package com.atsun.dormitory.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: SH
 * @create: 2021-11-25 09:36
 **/
@Log4j2
@RequestMapping("/test")
@RestController
public class TestController {

    @GetMapping("/t")
    public  String test(){
        log.info("进入");
        return "成功";
    }
}
