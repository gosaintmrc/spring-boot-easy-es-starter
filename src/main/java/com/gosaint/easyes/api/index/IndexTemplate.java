package com.gosaint.easyes.api.index;

import java.io.IOException;

/**
 * @Author: gosaint
 * @Description:
 * @Date Created in 1:33 2020/7/18
 * @Modified By:
 */
public interface IndexTemplate {
    /**
     * 同步方法，不是异步的创建索引
     * 创建一个索引并且存储json数据。设置ID
     * @param id
     * @param jsonStr
     * @param indexName 索引名称
     */
    void createIndexAndStorageJsonStr(String indexName,String id,String jsonStr) throws IOException;

    /**
     * 异步的创建索引
     * @param indexName
     * @param id
     * @param jsonStr
     * @throws IOException
     */
    void asynCreateIndexAndStorageJsonStr(String indexName,String id,String jsonStr) throws IOException;
}
