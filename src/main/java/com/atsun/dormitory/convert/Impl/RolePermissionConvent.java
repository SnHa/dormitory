package com.atsun.dormitory.convert.Impl;


import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;


/**
 * @author: SH
 * @create: 2021-12-14 12:07
 **/
@Mapper
public interface RolePermissionConvent {

    RolePermissionConvent INSTANCE = Mappers.getMapper(RolePermissionConvent.class);


}
