package com.atsun.dormitory.service;

import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.UserRole;

import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-01 14:37
 **/
public interface UserRoleService {


    int deleteByPrimaryKey(String id) throws TransException;

    int insert(UserRole record) throws TransException;

    int insertSelective(UserRole record) throws TransException;

    UserRole selectByPrimaryKey(String id) throws TransException;

    int updateByPrimaryKeySelective(UserRole record) throws TransException;

    int updateByPrimaryKey(UserRole record) throws TransException;

    /**
     * 查找用户对于的角色
     *
     * @param userId 用户id
     * @return 角色 id集合
     * @throws TransException 异常
     */
    List<String> findRoleIds(String userId) throws TransException;
}
