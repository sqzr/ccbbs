package com.sqzr.ccbbs.auth.shiro.realm;

import com.sqzr.ccbbs.auth.entity.CcMember;
import com.sqzr.ccbbs.auth.service.MemberService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static com.xiaoleilu.hutool.Log.warn;

/**
 * Created by weiyang on 2015/6/9.
 */
public class CCRealm extends AuthorizingRealm {

    @Qualifier("memberService")
    @Autowired
    private MemberService memberService;

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     * 获取登陆用户的对象Object principal = principalCollection.getPrimaryPrincipal()
     * 通过(PhrMember)principal.getId();获取需要授权用户的id
     * 在PhrMemberRole中查询用户拥有的身份
     * 在PhrMemberPermission中查询用户拥有的权限
     *
     * @return AuthorizationInfo
     * new SimpleAuthorizationInfo().setRoles(Set)
     * new SimpleAuthorizationInfo().setPermissions(Set)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证回调函数,登录时调用,在数据库查询有没有用户传来的用户名,如果存在则交给密码匹配器判断密码是否正确
     * 获取token UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
     * 获取用户输入的用户名 token.getUsername()
     *
     * @return AuthenticationInfo
     * new SimpleAuthenticationInfo(查询到的用户对象,密码,realmName)
     * @throws AuthenticationException 用户名或者密码错误
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = authenticationToken.getPrincipal().toString();
        CcMember member = memberService.findByUsername(username);
        if (member == null) {
            warn("未找到用户[{}]", username);
            throw new UnknownAccountException("登陆账号或密码不正确");
        }

        if ("notaudit".equals(member.getStatus())) {
            warn("账户未审核[{}}", username);
            throw new DisabledAccountException("账户未审核,请等待审核");
        }

        if ("lock".equals(member.getStatus())) {
            warn("账户锁定[{}]", username);
            throw new LockedAccountException("账户已锁定停用");
        }


        String hashedCredentials = member.getPassword();
        return new SimpleAuthenticationInfo(member, hashedCredentials, getName());
    }
}
