package com.vic.ck.console.statistics.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vic.base.BaseMapper;
import com.vic.ck.console.statistics.lookup.ReportCityOrderLookup;
import com.vic.ck.entity.ReportCityOrder;
import com.vic.wroot.common.annotation.MybatisMapper;

/**
 * 城市订单流水
 * 
 * @author VIC
 */
@MybatisMapper
public interface ReportCityOrderMapper extends BaseMapper<ReportCityOrder> {

	/**当天城市流水*/
	void staticticsCityOrder(@Param("day")String day);

	/**更新冗余字段*/
	void updateRedundant(@Param("day")String day);

	/**当天平台总流水*/
	void staticticsPlatformOrder(@Param("day")String day);

	/**柱状图统计数据*/
	List<ReportCityOrder> barData(ReportCityOrderLookup lookup);
	
}
