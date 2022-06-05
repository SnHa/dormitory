package com.atsun.dormitory.config;

import com.atsun.dormitory.utils.AuthFilter;
import com.atsun.dormitory.utils.CustomRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author SH
 */
@Configuration
public class ShiroConfig {

    @Bean
    public Realm getRealm() {
        return new CustomRealm();
    }

    @Bean
    public DefaultWebSecurityManager getSecurityManager(Realm getRealm) {

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(getRealm);

        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager getSecurityManager) {

        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(getSecurityManager);

        Map<String, Filter> filters = new HashMap<>(10);
        filters.put("auth", new AuthFilter());
        factoryBean.setFilters(filters);

        // ShrioFilterFactroy 拦截控制
        HashMap<String, String> filterMap = new HashMap<>(10);
        //配置路径过滤器 anthc表示需要登录后才能进入
        filterMap.put("/user/**", "auth");
        filterMap.put("/permission/**","auth");
        filterMap.put("/building/**","auth");
        filterMap.put("/depart/**","auth");
        filterMap.put("/faculty/**","auth");
        filterMap.put("/leave/**","auth");
        filterMap.put("/log/**","auth");
        filterMap.put("/notice/**","auth");
        filterMap.put("/person/**","auth");
        filterMap.put("/repair/**","auth");
        filterMap.put("/role/**","auth");
        filterMap.put("/room/**","auth");
        filterMap.put("/student/**","auth");

        factoryBean.setFilterChainDefinitionMap(filterMap);

        return factoryBean;
    }

    /**
     * 开启注解
     *
     * @param getSecurityManager 安全管理器
     * @return authorizationAttributeSourceAdvisor
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager getSecurityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(getSecurityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
