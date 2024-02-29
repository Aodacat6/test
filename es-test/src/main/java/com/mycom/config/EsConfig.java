package com.mycom.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：songdalin
 * @date ：2024/2/28 下午 2:51
 * @description：
 * @modified By：
 * @version: 1.0
 */
@Configuration
public class EsConfig {

    @Bean("restHighLevelClient")
    public RestHighLevelClient restHighLevelClient(){
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("127.0.0.1",9200,"http")
                )
        );
        return client;
    }
}
