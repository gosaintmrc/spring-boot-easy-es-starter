package com.gosaint.easyes.api.index.impl;

import java.io.IOException;

import com.gosaint.easyes.SpringBootEasyEsStarterApplicationTests;
import com.gosaint.easyes.api.index.IndexTemplate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: gosaint
 * @Description:
 * @Date Created in 1:38 2020/7/18
 * @Modified By:
 */
class IndexImplTest extends SpringBootEasyEsStarterApplicationTests {
    @Autowired
    private IndexTemplate indexTemplate;
    @Test
    void createIndexAndStorageJsonStr() throws IOException {
        String jsonString = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        indexTemplate.createIndexAndStorageJsonStr("posts","1",jsonString);
    }
}