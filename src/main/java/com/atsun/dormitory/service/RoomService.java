package com.atsun.dormitory.service;

import com.atsun.dormitory.dto.RoomDTO;
import com.atsun.dormitory.dto.RoomPageDTO;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.Room;
import com.atsun.dormitory.vo.DataResponse;
import com.atsun.dormitory.vo.RoomVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-01 14:38
 **/
public interface RoomService {


    int deleteByPrimaryKey(String id) throws TransException;

    int insert(Room record) throws TransException;

    int insertSelective(Room record) throws TransException;

    Room selectByPrimaryKey(String id) throws TransException;

    int updateByPrimaryKeySelective(Room record) throws TransException;

    int updateByPrimaryKey(Room record) throws TransException;

    /**
     * 根据条件查询账号下宿舍
     *
     * @param roomPageDTO 条件
     * @param token       token
     * @return PageInfo<RoomVO>
     * @throws TransException 异常
     */
    PageInfo<RoomVO> list(RoomPageDTO roomPageDTO, String token) throws TransException;

    /**
     * 根据宿舍id查询单个宿舍信息
     *
     * @param roomId 宿舍id
     * @return RoomVO
     * @throws TransException 异常
     */
    RoomVO query(String roomId) throws TransException;

    /**
     * 根据宿舍id删除宿舍信息
     *
     * @param roomId 宿舍id
     * @throws TransException 异常
     */
    void delete(String roomId) throws TransException;

    /**
     * 根据宿舍id判断是修改--添加
     *
     * @param roomDTO 宿舍表单信息
     * @throws TransException 异常
     */
    void saveOrUpdate(RoomDTO roomDTO) throws TransException;

    /**
     * 列表查询全部宿舍
     *
     * @param token token
     * @return List<RoomVO>
     * @throws TransException 异常
     */
    List<RoomVO> listAll(String token) throws TransException;

    /**
     * 查询该宿舍的最大容量
     *
     * @param roomId 宿舍id
     * @return int
     * @throws TransException 异常
     */
    int getMaxCapacity(String roomId) throws TransException;

    /**
     * 查询建筑数量
     *
     * @param buildingId 建筑id
     * @return int
     * @throws TransException 异常
     */
    int count(String buildingId) throws TransException;

    /**
     * 根据宿舍号查找宿舍信息
     *
     * @param number 宿舍号
     * @return room
     * @throws TransException 异常
     */
    Room selectByNumber(String number) throws TransException;
}
