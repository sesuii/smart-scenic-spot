package com.smartscenicspot;

import com.smartscenicspot.dto.AdminDto;
import com.smartscenicspot.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/31 16:31
 **/

@SpringBootTest
public class AdminServiceTest {

    @Resource
    AdminService adminService;

    @Test
    void test1() {
        AdminDto adminInfo = adminService.getAdminInfo();
        assert adminInfo != null;
        System.out.println(adminInfo);
    }
}
