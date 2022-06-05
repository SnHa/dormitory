package com.atsun.dormitory.service.impl;

import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.service.UserService;
import com.atsun.dormitory.vo.NoticeVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.atsun.dormitory.po.NoticeUser;
import com.atsun.dormitory.dao.NoticeUserMapper;
import com.atsun.dormitory.service.NoticeUserService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-01 14:38
 **/
@Log4j2
@Transactional(readOnly = true, rollbackFor = Exception.class)
@Service
public class NoticeUserServiceImpl implements NoticeUserService {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Resource
    private NoticeUserMapper noticeUserMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(String id) {
        return noticeUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(NoticeUser record) {
        return noticeUserMapper.insert(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertSelective(NoticeUser record) {
        return noticeUserMapper.insertSelective(record);
    }

    @Override
    public NoticeUser selectByPrimaryKey(String id) {
        return noticeUserMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKeySelective(NoticeUser record) {
        return noticeUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKey(NoticeUser record) {
        return noticeUserMapper.updateByPrimaryKey(record);
    }

    @Override
    public int countByUserId(String userId) throws TransException {
        return noticeUserMapper.countByUserId(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRelevance(String id, String uid, String nid) throws TransException {
        noticeUserMapper.saveRelevance(id, uid, nid);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRead(String userId, String nid) throws TransException {
        noticeUserMapper.updateRead(userId, nid);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteReceive(String nid, String token) throws TransException {
        noticeUserMapper.deleteReceive(nid, userService.findUser(token).getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByNoticeId(String nid) throws TransException {
        noticeUserMapper.deleteByNoticeId(nid);
    }

}
