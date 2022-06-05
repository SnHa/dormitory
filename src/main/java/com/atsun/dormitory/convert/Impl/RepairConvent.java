package com.atsun.dormitory.convert.Impl;

import com.atsun.dormitory.dto.RepairPageDTO;
import com.atsun.dormitory.po.Repair;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author SH
 * @Date 2022/2/15 11:28
 */
@Mapper
public interface RepairConvent {

    RepairConvent INSTANCE = Mappers.getMapper(RepairConvent.class);


    /**
     * dto_>po
     *
     * @param repairPageDTO 维修表单
     * @return 维修
     */
    @Mapping(target = "createDate", ignore = true)
    //@Mapping(target = "createDate", source = "createDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    Repair dtoToPo(RepairPageDTO repairPageDTO);
}
