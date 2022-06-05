package com.atsun.dormitory.dao;

import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.NoticeUser;
import com.atsun.dormitory.vo.NoticeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-01 14:38
 **/
@Mapper
public interface NoticeUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(NoticeUser record);

    int insertSelective(NoticeUser record);

    NoticeUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(NoticeUser record);

    int updateByPrimaryKey(NoticeUser record);

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
     * 跟新通知已读
     *
     * @param userId 用户id
     * @param nid    通知id
     * @throws TransException 异常
     */
    void updateRead(String userId, String nid) throws TransException;

    /**
     * 删除自己的通知
     *
     * @param nid    通知id
     * @param userId 用户id
     * @throws TransException 异常
     */
    void deleteReceive(String nid, String userId) throws TransException;

    /**
     * 删除通知转发消息
     *
     * @param nid 通知
     * @throws TransException 异常
     */
    void deleteByNoticeId(String nid) throws TransException;
}
