package com.vic.ck.console.statistics.controller;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.statistics.lookup.ReportCityOrderLookup;
import com.vic.ck.console.statistics.service.ReportCityOrderService;
import com.vic.ck.console.statistics.vo.BarGraphVo;
import com.vic.ck.console.system.area.service.SysAreaService;
import com.vic.ck.entity.ReportCityOrder;

/**
 * 城市订单流水
 * 
 */
@Controller
@RequestMapping("/console/statistics")
public class ReportCityOrderController extends BaseConsoleController {

	@Resource
	private ReportCityOrderService reportCityOrderService;

	@Resource
	private SysAreaService sysAreaService;

	@ModelAttribute
	private void bindSelect(Model model) {
		model.addAttribute("cityList", sysAreaService.opendCityList());
	}

	/** 重写查询条件 */
	protected Lookup instanceLookup() {
		return new ReportCityOrderLookup();
	}

	/** 平台订单流水 */
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public String page(Model model) {
		ReportCityOrderLookup lookup = (ReportCityOrderLookup) getLookup();
		lookup.setType(2);
		PageInfo<ReportCityOrder> pager = reportCityOrderService.list(lookup);
		model.addAttribute("pager", pager);
		return "console/statistics/order/list";
	}

	/** 平台订单流水查询 */
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public String search(ReportCityOrderLookup lookup) {
		setLookup(lookup);
		return "redirect:/console/statistics/order/";
	}

	
	
	/***********************************************************************/
	
	/** 城市订单流水 */
	@RequestMapping(value = "/orderCity", method = RequestMethod.GET)
	public String pageCity(Model model) {
		ReportCityOrderLookup lookup = (ReportCityOrderLookup) getLookup("city");
		lookup.setType(1);
		PageInfo<ReportCityOrder> pager = reportCityOrderService.list(lookup);
		model.addAttribute("pager", pager);
		return "console/statistics/order/cityList";
	}

	/** 城市订单流水查询 */
	@RequestMapping(value = "/orderCity", method = RequestMethod.POST)
	public String searchCity(ReportCityOrderLookup lookup) {
		setLookup(lookup, "city");
		return "redirect:/console/statistics/orderCity/";
	}
	
	
	/************************流水住状图*******************************************************/
	
	
	/**
	 * 前往流水住状图
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(value = "/orderBar", method = RequestMethod.GET)
	public String orderBar(Model model) throws JsonProcessingException {
		ReportCityOrderLookup lookup = (ReportCityOrderLookup) getLookup("bar");
		if(lookup.isFirst()) {
			Calendar c = Calendar.getInstance();    
			c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一
			lookup.setStartDate(c.getTime());
			lookup.setEndDate(new Date());
		}
		/**
		 *是否只能看到当前城市的权限 
		 */
		Integer cityId = getManageCityId();
		if(cityId != null) {
			lookup.setType(1);
			lookup.setCityId(cityId);
		}
		
		BarGraphVo data = reportCityOrderService.barData(lookup);
		model.addAttribute("barData", new ObjectMapper().writeValueAsString(data));
		model.addAttribute("pager", new PageInfo<>(null, lookup));
		return "console/statistics/order/bar";
	}
	
	/**
	 * 流水住状图数据
	 */
	@RequestMapping(value = "/orderBar", method = RequestMethod.POST)
	public String orderBarSearch(ReportCityOrderLookup lookup) {
		setLookup(lookup, "bar");
		lookup.setFirst(false);
		return "redirect:/console/statistics/orderBar/";
		
	}

}
