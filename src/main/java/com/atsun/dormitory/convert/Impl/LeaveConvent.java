package com.atsun.dormitory.convert.Impl;

import com.atsun.dormitory.dto.LeavePageDTO;
import com.atsun.dormitory.po.Leave;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.Date;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author SH
 * @Date 2022/2/16 18:00
 */
@Mapper
public interface LeaveConvent {

    LeaveConvent INSTANCE = Mappers.getMapper(LeaveConvent.class);

    /**
     * dto ->po
     *
     * @param leavePageDTO 请假表单
     * @return leave
     */
    Leave dtoToPo(LeavePageDTO leavePageDTO);
}