package com.atdyl.rabbitmq.controller;

import com.atdyl.rabbitmq.entities.User;
import com.atdyl.rabbitmq.service.RabbitMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dong YL
 * @version V1.0 2020/1/2 14:52
 */
@RestController
public class RabbitProviderController {

    @Autowired
    RabbitMqService rabbitMqService;

    @GetMapping("/str")
    public String sendMsg(String str){

        rabbitMqService.sendMsg(str);

        return "OK";
    }

    @GetMapping("/user")
    public String sendMsg(User user){

        rabbitMqService.sendUser(user);

        return "OK";
    }
}
