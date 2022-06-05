package com.atsun.dormitory.vo;

import com.atsun.dormitory.po.User;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: TODO(操作日志)
 * @Author SH
 * @Date 2022/1/7 13:05
 */
@Data
public class LogVO {
    /**
     * id
     */
    private String id;

    /**
     * 操作时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operateTim;

    /**
     * 操作id
     */
    private String userId;

    /**
     * 操作层面
     */
    private String clas;

    /**
     * 操作的方法
     */
    private String method;

    /**
     * 操作的ip地址
     */
    private String ip;

    /**
     * 操作参数
     */
    private String param;

    /**
     * 操作路径
     */
    private String url;

    /**
     * 操作描述
     */
    private String description;

    /**
     * 操作结果
     */
    private String result;

    private User user;
}
