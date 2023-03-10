package com.smartscenicspot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 */
@SpringBootApplication
@EnableJpaAuditing
@EnableNeo4jRepositories
@EnableTransactionManagement
public class SmartScenicSpotApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartScenicSpotApplication.class, args);
    }

}
