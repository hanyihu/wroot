package com.vic.ck.console.statistics.service;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.statistics.mapper.OrderStatisticsMapper;
import com.vic.ck.entity.Order;
import com.vic.ck.entity.OrderStatistics;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单统计报表
 *
 */
@Service
public class OrderStatisticsService extends BaseService {

    @Resource
    private OrderStatisticsMapper orderStatisticsMapper;



    //商品订单统计
    public OrderStatistics statistics(Lookup lookup) {
        System.out.println(lookup);
        return orderStatisticsMapper.statistics(lookup);
    }

    //商品全部订单统计
    public OrderStatistics allStatistics(Lookup lookup){

        return orderStatisticsMapper.allStatistics(lookup);
    }

    /**
     * 查询列表
     */
    public PageInfo<Order> list(Lookup lookup) {
        startPage(lookup);
        List<Order> datas = orderStatisticsMapper.list(lookup);
        return PageInfo.instance(datas, lookup);
    }


    //统计
    public Order total(Lookup lookup) {
        return orderStatisticsMapper.total(lookup);
    }




    /**
     * 根据主键查询
     */
    public Order findById(int id) {
        return orderStatisticsMapper.findById(id);
    }



}
