package com.atsun.dormitory.service.impl;

import com.atsun.dormitory.dto.LogPageDTO;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.vo.LogVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.atsun.dormitory.dao.LogMapper;
import com.atsun.dormitory.po.Log;
import com.atsun.dormitory.service.LogService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-01 14:38
 **/
@Log4j2
@Transactional(readOnly = true, rollbackFor = Exception.class)
@Service
public class LogServiceImpl implements LogService {

    @Resource
    private LogMapper logMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(String id) throws TransException {
        return logMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(Log record) throws TransException {
        return logMapper.insert(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertSelective(Log record) throws TransException {
        return logMapper.insertSelective(record);
    }

    @Override
    public Log selectByPrimaryKey(String id) throws TransException {
        return logMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKeySelective(Log record) throws TransException {
        return logMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKey(Log record) throws TransException {
        return logMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<LogVO> list(LogPageDTO logPageDTO) throws TransException {
        // TODO 删除到每页最后一个时候返回到前一页
        // TODO 总共记录数
        int count = logMapper.count();
        if ((int) Math.ceil((double) count / logPageDTO.getRows()) < logPageDTO.getPage()) {
            logPageDTO.setPage(logPageDTO.getPage() - 1);
        }
        PageHelper.startPage(logPageDTO.getPage(), logPageDTO.getRows());
        // TODO 条件查询记录
        List<LogVO> logVO = logMapper.list(logPageDTO.getClas(), logPageDTO.getMethod(), logPageDTO.getIp(),
                logPageDTO.getUserId(), logPageDTO.getUrl(), logPageDTO.getOperateTimeStart(),logPageDTO.getOperateTimeEnd());
        return new PageInfo<>(logVO);
    }

    @Override
    public LogVO query(String logId) throws TransException {
        return logMapper.query(logId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByUserId(String userId) throws TransException {
        logMapper.deleteByUserId(userId);
    }

}
