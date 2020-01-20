package com.atdyl.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

/**
 * @author Dong YL
 * @version V1.0 2020/1/2 17:50
 */
@SpringBootApplication
//在WebFlux下驱动MongoDB的JPA接口
@EnableReactiveMongoRepositories(basePackages = "com.atdyl.webflux.repository")
public class WebFluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebFluxApplication.class, args);
    }
}
