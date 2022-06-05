package com.atsun.dormitory.service.impl;

import com.atsun.dormitory.exception.TransException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.atsun.dormitory.po.UserRole;
import com.atsun.dormitory.dao.UserRoleMapper;
import com.atsun.dormitory.service.UserRoleService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-01 14:37
 **/
@Transactional(readOnly = true, rollbackFor = Exception.class)
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(String id) throws TransException {
        return userRoleMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(UserRole record) throws TransException {
        return userRoleMapper.insert(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertSelective(UserRole record) throws TransException {
        return userRoleMapper.insertSelective(record);
    }

    @Override
    public UserRole selectByPrimaryKey(String id) throws TransException {
        return userRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKeySelective(UserRole record) throws TransException {
        return userRoleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKey(UserRole record) throws TransException {
        return userRoleMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<String> findRoleIds(String userId) throws TransException {
        return userRoleMapper.findRoleIds(userId);
    }

}
