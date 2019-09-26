package com.vic.ck.console.statistics.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.statistics.lookup.ReportCityOrderLookup;
import com.vic.ck.console.statistics.mapper.ReportCityOrderMapper;
import com.vic.ck.console.statistics.vo.BarGraphVo;
import com.vic.ck.console.statistics.vo.SerieVo;
import com.vic.ck.entity.ReportCityOrder;

/**
 * 城市订单流水
 *
 */
@Service
public class ReportCityOrderService extends BaseService {

	@Resource
	private ReportCityOrderMapper reportCityOrderMapper;

	/**
	 * 查询列表
	 */
	public PageInfo<ReportCityOrder> list(Lookup lookup) {
		startPage(lookup);
		List<ReportCityOrder> datas = reportCityOrderMapper.list(lookup);
		return PageInfo.instance(datas, lookup);
	}

	/**
	 * 根据主键查询
	 */
	public ReportCityOrder findById(int id) {
		return reportCityOrderMapper.findById(id);
	}

	/**
	 * 新增
	 */
	public int insert(ReportCityOrder entity) {
		return reportCityOrderMapper.insert(entity);
	}

	/**
	 * 修改
	 */
	public int update(ReportCityOrder entity) {
		return reportCityOrderMapper.update(entity);
	}

	/***
	 * 删除
	 */
	public int delete(int... ids) {
		return reportCityOrderMapper.delete(ids);
	}

	/**
	 * 统计当天各个城市的流水
	 */
	public void staticticsCityOrder(String day) {
		reportCityOrderMapper.staticticsCityOrder(day);

	}

	/**
	 * 更新冗余字段
	 */
	public void updateRedundant(String day) {
		reportCityOrderMapper.updateRedundant(day);
	}
	
	/**
	 * 统计当天平台的总流水
	 * @param day
	 */
	public void staticticsPlatformOrder(String day) {
		reportCityOrderMapper.staticticsPlatformOrder(day);
	}

	/**
	 * 柱状图统计数据
	 */
	public BarGraphVo barData(ReportCityOrderLookup lookup) {
		return assembleBarData(reportCityOrderMapper.barData(lookup));
	}
	
	public BarGraphVo assembleBarData(List<ReportCityOrder> list){
		BarGraphVo bar = new BarGraphVo();
		bar.setLegend(new ArrayList<String>(Arrays.asList("总流水", "直接购买", "团购", "酒店预定")));
		SerieVo v1 =  new SerieVo("总流水", "总流水");
		SerieVo v2 =  new SerieVo("直接购买", "其他");
		SerieVo v3 =  new SerieVo("团购", "其他");
		SerieVo v4 =  new SerieVo("酒店预定", "其他");
		bar.addSeries(v1);bar.addSeries(v2);bar.addSeries(v3);bar.addSeries(v4);
		if(CollectionUtils.isNotEmpty(list)){
			for(ReportCityOrder r : list) {
				bar.addXAxis(r.getCityName());
				v1.addData(r.getTotalAmount().doubleValue());
				v2.addData(r.getBuyAmount().doubleValue());
				v3.addData(r.getGroupbuyAmount().doubleValue());
				v4.addData(r.getHotelAmount().doubleValue());
			}
		}
		return bar;
	}

}
