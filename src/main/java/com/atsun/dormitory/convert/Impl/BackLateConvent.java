package com.atsun.dormitory.convert.Impl;

import com.atsun.dormitory.dto.BackLatePageDTO;
import com.atsun.dormitory.po.BackLate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.omg.CORBA.BAD_CONTEXT;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author SH
 * @Date 2022/2/17 15:52
 */
@Mapper
public interface BackLateConvent {

    BackLateConvent INSTANCE = Mappers.getMapper(BackLateConvent.class);

    /**
     * dto->po
     *
     * @param backLatePageDTO 晚归列表
     * @return back
     */
    BackLate dtoToPo(BackLatePageDTO backLatePageDTO);
}
