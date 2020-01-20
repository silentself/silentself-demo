package com.atdyl.mongodb.entities;

import java.io.Serializable;

public enum SexEnum implements Serializable {

    MALE(1, "男"), FEMALE(2, "女");

    private Integer code;
    private String sex;

    SexEnum(Integer code, String sex) {
        this.code = code;
        this.sex = sex;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
