package com.atdyl.elasticsearch.dao;

import com.atdyl.elasticsearch.entities.UserInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends ElasticsearchRepository<UserInfo,String> {



}
