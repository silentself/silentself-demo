package com.atdyl.lock.single;


/**
 * double check的单例
 * ①为什么要加volatile
 * new DoubleCheckSingleton()的过程可以分为三部
 * a获取对象地址；
 * b在对象地址上初始化对象；
 * c将初始化的对象引用指向对象地址；
 * JVM会对bc进行指令，会出现a-b-c或者a-c-b两种情况，如果某个线程正在按着a-c-b过程创建对象，刚走到c，还没进行b，
 * 此时另一个线程进来此时最外层if判断为false，直接return出去的对象是还没有初始化完成的，就可能会出现空指针
 * <p>
 * ②为什么要进行两次判断
 * 方法第一次被调用时：加入同时进来多个线程，第一重if判断都为空，都进入第一重if语句，
 * 由于存在锁的机制，有且只有一个线程进入lock语句块，（此时第二重判断有或没有都不影响）并创建对象，
 * 当这个线程执行完后，其他线程开始进入lock语句块，如果此时没有第二重判断，所有线程就会都去创建一遍对象
 *
 * @author Dong YL
 * @version V1.0 2020/1/19 11:03
 */
public class DoubleCheckSingleton {

    private volatile static DoubleCheckSingleton single;

    private String name;

    private DoubleCheckSingleton(String name) {
        this.name = name;
    }

    public static DoubleCheckSingleton getSingle() {
        if (single == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (single == null) {
                    String name = "我是单例";
                    single = new DoubleCheckSingleton(name);
                }
            }
        }
        return single;
    }
}
