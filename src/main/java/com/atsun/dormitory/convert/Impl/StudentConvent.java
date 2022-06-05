package com.atsun.dormitory.convert.Impl;

import com.atsun.dormitory.dto.StudentDTO;
import com.atsun.dormitory.po.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Description: TODO(student的转换)
 * @Author SH
 * @Date 2022/2/2 12:40
 */
@Mapper
public interface StudentConvent {

    StudentConvent INSTANCE = Mappers.getMapper(StudentConvent.class);

    /**
     * dto->po
     *
     * @param studentDTO 学生表单
     * @return Student
     */
    Student dtoToPo(StudentDTO studentDTO);
}
