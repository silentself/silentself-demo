package com.atdyl.mongodb.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import com.atdyl.mongodb.entities.User;
import com.atdyl.mongodb.entities.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Dong YL
 * @version V1.0 2020/1/3 19:25
 */
@RestController
@RequestMapping("/mongo")
public class HelloController {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public HelloController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @PostMapping
    public Boolean save(@RequestBody UserInfo userInfo) {
        UserInfo insert = mongoTemplate.insert(userInfo);

        return Boolean.TRUE;
    }

    @PostMapping("/user")
    public Boolean saveUser(@RequestBody UserInfo userInfo) {
        User build = User.builder().build();

        BeanUtil.copyProperties(userInfo,build);

        User insert = mongoTemplate.insert(build);

        return Boolean.TRUE;
    }

    @GetMapping("/{key}/{value}")
    public List<UserInfo> get(@PathVariable("key") String key,
                              @PathVariable("value") String value) {

        UserInfo userInfo = UserInfo.builder().build();


        Map<String, Object> build = MapUtil.<String,Object>builder().put(key, value).build();
        BeanUtil.copyProperties(build,userInfo);

        Criteria regex = Criteria.where(key).regex(value);
        Query query = Query.query(regex);

        return mongoTemplate.find(query, UserInfo.class);
    }
}
