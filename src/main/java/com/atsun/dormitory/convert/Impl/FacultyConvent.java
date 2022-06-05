package com.atsun.dormitory.convert.Impl;

import com.atsun.dormitory.dto.FacultyDTO;
import com.atsun.dormitory.po.Faculty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @Description: TODO(Faculty转换)
 * @Author SH
 * @Date 2022/1/21 13:40
 */
@Mapper
public interface FacultyConvent {

    FacultyConvent INSTANCE = Mappers.getMapper(FacultyConvent.class);

    /**
     * dto-->po
     * @param facultyDTO
     * @return
     */
    @Mappings(value = {
            @Mapping(source = "pid" ,target = "PId")
    })
   Faculty dtoToPo(FacultyDTO facultyDTO);

}
