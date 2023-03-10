package com.smartscenicspot;

import com.smartscenicspot.domain.Admin;
import com.smartscenicspot.service.AdminService;
import com.smartscenicspot.vo.AdminVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@SpringBootTest
class SmartScenicSpotApplicationTests {

    @Resource
    AdminService adminService;
    @Test
    void contextLoads() {

    }

    @Transactional
    @Test
    @Commit
    void test1() {
        AdminVo adminVo = AdminVo.builder()
                .account("13387992132")
                .password("123456")
                .build();
        Admin admin = adminService.createAccount(adminVo);
        System.out.println(admin);
        assert (admin != null);
    }

}
