package com.smartscenicspot.service.Impl;

import com.smartscenicspot.constant.RedisConstant;
import com.smartscenicspot.constant.SecurityConstant;
import com.smartscenicspot.db.pgql.entity.InterestTag;
import com.smartscenicspot.db.pgql.entity.User;
import com.smartscenicspot.db.pgql.repository.AttractionRepository;
import com.smartscenicspot.db.pgql.repository.InterestTagRepository;
import com.smartscenicspot.db.pgql.repository.UserRepository;
import com.smartscenicspot.dto.InterestTagDto;
import com.smartscenicspot.mapper.InterestTagMapper;
import com.smartscenicspot.mapper.UserMapper;
import com.smartscenicspot.service.UserService;
import com.smartscenicspot.utils.JwtUtil;
import com.smartscenicspot.utils.WeChatUtil;
import com.smartscenicspot.vo.PageVo;
import com.smartscenicspot.vo.UserVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/15 16:08
 **/

@Service
public class UserServiceImpl implements UserService {
    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Resource
    UserRepository userRepository;

    @Resource
    InterestTagRepository interestTagRepository;
    private final AttractionRepository attractionRepository;

    public UserServiceImpl(AttractionRepository attractionRepository) {
        this.attractionRepository = attractionRepository;
    }

    /**
     * 从 Security 中拿出经过 WeChatAuthenticationProvider 验证的 openid
     * 生成 Token 存入 Redis 并返回给客户端
     *
     * @return token
     */
    public Map<String, String> toWeChatLogin(String code) {
        // ==== 测试用户登录为 test 前缀，不获取 openid ======
        String openid = code.startsWith("test_") ? code : WeChatUtil.jscode2session(code);
        if (openid == null) {
            return null;
        }
        // 微信用户如果不存在默认创建
        User user = userRepository.findByOpenid(openid).orElse(null);
        if (user == null) {
            user = userRepository.save(new User(openid, (byte) 1));
        }
        String token = JwtUtil.createJWT(openid);
        redisTemplate.opsForValue().set(RedisConstant.USER_PREFIX + openid,
                token, RedisConstant.TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
        return Map.of(SecurityConstant.SECURITY_HEADER,
                SecurityConstant.SECURITY_HEADER_PREFIX + token);
    }

    @Override
    @Transactional(value = "pgqlTransactionManger")
    public UserVo getUserVo(String openid) {
        User user = userRepository.findByOpenid(openid).orElse(null);
        // FIXME
        return UserMapper.INSTANCE.toVo(user);
    }

    @Override
    @Transactional(value = "pgqlTransactionManger")
    public UserVo updateUserInfo(UserVo userVo) {
        String openid = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByOpenid(openid).orElse(null);
        if(user == null) {
            return null;
        }
        User updated = UserMapper.INSTANCE.partialUpdate(userVo, user);
        return UserMapper.INSTANCE.toVo(userRepository.save(updated));
    }

    @Override
    public List<InterestTagDto> getAllTags() {
        List<InterestTag> tags = interestTagRepository.findAll();
        return InterestTagMapper.INSTANCE.toDtoList(tags);
    }

    @Override
    public User getUserByOpenid(String openid) {
        return userRepository.findByOpenid(openid).orElse(null);
    }

    @Override
    @Transactional(value = "pgqlTransactionManger")
    public PageVo<?> analyseUserInfo(int page, int size) {
        Page<User> users = userRepository.findAll(PageRequest.of(page, size, Sort.by(
                Sort.Order.desc("gmtModified"))));
        return PageVo.builder()
                .data(Collections.singletonList(UserMapper.INSTANCE.toDtoList(users.getContent())))
                .totalElements(users.getTotalElements())
                .totalPages(users.getTotalPages())
                .build();
    }

}
