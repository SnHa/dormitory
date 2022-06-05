package com.atsun.dormitory.convert.Impl;

import com.atsun.dormitory.dto.RoleDTO;
import com.atsun.dormitory.po.Role;
import com.atsun.dormitory.vo.OneRoleVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.Date;
import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-14 11:28
 **/
@Mapper
public interface RoleConvent {

    RoleConvent INSTANCE = Mappers.getMapper(RoleConvent.class);

    /**
     * po->vo
     *
     * @param role        角色
     * @param functionIds 权限id
     * @return OneRoleVO
     */
    OneRoleVO roleToRoleVO(Role role, List<String> functionIds);

    /**
     * dto->po
     *
     * @param rId        角色id
     * @param roleDTO    角色信息
     * @param createTime 创建时间
     * @return Role
     */
    @Mappings(value = {
            @Mapping(source = "rId", target = "id"),
    })
    Role dtoToRole(String rId, RoleDTO roleDTO, Date createTime);

    /**
     * dto-->po
     * 角色变更
     *
     * @param roleDTO 前端角色表单
     * @return 角色实体类
     */
    Role dtoToPo(RoleDTO roleDTO);
}
