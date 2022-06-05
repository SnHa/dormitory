package com.atsun.dormitory.service.impl;

import com.atsun.dormitory.convert.Impl.NoticeConvent;
import com.atsun.dormitory.dto.Message;
import com.atsun.dormitory.dto.NoticeDTO;
import com.atsun.dormitory.dto.NoticePageDTO;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.service.BuildingService;
import com.atsun.dormitory.service.NoticeUserService;
import com.atsun.dormitory.service.UserService;
import com.atsun.dormitory.socket.WebSocket;
import com.atsun.dormitory.utils.SnowFlake;
import com.atsun.dormitory.vo.NoticeVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.atsun.dormitory.dao.NoticeMapper;
import com.atsun.dormitory.po.Notice;
import com.atsun.dormitory.service.NoticeService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-01 14:38
 **/
@Log4j2
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class NoticeServiceImpl implements NoticeService {

    private UserService userService;
    private BuildingService buildingService;
    private NoticeUserService noticeUserService;

    @Autowired
    public void setNoticeUserService(NoticeUserService noticeUserService) {
        this.noticeUserService = noticeUserService;
    }

    @Autowired
    public void setBuildingService(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Resource
    private NoticeMapper noticeMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(String id) throws TransException {
        return noticeMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(Notice record) throws TransException {
        return noticeMapper.insert(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertSelective(Notice record) throws TransException {
        return noticeMapper.insertSelective(record);
    }

    @Override
    public Notice selectByPrimaryKey(String id) throws TransException {
        return noticeMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKeySelective(Notice record) throws TransException {
        return noticeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKey(Notice record) throws TransException {
        return noticeMapper.updateByPrimaryKey(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sendToBuilding(NoticeDTO noticeDTO, String buildingId, String token) throws TransException {
        // todo dto->po
        String noticeId = String.valueOf(SnowFlake.nextId());
        Notice notice = NoticeConvent.INSTANCE.dtoToPo(noticeDTO);
        notice.setId(noticeId);
        notice.setUserId(userService.findUser(token).getId());
        notice.setCreateTime(new Date());
        // TODO 添加notice 到表中
        noticeMapper.insert(notice);
        //TODO 通过父id查询所有的子id 并根据bid查询出对应的用户
        List<String> bids = buildingService.getIdsByParentId(buildingId);
        List<String> uids = userService.listByBuildingIds(bids);
        this.saveRelevance(uids, noticeId);
    }

    @Override
    public PageInfo<NoticeVO> list(String token, NoticePageDTO noticePageDTO) throws TransException {
        PageHelper.startPage(noticePageDTO.getPage(), noticePageDTO.getRows());
        return new PageInfo<>(noticeMapper.listByUserId(userService.findUser(token).getId()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public NoticeVO query(String nid, String token) throws TransException {
        // todo 设置已读
        noticeUserService.updateRead(userService.findUser(token).getId(), nid);
        return noticeMapper.query(nid);
    }

    @Override
    public PageInfo<NoticeVO> listSend(String token, NoticePageDTO noticePageDTO) throws TransException {
        PageHelper.startPage(noticePageDTO.getPage(), noticePageDTO.getRows());
        List<NoticeVO> list = noticeMapper.listBySendUser(userService.findUser(token).getId());
        return new PageInfo<>(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSend(String nid) throws TransException {
        noticeUserService.deleteByNoticeId(nid);
        noticeMapper.deleteNotice(nid);
    }

    @Override
    public NoticeVO queryWithReceiver(String nid) throws TransException {
        return noticeMapper.queryWithReceiver(nid);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sendToUser(String uid, String token, NoticePageDTO noticePageDTO) throws TransException {
        Notice notice = new Notice();
        String nid = String.valueOf(SnowFlake.nextId());
        notice.setMsg(noticePageDTO.getMsg());
        notice.setId(nid);
        notice.setCreateTime(new Date());
        notice.setUserId(userService.findUser(token).getId());
        noticeMapper.save(notice);
        this.saveRelevance(uid, nid);
    }

    /**
     * 单条消息发送通知调用
     *
     * @param uid
     * @param nid
     * @throws TransException
     */
    public void saveRelevance(String uid, String nid) throws TransException {
        noticeUserService.saveRelevance(String.valueOf(SnowFlake.nextId()), uid, nid);
        NoticeVO notice = noticeMapper.query(nid);
        WebSocket.sendMessage(uid,
                new Message()
                        .setMessageBody(notice.getMsg())
                        .setType(1)
                        .setFrom(notice.getUser().getName()).setTitle("新通知"), userService);
    }

    /**
     * 进行多条消息发送调用
     *
     * @param uid
     * @param nid
     * @throws TransException
     */
    public void saveRelevance(List<String> uid, String nid) throws TransException {
        uid.forEach(l -> {
            try {
                saveRelevance(l, nid);
            } catch (TransException e) {
                e.printStackTrace();
            }
        });
    }
}
