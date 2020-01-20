package com.atdyl.rabbitmq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Dong YL
 * @version V1.0 2020/1/2 14:31
 */
@SpringBootApplication
@EnableRabbit
public class RabbitMqApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitMqApplication.class,args);
    }
}
