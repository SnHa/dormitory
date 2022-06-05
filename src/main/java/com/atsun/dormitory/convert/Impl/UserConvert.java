package com.atsun.dormitory.convert.Impl;

import com.atsun.dormitory.dto.BasicUserDTO;
import com.atsun.dormitory.dto.LoginUser;
import com.atsun.dormitory.dto.UserDTO;
import com.atsun.dormitory.po.Building;
import com.atsun.dormitory.po.User;
import com.atsun.dormitory.vo.BasicUserVO;
import com.atsun.dormitory.vo.UserVO;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * user类 pojo之间的转换
 *
 * @author: SH
 * @create: 2021-12-01 15:36
 **/
@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    /**
     * userDTO--->userPO
     * numberFormat 按照什么规则格式化
     *
     * @param userDTO dto
     * @return User
     */
    @Mappings(value = {
            @Mapping(source = "realName",target = "name"),
            @Mapping(source = "leaderId" ,target = "PId"),
    })
    User dtoToPo(UserDTO userDTO);

    /**
     * user的信息存储到基本的UserVO
     *
     * @param user 用户信息
     * @return BasicUserVO
     */
    @Mapping(source = "name", target = "name")
    BasicUserVO poToVo(User user);

    /**
     * 基本信息-->user
     *
     * @param basicUserDTO basicUserDTO
     * @return User
     */
    @Mapping(source = "icon", target = "icon", ignore = true)
    User BasicUserDtoToUser(BasicUserDTO basicUserDTO);

    /**
     * ->vo
     *
     * @param user user
     * @return UserVO
     */
    @Mapping(source = "id", target = "id")
    UserVO poTOUserVO(User user);
}
