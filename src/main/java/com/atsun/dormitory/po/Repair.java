package com.atsun.dormitory.po;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
* @author: SH
* @create: 2021-12-01 14:38
**/
@Data
public class Repair implements Serializable {
    /**
    * id
    */
    private String id;

    /**
    * 房间号
    */
    private String roomId;

    /**
    * 问题
    */
    private String describe;

    /**
    * 状态
    */
    private Boolean status;

    /**
    * 创建时间
    */
    private Date createDate;

    /**
    * 完成时间
    */
    private Date finishDate;

    /**
    * 图片
    */
    private String img;

    private static final long serialVersionUID = 1L;
}