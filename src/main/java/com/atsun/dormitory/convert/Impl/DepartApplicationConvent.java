package com.atsun.dormitory.convert.Impl;

import com.atsun.dormitory.dto.DepartApplicationDTO;
import com.atsun.dormitory.po.DepartApplication;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Description: TODO(departApplication转换)
 * @Author SH
 * @Date 2022/2/3 12:08
 */
@Mapper
public interface DepartApplicationConvent {

    DepartApplicationConvent INSTANCE = Mappers.getMapper(DepartApplicationConvent.class);

    /**
     * dto->po
     *
     * @param departApplicationDTO dto
     * @return DepartApplication
     */
    DepartApplication dtoToPo(DepartApplicationDTO departApplicationDTO);
}
