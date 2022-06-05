package com.atsun.dormitory.service;

import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.RolePermission;
import org.apache.shiro.authz.AuthorizationInfo;

import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-01 14:38
 **/
public interface RolePermissionService {


    int deleteByPrimaryKey(String id);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    RolePermission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);

    /**
     * 角色对应的权限
     *
     * @param roleIds 角色id
     * @return 权限 id集合
     * @throws TransException 异常
     */
    List<String> findPermissionIds(List<String> roleIds) throws TransException;

    /**
     * 查询用户所以权限
     *
     * @param id 角色id
     * @return 权限
     * @throws TransException 异常
     */
    AuthorizationInfo authorizationInfo(String id) throws TransException;
}
