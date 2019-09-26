package com.vic.ck.api.elasticSearch.controller;

import com.alibaba.fastjson.JSONArray;
import com.vic.ck.api.elasticSearch.service.ElasticSearchService;
import com.vic.ck.entity.Commodity;
import com.vic.ck.entity.Merchant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * ElasticSearch 控制器
 *
 * @author houhaoran
 */
@Controller
@RequestMapping(value = "/api/elasticsearch")
public class ElasticSearchController {
    @Resource
    ElasticSearchService elasticSearchService;

    /**
     * 根据条件进行查询
     *
     * @param name
     * @param type
     * @throws IOException
     */
    @RequestMapping(value = "/search")
    public void searchByName(String name, int type) throws IOException {
        JSONArray array = elasticSearchService.searchByName(name, type, 10, 0);
        System.out.println(array);
    }

    @RequestMapping(value = "/updateCommodity")
    public void updateCommodity() throws IOException {
        Commodity commodity = new Commodity();
        commodity.setId(1);
        commodity.setName("小米手机1");
        commodity.setBrand_seller_id(10);
        commodity.setContent("这是一个性价比很高的手机");
        commodity.setPrice(new BigDecimal(2888));
        elasticSearchService.updateCommodity(commodity);
    }

    @RequestMapping(value = "/updateMerchant")
    public void updateMerchant() throws IOException {
        Merchant merchant = new Merchant();
        merchant.setId(1);
        merchant.setName("小米手机专卖店1");
        merchant.setAddress("山东省济宁市高新区");
        merchant.setType(3);
        merchant.setLatitude(new BigDecimal(100.00000000));
        merchant.setLongitude(new BigDecimal(39.00000000));
        elasticSearchService.updateMerchant(merchant);
    }

    @RequestMapping(value = "/insertCommodity")
    public void insertCommodity() {
        Commodity commodity = new Commodity();
        commodity.setId(2);
        commodity.setName("小米MIX3手机");
        commodity.setBrand_seller_id(10);
        commodity.setContent("这个手机的屏占比很高");
        commodity.setPrice(new BigDecimal(3888));
        elasticSearchService.insertCommodity(commodity);
    }

    @RequestMapping(value = "/insertMerchant")
    public void insertMerchant() {
        Merchant merchant = new Merchant();
        merchant.setId(2);
        merchant.setName("小米旗舰店");
        merchant.setAddress("山东省济宁市任城区");
        merchant.setType(3);
        merchant.setLatitude(new BigDecimal(102.00000123));
        merchant.setLongitude(new BigDecimal(37.00000342));
        elasticSearchService.insertMerchant(merchant);
    }

    @RequestMapping(value = "/deleteCommodity")
    public void deleteCommodity() {
        Commodity commodity = new Commodity();
        commodity.setId(2);
        commodity.setName("小米MIX3手机");
        commodity.setBrand_seller_id(10);
        commodity.setContent("这个手机的屏占比很高");
        commodity.setPrice(new BigDecimal(3888));
        elasticSearchService.deleteCommodity(commodity);
    }

}
