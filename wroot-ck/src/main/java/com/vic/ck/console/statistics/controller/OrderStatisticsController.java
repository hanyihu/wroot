package com.vic.ck.console.statistics.controller;

import com.vic.base.pager.Lookup;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.statistics.lookup.OrderStatisticsLookup;
import com.vic.ck.console.statistics.service.OrderStatisticsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * 商品订单统计
 * 
 */
@Controller
@RequestMapping("/console/statistics/orderStatistics")
public class OrderStatisticsController extends BaseConsoleController {

	@Resource
    private OrderStatisticsService orderStatisticsService;

	/*订单统计列表页*/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String page(Model model) {
		OrderStatisticsLookup lookup = (OrderStatisticsLookup) getLookup();
        model.addAttribute("allStatistics", orderStatisticsService.allStatistics(lookup));
        model.addAttribute("statistics", orderStatisticsService.statistics(lookup));

		return "console/statistics/orderStatistics/list";
	}
	/**重写查询条件*/
	protected Lookup instanceLookup() {
		return new OrderStatisticsLookup();
	}

	/** 查询 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String search(OrderStatisticsLookup lookup) {

		setLookup(lookup);
		return "redirect:/console/statistics/orderStatistics/";
	}



}
