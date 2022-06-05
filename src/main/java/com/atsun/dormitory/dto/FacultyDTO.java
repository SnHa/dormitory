package com.atsun.dormitory.dto;

import lombok.Data;

/**
 * @Description: TODO(学院表单)
 * @Author SH
 * @Date 2022/1/21 13:14
 */
@Data
public class FacultyDTO {
    /**
     * id
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 父id
     */
    private String pid;

    /**
     * 排序
     */
    private Integer orderNum;
}
