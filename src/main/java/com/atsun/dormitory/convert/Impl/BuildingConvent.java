package com.atsun.dormitory.convert.Impl;

import com.atsun.dormitory.dto.BuildingDTO;
import com.atsun.dormitory.po.Building;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Description: TODO(buliding的转换po dto vo)
 * @Author SH
 * @Date 2022/1/19 11:32
 */
@Mapper
public interface BuildingConvent {

    BuildingConvent INSTANCE = Mappers.getMapper(BuildingConvent.class);

    /**
     * dto -->po
     *
     * @param buildingDTO 表单
     * @return Building
     */
    Building dtoToPo(BuildingDTO buildingDTO);
}
