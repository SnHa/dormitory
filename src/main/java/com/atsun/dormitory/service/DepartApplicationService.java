package com.atsun.dormitory.service;

import com.atsun.dormitory.dto.DepartApplicationDTO;
import com.atsun.dormitory.dto.DepartApplicationPageDTO;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.DepartApplication;
import com.atsun.dormitory.vo.DepartApplicationVO;
import com.github.pagehelper.PageInfo;

/**
 * @author: SH
 * @create: 2021-12-01 14:39
 **/
public interface DepartApplicationService {


    int deleteByPrimaryKey(String id);

    int insert(DepartApplication record);

    int insertSelective(DepartApplication record);

    DepartApplication selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DepartApplication record);

    int updateByPrimaryKey(DepartApplication record);

    /**
     * 保存退舍记录
     *
     * @param departApplication 退舍
     */
    void saveApplication(DepartApplication departApplication) throws TransException;

    /**
     * 通过 departApplicationUserId 查询 申请信息
     *
     * @param departApplicationUserId id
     * @return DepartApplication
     * @throws TransException 异常
     */
    DepartApplication getByDepartApplicationUserId(String departApplicationUserId) throws TransException;

    /**
     * 查询自己发送的审核信息
     *
     * @param departApplicationPageDTO 审核表单
     * @param token                    token
     * @return PageInfo<DepartApplicationVO>
     * @throws TransException 异常
     */
    PageInfo<DepartApplicationVO> listMy(DepartApplicationPageDTO departApplicationPageDTO, String token) throws TransException;

    /**
     * 删除自己发出的申请
     *
     * @param departApplicationId 申请id
     * @throws TransException 异常
     */
    void delete(String departApplicationId) throws TransException;
}
