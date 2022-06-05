package com.atsun.dormitory.service;

import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.NoticeUser;
import com.atsun.dormitory.vo.NoticeVO;

import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-01 14:38
 **/
public interface NoticeUserService {


    int deleteByPrimaryKey(String id) throws TransException;

    int insert(NoticeUser record) throws TransException;

    int insertSelective(NoticeUser record) throws TransException;

    NoticeUser selectByPrimaryKey(String id) throws TransException;

    int updateByPrimaryKeySelective(NoticeUser record) throws TransException;

    int updateByPrimaryKey(NoticeUser record) throws TransException;

    /**
     * 用户的未读消息数量
     *
     * @param userId 用户id
     * @return 未读数量
     * @throws TransException 异常
     */
    int countByUserId(String userId) throws TransException;

    /**
     * 添加转发通知
     *
     * @param id  id
     * @param uid 用户id
     * @param nid 通知id
     * @throws TransException 异常
     */
    void saveRelevance(String id, String uid, String nid) throws TransException;

    /**
     * 跟新已读通知
     *
     * @param userId 用户id
     * @param nid    通知id
     * @throws TransException 异常
     */
    void updateRead(String userId, String nid) throws TransException;

    /**
     * 删除我的通知
     *
     * @param nid   通知id
     * @param token token
     * @throws TransException 异常
     */
    void deleteReceive(String nid, String token) throws TransException;

    /**
     * 删除nid转发通知
     *
     * @param nid nid
     * @throws TransException 异常
     */
    void deleteByNoticeId(String nid) throws TransException;
}
