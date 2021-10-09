package com.luo.config;

import com.luo.pojo.User;
import com.luo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;
    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=> 授权doGetAuthorizationInfo");

        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();

        //拿到当前登录的这个对象
        Subject subject= SecurityUtils.getSubject();


        return null;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了=> 认证doGetAuthenticationInfo");
        //连接真实的数据库
        UsernamePasswordToken userToken=(UsernamePasswordToken) token;
        User user=userService.queryUserByName(userToken.getUsername());
        System.out.println("user为"+user);
        //用户名认证
        if(user==null){ //没有这个人
            return null;
        }

        // 密码认证shiro做
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
