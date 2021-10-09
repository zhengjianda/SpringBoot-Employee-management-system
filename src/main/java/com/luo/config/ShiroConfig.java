package com.luo.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean =new ShiroFilterFactoryBean();

        // 添加shiro的内置过滤器
        /*
         *  anon:无需认证就可以访问
         *  authc 必须认证了才能访问
         *  user: 必须拥有记住我功能才能用
         *  perms 拥有对某个资源的权限才能访问
         *  role 拥有某个角色权限才能访问
         *
         * */
        //登录拦截
        Map<String,String> filterMap =new LinkedHashMap<>();

        //注销
        filterMap.put("/logout","logout");

        //登录拦截
        filterMap.put("/emp","authc");
        filterMap.put("/emps","authc");
        filterMap.put("/delemp/*","authc");
        filterMap.put("/emp/*","authc");

        bean.setFilterChainDefinitionMap(filterMap);


        bean.setLoginUrl("/toLogin");
        bean.setSecurityManager(defaultWebSecurityManager);
        return bean;
    }

    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager =new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }


    //创建realm对象，需要自定义类
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    //整合ShiroDialect  用来整合shiro thymeleaf
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}