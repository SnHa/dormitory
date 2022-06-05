package com.atsun.dormitory.vo;

import lombok.Data;

import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-16 14:30
 **/
@Data
public class BuildingVo {

    private String id;

    private String name;

    private String pid;

    private Integer studentNum;

    private Integer roomNum;

    private List<BuildingVo> children;



}
