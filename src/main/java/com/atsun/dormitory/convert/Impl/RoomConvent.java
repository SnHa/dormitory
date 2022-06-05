package com.atsun.dormitory.convert.Impl;

import com.atsun.dormitory.dto.RoomDTO;
import com.atsun.dormitory.po.Room;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Description: TODO(room的po dto vo相互转换)
 * @Author SH
 * @Date 2022/1/14 13:16
 */
@Mapper
public interface RoomConvent {

    RoomConvent INSTANCE = Mappers.getMapper(RoomConvent.class);

    /**
     * dto转po
     *
     * @param roomDTO room表单
     * @return room
     */
    Room dtoToPo(RoomDTO roomDTO);
}
