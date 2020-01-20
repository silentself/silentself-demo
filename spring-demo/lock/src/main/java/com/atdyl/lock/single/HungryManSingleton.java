package com.atdyl.lock.single;

/**
 * 饿汉单例
 *
 * @author Dong YL
 * @version V1.0 2020/1/19 11:52
 */
public class HungryManSingleton {

    private static HungryManSingleton instance = new HungryManSingleton();

    private HungryManSingleton() {
    }

    public static HungryManSingleton getInstance() {
        return instance;
    }
}
