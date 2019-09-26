package com.vic.ck.console.merchant.controller;

import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.base.pager.Remind;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.merchant.lookup.MerchantGroupbuyLookup;
import com.vic.ck.console.merchant.service.MerchantGroupbuyService;
import com.vic.ck.entity.MerchantGroupBuy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;



/**
 * 商户团购商品表
 * 
 */
@Controller
@RequestMapping("/console/merchant/groupbuy")
public class MerchantGroupbuyController extends BaseConsoleController{
	

	@Resource
	private MerchantGroupbuyService merchantGroupbuyService;
	
    /**列表页*/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String page(Model model) {
		PageInfo<MerchantGroupBuy> pager =  merchantGroupbuyService.list(getLookup());
		model.addAttribute("pager",pager);
		return "console/merchant/groupbuy/list";
	}
	
	/**重写查询条件*/
	protected Lookup instanceLookup() {
		return new MerchantGroupbuyLookup();
	}
	
	/**查询*/
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String search(MerchantGroupbuyLookup lookup) {
		setLookup(lookup);
		return "redirect:/console/merchant/groupbuy/";
	}
	
	/**前往新增页面*/
	@RequestMapping( value = "/0", method = RequestMethod.GET)
	public String add(Model model){
		return "console/merchant/groupbuy/form"; 
	}
	
	/**前往修改页面*/
	@RequestMapping( value = "/{id}", method = RequestMethod.GET)
	public String editor(@PathVariable int id, Model model){
		MerchantGroupBuy entity = merchantGroupbuyService.findById(id);
		model.addAttribute("entity", entity);
		return "console/merchant/groupbuy/form"; 
	}
	
	/**前往详情页面*/
	@RequestMapping( value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable int id, Model model){
		MerchantGroupBuy entity = merchantGroupbuyService.findById(id);
		model.addAttribute("entity", entity);
		return "console/merchant/groupbuy/detail"; 
	}
	
	
	
	/**保存*/
	@RequestMapping( value = "/{id}", method = RequestMethod.POST)
	public String save(@PathVariable int id, MerchantGroupBuy entity, RedirectAttributes attr){
		entity.setId(id);
		if(id == 0) {//to add
			merchantGroupbuyService.insert(entity);
			attr.addFlashAttribute(Remind.success());
		}else if(id > 0 ){ //to update
			merchantGroupbuyService.update(entity);
			attr.addFlashAttribute(Remind.success());
		}else {
			attr.addFlashAttribute(Remind.danger());
		}
		return "redirect:/console/merchant/groupbuy/";
	}
	
	/**单个删除*/
	@RequestMapping( value = "/{id}/remove", method = RequestMethod.GET)
	public String delete(@PathVariable int id, RedirectAttributes attributes){
		merchantGroupbuyService.delete(id);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/merchant/groupbuy/";
	}
	
	/**批量删除*/
	@RequestMapping( value = "/remove", method = RequestMethod.POST)
	public String batchDelete(int[] ids, RedirectAttributes attributes){
		merchantGroupbuyService.delete(ids);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/merchant/groupbuy/";
	}
	
	/**上下架团购*/
	@RequestMapping( value = "/{id}/status", method = RequestMethod.GET)
	public String status(@PathVariable int id, int status, RedirectAttributes attributes){
		MerchantGroupBuy entity = new MerchantGroupBuy();
		entity.setId(id); entity.setStatus(status);
		merchantGroupbuyService.update(entity);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/merchant/groupbuy/";
	}
	
	
	
	
}
