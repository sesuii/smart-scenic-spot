package com.smartscenicspot.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * timescaledb 数据源配置【postgresql 2】
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/4/9 1:30
 **/
@Configuration
@EnableJpaRepositories(
        basePackages = "com.smartscenicspot.db.timescaledb",
        transactionManagerRef = "timescaleTransactionManger",
        entityManagerFactoryRef = "timescaleEntityMangerFactory"
)
@EnableTransactionManagement
public class TimescaledbConfig {

    @Bean(name = "timescaledb")
    @ConfigurationProperties(prefix = "spring.datasource2")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "timescaleTransactionManger")
    public JpaTransactionManager pgqlTransactionManager(
            @Qualifier("timescaleEntityMangerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean
    ) throws Exception {
        return new JpaTransactionManager(entityManagerFactoryBean.getObject());
    }

    @Bean(name = "timescaleEntityMangerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(@Qualifier("timescaledb") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPackagesToScan("com.smartscenicspot.db.timescaledb");
        entityManagerFactoryBean.setPersistenceProvider(new HibernatePersistenceProvider());
        return entityManagerFactoryBean;
    }
}