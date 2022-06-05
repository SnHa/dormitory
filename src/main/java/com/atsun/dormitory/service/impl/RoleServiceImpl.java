package com.atsun.dormitory.service.impl;

import com.atsun.dormitory.convert.Impl.RoleConvent;
import com.atsun.dormitory.dao.RolePermissionMapper;
import com.atsun.dormitory.dao.UserRoleMapper;
import com.atsun.dormitory.dto.RoleDTO;
import com.atsun.dormitory.dto.RolePageDTO;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.RolePermission;
import com.atsun.dormitory.utils.SnowFlake;
import com.atsun.dormitory.vo.OneRoleVO;
import com.atsun.dormitory.vo.RoleVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.atsun.dormitory.dao.RoleMapper;
import com.atsun.dormitory.po.Role;
import com.atsun.dormitory.service.RoleService;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
 * @author: SH
 * @create: 2021-12-01 14:38
 **/
@Log4j2
@Transactional(readOnly = true, rollbackFor = Exception.class)
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(String id) throws TransException {
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(Role record) throws TransException {
        return roleMapper.insert(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertSelective(Role record) throws TransException {
        return roleMapper.insertSelective(record);
    }

    @Override
    public Role selectByPrimaryKey(String id) throws TransException {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKeySelective(Role record) throws TransException {
        return roleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKey(Role record) throws TransException {
        return roleMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<RoleVO> listPage(RolePageDTO rolePageDTO) throws TransException {

        // TODO 先求出总的条数 判断是否是本页的嘴和一条数据
        int count = roleMapper.count();
        if ((int) Math.ceil((double) count / rolePageDTO.getRows()) < rolePageDTO.getPage()) {
            rolePageDTO.setPage(rolePageDTO.getPage() - 1);
        }
        // TODO 判断 是否是最后一条数据
        PageHelper.startPage(rolePageDTO.getPage(), rolePageDTO.getRows());
        List<RoleVO> rolePage = roleMapper.findRolePage(rolePageDTO.getName());
        PageInfo<RoleVO> pageInfo = new PageInfo<>(rolePage);
        log.info("总记页数" + pageInfo.getPages() + "======" + "当前页" + pageInfo.getPageNum() + "=======" + "每页数量" + pageInfo.getPageSize());
        return pageInfo;
    }

    @Override
    public OneRoleVO findByPrimaryKey(String id) throws TransException {

        Role role = roleMapper.selectByPrimaryKey(id);
        List<String> functionIds = roleMapper.functionIds(id);

        return RoleConvent.INSTANCE.roleToRoleVO(role, functionIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(RoleDTO roleDTO) throws TransException {

        String idSnowFlake = String.valueOf(SnowFlake.nextId());
        List<RolePermission> rolePermissions = new ArrayList<>();

        roleDTO.getFunctionIds().removeIf(Objects::isNull);
        if (!roleDTO.getFunctionIds().isEmpty()) {
            roleDTO.getFunctionIds().forEach(id -> rolePermissions.add(new RolePermission(String.valueOf(SnowFlake.nextId()),
                    StringUtils.isBlank(roleDTO.getId()) ? idSnowFlake : roleDTO.getId(), id, new Date(), new Date())));
        }

        if (!StringUtils.isBlank(roleDTO.getId())) {
            rolePermissionMapper.deleteByRoleId(roleDTO.getId());
            // TODO 更新角色信息
            roleMapper.updateByPrimaryKeySelective(RoleConvent.INSTANCE.dtoToPo(roleDTO));
        } else {
            roleMapper.insert(RoleConvent.INSTANCE.dtoToRole(idSnowFlake, roleDTO, new Date()));
        }

        if (!rolePermissions.isEmpty()) {
            rolePermissionMapper.inserts(rolePermissions);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String roleId) throws TransException {

        // TODO 删除角色——用户表
        userRoleMapper.deleteByRoleId(roleId);
        // TODO 删除角色--权限表
        rolePermissionMapper.deleteByRoleId(roleId);
        // TODO 删除角色表
        roleMapper.deleteByPrimaryKey(roleId);
    }

    @Override
    public List<RoleVO> listInSelect() throws TransException {
        return roleMapper.finds(null);
    }

    @Override
    public Set<String> RoleNames(List<String> roleIds) throws TransException {
        return roleMapper.RoleNames(roleIds);
    }

}
