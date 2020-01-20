package com.atdyl.lock.sync;


/**
 * ①多线程中while和if的区别
 * 同样在执行wait()方法之后，使用while在被唤醒之后会重新判断while中的条件是否成立，而if在被唤醒之后不再判断直接往下执行
 * <p>
 * ②关于多线程操作资源类锁对象的问题
 * 一般在操作资源类的方法写在资源类中，使用synchronized代码块所对象设置为this（synchronized关键字默认锁对象就是this）
 * 如果操作资源类的方法不在资源类中，锁对象只要统一即可（此次示例中操作资源类的方法就不在资源类中）
 * <p>
 * ③消费者生产者模式while判断条件
 * 如果是判断数值大小一定要注意消费者和生产者的判断数值中间要留有空间
 * <p>
 * ④notify和notifyAll
 * notify是唤醒一个等待锁对象的线程去获取对象锁，而notifyAll是唤醒所有的等待锁对象的线程去获取对象锁，
 * 此处若使用notify可能会出现死锁或线程饥饿
 * 死锁为重点：比如在使用notify时，当一个消费者唤醒的还是一个消费者，此时会发生死锁
 *
 * @author Dong YL
 * @version V1.0 2020/1/17 19:53
 */
public class MainClass {
    public static void sleep5s() {
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Object lock = new Object();
        for (int i = 0; i < 1000; i++) {
            new Thread(new ProducerSync(lock)).start();
            //sleep5s();
            new Thread(new ConsumerSync(lock)).start();
            new Thread(new ConsumerSync(lock)).start();
            //sleep5s();
            new Thread(new ProducerSync(lock)).start();
        }
    }

}
