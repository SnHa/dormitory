package com.atsun.dormitory.dao;

import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.User;
import com.atsun.dormitory.vo.UserListVO;
import com.atsun.dormitory.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author: SH
 * @create: 2021-12-01 14:36
 **/
@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(String id) throws TransException;

    int insert(User record) throws TransException;

    int insertSelective(User record) throws TransException;

    /**
     * 根据id查询用户信息
     *
     * @param id 用户id
     * @return 用户信息
     * @throws TransException 异常
     */
    User selectByPrimaryKey(String id) throws TransException;

    int updateByPrimaryKeySelective(User record) throws TransException;

    int updateByPrimaryKey(User record) throws TransException;

    /**
     * 查询用户
     *
     * @param loginName 用户名
     * @return 用户名和密码
     */
    UserVO selectByName(String loginName);

    /**
     * 按id查找密码
     *
     * @param userId
     * @return User
     * @throws TransException 异常
     */
    User findPasswordById(String userId) throws TransException;

    /**
     * 查询父id的名字和id
     *
     * @param pId 父id
     * @return User
     * @throws TransException 异常
     */
    User findNameByPid(String pId) throws TransException;

    /**
     * 全部信息（列表）
     *
     * @return list
     * @throws TransException 异常
     */
    List<UserListVO> findAll() throws TransException;

    /**
     * 条件查询
     *
     * @param pid        父id
     * @param name       name
     * @param userId     用户id
     * @param buildingId 宿舍
     * @return list
     */
    List<String> findByCondition(@Param("pid") String pid, @Param("name") String name, @Param("userId") List<String> userId, @Param("buildingId") String buildingId) throws TransException;

    /**
     * 用户信息
     *
     * @param userId 用户id
     * @return UserVO
     * @throws TransException 异常
     */
    UserVO findByUserId(String userId) throws TransException;

    List<UserVO> findByUserIds(@Param("list") List<String> list) throws TransException;

    /**
     * 根据id查询building
     *
     * @param userId 主键
     * @return String
     * @throws TransException 异常
     */
    String findBuilding(String userId) throws TransException;

    /**
     * 根据建筑id查询出用户id
     *
     * @param bids 建筑id
     * @return 用户id
     * @throws TransException 异常
     */
    List<String> listByBuildingIds(@Param("bids") List<String> bids) throws TransException;


}
