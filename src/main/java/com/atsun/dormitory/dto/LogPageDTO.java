package com.atsun.dormitory.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: TODO(日志操作DTO)
 * @Author SH
 * @Date 2022/1/7 16:24
 */
@Data
public class LogPageDTO {

    /**
     * 操作id
     */
    private String userId;

    /**
     * 操作时间开始
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operateTimeStart;

    /**
     * 操作时间结束
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operateTimeEnd;

    /**
     * 操作路径
     */
    private String url;

    /**
     * 操作的ip地址
     */
    private String ip;


    /**
     * 操作的方法
     */
    private String method;

    /**
     * 操作层面
     */
    private String clas;
    /**
     * 当前页
     */
    private Integer page;
    /**
     * 每页最大记录数
     */
    private Integer rows;
}
