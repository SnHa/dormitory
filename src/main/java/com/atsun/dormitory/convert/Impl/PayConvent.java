package com.atsun.dormitory.convert.Impl;

import com.atsun.dormitory.dto.PayDTO;
import com.atsun.dormitory.po.Pay;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author SH
 * @Date 2022/3/3 23:22
 */
@Mapper
public interface PayConvent {
    PayConvent INSTANCE = Mappers.getMapper(PayConvent.class);

    Pay dtoToPo(PayDTO payDTO);
}
