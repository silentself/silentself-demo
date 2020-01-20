package com.atdyl.elasticsearch.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * @author Dong YL
 * @version V1.0 2020/1/11 18:50
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "elasticsearch", type = "user_info", shards = 1, replicas = 0, refreshInterval = "-1")
public class UserInfo {

    @Id
    private String id;
    @Field
    private String firstName;
    @Field
    private String lastName;
    @Field()
    private Integer age;
    @Field
    private String about;
}
