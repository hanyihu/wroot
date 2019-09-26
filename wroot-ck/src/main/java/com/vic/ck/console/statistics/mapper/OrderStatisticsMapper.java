package com.vic.ck.console.statistics.mapper;

import com.vic.base.BaseMapper;
import com.vic.base.pager.Lookup;
import com.vic.ck.entity.Order;
import com.vic.ck.entity.OrderStatistics;
import com.vic.ck.entity.ReportOrder;
import com.vic.wroot.common.annotation.MybatisMapper;

/**
 * 订单统计报表
 * 
 * @author VIC
 */
@MybatisMapper
public interface OrderStatisticsMapper extends BaseMapper<Order> {
	/**统计*/
	Order total(Lookup lookup);

	/*商品订单统计*/
	OrderStatistics statistics(Lookup lookup);

	/*商品全部订单*/
	OrderStatistics allStatistics(Lookup lookup);

}
