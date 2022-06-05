package com.atsun.dormitory.dao;

import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.Room;
import com.atsun.dormitory.vo.RoomVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-01 14:38
 **/
@Mapper
public interface RoomMapper {
    int deleteByPrimaryKey(String id) throws TransException;

    int insert(Room record) throws TransException;

    int insertSelective(Room record) throws TransException;

    Room selectByPrimaryKey(String id) throws TransException;

    int updateByPrimaryKeySelective(Room record) throws TransException;

    int updateByPrimaryKey(Room record) throws TransException;

    /**
     * @param isFull
     * @param number
     * @param byParentIds
     * @param selectBid
     * @return
     * @throws TransException
     */
    List<RoomVO> list(@Param("isFull") Boolean isFull, @Param("number") String number,
                      @Param("byParentIds") List<String> byParentIds, @Param("selectBid") List<String> selectBid) throws TransException;

    /**
     * 查询单个宿舍信息
     *
     * @param roomId 宿舍id
     * @return RoomVO
     * @throws TransException 异常
     */
    RoomVO query(String roomId) throws TransException;


    int count(@Param("isFull") Boolean isFull, @Param("number") String number,
              @Param("byParentIds") List<String> byParentIds, @Param("selectBid") List<String> selectBid) throws TransException;

    /**
     * 列表
     *
     * @param bids buids集合
     * @return room
     * @throws TransException 异常
     */
    List<Room> listByBuildingId(@Param("buildingId") List<String> bids) throws TransException;

    /**
     * 查询宿舍的最大容量
     *
     * @param roomId 宿舍id
     * @return int
     * @throws TransException 异常
     */
    int getMaxCapacity(String roomId) throws TransException;

    /**
     * 根据建筑查询房间数量
     *
     * @param bids 建筑id
     * @return int
     * @throws TransException 异常
     */
    int roomCountByBuilding(@Param("bids") List<String> bids) throws TransException;

    /**
     * 根据宿舍号查找宿舍信息
     *
     * @param number 宿舍号
     * @return room
     * @throws TransException 异常
     */
    Room selectByNumber(String number) throws TransException;
}
