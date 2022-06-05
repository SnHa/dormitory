package com.atsun.dormitory.dao;

import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.RolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-01 14:38
 **/
@Mapper
public interface RolePermissionMapper {
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
     * @return 权限id
     * @throws TransException 异常
     */
    List<String> findPermissionIds(@Param("roleIds") List<String> roleIds) throws TransException;

    /**
     * 根据权限id删除数据
     *
     * @param permissionId 根据权限id
     * @throws TransException 异常
     */
    void deleteByPermissionId(String permissionId) throws TransException;

    /**
     * 按角色id删除
     *
     * @param roleId 角色id
     * @throws TransException 异常
     */
    void deleteByRoleId(String roleId) throws TransException;

    /**批量插入数据
     * @param rolePermissions
     * @throws TransException
     */
    void inserts(@Param("rolePermissions") List<RolePermission> rolePermissions) throws TransException;
}
