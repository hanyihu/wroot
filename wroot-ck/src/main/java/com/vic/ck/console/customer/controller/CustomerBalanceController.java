package com.vic.ck.console.customer.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.base.pager.Remind;
import com.vic.ck.api.constant.FinalFiledParams;
import com.vic.ck.api.system.service.CustomerBalanceService;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.customer.lookup.CustomerBalanceLookup;
import com.vic.ck.entity.CustomerBalance;
import com.vic.ck.entity.CustomerBalanceRecord;



/**
 * 用户余额表
 * 
 */
@Controller
@RequestMapping("/console/customer/balance")
public class CustomerBalanceController extends BaseConsoleController{
	

	@Resource
	private CustomerBalanceService customerBalanceService;
	
    /**列表页*/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String page(Model model) {
		PageInfo<CustomerBalance> pager =  customerBalanceService.list(getLookup());
		model.addAttribute("pager",pager);
		return "console/customer/balance/list";
	}
	
	
	
	@Override
	protected Lookup instanceLookup() {
		return new CustomerBalanceLookup();
	}



	/**查询*/
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String search(CustomerBalanceLookup lookup) {
		setLookup(lookup);
		return "redirect:/console/customer/balance/";
	}
	
	/**前往修改页面*/
	@RequestMapping( value = "/{id}", method = RequestMethod.GET)
	public String editor(@PathVariable int id, Model model){
		CustomerBalance entity = customerBalanceService.findById(id);
		model.addAttribute("entity", entity);
		return "console/customer/balance/form"; 
	}
	
	/**保存*/
	@RequestMapping( value = "/{id}", method = RequestMethod.POST)
	public String save(@PathVariable int id, CustomerBalance entity, RedirectAttributes attr){
		entity.setId(id);
		if(id == 0) {//to add
			
		}else if(id > 0 ){ //to update
			customerBalanceService.update(entity);
			attr.addFlashAttribute(Remind.success());
		}else {
			attr.addFlashAttribute(Remind.danger());
		}
		return "redirect:/console/ck/customerbalance/";
	}
	
	/********************余额明细列表 ↓↓        **************************/
	
	@RequestMapping(value = "{customerId}/records", method = RequestMethod.GET)
	public String detailPage(@PathVariable int customerId, Model model) {
		CustomerBalanceLookup lookup = (CustomerBalanceLookup) getLookup("details");
		lookup.setUserId(customerId);
		PageInfo<CustomerBalanceRecord> pager =  customerBalanceService.balanceRecords(lookup);
		model.addAttribute("pager",pager);
		model.addAttribute("types", FinalFiledParams.BALANCE_TYPE_MAP);
		return "console/customer/balance/records";
	}
	
	/**查询*/
	@RequestMapping(value = "{customerId}/records", method = RequestMethod.POST)
	public String detailSearch(CustomerBalanceLookup lookup) {
		setLookup(lookup, "details");
		return "redirect:/console/customer/balance/{customerId}/records";
	}
	
	/********************余额明细列表 ↑↑      **************************/
	
}
