package com.smartscenicspot.config;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.neo4j.Neo4jAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.core.transaction.Neo4jTransactionManager;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Neo4j 数据源配置
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/4/9 1:33
 **/

@Configuration
@EnableNeo4jRepositories(
        basePackages = {"com.smartscenicspot.db.neo4j"},
        transactionManagerRef = "neo4jTransactionManager"
)
@EnableTransactionManagement
public class Neo4jConfig extends Neo4jAutoConfiguration {

    @Value("${spring.neo4j.uri}")
    private String neo4jUri;

    @Value("${spring.neo4j.username}")
    private String username;

    @Value("${spring.neo4j.password}")
    private String password;

    @Bean
    public Driver getConfiguration() {
        return GraphDatabase.driver(neo4jUri, AuthTokens.basic(username, password));
    }

    @Bean(name = "neo4jTransactionManager")
    public Neo4jTransactionManager neo4jTransactionManager() {
        return new Neo4jTransactionManager(getConfiguration());
    }
}
