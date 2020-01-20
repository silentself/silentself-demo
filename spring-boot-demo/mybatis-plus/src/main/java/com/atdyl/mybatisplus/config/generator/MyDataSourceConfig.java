package com.atdyl.mybatisplus.config.generator;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * @author Dong YL
 * @version V1.0 2020/1/4 15:38
 */
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
@Data
public class MyDataSourceConfig {

    private String url;

    private String username;

    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverName;
}
