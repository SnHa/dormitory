package com.atsun.dormitory.utils;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: SH
 * @create: 2021-11-24 10:34
 **/
@Log4j2
public class AuthFilter extends AuthenticatingFilter {

    /**
     * 验证身份token
     * @param servletRequest
     * @param servletResponse
     * @return 身份验证令牌
     * @throws Exception 异常
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        log.info("========================获取token");
        String token = getToken((HttpServletRequest) servletRequest);
        if (StringUtils.isBlank(token)) {
            return null;
        }
        log.info("token"+token);
        return new JwtToken(token);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        //获取请求token，
        String token = getToken((HttpServletRequest) servletRequest);
        if (StringUtils.isBlank(token)) {
            log.info("=====token不存在1======");
            return false;
        }
        log.info("=====token存在1======");
        return executeLogin(servletRequest, servletResponse);
    }

    /**
     * 判断用户是否已经登录
     *
     * @param request     服务请求
     * @param response    响应请求
     * @param mappedValue mappedValue
     * @return 判断请求是否是options 如果是false执行onAccessDenied
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        log.info("++++++++++++++"+((HttpServletRequest) request).getMethod().equals(RequestMethod.OPTIONS.name()));
        return ((HttpServletRequest) request).getMethod().equals(RequestMethod.OPTIONS.name());

    }

    public String getToken(HttpServletRequest request) {

        String token = request.getHeader("_ut");

        if (StringUtils.isBlank(token)) {
            token = request.getParameter("_ut");
        }

        return token;
    }
}
