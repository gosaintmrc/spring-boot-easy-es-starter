package com.gosaint.easyes.api.index.impl;

import java.io.IOException;
import java.util.Map;

import com.gosaint.easyes.api.index.IndexAPI;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
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
public class IndexImpl implements IndexAPI {

    @Autowired
    private RestHighLevelClient restHighLevelClient;


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
            final String jsonStr, ActionListener<IndexResponse> listener)
            throws IOException {

        IndexRequest request = new IndexRequest(indexName);
        request.id(id);
        request.source(jsonStr, XContentType.JSON);
        restHighLevelClient.indexAsync(request, RequestOptions.DEFAULT, listener);
    }

    @Override
    public GetResponse query(final String indexName, final String id) throws IOException {
        GetRequest getRequest = new GetRequest(
                indexName,
                id);

        return restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
    }

    @Override
    public void asycQuery(final String indexName, final String id,ActionListener<GetResponse> listener) throws IOException {
        GetRequest request = new GetRequest(
                indexName,
                id);
        restHighLevelClient.getAsync(request, RequestOptions.DEFAULT, listener);
    }

    @Override
    public boolean isExists(final String indexName, final String id) throws IOException {
        GetRequest request = new GetRequest(indexName, id);
        return restHighLevelClient.exists(request, RequestOptions.DEFAULT);
    }

    @Override
    public void asynIsExists(final String indexName, final String id,ActionListener<Boolean> listener) throws IOException {
        GetRequest request = new GetRequest(indexName, id);
        restHighLevelClient.existsAsync(request, RequestOptions.DEFAULT, listener);
    }

    @Override
    public DeleteResponse delete(final String indexName, final String id) throws Exception {
        DeleteRequest request = new DeleteRequest(
                indexName,
                id);
        return restHighLevelClient.delete(
                request, RequestOptions.DEFAULT);
    }

    @Override public void asynDelete(
            final String indexName, final String id, final ActionListener<DeleteResponse> listener) throws Exception {
        DeleteRequest request = new DeleteRequest(
                indexName,
                id);
        restHighLevelClient.deleteAsync(request, RequestOptions.DEFAULT, listener);
    }

    @Override
    public UpdateResponse update(final String indexName, final String id,Map<String,Object> jsonMap) throws IOException {


        UpdateRequest request = new UpdateRequest(indexName, id)
                .doc(jsonMap);
        return restHighLevelClient.update(
                request, RequestOptions.DEFAULT);
    }

    @Override public void asynUpdate(
            final String indexName,
            final String id,
            final Map<String, Object> jsonMap,
            final ActionListener<UpdateResponse> listener)
            throws IOException {
        UpdateRequest request = new UpdateRequest(indexName, id)
                .doc(jsonMap);
        restHighLevelClient.updateAsync(request, RequestOptions.DEFAULT, listener);
    }

}
