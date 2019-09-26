package com.vic.ck.api.elasticSearch.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.vic.ck.api.elasticSearch.controller.ElasticSearchConfig;
import com.vic.ck.entity.Commodity;
import com.vic.ck.entity.Merchant;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ElasticSearchService {
    TransportClient client = null;

    /**
     * 根据输入的信息进行模糊查询
     *
     * @param name 输入的文字
     * @param type 判断是搜索商品还是店铺,null为搜索商品,否则为店铺
     * @param size 每页需要展示的条数
     * @param page 页数
     * @return JSON数组
     */
    public JSONArray searchByName(String name, int type, int size, int page) {
        SearchRequestBuilder searchRequestBuilder = null;
        try {
            ElasticSearchConfig config = new ElasticSearchConfig();
            //创建连接
            client = config.createClient();
            //设置查询条件
            QueryBuilder builder1 = QueryBuilders.matchQuery("name", name);
            if (type == 0) {//默认查询商品(从名称和描述上进行匹配)
                QueryBuilder builder2 = QueryBuilders.matchQuery("content", name);
                searchRequestBuilder = client.prepareSearch("commodity");
                searchRequestBuilder.setTypes("com");
                searchRequestBuilder.setQuery(
                        QueryBuilders.boolQuery()
                                .should(builder1)
                                .should(builder2)
                );
            } else if (type == 1) {//指定查询类型时才查询店铺
                searchRequestBuilder = client.prepareSearch("merchant");
                searchRequestBuilder.setTypes("mer");
                searchRequestBuilder.setQuery(
                        QueryBuilders.boolQuery()
                                .must(builder1)
                );
            }
            if ((page > 0) && (size > 0)) {
                // 设置查询数据的位置,分页用
                searchRequestBuilder.setFrom(size * (page - 1));
                // 设置查询结果集的最大条数
                searchRequestBuilder.setSize(size);
            }
            SearchResponse response = searchRequestBuilder.execute().actionGet();
            SearchHits hits = response.getHits();
            JSONArray array = new JSONArray();
            for (SearchHit hit : hits) {
                //将获取的值转换成map的形式
                Map<String, Object> map = hit.getSourceAsMap();
                JSONObject jsonObject = new JSONObject(map);
                array.add(jsonObject);
            }
            return array;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 同步数据库修改商品信息
     *
     * @param commodity
     */
    public void updateCommodity(Commodity commodity) {
        SearchRequestBuilder searchRequestBuilder = null;
        try {
            ElasticSearchConfig config = new ElasticSearchConfig();
            //创建连接
            client = config.createClient();
            //设置查询条件
            String elaId = getElaIdByObjId(commodity.getId().toString(), null);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", commodity.getName());
            map.put("price", commodity.getPrice().toString());
            map.put("brand_seller_id", commodity.getBrand_seller_id().toString());
            map.put("content", commodity.getContent());
            UpdateResponse updateResponse = client.prepareUpdate("commodity", "com", elaId)
                    .setDoc(map).get();
            System.out.println(updateResponse.status());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 同步数据库修改店铺信息
     *
     * @param merchant
     */
    public void updateMerchant(Merchant merchant) {
        try {
            ElasticSearchConfig config = new ElasticSearchConfig();
            //创建连接
            client = config.createClient();
            //设置查询条件
            String elaId = getElaIdByObjId(merchant.getId().toString(), "1");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", merchant.getName());
            map.put("address", merchant.getAddress());
            map.put("type", merchant.getType() == null ? "" : merchant.getType().toString());
            map.put("latitude", merchant.getLatitude() == null ? "" : merchant.getLatitude().toString());
            map.put("longitude", merchant.getLongitude() == null ? "" : merchant.getLongitude().toString());

            UpdateResponse updateResponse = client.prepareUpdate("merchant", "mer", elaId)
                    .setDoc(map).get();
            System.out.println(updateResponse.status());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 新增商品完成后向elasticsearch添加一条对应商品信息
     *
     * @param commodity
     */
    public void insertCommodity(Commodity commodity) {
        try {
            ElasticSearchConfig config = new ElasticSearchConfig();
            //创建连接
            client = config.createClient();
            XContentBuilder doContentBuilder = XContentFactory.jsonBuilder()
                    .startObject()
                    .field("id", commodity.getId().toString())
                    .field("name", commodity.getName())
                    .field("price", commodity.getPrice() == null ? "" : commodity.getPrice().toString())
                    .field("brand_seller_id", commodity.getBrand_seller_id() == null ? "" : commodity.getBrand_seller_id().toString())
                    .field("type", "commodity")
                    .field("content", commodity.getContent())
                    .endObject();
            IndexResponse response = client.prepareIndex("commodity", "com")
                    .setSource(doContentBuilder)
                    .setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE).get();
            System.out.println(response.status());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 新增店铺完成后向elasticsearch添加一条对应店铺信息
     *
     * @param merchant
     */
    public void insertMerchant(Merchant merchant) {
        try {
            ElasticSearchConfig config = new ElasticSearchConfig();
            //创建连接
            client = config.createClient();
            XContentBuilder doContentBuilder = XContentFactory.jsonBuilder()
                    .startObject()
                    .field("id", merchant.getId().toString())
                    .field("name", merchant.getName())
                    .field("address", merchant.getAddress())
                    .field("longitude", merchant.getLongitude() == null ? "" : merchant.getLongitude().toString())
                    .field("latitude", merchant.getLatitude() == null ? "" : merchant.getLatitude().toString())
                    .field("type", merchant.getType() == null ? "" : merchant.getType().toString())
                    .field("mobile", merchant.getMobile())
                    .endObject();
            IndexResponse response = client.prepareIndex("merchant", "mer")
                    .setSource(doContentBuilder)
                    .setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE).get();
            System.out.println(response.status());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除商品的时候同步删除elasticsearch里对应的商品信息
     *
     * @param commodity
     */
    public void deleteCommodity(Commodity commodity) {
        try {
            ElasticSearchConfig config = new ElasticSearchConfig();
            //创建连接
            client = config.createClient();
            String elaId = getElaIdByObjId(commodity.getId().toString(), null);
            DeleteResponse deleteResponse = client.prepareDelete("commodity", "com", elaId).get();
            System.out.println(deleteResponse.status());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 删除商品的时候同步删除elasticsearch里对应的商品信息
     *
     * @param merchant
     */
    public void deleteMerchant(Merchant merchant) {
        try {
            ElasticSearchConfig config = new ElasticSearchConfig();
            //创建连接
            client = config.createClient();
            String elaId = getElaIdByObjId(merchant.getId().toString(), null);
            DeleteResponse deleteResponse = client.prepareDelete("merchant", "mer", elaId).get();
            System.out.println(deleteResponse.status());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 根据对象的id获取索引的id（精确查找）
     *
     * @param objId 对象的id
     * @param type  为null则是查询商品，否则为店铺
     * @return
     */
    public String getElaIdByObjId(String objId, String type) {
        SearchRequestBuilder searchRequestBuilder = null;
        QueryBuilder builder = QueryBuilders.matchQuery("id", objId);
        if (type != null) {
            searchRequestBuilder = client.prepareSearch("merchant");
            searchRequestBuilder.setTypes("mer");
        } else {
            searchRequestBuilder = client.prepareSearch("commodity");
            searchRequestBuilder.setTypes("com");
        }

        searchRequestBuilder.setQuery(
                QueryBuilders.boolQuery()
                        .must(builder)
        );
        //设置页数
        searchRequestBuilder.setFrom(0);
        //设置查询条数
        searchRequestBuilder.setSize(1);
        SearchResponse response = searchRequestBuilder.execute().actionGet();
        SearchHits hits = response.getHits();
        String elaId = hits.getHits()[0].getId();
        return elaId;
    }


}
