package com.atsun.dormitory.vo;

import com.atsun.dormitory.po.Student;
import lombok.Data;

import java.util.List;

/**
 * @Description: TODO(学院返回 数据)
 * @Author SH
 * @Date 2022/1/19 13:51
 */
@Data
public class FacultyVO {
    /**
     * id
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 父id
     */
    private String pId;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 子节点
     */
    private List<FacultyVO> children;

    /**
     * 学生数量
     */
    private Integer studentNum;

    /**
     * 学生
     */
    private List<Student> students;
}
