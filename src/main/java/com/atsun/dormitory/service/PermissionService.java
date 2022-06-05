package com.atsun.dormitory.service;

import com.atsun.dormitory.dto.PermissionDTO;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.Permission;
import com.atsun.dormitory.vo.PermissionVO;

import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-01 14:38
 **/
public interface PermissionService {


    int deleteByPrimaryKey(String id) throws TransException;

    int insert(Permission record) throws TransException;

    int insertSelective(Permission record) throws TransException;

    Permission selectByPrimaryKey(String id) throws TransException;

    int updateByPrimaryKeySelective(Permission record) throws TransException;

    int updateByPrimaryKey(Permission record) throws TransException;


    /**
     * 权限对应的菜单
     *
     * @param permissionIds 权限 id
     * @return 菜单信息
     * @throws TransException 异常
     */
    List<PermissionVO> findMenu(List<String> permissionIds) throws TransException;

    /**
     * 查找权限
     *
     * @param permissionIds 权限id
     * @return List<String>
     */
    List<String> findPermiss(List<String> permissionIds);

    /**
     * 所有菜单
     *
     * @return List<PermissionVO>
     * @throws TransException 异常
     */
    List<PermissionVO> listPermission() throws TransException;

    /**
     * 查询 权限信息
     *
     * @param permissionId 权限id
     * @return 权限信息
     * @throws TransException 异常
     */
    Permission queryPermission(String permissionId) throws TransException;

    /**
     * 根据id判断修改或添加权限
     *
     * @param permissionDTO 权限信息
     * @throws TransException 异常
     */
    void saveOrUpdate(PermissionDTO permissionDTO) throws TransException;

    /**
     * 删除权限
     *
     * @param permissionId 权限id
     * @throws TransException 异常
     */
    void deletePermission(String permissionId) throws TransException;
}
