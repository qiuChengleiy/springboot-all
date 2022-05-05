package com.example.shiro.authentication;

import cn.hutool.system.UserInfo;
import com.example.shiro.dao.User;
import com.example.shiro.mock.UserMock;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

/**
 * @author qcl
 * @version 1.0
 * @date 2022/5/5 11:04 AM
 * @description
 */
public class CustomRealm extends AuthorizingRealm {

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 授权模块，获取用户角色和权限
     * @param token token
     * @return AuthorizationInfo 权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection token) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        String userId = JwtUtil.getUserId(token.toString());
        if(userId == null) {
            return simpleAuthorizationInfo;
        }

        String userRole = UserMock.getRoleById(userId);
        Set<String> role = new HashSet<>();
        role.add(userRole);
        simpleAuthorizationInfo.setRoles(role);
        simpleAuthorizationInfo.setStringPermissions(role);
        return simpleAuthorizationInfo;
    }

    /**
     * 用户认证
     *
     * @param authenticationToken 身份认证 token
     * @return AuthenticationInfo 身份认证信息
     * @throws AuthenticationException 认证相关异常
     */
    @Override
    protected SimpleAuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        String userId = JwtUtil.getUserId(token);
        if (StringUtils.isBlank(userId)) {
            throw new AuthenticationException("验证失败");
        }

        String userRole = UserMock.getRoleById(userId);
        User userBean = new User();
        userBean.setUserId(userId);
        userBean.setRole(userRole);
        if (!JwtUtil.verify(token, userBean)) {
            throw new AuthenticationException("token失效");
        }
        return new SimpleAuthenticationInfo(token, token, "shiroJwtRealm");
    }
}


