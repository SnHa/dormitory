package com.atsun.dormitory.dao;

import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.Repair;
import com.atsun.dormitory.vo.RepairVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-01 14:38
 **/
@Mapper
public interface RepairMapper {
    int deleteByPrimaryKey(String id) throws TransException;

    int insert(Repair record) throws TransException;

    int insertSelective(Repair record) throws TransException;

    Repair selectByPrimaryKey(String id) throws TransException;

    int updateByPrimaryKeySelective(Repair record) throws TransException;

    int updateByPrimaryKey(Repair record) throws TransException;

    /**
     * 查询权限的维修
     *
     * @param roomId     房间号
     * @param status     状态
     * @param createDate 创建时间
     * @param bids       建筑id集合
     * @return 返回
     * @throws TransException 异常
     */
    List<RepairVO> list(@Param("roomId") String roomId, @Param("status") Boolean status, @Param("createDate") String createDate,
                        @Param("bids") List<String> bids) throws TransException;

    /**
     * 查询单个维修
     *
     * @param rid 维修id
     * @return RepairVO
     * @throws TransException 异常
     */
    RepairVO query(String rid) throws TransException;
}
