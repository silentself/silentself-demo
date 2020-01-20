package com.atdyl.elasticsearch.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.atdyl.elasticsearch.dao.UserDao;
import com.atdyl.elasticsearch.entities.UserInfo;
import com.atdyl.elasticsearch.service.IndexerService;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dong YL
 * @version V1.0 2020/1/11 18:39
 */
@RestController
public class HelloController {


    @Resource
    UserDao userDao;

    @Autowired
    IndexerService indexerService;

    @GetMapping("/user")
    public List<UserInfo> get() {
        Iterable<UserInfo> all = userDao.findAll();

        List<UserInfo> userInfos = CollUtil.newArrayList();

        all.forEach(userInfos::add);

        return userInfos;

    }

    @GetMapping("/user/{str}")
    public List<UserInfo> getBu(@PathVariable("str") String str) {
        Iterable<UserInfo> all = userDao.findAll();
        List<UserInfo> userInfos = CollUtil.newArrayList();

        all.forEach(userInfos::add);

        return userInfos;

    }

    @PostMapping("/user")
    public Boolean save(@RequestBody UserInfo userInfo) {
        UserInfo save = userDao.save(userInfo);
        return Boolean.TRUE;
    }


    @PostMapping("/bulkIndex")
    public void bulkIndex() {
        indexerService.in();
    }

    public static void main(String[] args) {
        UserInfo build = UserInfo.builder().id("111").age(null).build();
//        JSONObject jsonObject = new JSONObject(build, Boolean.TRUE);
        String s = JSONUtil.toJsonStr(new JSONObject(build, Boolean.TRUE));
        System.err.println(s);
    }


}
