package com.atsun.dormitory.dto;

import lombok.Data;

import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-14 11:45
 **/
@Data
public class RoleDTO {

    private String id;

    private String name;

    private List<String> functionIds;
}
