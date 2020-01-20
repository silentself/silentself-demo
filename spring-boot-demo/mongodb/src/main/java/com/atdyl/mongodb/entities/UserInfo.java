package com.atdyl.mongodb.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author Dong YL
 * @version V1.0 2020/1/3 19:30
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("userInfo")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 4616364386227702709L;

    @Id
    private Long id;

    private String username;

    private String nickname;

    private SexEnum sex;

    private String desc;
}
