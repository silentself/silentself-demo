package com.atdyl.mybatisplus.entities;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.mail.InternalMailUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dong YL
 * @version V1.0 2020/1/8 13:45
 */
public class A {
    /**
     * 判断该字符串是否为中文
     *
     * @param string -
     * @return -
     */
    public static boolean isChinese(String string) {
        int n = 0;
        for (int i = 0; i < string.length(); i++) {
            n = (int) string.charAt(i);
            if (!(19968 <= n && n < 40869)) {
                return false;
            }
        }
        return true;
    }


    /**
     * @param string -
     * @return -
     */
    public static List<Integer> spiltChinese(String string) {

        List<Integer> list = new ArrayList<>();
        int n = 0;
        boolean cn = true;
        boolean flag;
        for (int i = 0; i < string.length(); i++) {
            n = (int) string.charAt(i);
            flag = cn;
            cn = 19968 <= n && n < 40869;
            if (cn != flag) {
                list.add(i);
            }
        }

        return list;
    }


    public static void main(String[] args) {

    }

}
