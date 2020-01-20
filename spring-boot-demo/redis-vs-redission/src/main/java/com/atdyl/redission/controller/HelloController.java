package com.atdyl.redission.controller;

import lombok.extern.slf4j.Slf4j;
import org.redisson.RedissonMultiLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author Dong YL
 * @version V1.0 2019/12/31 17:42
 */
@RestController
@RequestMapping("/hello")
@Slf4j
public class HelloController {

    /**
     * 直接注入RedissonClient就可以直接使用.
     */
    @Resource
    private RedissonClient redissonClient;

    @Autowired
    ApplicationArguments applicationArguments;


    @GetMapping
    public String hello() throws Exception {
        run(applicationArguments);

        return "OK";
    }


    public void run(ApplicationArguments args) throws Exception {
        log.info("spring boot run");

        //创建所
        RLock helloLock = redissonClient.getLock("hello");
        RLock helloLock2 = redissonClient.getLock("hello2");
        RLock helloLock3 = redissonClient.getLock("hello3");

        RedissonMultiLock redissonMultiLock = new RedissonMultiLock(helloLock, helloLock2, helloLock3);

        redissonMultiLock.lock();

        Thread.sleep(1000 * 2000);

        //加锁
        boolean b = helloLock.tryLock(5,  TimeUnit.SECONDS);
        try {


            log.info("locked");
            Thread.sleep(1000 * 2);
        } finally {
            //释放锁
            helloLock.unlock();
        }
        log.info("finished");
    }

    @GetMapping("/2")
    public String hello2() throws Exception {
        run(applicationArguments);

        return "OK";
    }


}
