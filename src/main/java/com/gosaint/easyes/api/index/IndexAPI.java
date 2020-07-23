package com.gosaint.easyes.api.index;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;

import java.io.IOException;
import java.util.Map;

/**
 * @author: gosaint
 */
public interface IndexAPI{
    /**
     * @param id 索引id
     * @param jsonStr 文档json内容
     * @param indexName 索引名称
     * @throws IOException 抛出异常
     */
    void createIndexAndStorageJsonStr(String indexName,String id,String jsonStr) throws IOException;

    /**
     *
     * @param indexName 索引名称
     * @param id 索引id
     * @param jsonSt 文档json内容
     * @param listener 监听器
     * @throws IOException 抛出异常
     */
    void asynCreateIndexAndStorageJsonStr(String indexName,String id,String jsonSt, ActionListener<IndexResponse> listener) throws IOException;

    /**
     *
     * @param indexName 索引名称
     * @param id 索引id
     * @return 返回查询结果
     * @throws IOException 抛出异常
     */
    GetResponse query(String indexName,String id) throws IOException;

    /**
     *
     * @param indexName 索引名称
     * @param id 索引id
     * @param listener 监听器
     * @throws IOException 抛出异常
     */
    void asycQuery(String indexName,String id, ActionListener<GetResponse> listener) throws IOException;

    /**
     *
     * @param indexName 索引名称
     * @param id 索引id
     * @return boolean类型返回值
     * @throws IOException 抛出异常
     */
    boolean isExists(String indexName,String id) throws IOException;

    /**
     *
     * @param indexName 索引名称
     * @param id 索引id
     * @param listener 索引名称
     * @throws IOException 抛出异常
     */
    void asynIsExists(String indexName,String id,ActionListener<Boolean> listener) throws IOException;

    /**
     *
     * @param indexName 索引名称
     * @param id 索引id
     * @return 返回删除结果
     * @throws Exception 抛出异常
     */
    DeleteResponse delete(String indexName,String id) throws Exception;

    /**
     *
     * @param indexName 索引名称
     * @param id 索引id
     * @param listener 监听器
     * @throws Exception 抛出异常
     */
    void asynDelete(String indexName,String id,ActionListener<DeleteResponse> listener) throws Exception;

    /**
     *
     * @param indexName 索引名称
     * @param id 索引id
     * @param jsonMap json内容
     * @return 返回修改结果
     * @throws IOException 抛出异常
     */
    UpdateResponse update(String indexName,String id, Map<String,Object> jsonMap) throws IOException;

    /**
     *
     * @param indexName 抛出异常
     * @param id 索引id
     * @param jsonMap json内容
     * @param listener 监听器
     * @throws IOException 抛出异常
     */
    void asynUpdate(String indexName,String id, Map<String,Object> jsonMap,ActionListener<UpdateResponse> listener) throws IOException;
}
