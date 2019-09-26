package com.vic.ck.console.mall.controller;

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
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.mall.lookup.CustomerSwapLookup;
import com.vic.ck.console.mall.service.CustomerSwapService;
import com.vic.ck.entity.CustomerSwap;
import com.vic.ck.vo.ConsoleBellTip.TipFlag;



/**
 * 我的兑换-一般为积分兑换
 * 
 */
@Controller
@RequestMapping("/console/mall/swap")
public class CustomerSwapController extends BaseConsoleController{
	

	@Resource
	private CustomerSwapService customerSwapService;
	
    /**列表页*/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String page(Model model) {
		PageInfo<CustomerSwap> pager =  customerSwapService.list(getLookup());
		removeTip(TipFlag.mallSwap);
		model.addAttribute("pager",pager);
		return "console/mall/swap/list";
	}
	
	/**重写查询条件*/
	protected Lookup instanceLookup() {
		return new CustomerSwapLookup();
	}
	
	/**查询*/
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String search(CustomerSwapLookup lookup) {
		setLookup(lookup);
		return "redirect:/console/mall/swap/";
	}
	
	
	/**前往详情页面*/
	@RequestMapping( value = "/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable int id, Model model){
		CustomerSwap entity = customerSwapService.findById(id);
		model.addAttribute("entity", entity);
		return "console/mall/swap/detail"; 
	}
	
	
	/**修改状态*/
	@RequestMapping( value = "/{id}/status", method = RequestMethod.GET)
	public String delete(@PathVariable int id, int status, RedirectAttributes attributes){
		CustomerSwap entity =  new CustomerSwap();
		entity.setId(id); entity.setStatus(status);
		customerSwapService.update(entity);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/mall/swap/";
	}
	
	
}
