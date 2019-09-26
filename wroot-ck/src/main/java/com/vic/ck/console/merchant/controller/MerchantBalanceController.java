package com.vic.ck.console.merchant.controller;

import java.math.BigDecimal;

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
import com.vic.ck.api.merchant.service.MerchantBalanceService;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.merchant.lookup.MerchantBalanceLookup;
import com.vic.ck.entity.MerchantBalance;
import com.vic.ck.entity.MerchantBalanceRecord;



/**
 * 商户余额明细表
 * 
 */
@Controller
@RequestMapping("/console/merchant/balance")
public class MerchantBalanceController extends BaseConsoleController{
	

	@Resource
	private MerchantBalanceService merchantBalanceService;
	
    /**列表页*/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String page(Model model) {
		PageInfo<MerchantBalance> pager =  merchantBalanceService.list(getLookup());
		model.addAttribute("pager",pager);
		return "console/merchant/balance/list";
	}
	
	/**重写查询条件*/
	protected Lookup instanceLookup() {
		return new MerchantBalanceLookup();
	}
	
	/**查询*/
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String search(MerchantBalanceLookup lookup) {
		setLookup(lookup);
		return "redirect:/console/merchant/balance/";
	}
	
	
	/**前往提取余额页面*/
	@RequestMapping( value = "/{id}", method = RequestMethod.GET)
	public String editor(@PathVariable int id, Model model){
		MerchantBalance entity = merchantBalanceService.findById(id);
		model.addAttribute("entity", entity);
		return "console/merchant/balance/form"; 
	}
	
	/**商户提取余额*/
	@RequestMapping( value = "/{id}", method = RequestMethod.POST)
	public String save(@PathVariable int id, MerchantBalance entity, RedirectAttributes attr){
		BigDecimal balance = entity.getBalance();
		if(balance == null || balance.compareTo(BigDecimal.ZERO)<=0){
			attr.addFlashAttribute(Remind.danger().setMessage("请输入正确的余额"));
		}else {
			MerchantBalance cur = merchantBalanceService.findById(id);
			if(cur == null ){
				attr.addFlashAttribute(Remind.danger().setMessage("不存在的商户"));
			}else {
				if(balance.compareTo(cur.getBalance()) > 0) {
					attr.addFlashAttribute(Remind.danger().setMessage("余额不足，提现失败"));
				}else {
					merchantBalanceService.addBalance(balance.negate(), FinalFiledParams.MERCHAN_BALANCE_FETCH, cur.getMerchantId());
					attr.addFlashAttribute(Remind.success());
				}
			}
		}
		
		return "redirect:/console/merchant/balance/";
	}
	
	
	/********************商家余额明细列表 ↓↓        **************************/
	
	@RequestMapping(value = "{merchantId}/records", method = RequestMethod.GET)
	public String detailPage(@PathVariable int merchantId, Model model) {
		MerchantBalanceLookup lookup = (MerchantBalanceLookup) getLookup("details");
		lookup.setMerchantId(merchantId);
		PageInfo<MerchantBalanceRecord> pager =  merchantBalanceService.balanceRecords(lookup);
		model.addAttribute("pager",pager);
		model.addAttribute("types", FinalFiledParams.MERCHANT_BALANCE_TYPE_MAP);
		return "console/merchant/balance/records";
	}
	
	/**查询*/
	@RequestMapping(value = "{merchantId}/records", method = RequestMethod.POST)
	public String detailSearch(MerchantBalanceLookup lookup) {
		setLookup(lookup, "details");
		return "redirect:/console/merchant/balance/{merchantId}/records";
	}
	
	/********************余额明细列表 ↑↑      **************************/
	
}
