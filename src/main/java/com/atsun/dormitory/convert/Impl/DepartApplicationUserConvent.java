package com.atsun.dormitory.convert.Impl;

import com.atsun.dormitory.dto.DepartApplicationUserDTO;
import com.atsun.dormitory.po.DepartApplication;
import com.atsun.dormitory.po.DepartApplicationUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Description: TODO(departApplicationUSER转换)
 * @Author SH
 * @Date 2022/2/3 12:08
 */
@Mapper
public interface DepartApplicationUserConvent {

    DepartApplicationUserConvent INSTANCE = Mappers.getMapper(DepartApplicationUserConvent.class);

    /**
     * dto->po
     *
     * @param departApplicationUserDTO dto
     * @return DepartApplication
     */
    DepartApplicationUser dtoToPo(DepartApplicationUserDTO departApplicationUserDTO);
}
