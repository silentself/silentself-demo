package com.atdyl.webflux.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * @author Dong YL
 * @version V1.0 2020/1/2 18:11
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@Document(collection = "person")
public class User implements Serializable {
    private static final long serialVersionUID = 24327691829569710L;

    //性别
    private SexEnum sex;

    @Field("user_name")
    private String userName;


    private String note;
}
