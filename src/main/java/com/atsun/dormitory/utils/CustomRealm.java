package com.atsun.dormitory.utils;


import com.atsun.dormitory.exception.TransException;
import com.atsun.dormitory.po.User;
import com.atsun.dormitory.service.RolePermissionService;
import com.atsun.dormitory.service.UserService;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author: SH
 * @create: 2021-11-24 10:06
 **/
@Log4j2
@Component
public class CustomRealm extends AuthorizingRealm {

    private UserService userService;
    private RolePermissionService rolePermissionService;

    @Autowired
    public void setRolePermissionService(RolePermissionService rolePermissionService) {
        this.rolePermissionService = rolePermissionService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }


    /*用于当前登录用户授权，角色与权限自动调用*/

    @SneakyThrows
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        log.info("==========开始授权=========");

        String token = (String) principalCollection.getPrimaryPrincipal();
        String id = JwtUtil.decode(token);

        // 查询用户的角色，授权返回AuthorizationInfo类型
            return rolePermissionService.authorizationInfo(id);

    }

    /*获取授权信息
    * authenticationToken获取省份验证信息
    * */

    @SneakyThrows
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        log.info("=======开始认证=======");

        JwtToken token = (JwtToken) authenticationToken;
        String userId = JwtUtil.decode(token.getToken());
        log.info("解析token： id=" + userId);
        User user = userService.selectByPrimaryKey(userId);
        if (user == null) {
            throw new UnknownAccountException("账户不存在");
        }
        return new SimpleAuthenticationInfo(token.getPrincipal(), token.getCredentials(), user.getName());
    }
}
