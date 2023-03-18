package com.smartscenicspot;

import com.smartscenicspot.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SmartScenicSpotApplicationTests {

    @Resource
    AdminService adminService;
    @Test
    void contextLoads() {

    }


}
