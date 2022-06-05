package com.atsun.dormitory.dto;

import com.atsun.dormitory.po.NoticeUser;
import com.atsun.dormitory.po.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author SH
 * @Date 2022/2/9 21:17
 */
@Data
public class NoticeDTO {
    /**
     * id
     */
    private String id;

    /**
     * 消息
     */
    private String msg;

    /**
     * 创建人
     */
    private String userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 是否已读
     */
    private Boolean isRead;

    private User user;

    private List<NoticeUser> receiveUsers;
}
