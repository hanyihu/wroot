package com.vic.ck.console.order.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.constant.FinalFiledParams;
import com.vic.ck.api.platform.service.OrderService;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.order.lookup.OrderLookup;
import com.vic.ck.console.system.area.service.SysAreaService;
import com.vic.ck.entity.Order;
import com.vic.ck.entity.OrderStatusRecord;
import com.vic.ck.vo.OrderDetailVo;



/**
 * 订单表
 * 
 */
@Controller
@RequestMapping("/console/order/order")
public class OrderController extends BaseConsoleController{
	

	@Resource
	private OrderService orderService;
	
	@Resource
	private SysAreaService sysAreaService;
	
	
	@ModelAttribute
	public void bindSelect(Model model) {
		model.addAttribute("cityList", sysAreaService.opendCityList());
		
		model.addAttribute("statuses", FinalFiledParams.ORDER_SCREENING_MAP);
	}
	
    /**列表页*/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String page(Model model) {
		PageInfo<Order> pager =  orderService.orderList(getLookup());
		model.addAttribute("pager",pager);
		return "console/order/order/list";
	}
	
	/**重写查询条件*/
	protected Lookup instanceLookup() {
		return new OrderLookup();
	}
	
	/**查询*/
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String search(OrderLookup lookup) {
		setLookup(lookup);
		return "redirect:/console/order/order/";
	}
	
	
	/**前往详情页面*/
	@RequestMapping( value = "/{orderno}", method = RequestMethod.GET)
	public String detail(@PathVariable String orderno, Model model){
		OrderDetailVo entity = orderService.orderDetail(orderno);
		model.addAttribute("entity", entity);
		return "console/order/order/detail"; 
	}
	
	/**前往订单状态时间轴*/
	@RequestMapping( value = "/{orderno}/timeline", method = RequestMethod.GET)
	public String timeline(@PathVariable String orderno, Model model){
		List<OrderStatusRecord> records = orderService.orderStatusRecord(orderno);
		model.addAttribute("records", records);
		return "console/order/order/timeline"; 
	}
	
}
