package com.atsun.dormitory.service;

import com.atsun.dormitory.dto.RoleDTO;
import com.atsun.dormitory.dto.RolePageDTO;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.Role;
import com.atsun.dormitory.vo.OneRoleVO;
import com.atsun.dormitory.vo.RoleVO;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Set;


/**
 * @author: SH
 * @create: 2021-12-01 14:38
 **/
public interface RoleService {


    int deleteByPrimaryKey(String id) throws TransException;

    int insert(Role record) throws TransException;

    int insertSelective(Role record) throws TransException;

    Role selectByPrimaryKey(String id) throws TransException;

    int updateByPrimaryKeySelective(Role record) throws TransException;

    int updateByPrimaryKey(Role record) throws TransException;

    /**
     * 查询所有角色信息
     *
     * @param rolePageDTO 查询所有角色信息
     * @return list
     * @throws TransException 异常
     */
    PageInfo<RoleVO> listPage(RolePageDTO rolePageDTO) throws TransException;

    /**
     * 查询单个角色信息
     *
     * @param id 角色id
     * @return OneRoleVO
     * @throws TransException 异常
     */
    OneRoleVO findByPrimaryKey(String id) throws TransException;

    /**
     * 根据id进行添加或修改
     *
     * @param roleDTO role
     * @throws TransException 异常
     */
    void saveOrUpdate(RoleDTO roleDTO) throws TransException;

    /**
     * 删除角色
     *
     * @param roleId 角色id
     * @throws TransException 异常
     */
    void delete(String roleId) throws TransException;

    /**
     * 查询全部
     * @return list
     * @throws TransException 异常
     */
    List<RoleVO> listInSelect() throws TransException;

    /**
     * 查询角色名称
     * @param roleIds 角色id
     * @return set
     */
    Set<String> RoleNames(List<String> roleIds) throws TransException;
}
