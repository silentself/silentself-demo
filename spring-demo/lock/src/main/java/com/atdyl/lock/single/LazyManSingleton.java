package com.atdyl.lock.single;

/**
 * 懒汉式，去掉synchronized关键字就是线程不安全的单例，生产上必然不会使用，就不再展示
 *
 * @author Dong YL
 * @version V1.0 2020/1/19 11:53
 */
public class LazyManSingleton {

    private static LazyManSingleton instance;

    private LazyManSingleton() {
    }

    public synchronized static LazyManSingleton getInstance() {
        if (instance == null) {
            instance = new LazyManSingleton();
        }
        return instance;
    }
}
