package com.vic.ck.console.customer.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vic.base.pager.CommonLookup;
import com.vic.base.pager.PageInfo;
import com.vic.base.pager.Remind;
import com.vic.ck.api.constant.FinalFiledParams;
import com.vic.ck.api.system.service.CustomerBalanceService;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.customer.service.CustomerFetchMoneyService;
import com.vic.ck.entity.CustomerFetchMoney;
import com.vic.ck.vo.ConsoleBellTip.TipFlag;



/**
 * 用户提现申请表
 * 
 */
@Controller
@RequestMapping("/console/customer/fetch")
public class CustomerFetchMoneyController extends BaseConsoleController{
	

	@Resource
	private CustomerFetchMoneyService customerFetchMoneyService;
	
	@Resource
	private CustomerBalanceService customerBalanceService;
	
    /**列表页*/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String page(Model model) {
		removeTip(TipFlag.customeFetch);
		PageInfo<CustomerFetchMoney> pager =  customerFetchMoneyService.list(getLookup());
		model.addAttribute("pager",pager);
		return "console/customer/fetch/list";
	}
	
	
	/**查询*/
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String search(CommonLookup lookup) {
		setLookup(lookup);
		return "redirect:/console/customer/fetch/";
	}
	
	/**前往详情页面*/
	@RequestMapping( value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable int id, Model model){
		CustomerFetchMoney entity = customerFetchMoneyService.findById(id);
		model.addAttribute("entity", entity);
		return "console/customer/fetch/detail"; 
	}
	
	/**前往修改页面*/
	@RequestMapping( value = "/{id}", method = RequestMethod.GET)
	public String editor(@PathVariable int id, Model model){
		CustomerFetchMoney entity = customerFetchMoneyService.findById(id);
		model.addAttribute("entity", entity);
		return "console/customer/fetch/form"; 
	}
	
	/**保存*/
	@RequestMapping( value = "/{id}", method = RequestMethod.POST)
	@Transactional
	public String save(@PathVariable int id, CustomerFetchMoney entity, RedirectAttributes attr){
		entity.setId(id);
		if(id == 0) {//to add
//			customerFetchMoneyService.insert(entity);
//			attr.addFlashAttribute(Remind.success());
		}else if(id > 0 ){ //to update
			customerFetchMoneyService.update(entity);
			if(entity.getStatus() == 2) {// 不通过则应该返回余额
				entity = customerFetchMoneyService.findById(id);
				customerBalanceService.addBalance(entity.getMoney(), FinalFiledParams.BALANCE_FETCH_FAIL, entity.getCustomerId());
			}
			attr.addFlashAttribute(Remind.success());
		}else {
			attr.addFlashAttribute(Remind.danger());
		}
		return "redirect:/console/customer/fetch/";
	}
	
	/**单个删除*/
	@RequestMapping( value = "/{id}/remove", method = RequestMethod.GET)
	public String delete(@PathVariable int id, RedirectAttributes attributes){
		customerFetchMoneyService.delete(id);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/customer/fetch/";
	}
	
	/**批量删除*/
	@RequestMapping( value = "/remove", method = RequestMethod.POST)
	public String batchDelete(int[] ids, RedirectAttributes attributes){
		customerFetchMoneyService.delete(ids);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/customer/fetch/";
	}
	
}
