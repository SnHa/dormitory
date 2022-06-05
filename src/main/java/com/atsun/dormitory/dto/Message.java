package com.atsun.dormitory.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author SH
 * @Date 2022/2/3 12:30
 */
@Data
@Accessors(chain = true)
public class Message {
    private int type;

    private String title;

    private String from;

    private String to;

    private String messageBody;
}
