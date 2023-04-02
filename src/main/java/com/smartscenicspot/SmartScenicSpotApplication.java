package com.smartscenicspot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 */
@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
@EnableAsync
@EnableTransactionManagement
@EnableNeo4jRepositories(basePackages = { "com.smartscenicspot.neo4j.repo" })
@EnableJpaRepositories(basePackages = "com.smartscenicspot.repository")
public class SmartScenicSpotApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartScenicSpotApplication.class, args);
    }

}
