package com.gosaint.easyes.api.index;

import java.io.IOException;
import java.util.Map;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;

/**
 * @Author: gosaint
 * @Description:
 * @Date Created in 1:33 2020/7/18
 * @Modified By:
 */
public interface IndexAPI extends DocumentAPI{
    /**
     * 同步方法，不是异步的创建索引
     * 创建一个索引并且存储json数据。设置ID
     * @param id
     * @param jsonStr
     * @param indexName 索引名称
     */
    void createIndexAndStorageJsonStr(String indexName,String id,String jsonStr) throws IOException;

    /**
     * 异步创建索引
     * @param indexName
     * @param id
     * @param jsonSt
     * @param listener
     * @throws IOException
     */
    void asynCreateIndexAndStorageJsonStr(String indexName,String id,String jsonSt, ActionListener<IndexResponse> listener) throws IOException;

    /**
     * 根据id和索引名称查询
     * @param indexName
     * @param id
     */
    GetResponse query(String indexName,String id) throws IOException;

    /**
     * 异步查询
     * @param indexName
     * @param id
     * @param listener
     * @throws IOException
     */

    void asycQuery(String indexName,String id, ActionListener<GetResponse> listener) throws IOException;

    /**
     * 同步判断文档
     * @param indexName
     * @param id
     * @return
     * @throws IOException
     */
    boolean isExists(String indexName,String id) throws IOException;

    /**
     * 异步判断文档
     * @param indexName
     * @param id
     * @throws IOException
     */
    void asynIsExists(String indexName,String id,ActionListener<Boolean> listener) throws IOException;

    /**
     * 删除API
     * @param indexName
     * @param id
     * @return
     * @throws Exception
     */
    DeleteResponse delete(String indexName,String id) throws Exception;

    /**
     * 异步删除
     * @param indexName
     * @param id
     * @param listener
     * @throws Exception
     */
    void asynDelete(String indexName,String id,ActionListener<DeleteResponse> listener) throws Exception;

    /**
     * 修改文档
     * @param indexName
     * @param id
     * @param jsonMap
     * @return
     * @throws IOException
     */
    UpdateResponse update(String indexName,String id, Map<String,Object> jsonMap) throws IOException;

    /**
     * 异步修改
     * @param indexName
     * @param id
     * @param jsonMap
     * @param listener
     * @throws IOException
     */
    void asynUpdate(String indexName,String id, Map<String,Object> jsonMap,ActionListener<UpdateResponse> listener) throws IOException;
}
