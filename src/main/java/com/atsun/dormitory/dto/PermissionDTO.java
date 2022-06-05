package com.atsun.dormitory.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author: SH
 * @create: 2021-12-10 16:36
 **/
@Data
public class PermissionDTO {

    private String id;
    private String name;
    private String pid;
    private Integer orderNum;
    private String path;
    private String component;
    private String type;
    private String sn;
    private String icon;


}
