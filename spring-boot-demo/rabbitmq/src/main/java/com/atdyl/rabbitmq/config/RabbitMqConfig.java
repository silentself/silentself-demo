package com.atdyl.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author Dong YL
 * @version V1.0 2020/1/2 14:32
 */
@Configuration
@Slf4j
public class RabbitMqConfig {

    //消息队列名称
    @Value("${rabbitmq.queue.msg}")
    private String msgQueueName;

    //用户队列名称
    @Value("${rabbitmq.queue.user}")
    private String userQueueName;

    @Bean
    public Queue createQueueMsg() {
        //创建字符串消息队列，boolean值代表是否持久化消息
        return new Queue(msgQueueName, Boolean.TRUE);
    }

    @Bean
    public Queue createQueueUser() {
        //创建用户消息队列，boolean值代表是否持久化消息
        return new Queue(userQueueName, Boolean.TRUE);
    }

//    @Bean
//    @Primary
//    public ConnectionFactory connectionFactory() {
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//        connectionFactory.setUsername("admin");
//        connectionFactory.setPassword("admin");
//        //设置virtualHost。
//        connectionFactory.setVirtualHost("/my_vhost");
//        //消息的确认机制（confirm）；
//        connectionFactory.setPublisherConfirms(true);
//        connectionFactory.setPublisherReturns(true);
//        //setCacheMode：设置缓存模式，共有两种，CHANNEL和CONNECTION模式。
//        //1、CONNECTION模式，这个模式下允许创建多个Connection，会缓存一定数量的Connection，每个Connection中同样会缓存一些Channel，
//        // 除了可以有多个Connection，其它都跟CHANNEL模式一样。
//        //2、CHANNEL模式，程序运行期间ConnectionFactory会维护着一个Connection，
//        // 所有的操作都会使用这个Connection，但一个Connection中可以有多个Channel，
//        // 操作rabbitmq之前都必须先获取到一个Channel，
//        // 否则就会阻塞（可以通过setChannelCheckoutTimeout()设置等待时间），
//        // 这些Channel会被缓存（缓存的数量可以通过setChannelCacheSize()设置）；
//        connectionFactory.setCacheMode(CachingConnectionFactory.CacheMode.CONNECTION);   //设置CONNECTION模式，可创建多个Connection连接
//        //设置每个Connection中缓存Channel的数量，不是最大的。操作rabbitmq之前（send/receive message等）
//        // 要先获取到一个Channel.获取Channel时会先从缓存中找闲置的Channel，如果没有则创建新的Channel，
//        // 当Channel数量大于缓存数量时，多出来没法放进缓存的会被关闭。
//        connectionFactory.setChannelCacheSize(10);
//        //单位：毫秒；配合channelCacheSize不仅是缓存数量，而且是最大的数量。
//        // 从缓存获取不到可用的Channel时，不会创建新的Channel，会等待这个值设置的毫秒数
//        //同时，在CONNECTION模式，这个值也会影响获取Connection的等待时间，
//        // 超时获取不到Connection也会抛出AmqpTimeoutException异常。
//        connectionFactory.setChannelCheckoutTimeout(600);
//
//        //仅在CONNECTION模式使用，设置Connection的缓存数量。
//        connectionFactory.setConnectionCacheSize(3);
//        //setConnectionLimit：仅在CONNECTION模式使用，设置Connection的数量上限。
//        connectionFactory.setConnectionLimit(10);
//        return connectionFactory;
//    }
//
//    @Bean
//    public ConnectionFactory provideConnectionFactory() {
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//        connectionFactory.setUsername("admin");
//        connectionFactory.setPassword("admin");
//        //设置virtualHost。
//        connectionFactory.setVirtualHost("/my_vhost");
//        //消息的确认机制（confirm）；
//        connectionFactory.setPublisherConfirms(true);
//        connectionFactory.setPublisherReturns(true);
//        //setCacheMode：设置缓存模式，共有两种，CHANNEL和CONNECTION模式。
//        //1、CONNECTION模式，这个模式下允许创建多个Connection，会缓存一定数量的Connection，每个Connection中同样会缓存一些Channel，
//        // 除了可以有多个Connection，其它都跟CHANNEL模式一样。
//        //2、CHANNEL模式，程序运行期间ConnectionFactory会维护着一个Connection，
//        // 所有的操作都会使用这个Connection，但一个Connection中可以有多个Channel，
//        // 操作rabbitmq之前都必须先获取到一个Channel，
//        // 否则就会阻塞（可以通过setChannelCheckoutTimeout()设置等待时间），
//        // 这些Channel会被缓存（缓存的数量可以通过setChannelCacheSize()设置）；
//        connectionFactory.setCacheMode(CachingConnectionFactory.CacheMode.CONNECTION);   //设置CONNECTION模式，可创建多个Connection连接
//        //设置每个Connection中缓存Channel的数量，不是最大的。操作rabbitmq之前（send/receive message等）
//        // 要先获取到一个Channel.获取Channel时会先从缓存中找闲置的Channel，如果没有则创建新的Channel，
//        // 当Channel数量大于缓存数量时，多出来没法放进缓存的会被关闭。
//        connectionFactory.setChannelCacheSize(10);
//        //单位：毫秒；配合channelCacheSize不仅是缓存数量，而且是最大的数量。
//        // 从缓存获取不到可用的Channel时，不会创建新的Channel，会等待这个值设置的毫秒数
//        //同时，在CONNECTION模式，这个值也会影响获取Connection的等待时间，
//        // 超时获取不到Connection也会抛出AmqpTimeoutException异常。
//        connectionFactory.setChannelCheckoutTimeout(600);
//
//        //仅在CONNECTION模式使用，设置Connection的缓存数量。
//        connectionFactory.setConnectionCacheSize(3);
//        //setConnectionLimit：仅在CONNECTION模式使用，设置Connection的数量上限。
//        connectionFactory.setConnectionLimit(10);
//        return connectionFactory;
//    }
//
//    @Bean
//    public RabbitTemplate rabbitTemplate(@Qualifier("provideConnectionFactory") ConnectionFactory connectionFactory) {
//        //客户端开启confirm模式
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMandatory(true);
//        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> log.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause));
//        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey, replyCode, replyText, message));
//        return rabbitTemplate;
//    }


}
