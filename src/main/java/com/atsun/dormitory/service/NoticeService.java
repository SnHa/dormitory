package com.atsun.dormitory.service;

import com.atsun.dormitory.dto.NoticeDTO;
import com.atsun.dormitory.dto.NoticePageDTO;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.Notice;
import com.atsun.dormitory.vo.NoticeVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

/**
 * @author: SH
 * @create: 2021-12-01 14:38
 **/
public interface NoticeService {


    int deleteByPrimaryKey(String id) throws TransException;

    int insert(Notice record) throws TransException;

    int insertSelective(Notice record) throws TransException;

    Notice selectByPrimaryKey(String id) throws TransException;

    int updateByPrimaryKeySelective(Notice record) throws TransException;

    int updateByPrimaryKey(Notice record) throws TransException;

    /**
     * 发送消息给多个用户
     *
     * @param noticeDTO  通知dto
     * @param buildingId 建筑id
     * @param token      token
     * @throws TransException 异常
     */
    void sendToBuilding(NoticeDTO noticeDTO, String buildingId, String token) throws TransException;

    /**
     * 根据用户id 获取通知
     *
     * @param token         token
     * @param noticePageDTO notice表单
     * @return 分页通知
     * @throws TransException 异常
     */
    PageInfo<NoticeVO> list(String token, NoticePageDTO noticePageDTO) throws TransException;

    /**
     * 查询 单个消息并设置已读
     *
     * @param nid   通知id
     * @param token token
     * @return NoticeVO
     * @throws TransException 异常
     */
    NoticeVO query(String nid, String token) throws TransException;

    /**
     * 自己发送的通知
     *
     * @param token         token
     * @param noticePageDTO 通知表单
     * @return 分页通知
     * @throws TransException 异常
     */
    PageInfo<NoticeVO> listSend(String token, NoticePageDTO noticePageDTO) throws TransException;

    /**
     * 删除自己发送的消息
     *
     * @param nid 通知id
     * @throws TransException 异常
     */
    void deleteSend(String nid) throws TransException;

    /**
     * 消息详情，带有接收者列表
     *
     * @param nid 通知id
     * @return NoticeVO
     * @throws TransException 异常
     */
    NoticeVO queryWithReceiver(String nid) throws TransException;

    /**
     * 给单个用户发送信息
     *
     * @param uid           用户id
     * @param token         token
     * @param noticePageDTO 消息列表
     * @throws TransException 异常
     */
    void sendToUser(String uid, String token, NoticePageDTO noticePageDTO) throws TransException;
}
