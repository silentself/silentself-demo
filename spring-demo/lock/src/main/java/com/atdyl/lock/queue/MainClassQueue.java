package com.atdyl.lock.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * ①队列的put和take是已经实现了lock块
 * ②生产日志消费是需要在后打印，生产时需要在前打印
 * ③LinkedBlockingDeque vs ArrayBlockingQueue
 * ④借助“queue.put()与queue.take()的偏序关系 TODO
 * ⑤put在队列容量不足时等待，take在队列为空时等待
 *
 * @author Dong YL
 * @version V1.0 2020/1/19 14:57
 */
public class MainClassQueue {

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<Integer> queue = new LinkedBlockingDeque<>(1);
//        BlockingQueue<String> queue = new ArrayBlockingQueue<>(1);
        GoodsBlockingQueue goodsBlockingQueue = new GoodsBlockingQueue(queue);

        for (int i = 0; i < 10; i++) {

            new Thread(goodsBlockingQueue::plus, "produce").start();
            new Thread(goodsBlockingQueue::sub, "consumer").start();

        }

        Thread.sleep(3*1000);

        System.err.println(Thread.activeCount());

        if (Thread.activeCount() > 2){
            Thread.sleep(2000*1000);
        }
    }

}


