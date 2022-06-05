package com.atsun.dormitory.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author SH
 * @Date 2022/2/4 12:55
 */
@Data
public class DepartApplicationUserDTO {
    /**
     * id
     */
    private String id;

    /**
     * 是否同意
     */
    private Boolean isAgree;


    /**
     * 原因
     */
    private String reason;

}
