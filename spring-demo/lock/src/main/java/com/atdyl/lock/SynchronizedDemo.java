package com.atdyl.lock;

import cn.hutool.core.collection.CollUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Dong YL
 * @version V1.0 2020/1/17 14:22
 */
public class SynchronizedDemo {

    public static void main(String[] args) {

        String s = null;
        Set<String> set = new HashSet<>();

        set.add(s);

        System.err.println(set);


        System.err.println(CollUtil.isNotEmpty(CollUtil.removeNull(set)));

    }
}


