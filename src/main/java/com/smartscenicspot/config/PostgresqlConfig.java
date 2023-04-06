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
 * smart_scenic_spot 业务表数据源配置
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/4/9 1:19
 **/
@Configuration
@EnableJpaRepositories(
        basePackages = "com.smartscenicspot.db.pgql",
        transactionManagerRef = "pgqlTransactionManger",
        entityManagerFactoryRef = "pgqlEntityMangerFactory"
)
@EnableTransactionManagement
public class PostgresqlConfig {

    @Bean(name = "postgresql")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "pgqlTransactionManger")
    public JpaTransactionManager pgqlTransactionManager(
            @Qualifier("pgqlEntityMangerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean
    ) throws Exception {
        return new JpaTransactionManager(entityManagerFactoryBean.getObject());
    }

    @Bean(name = "pgqlEntityMangerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(@Qualifier("postgresql") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPackagesToScan("com.smartscenicspot.db.pgql");
        entityManagerFactoryBean.setPersistenceProvider(new HibernatePersistenceProvider());
        return entityManagerFactoryBean;
    }
}
