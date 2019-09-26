package com.vic;


import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Map;

public class ElasticSearchTest {
    @Test
    public void search() throws IOException {
        Settings settings = Settings.builder().put("cluster.name", "elasticSearch").build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
        QueryBuilder builder = QueryBuilders.matchQuery("name", "苹果");
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch("commodity");
        searchRequestBuilder.setTypes("com");
        searchRequestBuilder.setQuery(builder);
        searchRequestBuilder.setFrom(0);
        searchRequestBuilder.setSize(10);
        SearchResponse response = searchRequestBuilder.execute().actionGet();
        SearchHits hits = response.getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
            //将获取的值转换成map的形式
            Map<String, Object> map = hit.getSourceAsMap();
            for (String key : map.keySet()) {
                System.out.println(key + " key对应的值为：" + map.get(key));
            }
        }
    }

    @Test
    public void insert() throws IOException {
        Settings settings = Settings.builder().put("cluster.name", "elasticSearch").build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
        XContentBuilder doContentBuilder = XContentFactory.jsonBuilder()
                .startObject()
                .field("id", "1")
                .field("name", "手机专卖店")
                .field("address", "山东省济宁市任城区")
                .field("longitude", "39.26565698")
                .field("latitude", "102.22565687")
                .field("type", "3")
                .field("mobile", "15188869963")
                .endObject();
        IndexResponse response = client.prepareIndex("merchant", "mer")
                .setSource(doContentBuilder)
                .setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE).get();
        System.out.println(response.status());
    }

    /**
     * 创建索引
     *
     * @throws IOException
     */
    @Test
    public void createIndex() throws IOException {
        Settings settings = Settings.builder().put("cluster.name", "elasticSearch").build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
        client.admin().indices().prepareCreate("merchant").execute().actionGet();
    }

    @Test
    public void insertCommodity() throws IOException {
        Settings settings = Settings.builder().put("cluster.name", "elasticSearch").build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));

    }

}
