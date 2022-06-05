package com.atsun.dormitory.service.impl;

import com.atsun.dormitory.convert.Impl.UserConvert;
import com.atsun.dormitory.convert.Impl.UserRoleConvent;
import com.atsun.dormitory.dao.BuildingMapper;
import com.atsun.dormitory.dao.UserRoleMapper;
import com.atsun.dormitory.dto.*;
import com.atsun.dormitory.enums.TransCode;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.UserRole;
import com.atsun.dormitory.service.LogService;
import com.atsun.dormitory.service.UserRoleService;
import com.atsun.dormitory.socket.WebSocket;
import com.atsun.dormitory.utils.JwtUtil;
import com.atsun.dormitory.utils.MD5Util;
import com.atsun.dormitory.utils.SnowFlake;
import com.atsun.dormitory.vo.UserListVO;
import com.atsun.dormitory.vo.UserVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.atsun.dormitory.po.User;
import com.atsun.dormitory.dao.UserMapper;
import com.atsun.dormitory.service.UserService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author: SH
 * @create: 2021-12-01 14:36
 **/
@Log4j2
@Transactional(readOnly = true, rollbackFor = Exception.class)
@Service
public class UserServiceImpl implements UserService {

    private UserRoleService userRoleService;
    private LogService logService;

    @Autowired
    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    @Autowired
    public void setUserRoleService(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @Resource
    private UserMapper userMapper;

    @Resource
    private BuildingMapper buildingMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(String id) throws TransException {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(User record) throws TransException {
        return userMapper.insert(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertSelective(User record) throws TransException {
        return userMapper.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(String id) throws TransException {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKeySelective(User record) throws TransException {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKey(User record) throws TransException {
        return userMapper.updateByPrimaryKey(record);
    }

    @Override
    public String login(LoginUser loginUser) throws TransException {

        String loginName = loginUser.getLoginName();
        String password = loginUser.getPassword();
        String redirectUrl = loginUser.getRedirectUrl();

        UserVO userVO = userMapper.selectByName(loginName);
        if (userVO == null) {
            throw new TransException(TransCode.CUSTOM_EXCEPTION_MSG, "用户不存在");
        }
        if (!userVO.getPassword().equals(MD5Util.md5(password))) {
            throw new TransException(TransCode.CUSTOM_EXCEPTION_MSG, "密码错误");
        }

        return redirectUrl + "#/token=" + JwtUtil.generateToken(userVO.getId());
    }

    @Override
    public User findUser(String token) throws TransException {

        String userId = JwtUtil.decode(token);
        return userMapper.selectByPrimaryKey(userId);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateByBasicUser(BasicUserDTO basicUserDTO) throws TransException {
        userMapper.updateByPrimaryKeySelective(UserConvert.INSTANCE.BasicUserDtoToUser(basicUserDTO));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String imgUpload(MultipartFile file, String token) throws TransException {

        if (file.isEmpty()) {
            throw new TransException(TransCode.NULL_FILE);
        }

        String filename = file.getOriginalFilename();
        String suffixName = filename.substring(filename.lastIndexOf("."));

        String filePath = "E://uploadImg//";
        filename = MD5Util.md5(filename) + suffixName;
        File dest = new File(filePath + filename);
        // TODO 修改文件时候删除原来的图片
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            String userId = JwtUtil.decode(token);
            User user = userMapper.selectByPrimaryKey(userId);
            user.setIcon(filename);
            userMapper.updateByPrimaryKeySelective(user);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filename;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changePassword(String token, PasswordDTO passwordDTO) throws TransException {

        User user = userMapper.findPasswordById(JwtUtil.decode(token));
        if (!user.getPassword().equals(MD5Util.md5(passwordDTO.getOldPassword()))) {
            throw new TransException(TransCode.CUSTOM_EXCEPTION_MSG, "原密码错误");
        }
        user.setPassword(MD5Util.md5(passwordDTO.getCurrent1()));
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public UserVO get(String userId) throws TransException {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            throw new TransException(TransCode.CUSTOM_EXCEPTION_MSG, "用户不存在");
        }
        UserVO userVO = UserConvert.INSTANCE.poTOUserVO(user);
        userVO.setBuilding(buildingMapper.selectByPrimaryKey(user.getBuildingId()));
        userVO.setUserRoleId(userRoleMapper.findRoleIds(userId));
        userVO.setLeader(userMapper.findNameByPid(user.getPId()));
        userVO.setIsOnLine(WebSocket.WEB_SOCKET_SET.containsKey(userVO.getLoginName()));
        return userVO;
    }

    @Override
    public List<UserListVO> listInAll() throws TransException {
        return userMapper.findAll();
    }

    @Override
    public PageInfo<UserVO> findByCondition(UserPageDTO userPageDTO) throws TransException {

        // TODO 多对多查询，先获取符合条件的id，否则pageInfo中的数据会出错
        List<String> list = userMapper.findByCondition(userPageDTO.getLeaderId(), userPageDTO.getRealName(),
                userRoleMapper.findUserIds(userPageDTO.getUserRoleId()),
                userPageDTO.getBuildingId());

        if (list.isEmpty()) {
            return new PageInfo<>(new ArrayList<>());
        }
        if ((int) Math.ceil((double) list.size() / userPageDTO.getRows()) < userPageDTO.getPage()) {
            userPageDTO.setPage(userPageDTO.getPage() - 1);
        }
        PageHelper.startPage(userPageDTO.getPage(), userPageDTO.getRows());
        List<UserVO> userVOList = userMapper.findByUserIds(list);
        // TODO 设置在线 根据用户名判断是否与websocket建立连接
        userVOList.forEach(userVO -> {
            userVO.setIsOnLine(WebSocket.WEB_SOCKET_SET.containsKey(userVO.getLoginName()));
            userVO.setUserRole(userRoleMapper.findRole(userVO.getId()));
            try {
                userVO.setBuilding(buildingMapper.selectByPrimaryKey(userVO.getBuildingId()));
                userVO.setLeader(userMapper.findNameByPid(userVO.getLeaderId()));
            } catch (TransException e) {
                e.printStackTrace();
            }
        });
        return new PageInfo<>(userVOList);
    }

    @Override
    public UserVO findByPrimaryKey(String userId) throws TransException {
        List<String> roleIds = userRoleService.findRoleIds(userId);
        UserVO byUserId = userMapper.findByUserId(userId);
        byUserId.setUserRoleId(roleIds);
        return byUserId;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(UserDTO userDTO) throws TransException {
        User user = UserConvert.INSTANCE.dtoToPo(userDTO);
        if (userDTO.getId() == null) {
            user.setId(String.valueOf(SnowFlake.nextId()));
            user.setPassword(MD5Util.md5("123456"));
            user.setCreateTime(new Date());
            userMapper.insert(user);

        } else {
            user.setUpdateTime(new Date());
            userMapper.updateByPrimaryKeySelective(user);
            // TODO 删除原有角色
            userRoleMapper.deleteByUserId(user.getId());
        }
        // TODO 添加角色
        if (!userDTO.getUserRoleId().isEmpty()) {
            List<UserRole> userRoles = new ArrayList<>();
            userDTO.getUserRoleId().forEach(
                    roleId -> userRoles.add(UserRoleConvent.INSTANCE.ToUserRole(
                            /*StringUtils.isBlank(userDTO.getId()) ? String.valueOf(SnowFlake.nextId()) : userDTO.getId()*/
                            String.valueOf(SnowFlake.nextId()),
                            user.getId(), roleId, new Date(), new Date())));
            userRoleMapper.batchInsert(userRoles);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String userId) throws TransException {
        // TODO 删用户除角色表
        userRoleMapper.deleteByUserId(userId);
        // TODO 删除该用户日志
        logService.deleteByUserId(userId);
        //  TODO 删除用户
        userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public List<String> listByBuildingIds(List<String> bids) throws TransException {
        return userMapper.listByBuildingIds(bids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resetPassword(String userId) throws TransException {
        User user = userMapper.selectByPrimaryKey(userId);
        user.setPassword(MD5Util.md5("123456"));
        this.updateByPrimaryKeySelective(user);
    }

}
