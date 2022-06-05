package com.atsun.dormitory.dao;

import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.DepartApplication;
import com.atsun.dormitory.vo.DepartApplicationVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-01 14:39
 **/
@Mapper
public interface DepartApplicationMapper {
    int deleteByPrimaryKey(String id);

    int insert(DepartApplication record);

    int insertSelective(DepartApplication record);

    DepartApplication selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DepartApplication record);

    int updateByPrimaryKey(DepartApplication record);

    /**
     * 插入退宿申请
     *
     * @param departApplication 退舍
     * @throws TransException 异常
     */
    void saveApplication(DepartApplication departApplication) throws TransException;

    /**
     * 获取申请
     *
     * @param departApplicationUserId 审核id
     * @return DepartApplication
     * @throws TransException 异常
     */
    DepartApplication getByDepartApplicationUserId(String departApplicationUserId) throws TransException;

    /**
     * 获取我的申请列表
     *
     * @param userId 用户id
     * @return list
     * @throws TransException 异常
     */
    List<DepartApplicationVO> listMyApplication(String userId) throws TransException;

    /**
     * 删除申请
     *
     * @param departApplicationId 审核id
     * @throws TransException 异常
     */
    void deleteApplication(String departApplicationId) throws TransException;
}
