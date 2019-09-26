package com.vic.ck.console.activity.controller;

import java.util.Date;

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
import com.vic.ck.api.community.service.PhoneChargeBalanceService;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.customer.service.PhoneChargeFetchMoneyService;
import com.vic.ck.entity.PhoneChargeFetchMoney;
import com.vic.ck.vo.ConsoleBellTip.TipFlag;



/**
 * 用户话费充值申请表
 * 
 */
@Controller
@RequestMapping("/console/activity/fetch")
public class PhoneChargeFetchMoneyController extends BaseConsoleController{
	

	@Resource
	private PhoneChargeFetchMoneyService phoneChargeFetchMoneyService;
	
	@Resource
	private PhoneChargeBalanceService phoneChargeBalanceService;
	
    /**列表页*/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String page(Model model) {
		removeTip(TipFlag.phoneFetch);
		PageInfo<PhoneChargeFetchMoney> pager =  phoneChargeFetchMoneyService.list(getLookup());
		model.addAttribute("pager",pager);
		return "/console/activity/fetch/list";
	}
	
	/**查询*/
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String search(CommonLookup lookup) {
		setLookup(lookup);
		return "redirect:/console/activity/fetch/";
	}
	
	
	/**前往详情页面*/
	@RequestMapping( value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable int id, Model model){
		PhoneChargeFetchMoney entity = phoneChargeFetchMoneyService.findById(id);
		model.addAttribute("entity", entity);
		return "console/activity/fetch/detail"; 
	}
	
	
	/**前往修改页面*/
	@RequestMapping( value = "/{id}", method = RequestMethod.GET)
	public String editor(@PathVariable int id, Model model){
		PhoneChargeFetchMoney entity = phoneChargeFetchMoneyService.findById(id);
		model.addAttribute("entity", entity);
		return "console/activity/fetch/form"; 
	}
	
	/**保存*/
	@RequestMapping( value = "/{id}", method = RequestMethod.POST)
	@Transactional
	public String save(@PathVariable int id, PhoneChargeFetchMoney entity, RedirectAttributes attr){
		entity.setId(id);
		if(id == 0) {//to add
			/*phoneChargeFetchMoneyService.insert(entity);
			attr.addFlashAttribute(Remind.success());*/
		}else if(id > 0 ){ //to update
			entity.setFetchTime(new Date());
			phoneChargeFetchMoneyService.update(entity);
			if(entity.getStatus() == 2) {// 不通过则应该返回余额
				entity = phoneChargeFetchMoneyService.findById(id);
				phoneChargeBalanceService.addBalance(entity.getMoney(), 1, entity.getCustomerId(), "话费充值返还");
			}
			attr.addFlashAttribute(Remind.success());
		}else {
			attr.addFlashAttribute(Remind.danger());
		}
		return "redirect:/console/activity/fetch/";
	}
	
	/**单个删除*/
	@RequestMapping( value = "/{id}/remove", method = RequestMethod.GET)
	public String delete(@PathVariable int id, RedirectAttributes attributes){
		phoneChargeFetchMoneyService.delete(id);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/activity/fetch/";
	}
	
	/**批量删除*/
	@RequestMapping( value = "/remove", method = RequestMethod.POST)
	public String batchDelete(int[] ids, RedirectAttributes attributes){
		phoneChargeFetchMoneyService.delete(ids);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/activity/fetch/";
	}
	
}
