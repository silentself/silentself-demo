package com.atdyl.smartdocdemo.entities.vo;

import lombok.Data;

/**
 * @author Dong YL
 * @version V1.0 2019/12/20 10:50
 */
@Data
public class ResultVO {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 用户地址
     */
    private String userAddress;

    /**
     * 用户年龄
     */
    private int userAge;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * ipv6
     */
    private String ipv6;

    /**
     * 固定电话
     */
    private String telephone;
}
