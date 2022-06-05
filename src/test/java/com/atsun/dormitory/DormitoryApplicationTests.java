package com.atsun.dormitory;

import com.atsun.dormitory.dao.BuildingMapper;
import com.atsun.dormitory.dao.UserMapper;
import com.atsun.dormitory.dao.UserRoleMapper;
import com.atsun.dormitory.enums.TransCode;
import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.service.PermissionService;
import com.atsun.dormitory.service.RolePermissionService;
import com.atsun.dormitory.service.UserService;
import com.atsun.dormitory.utils.MD5Util;
import com.atsun.dormitory.vo.PermissionVO;
import com.atsun.dormitory.vo.UserVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DormitoryApplicationTests {

    @Resource
    private UserService userService;
    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private PermissionService permissionService;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private BuildingMapper buildingMapper;
    @Test
    void contextLoads() throws TransException {
        System.out.println(buildingMapper.finds());

    }

}
