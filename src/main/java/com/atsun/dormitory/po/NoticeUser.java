package com.atsun.dormitory.po;

import java.io.Serializable;
import lombok.Data;

/**
* @author: SH
* @create: 2021-12-01 14:38
**/
@Data
public class NoticeUser implements Serializable {
    /**
    * id
    */
    private String id;

    /**
    * 用户id
    */
    private String userId;

    /**
    * 通告id
    */
    private String noticeId;

    /**
    * 是否读取
    */
    private String isRead;

    private static final long serialVersionUID = 1L;
}