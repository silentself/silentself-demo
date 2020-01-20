package com.atdyl.rabbitmq.service.impl;

import com.atdyl.rabbitmq.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author Dong YL
 * @version V1.0 2020/1/2 15:11
 */
@Service
@Slf4j
public class RabbitMessageReceiver {

    @RabbitListener(queues = {"${rabbitmq.queue.msg}"})
    public void receiveMsg(String msg) {
        log.info("收到消息：【" + msg + "】");
    }

    @RabbitListener(queues = {"${rabbitmq.queue.user}"})
    public void receiveUser(User user) {
        log.info("收到消息：【" + user + "】");
    }
}
