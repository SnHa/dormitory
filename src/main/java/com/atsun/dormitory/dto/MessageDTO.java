package com.atsun.dormitory.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * SH
 */
@Data
public class MessageDTO {

    private int type;

    private String title;

    private String from;

    private String to;

    private String messageBody;
}
