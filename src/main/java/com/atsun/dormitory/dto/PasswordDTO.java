package com.atsun.dormitory.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author: SH
 * @create: 2021-12-09 17:56
 **/
@Data
public class PasswordDTO {

    private String oldPassword;

    private String current1;

    private String current2;
}
