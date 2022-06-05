package com.atsun.dormitory.dao;

import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.UserRole;
import com.atsun.dormitory.vo.RoleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-01 14:37
 **/
@Mapper
public interface UserRoleMapper {
    int deleteByPrimaryKey(String id) throws TransException;

    int insert(UserRole record) throws TransException;

    int insertSelective(UserRole record) throws TransException;

    UserRole selectByPrimaryKey(String id) throws TransException;

    int updateByPrimaryKeySelective(UserRole record) throws TransException;

    int updateByPrimaryKey(UserRole record) throws TransException;

    /**
     * 用户包含的角色
     *
     * @param userId 用户id
     * @return 角色id
     * @throws TransException 异常
     */
    List<String> findRoleIds(String userId) throws TransException;

    /**
     * 根据角色id进行删除
     *
     * @param roleId 角色id
     * @throws TransException 异常
     */
    void deleteByRoleId(String roleId) throws TransException;

    /**
     * 查询用户id
     *
     * @param userRoleIds 角色id
     * @return list
     * @throws TransException 异常
     */
    List<String> findUserIds(@Param("userRoleIds") List<String> userRoleIds) throws TransException;

    /**
     * 角色信息
     *
     * @param userId 用户id
     * @return list
     * @throws TransException 异常
     */
    List<RoleVO> findRole(String userId);

    /**
     * 删除相关用户角色
     *
     * @param userId 用户id
     * @throws TransException 异常
     */
    void deleteByUserId(String userId) throws TransException;

    /**
     * 批量添加
     *
     * @param userRoles 关系
     * @throws TransException 异常
     */
    void batchInsert(@Param("userRoles") List<UserRole> userRoles) throws TransException;
}
