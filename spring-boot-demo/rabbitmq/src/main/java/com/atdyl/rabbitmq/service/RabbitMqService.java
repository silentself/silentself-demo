package com.atdyl.rabbitmq.service;

import com.atdyl.rabbitmq.entities.User;

public interface RabbitMqService {

    //发送字符串消息
    public void sendMsg(String msg);

    //发送用户消息
    public void sendUser(User user);
}
