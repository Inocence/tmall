package com.baiyu.tmall.secure;

import com.baiyu.tmall.pojo.User;
import com.baiyu.tmall.service.SysPermissionService;
import com.baiyu.tmall.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Set;

@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Value("tmall.encrypt")
    private String encrypt;

    @Autowired
    private UserService userService;

    @Autowired
    private SysPermissionService sysPermissionService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.getByName(token.getUsername());
        if(user == null) {
            return null;
        }

        log.info("doGetAuthenticationInfo");
        return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(encrypt), getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection){
        User user = (User) principalCollection.getPrimaryPrincipal();
        Set<String> names = sysPermissionService.getNameByUserId(user.getUserId());

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(names);

        log.info("doGetAuthorizationInfo");
        System.out.println(names);
        return simpleAuthorizationInfo;
    }
}
