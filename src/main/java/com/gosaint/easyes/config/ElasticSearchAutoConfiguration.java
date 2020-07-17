package com.gosaint.easyes.config;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.google.common.base.Splitter;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;


/**
 * @author gosaint
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(ElasticSearchProperties.class)
public class ElasticSearchAutoConfiguration implements DisposableBean{

    private RestHighLevelClient restHighLevelClient;
    @Resource
    private ElasticSearchProperties properties;

    @Bean
    @ConditionalOnMissingBean(RestHighLevelClient.class)
    public RestHighLevelClient restHighLevelClient() {
        String clusterNodes = properties.getClusterNodes();
        if(StringUtils.isEmpty(clusterNodes)){
            log.error("es集群节点不能为空");
        }
        log.info("es集群节点名称为{}" ,properties.getClusterName());
        log.info("es集群用户名为{}" ,properties.getUserName());
        log.info("es集群节密码为{}" ,properties.getPassword());
        log.info("es集群节点为{}" ,clusterNodes);
        log.info("开始建立elasticsearch连接");
        List<HttpHost> nodeList=new ArrayList<>();
        Splitter.on(",").omitEmptyStrings().split(clusterNodes).forEach(s->{
            String[] node = s.split(":");
            nodeList.add(new HttpHost(node[0],Integer.parseInt(node[1])));
        });

        HttpHost[] nodes = (HttpHost[]) Array.newInstance(HttpHost.class, nodeList.size());
        for (int i = 0, length = nodes.length; i < length; i++){
            nodes[i] = nodeList.get(i);
        }
        restHighLevelClient= new RestHighLevelClient(
                RestClient.builder(nodes));
        return restHighLevelClient;
    }

    private Settings settings() {
        return Settings.builder()
                .put("cluster.name", properties.getClusterName())
                .put("xpack.security.user", properties.getUserName() +
                        ":" + properties.getPassword())
                .build();
    }

    @Override
    public void destroy() throws Exception {
        log.info("开始销毁Es的连接");
        if (restHighLevelClient != null) {
            restHighLevelClient.close();
        }
    }
}