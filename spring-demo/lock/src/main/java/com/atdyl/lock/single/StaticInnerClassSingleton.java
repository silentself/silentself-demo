package com.atdyl.lock.single;

/**
 * 静态内部类单例模式
 * 在《java并发编程实践》一书建议用静态内部类单例模式来替代DCL
 * <p>
 * 第一次加载StaticInnerClassSingleton类时并不会初始化instance，
 * 只有第一次调用getInstance方法时虚拟机加载StaticInnerClassSingletonHolder 并初始化instance，
 * 这样不仅能确保线程安全也能保证StaticInnerClassSingleton类的唯一性，所以推荐使用静态内部类单例模式
 *
 * @author Dong YL
 * @version V1.0 2020/1/19 11:58
 */
public class StaticInnerClassSingleton {

    private StaticInnerClassSingleton() {
    }

    private static StaticInnerClassSingleton getInstance() {
        return StaticInnerClassSingletonHolder.instance;
    }

    private static class StaticInnerClassSingletonHolder {

        private static final StaticInnerClassSingleton instance = new StaticInnerClassSingleton();
    }

}
