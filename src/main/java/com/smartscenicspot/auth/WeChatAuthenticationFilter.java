package com.smartscenicspot.auth;

import com.alibaba.fastjson.JSONObject;
import com.smartscenicspot.constant.SecurityConstant;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.client.RestTemplate;
import reactor.util.annotation.Nullable;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 微信用户登录认证
 * 用 code 换取 openid 等验证信息
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/6 20:46
 **/
public class WeChatAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final String codeParameter = "code";

    private final String WECHAT_AUTH_URL = "https://api.weixin.qq.com/sns/jscode2session?" +
            "appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

    @Resource
    private RestTemplate restTemplate;
    /**
     * 只限 Post 请求
     */
    private boolean postOnly = true;

    @Resource
    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    public WeChatAuthenticationFilter() {
        super(new AntPathRequestMatcher("/api/auth/wechatlogin","POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        if(postOnly && !"POST".equals(request.getMethod())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        String code = this.obtainCode(request);

        if(code == null) {
            code = "";
        }
        String url = String.format(WECHAT_AUTH_URL, SecurityConstant.WECHAT_APPID, SecurityConstant.SECRET_KEY,
                code, SecurityConstant.WECHAT_GRANT_TYPE);
        String jsonData = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = JSONObject.parseObject(jsonData);
        String openid = jsonObject.getString("openid");
        String sessionKey = jsonObject.getString("session_key");
        if(openid == null || sessionKey == null) {
            throw new AuthenticationServiceException("微信授权失败");
        }
        WeChatAuthenticationToken token = new WeChatAuthenticationToken(openid, sessionKey);

        return this.getAuthenticationManager().authenticate(token);
    }

    @Nullable
    protected String obtainCode(HttpServletRequest request) {
        return request.getParameter(this.codeParameter);
    }

    public void setDetails(HttpServletRequest request, WeChatAuthenticationToken token) {
        token.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }
}
