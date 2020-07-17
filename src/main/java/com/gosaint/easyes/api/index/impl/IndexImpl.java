package com.gosaint.easyes.api.index.impl;

import java.io.IOException;

import com.gosaint.easyes.api.index.IndexTemplate;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.gosaint.easyes.common.RequestMethod.POSTS;

/**
 * @Author: gosaint
 * @Description:
 * @Date Created in 1:36 2020/7/18
 * @Modified By:
 */
@Service
public class IndexImpl implements IndexTemplate {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public void createIndexAndStorageJsonStr(String indexName,final String id, final String jsonStr) throws IOException {
        IndexRequest request = new IndexRequest(indexName);
        request.id(id);
        request.source(jsonStr, XContentType.JSON);
        restHighLevelClient.index(request, RequestOptions.DEFAULT);
    }
}
