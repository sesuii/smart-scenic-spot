package com.smartscenicspot;

import com.smartscenicspot.service.AdminService;
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
    }

}
