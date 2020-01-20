package com.atdyl.webflux.pojo;

public enum SexEnum {

    MALE(1, "男"),
    FEMALE(2, "女");

    SexEnum(int code, String sex) {
        this.code = code;
        this.sex = sex;
    }

    public static SexEnum getSexEnum(int code) {
        SexEnum[] values = SexEnum.values();

        for (SexEnum value : values) {
            if (value.code == code) {
                return value;
            }
        }
        return null;
    }

    private int code;

    private String sex;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
