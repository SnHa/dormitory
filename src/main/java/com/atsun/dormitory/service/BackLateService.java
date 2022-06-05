package com.atsun.dormitory.service;

import com.atsun.dormitory.dto.BackLatePageDTO;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.BackLate;
import com.atsun.dormitory.vo.BackLateVO;
import com.github.pagehelper.PageInfo;

/**
 * @author: SH
 * @create: 2021-12-01 14:39
 **/
public interface BackLateService {


    int deleteByPrimaryKey(String id) throws TransException;

    int insert(BackLate record) throws TransException;

    int insertSelective(BackLate record) throws TransException;

    BackLate selectByPrimaryKey(String id) throws TransException;

    int updateByPrimaryKeySelective(BackLate record) throws TransException;

    int updateByPrimaryKey(BackLate record) throws TransException;

    /**
     * 查询全部晚归记录
     *
     * @param backLatePageDTO 晚归列表
     * @param token           token
     * @return 分页 晚归
     * @throws TransException 异常
     */
    PageInfo<BackLateVO> list(BackLatePageDTO backLatePageDTO, String token) throws TransException;

    /**
     * 根据id存在进行添加-修改晚归
     *
     * @param backLatePageDTO 晚归列表
     * @throws TransException 异常
     */
    void saveOrUpdate(BackLatePageDTO backLatePageDTO) throws TransException;

    /**
     * 查询单个晚归记录
     *
     * @param blid 晚归di
     * @return BaCKlATE
     * @throws TransException 异常
     */
    BackLateVO query(String blid) throws TransException;
}
