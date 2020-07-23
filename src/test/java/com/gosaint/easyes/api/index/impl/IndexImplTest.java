package com.gosaint.easyes.api.index.impl;

import com.gosaint.easyes.SpringBootEasyEsStarterApplicationTests;
import com.gosaint.easyes.api.index.IndexAPI;
import org.elasticsearch.action.get.GetResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Map;

/**
 * @Author: gosaint
 * @Description:
 * @Date Created in 1:38 2020/7/18
 * @Modified By:
 */
class IndexImplTest extends SpringBootEasyEsStarterApplicationTests {
   /* @Autowired
    private IndexAPI indexTemplate;
    @Test
    void createIndexAndStorageJsonStr() throws IOException {
        String jsonString = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        indexTemplate.createIndexAndStorageJsonStr("posts","1",jsonString);
    }

    @Test
    void query() throws IOException {
        GetResponse posts = indexTemplate.query("posts", "1");
        String index = posts.getIndex();
        System.out.println(index);
        Map<String, Object> source = posts.getSource();
        for (Map.Entry<String, Object> entry : source.entrySet()){
            System.out.println(entry.getKey()+"===="+entry.getValue());
        }
    }*/
}