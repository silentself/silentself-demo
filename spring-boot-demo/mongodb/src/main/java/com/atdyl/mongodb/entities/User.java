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
 * @version V1.0 2020/1/11 15:28
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("userInfo")
public class User implements Serializable {

    private static final long serialVersionUID = 7652502254587682709L;

    @Id
    private Long id;

    private String username;

    private String nickname;

    private String desc;
}
