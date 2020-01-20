package com.atdyl.rabbitmq.service.impl;

import com.atdyl.rabbitmq.entities.User;
import com.atdyl.rabbitmq.service.RabbitMqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Dong YL
 * @version V1.0 2020/1/2 14:39
 */
@Service
@Slf4j
public class RabbitMqServiceImpl implements RabbitTemplate.ConfirmCallback, RabbitMqService {

    //消息队列名称
    @Value("${rabbitmq.queue.msg}")
    private String msgRouting;

    //用户队列名称
    @Value("${rabbitmq.queue.user}")
    private String userRouting;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendMsg(String msg) {
        log.info("发送消息：【" + msg + "】");
        //设置回调
        rabbitTemplate.setConfirmCallback(this);
        //发送消息，通过msgRouting确定队列
        rabbitTemplate.convertAndSend(msgRouting, msg);

    }

    @Override
    public void sendUser(User user) {
        log.info("发送用户消息：【" + user + "】");
        //设置回调
        rabbitTemplate.setConfirmCallback(this);
        //发送消息，通过msgRouting确定队列
        rabbitTemplate.convertAndSend(userRouting, user);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            log.info("消息消费成功");
        } else {
            log.info("消息消费失败：" + cause);
        }
    }
}
