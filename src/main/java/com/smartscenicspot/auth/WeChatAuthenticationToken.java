package com.smartscenicspot.auth;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Collection;

/**
 * 微信用户 Token
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/6 20:22
 **/
public class WeChatAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 560L;
    private final String openid;

    private String sessionKey;

    public WeChatAuthenticationToken(String openid, String sessionKey) {
        super(null);
        this.openid = openid;
        this.sessionKey = sessionKey;
    }
    public WeChatAuthenticationToken(String openid, String sessionKey, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.openid = openid;
        this.sessionKey = sessionKey;
        this.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return sessionKey;
    }

    @Override
    public Object getPrincipal() {
        return this.openid;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        Assert.isTrue(!isAuthenticated, "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        super.setAuthenticated(false);
    }

    public void eraseCredentials() {
        super.eraseCredentials();
    }
}
