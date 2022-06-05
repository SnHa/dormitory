package com.atsun.dormitory.dao;

import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.Notice;
import com.atsun.dormitory.vo.NoticeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-01 14:38
 **/
@Mapper
public interface NoticeMapper {
    int deleteByPrimaryKey(String id);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKey(Notice record);

    /**
     * 查询通知
     *
     * @param nid 通知id
     * @return noticeVo
     * @throws TransException 异常
     */
    NoticeVO query(String nid) throws TransException;

    /**
     * 根据用户id查询 通知
     *
     * @param userId 用户id
     * @return list
     * @throws TransException 异常
     */
    List<NoticeVO> listByUserId(String userId) throws TransException;

    /**
     * 按发送用户列出来
     *
     * @param userId 用户id
     * @return
     * @throws TransException
     */
    List<NoticeVO> listBySendUser(String userId) throws TransException;

    /**
     * 删除自己发送的通知
     *
     * @param nid
     * @throws TransException
     */
    void deleteNotice(String nid) throws TransException;

    /**
     * 消息详情，带有接收者列表
     *
     * @param nid 通知id
     * @return NoticeVO
     * @throws TransException 异常
     */
    NoticeVO queryWithReceiver(String nid) throws TransException;

    /**
     * 插入消息
     *
     * @param notice 通知
     * @throws TransException 异常
     */
    void save(Notice notice) throws TransException;
}
