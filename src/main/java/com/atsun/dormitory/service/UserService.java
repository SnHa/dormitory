package com.atsun.dormitory.service;

import com.atsun.dormitory.dto.*;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.User;
import com.atsun.dormitory.vo.UserListVO;
import com.atsun.dormitory.vo.UserVO;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author: SH
 * @create: 2021-12-01 14:36
 **/
public interface UserService {


    int deleteByPrimaryKey(String id) throws TransException;

    int insert(User record) throws TransException;

    int insertSelective(User record) throws TransException;

    User selectByPrimaryKey(String id) throws TransException;

    int updateByPrimaryKeySelective(User record) throws TransException;

    int updateByPrimaryKey(User record) throws TransException;

    /**
     * 登录
     *
     * @param loginUser 登录参数
     * @return token
     * @throws TransException 异常处理
     */
    String login(LoginUser loginUser) throws TransException;

    /**
     * token解析查询用户
     *
     * @param token token
     * @return 用户信息
     * @throws TransException 异常
     */
    User findUser(String token) throws TransException;

    /**
     * 修改用户的基本信息
     *
     * @param basicUserDTO 修改信息
     * @throws TransException 异常
     */
    void updateByBasicUser(BasicUserDTO basicUserDTO) throws TransException;

    /**
     * 图片上传
     *
     * @param file  图片
     * @param token token
     * @return 图片名称
     * @throws TransException 异常
     */
    String imgUpload(MultipartFile file, String token) throws TransException;

    /**
     * 修改密码
     *
     * @param token       token
     * @param passwordDTO 原密码和现在的密码
     * @throws TransException 异常
     */
    void changePassword(String token, PasswordDTO passwordDTO) throws TransException;

    /**
     * 获取信息
     *
     * @param userId 用户id
     * @return Optional
     * @throws TransException 异常
     */
    UserVO get(String userId) throws TransException;

    /**
     * 列表查询 全部
     *
     * @return list
     * @throws TransException 异常
     */
    List<UserListVO> listInAll() throws TransException;

    /**
     * 条件查询
     *
     * @param userPageDTO userpage
     * @return list
     * @throws TransException 异常
     */
    PageInfo<UserVO> findByCondition(UserPageDTO userPageDTO) throws TransException;

    /**
     * id查询用户
     *
     * @param userId 用户id
     * @return USERVO
     * @throws TransException 异常
     */
    UserVO findByPrimaryKey(String userId) throws TransException;

    /**
     * 根据id判断是添加还是修改
     *
     * @param userDTO user
     * @throws TransException 异常
     */
    void saveOrUpdate(UserDTO userDTO) throws TransException;

    /**
     * 删除用户
     *
     * @param userId 用户id
     * @throws TransException 异常
     */
    void delete(String userId) throws TransException;

    /**
     * 根据建筑id查询所有的用户
     *
     * @param bids 建筑id查询
     * @return 用户id
     * @throws TransException 异常
     */
    List<String> listByBuildingIds(List<String> bids) throws TransException;

    /**
     * 用户密码重置
     *
     * @param userId 用户id
     * @throws TransException 异常
     */
    void resetPassword(String userId) throws TransException;
}
