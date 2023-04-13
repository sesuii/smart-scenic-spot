package com.smartscenicspot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 */
@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
@EnableScheduling
public class SmartScenicSpotApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmartScenicSpotApplication.class, args);
    }

}
