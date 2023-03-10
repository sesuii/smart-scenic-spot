package com.smartscenicspot.utils;

import com.alibaba.fastjson.JSONObject;
import com.smartscenicspot.constant.SecurityConstant;
import org.springframework.web.client.RestTemplate;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/12 18:27
 **/
public class WeChatUtil {
    private static RestTemplate restTemplate = new RestTemplate();


    private static final String WECHAT_AUTH_URL = "https://api.weixin.qq.com/sns/jscode2session?" +
            "appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";


    public static String jscode2session(String code) {
        String url = String.format(WECHAT_AUTH_URL, SecurityConstant.WECHAT_APPID,
                SecurityConstant.WECHAT_SECRET, code);
        String jsonData = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = JSONObject.parseObject(jsonData);
        String openid = jsonObject.getString("openid");
        String sessionKey = jsonObject.getString("session_key");
        if(openid == null || sessionKey == null) {
            return null;
        }
        return openid;
    }

    public static void setRestTemplate(RestTemplate restTemplate) {
        WeChatUtil.restTemplate = restTemplate;
    }
}
