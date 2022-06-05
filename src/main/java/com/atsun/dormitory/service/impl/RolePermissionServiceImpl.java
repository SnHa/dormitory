package com.atsun.dormitory.service.impl;

import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.service.PermissionService;
import com.atsun.dormitory.service.RoleService;
import com.atsun.dormitory.service.UserRoleService;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.atsun.dormitory.po.RolePermission;
import com.atsun.dormitory.dao.RolePermissionMapper;
import com.atsun.dormitory.service.RolePermissionService;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author: SH
 * @create: 2021-12-01 14:38
 **/
@Log4j2
@Transactional(readOnly = true, rollbackFor = Exception.class)
@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    private RoleService roleService;
    private UserRoleService userRoleService;
    private PermissionService permissionService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Autowired
    public void setUserRoleService(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(String id) {
        return rolePermissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(RolePermission record) {
        return rolePermissionMapper.insert(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertSelective(RolePermission record) {
        return rolePermissionMapper.insertSelective(record);
    }

    @Override
    public RolePermission selectByPrimaryKey(String id) {
        return rolePermissionMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKeySelective(RolePermission record) {
        return rolePermissionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKey(RolePermission record) {
        return rolePermissionMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<String> findPermissionIds(List<String> roleIds) throws TransException {
        return rolePermissionMapper.findPermissionIds(roleIds);
    }

    @Override
    public AuthorizationInfo authorizationInfo(String id) throws TransException {
        // TODO 查询所以角色 id和名称
        List<String> roleIds = userRoleService.findRoleIds(id);
        Set<String> roleNames= null;
        if (roleIds.size()>0){
            roleNames= roleService.RoleNames(roleIds);
            log.info("角色名称"+roleNames);

        }
        // TODO 权限id
        List<String> permissionIds = this.findPermissionIds(roleIds);
        List<String> permiss;
        Set<String> set =null;
        if (permissionIds.size() > 0) {
            permiss = permissionService.findPermiss(permissionIds);
             set = new HashSet<>(permiss);
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roleNames);
        info.setStringPermissions(set);
        return info;
    }

}
