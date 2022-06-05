package com.atsun.dormitory.dao;

import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.Role;
import com.atsun.dormitory.vo.RoleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @author: SH
 * @create: 2021-12-01 14:38
 **/
@Mapper
public interface RoleMapper {
    int deleteByPrimaryKey(String id) throws TransException;

    int insert(Role record) throws TransException;

    int insertSelective(Role record) throws TransException;

    Role selectByPrimaryKey(String id) throws TransException;

    int updateByPrimaryKeySelective(Role record) throws TransException;

    int updateByPrimaryKey(Role record) throws TransException;


    /**
     * 根据条件查询角色
     *
     * @param roleName 角色名
     * @return list
     * @throws TransException 异常
     */
    List<RoleVO> findRolePage(@Param("roleName") String roleName) throws TransException;

    /**
     * 根据角色id查询对应权限id
     *
     * @param roleId
     * @return list
     * @throws TransException 异常
     */
    List<String> functionIds(String roleId) throws TransException;

    /**
     * 查询角色的权限id
     *
     * @param roleId 角色id
     * @return list
     */
    List<String> findPermissionIds(String roleId);

    /**
     * 总计数
     *
     * @return
     * @throws TransException
     */
    int count() throws TransException;

    /**
     * 根据条件查询
     * @param role 角色
     * @return list
     * @throws TransException 异常
     */
    List<RoleVO> finds(Role role) throws TransException;

    /**
     * 查询角色名称
     * @param roleIds 角色名称
     * @return set
     * @throws TransException 异常
     */
    Set<String> RoleNames(@Param("roleIds") List<String> roleIds) throws TransException;
}
