package com.erp.jwt.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Configuration
public class DBConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource cartDataSource() {
        return DataSourceBuilder.create().build();
    }
}
