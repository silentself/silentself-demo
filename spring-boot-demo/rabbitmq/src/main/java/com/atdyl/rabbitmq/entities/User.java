package com.atdyl.rabbitmq.entities;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Dong YL
 * @version V1.0 2020/1/2 14:38
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -663518932255461974L;

    private String username;

    private String nickname;
}
