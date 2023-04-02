package com.smartscenicspot.service;

import com.smartscenicspot.dto.InterestTagDto;
import com.smartscenicspot.pojo.User;
import com.smartscenicspot.vo.UserVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/6 21:26
 **/
@Service
public interface UserService {

    /**
     * 从 Security 中拿出经过 WeChatAuthenticationProvider 验证的 openid
     * 生成 Token 存入 Redis 并返回给客户端
     * @param code
     * @return token
     */
    Map<String, String> toWeChatLogin(String code);

    UserVo getUserVo(String openid);

    UserVo updateUserInfo(UserVo userVo);

    List<InterestTagDto> getAllTags();

    User getUserByOpenid(String openid);
}
