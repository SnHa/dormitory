package com.atsun.dormitory.convert.Impl;

import com.atsun.dormitory.po.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Date;

/**
 * @author: SH
 * @create: 2021-12-17 15:15
 **/
@Mapper
public interface UserRoleConvent {

    UserRoleConvent INSTANCE = Mappers.getMapper(UserRoleConvent.class);

    /**
     * 创建 UserRole对象
     * @param id
     * @param userId
     * @param roleId
     * @param createTime
     * @param updateTime
     * @return
     */
    UserRole ToUserRole(String id, String userId, String roleId, Date createTime,Date updateTime);

}
