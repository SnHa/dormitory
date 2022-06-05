package com.atsun.dormitory.dao;

import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.Permission;
import com.atsun.dormitory.vo.PermissionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-01 14:38
 **/
@Mapper
public interface PermissionMapper {
    int deleteByPrimaryKey(String id) throws TransException;

    int insert(Permission record) throws TransException;

    int insertSelective(Permission record) throws TransException;

    Permission selectByPrimaryKey(String id) throws TransException;

    int updateByPrimaryKeySelective(Permission record) throws TransException;

    int updateByPrimaryKey(Permission record) throws TransException;

    /**
     * 查询权限是菜单
     *
     * @param permissionIds 权限
     * @return 菜单
     * @throws TransException 异常
     */
    List<Permission> findMenu(@Param("permissionIds") List<String> permissionIds) throws TransException;

    List<String> findPermiss(@Param("permissionIds") List<String> permissionIds);

    /**
     * 全部权限信息
     *
     * @return List<PermissionVO>
     * @throws TransException 异常
     */
    List<PermissionVO> find() throws TransException;

    /**
     * 查找子id
     *
     * @param permissionId 父id
     * @return 子id Permission
     * @throws TransException 异常
     */
    List<Permission> findChildIdS(String permissionId) throws TransException;
}
