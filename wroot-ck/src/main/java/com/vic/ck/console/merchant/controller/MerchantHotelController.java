package com.vic.ck.console.merchant.controller;

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
import com.vic.ck.console.merchant.lookup.MerchantHotelLookup;
import com.vic.ck.console.merchant.service.MerchantHotelService;
import com.vic.ck.entity.MerchantHotel;



/**
 * 酒店表
 * 
 */
@Controller
@RequestMapping("/console/merchant/hotel")
public class MerchantHotelController extends BaseConsoleController{
	

	@Resource
	private MerchantHotelService merchantHotelService;
	
    /**列表页*/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String page(Model model) {
		PageInfo<MerchantHotel> pager =  merchantHotelService.list(getLookup());
		model.addAttribute("pager",pager);
		return "console/merchant/hotel/list";
	}
	
	/**重写查询条件*/
	protected Lookup instanceLookup() {
		return new MerchantHotelLookup();
	}
	
	/**查询*/
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String search(MerchantHotelLookup lookup) {
		setLookup(lookup);
		return "redirect:/console/merchant/hotel/";
	}
	
	/**前往新增页面*/
	@RequestMapping( value = "/0", method = RequestMethod.GET)
	public String add(Model model){
		return "console/ck/merchanthotel/form"; 
	}
	
	/**前往修改页面*/
	@RequestMapping( value = "/{id}", method = RequestMethod.GET)
	public String editor(@PathVariable int id, Model model){
		MerchantHotel entity = merchantHotelService.findById(id);
		model.addAttribute("entity", entity);
		return "console/merchant/hotel/form"; 
	}
	
	
	/**前往详情页面*/
	@RequestMapping( value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable int id, Model model){
		MerchantHotel entity = merchantHotelService.findById(id);
		model.addAttribute("entity", entity);
		return "console/merchant/hotel/detail"; 
	}
	
	/**保存*/
	@RequestMapping( value = "/{id}", method = RequestMethod.POST)
	public String save(@PathVariable int id, MerchantHotel entity, RedirectAttributes attr){
		entity.setId(id);
		if(id == 0) {//to add
			merchantHotelService.insert(entity);
			attr.addFlashAttribute(Remind.success());
		}else if(id > 0 ){ //to update
			merchantHotelService.update(entity);
			attr.addFlashAttribute(Remind.success());
		}else {
			attr.addFlashAttribute(Remind.danger());
		}
		return "redirect:/console/merchant/hotel/";
	}
	
	/**单个删除*/
	@RequestMapping( value = "/{id}/remove", method = RequestMethod.GET)
	public String delete(@PathVariable int id, RedirectAttributes attributes){
		merchantHotelService.delete(id);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/merchant/hotel/";
	}
	
	/**批量删除*/
	@RequestMapping( value = "/remove", method = RequestMethod.POST)
	public String batchDelete(int[] ids, RedirectAttributes attributes){
		merchantHotelService.delete(ids);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/merchant/hotel/";
	}
	
	/**上下架*/
	@RequestMapping( value = "/{id}/status", method = RequestMethod.GET)
	public String status(@PathVariable int id, int status, RedirectAttributes attributes){
		MerchantHotel entity = new MerchantHotel();
		entity.setId(id); entity.setStatus(status);
		merchantHotelService.update(entity);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/merchant/hotel/";
	}
	
	
}
