package com.smartscenicspot.auth;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 微信用户 Token
 *
 * @author <a href="mailto: sjiahui@gmail.com">songjiahui</a>
 * @since 2023/3/6 20:22
 **/
public class WeChatAuthenticationToken extends AbstractAuthenticationToken {

    private final Object principal;

    public WeChatAuthenticationToken(Object principal) {
        super((null));
        this.principal = principal;
        super.setAuthenticated(false);
    }

    public WeChatAuthenticationToken(Collection<? extends GrantedAuthority> authorities, Object principal) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
