package com.atdyl.lock.single;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Dong YL
 * @version V1.0 2020/1/19 13:17
 */
public class ContainerSingleton {
    private static Map<String, Object> objMap = new ConcurrentHashMap<>();

    private ContainerSingleton() {
    }

    public static void registerService(String key, Object instance) {
        if (!objMap.containsKey(key)) {
            objMap.put(key, instance);
        }
    }

    public static Object getService(String key) {
        return objMap.get(key);
    }

}
