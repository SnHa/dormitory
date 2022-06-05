package com.atsun.dormitory.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-13 12:24
 **/
@Data
public class OneRoleVO {

    private String id;

    private String name;

    private List<String> functionIds;

}
