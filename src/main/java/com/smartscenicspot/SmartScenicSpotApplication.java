package com.smartscenicspot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 */
@SpringBootApplication
@EnableJpaAuditing
public class SmartScenicSpotApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartScenicSpotApplication.class, args);
    }

}
