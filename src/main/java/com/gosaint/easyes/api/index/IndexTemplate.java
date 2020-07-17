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
     * 创建一个索引并且存储json数据。设置ID
     * @param id
     * @param jsonStr
     * @param indexName 索引名称
     */
    void createIndexAndStorageJsonStr(String indexName,String id,String jsonStr) throws IOException;
}
