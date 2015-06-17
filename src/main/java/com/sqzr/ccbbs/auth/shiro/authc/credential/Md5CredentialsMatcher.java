package com.sqzr.ccbbs.auth.shiro.authc.credential;

import com.sqzr.ccbbs.auth.entity.CcMember;
import com.sqzr.ccbbs.core.util.PasswordUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

/**
 * Created by weiyang on 2015/6/18.
 */
public class Md5CredentialsMatcher extends HashedCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        Object _principals = info.getPrincipals().getPrimaryPrincipal();
        if (!(_principals instanceof CcMember)) {
            return false;
        }
        CcMember member = (CcMember) _principals;
        // 用户输入的密码
        String tokenCredentials = PasswordUtil.encrypt(new String((char[]) token.getCredentials()), member.getSalt());
        // 数据库中的密码
        String accountCredentials = (String) info.getCredentials();
        return equals(tokenCredentials, accountCredentials);
    }
}
