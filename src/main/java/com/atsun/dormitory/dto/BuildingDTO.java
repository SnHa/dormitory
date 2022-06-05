package com.atsun.dormitory.dto;

import lombok.Data;

/**
 * @Description: TODO(Building表单)
 * @Author SH
 * @Date 2022/1/19 11:11
 */
@Data
public class BuildingDTO {

    /**
     * id
     */
    private String id;

    /**
     * 楼栋名称
     */
    private String name;

    /**
     * 父id
     */
    private String pId;
}
