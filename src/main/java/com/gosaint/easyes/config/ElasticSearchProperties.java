package com.gosaint.easyes.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * @author gosaint
 */
@Data
@ConfigurationProperties(prefix = "elasticsearch")
public class ElasticSearchProperties  {

    private String elasticsearch;
    private String clusterName;
    private String clusterNodes ;
    private String userName;
    private String password;



}

