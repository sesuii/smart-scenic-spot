package com.smartscenicspot;

import com.smartscenicspot.constant.SecurityConstant;
import com.smartscenicspot.dto.AdminDto;
import com.smartscenicspot.service.AdminService;
import com.smartscenicspot.vo.AdminVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/31 15:30
 **/

@SpringBootTest
public class AuthTest {

    @Resource
    AdminService adminService;

    @Test
    void test1() {
        // admin 注册
        AdminDto adminDto = AdminDto.builder()
                .avatar("https://img.zcool.cn/community/01dc8f5c105392a80121ab5d3634a9.jpg@2o.jpg" )
                .username("song")
                .name("sungjah")
                .introduction("庐山景区管理员")
                .roles(List.of("ADMIN"))
                .password("123456")
                .ip("100.66.108.73")
                .build();
        boolean saved = adminService.createAccount(adminDto);
        AdminDto adminDto1 = AdminDto.builder()
                .avatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif" )
                .username("admin")
                .name("admin")
                .roles(List.of("ADMIN"))
                .introduction("庐山景区管理员")
                .password("infinite")
                .ip("10.21.19.1")
                .build();
        boolean saved1 = adminService.createAccount(adminDto1);
        assert saved1;
    }

    @Test
    void loginTest() {
        AdminVo adminVo = AdminVo.builder()
                .username("song")
                .password("123456")
                .build();
        Map<String, String> token = adminService.toAdminLogin(adminVo);
        assert !token.isEmpty();
        System.out.println(token.get(SecurityConstant.SECURITY_HEADER));
    }
}
