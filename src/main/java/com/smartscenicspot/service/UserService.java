package com.smartscenicspot.service;

import com.smartscenicspot.domain.User;
import com.smartscenicspot.repository.UserRepository;
import com.smartscenicspot.vo.WeChatUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/6 21:26
 **/
@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails getByOpenid(String openid) {
        User user = userRepository.getUserByOpenid(openid);
        return WeChatUserVo.builder()
                .openid(user.getOpenid())
                .build();
    }
}
