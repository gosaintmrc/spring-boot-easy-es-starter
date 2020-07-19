package com.gosaint.easyes.api.index.impl;

import java.io.IOException;

import com.gosaint.easyes.api.index.IndexTemplate;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: gosaint
 * @Description:
 * @Date Created in 1:36 2020/7/18
 * @Modified By:
 */
@Service
@Slf4j
public class IndexImpl implements IndexTemplate {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    private ActionListener<IndexResponse> listener = new ActionListener<IndexResponse>() {
        @Override
        public void onResponse(IndexResponse indexResponse) {
            String index = indexResponse.getIndex();
            String id = indexResponse.getId();
            if (indexResponse.getResult() == DocWriteResponse.Result.CREATED) {
                log.info("索引被创建,indexName is {},id is {}",index,id);
            } else if (indexResponse.getResult() == DocWriteResponse.Result.UPDATED) {
                log.info("索引被更新");
            }
            ReplicationResponse.ShardInfo shardInfo = indexResponse.getShardInfo();
        }

        @Override
        public void onFailure(Exception e) {
            log.error(e.getMessage());
        }
    };

    @Override
    public void createIndexAndStorageJsonStr(String indexName, final String id, final String jsonStr)
            throws IOException {
        IndexRequest request = new IndexRequest(indexName);
        request.id(id);
        request.source(jsonStr, XContentType.JSON);
        restHighLevelClient.index(request, RequestOptions.DEFAULT);
    }

    @Override public void asynCreateIndexAndStorageJsonStr(
            final String indexName,
            final String id,
            final String jsonStr)
            throws IOException {

        IndexRequest request = new IndexRequest(indexName);
        request.id(id);
        request.source(jsonStr, XContentType.JSON);
        restHighLevelClient.indexAsync(request, RequestOptions.DEFAULT, listener);
    }

}
