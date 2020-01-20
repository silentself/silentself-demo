package com.atdyl.elasticsearch.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.atdyl.elasticsearch.entities.UserInfo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IndexerService {

    private static final String CAR_INDEX_NAME = "first_name";

    private static final String CAR_INDEX_TYPE = "last_name";

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    public long in() {
        int counter = 0;
        try {
            //判断索引是否存在
            if (!elasticsearchTemplate.indexExists(CAR_INDEX_NAME)) {
                elasticsearchTemplate.createIndex(CAR_INDEX_NAME);
            }
            Gson gson = new Gson();
            List<IndexQuery> queries = new ArrayList<>();
            List<UserInfo> userInfos = assembleTestData();
            for (UserInfo userInfo : userInfos) {
                IndexQuery indexQuery = new IndexQuery();
                indexQuery.setId(userInfo.getId());
                indexQuery.setSource(JSONUtil.toJsonPrettyStr(userInfo));
                indexQuery.setIndexName(CAR_INDEX_NAME);
                indexQuery.setType("");
                queries.add(indexQuery);
                //分批提交索引
                if (counter % 500 == 0) {
                    elasticsearchTemplate.bulkIndex(queries);
                    queries.clear();
                    System.out.println("bulkIndex counter : " + counter);
                }
                counter++;
            }
            //不足批的索引最后不要忘记提交
            if (queries.size() > 0) {
                elasticsearchTemplate.bulkIndex(queries);
            }
            elasticsearchTemplate.refresh(CAR_INDEX_NAME);
            System.out.println("bulkIndex completed.");
        } catch (Exception e) {
            System.out.println("IndexerService.bulkIndex e;" + e.getMessage());
            throw e;
        }
        return -1;
    }

    private List<UserInfo> assembleTestData() {
        ArrayList<UserInfo> userInfos = CollUtil.newArrayList();
        for (int i = 0; i < 10000; i++) {
            userInfos.add(UserInfo.builder()
                    .id(String.valueOf(i))
                    .about(i + "sssdh")
                    .age(i)
                    .firstName("admin" + i)
                    .lastName("user" + i)
                    .build()
            );
        }
        return userInfos;
    }

}
