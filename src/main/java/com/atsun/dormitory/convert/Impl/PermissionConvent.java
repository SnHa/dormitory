package com.atsun.dormitory.convert.Impl;

import com.atsun.dormitory.dto.PermissionDTO;
import com.atsun.dormitory.po.Permission;
import com.atsun.dormitory.vo.PermissionVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author: SH
 * @create: 2021-12-06 19:04
 **/
@Mapper
public interface PermissionConvent {

    PermissionConvent INSTANCE = Mappers.getMapper(PermissionConvent.class);

    /**
     * po-->vo
     *
     * @param permission 权限
     * @return vo
     */
    PermissionVO permissionToPermissionVO(Permission permission);

    /**
     * dto->po
     *
     * @param permissionDTO dto
     * @return Permission
     */
    Permission permissionDtoToPermission(PermissionDTO permissionDTO);
}
