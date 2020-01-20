package com.atdyl.lock.single;

/**
 * 枚举单例
 * 默认枚举实例的创建是线程安全的，并且在任何情况下都是单例
 * 上述讲的几种单例模式实现中，有一种情况下他们会重新创建对象，那就是反序列化，将一个单例实例对象写到磁盘再读回来
 * 反序列化操作提供了readResolve方法，这个方法可以让开发人员控制对象的反序列化。在上述的几个方法示例中如果要杜绝单例对象被反序列化是重新生成对象，就必须加入如下方法：
 * private Object readResolve() throws ObjectStreamException{ return singleton; }
 * <p>
 * 枚举单例的优点就是简单，但是大部分应用开发很少用枚举，可读性并不是很高，不建议用。
 *
 * @author Dong YL
 * @version V1.0 2020/1/19 12:52
 */
public enum EnumSingleton {
    INSTANCE;

    public void doSomeThing() {
    }

}
