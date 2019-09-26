package com.vic.ck.console.platform.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vic.base.pager.CommonLookup;
import com.vic.base.pager.PageInfo;
import com.vic.base.pager.Remind;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.platform.service.PlatformBankService;
import com.vic.ck.entity.PlatformBank;



/**
 * 银行表
 * 
 */
@Controller
@RequestMapping("/console/platform/bank")
public class PlatformBankController extends BaseConsoleController{
	

	@Resource
	private PlatformBankService platformBankService;
	
    /**列表页*/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String page(Model model) {
		PageInfo<PlatformBank> pager =  platformBankService.list(getLookup());
		model.addAttribute("pager",pager);
		return "console/platform/bank/list";
	}
	
	
	/**查询*/
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String search(CommonLookup lookup) {
		setLookup(lookup);
		return "redirect:/console/platform/bank/";
	}
	
	/**前往新增页面*/
	@RequestMapping( value = "/0", method = RequestMethod.GET)
	public String add(Model model){
		return "console/platform/bank//form"; 
	}
	
	/**前往修改页面*/
	@RequestMapping( value = "/{id}", method = RequestMethod.GET)
	public String editor(@PathVariable int id, Model model){
		PlatformBank entity = platformBankService.findById(id);
		model.addAttribute("entity", entity);
		return "console/platform/bank//form"; 
	}
	
	/**保存*/
	@RequestMapping( value = "/{id}", method = RequestMethod.POST)
	public String save(@PathVariable int id, PlatformBank entity, RedirectAttributes attr){
		entity.setId(id);
		if(id == 0) {//to add
			platformBankService.insert(entity);
			attr.addFlashAttribute(Remind.success());
		}else if(id > 0 ){ //to update
			platformBankService.update(entity);
			attr.addFlashAttribute(Remind.success());
		}else {
			attr.addFlashAttribute(Remind.danger());
		}
		return "redirect:/console/platform/bank/";
	}
	
	/**单个删除*/
	@RequestMapping( value = "/{id}/remove", method = RequestMethod.GET)
	public String delete(@PathVariable int id, RedirectAttributes attributes){
		platformBankService.delete(id);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/platform/bank/";
	}
	
	/**批量删除*/
	@RequestMapping( value = "/remove", method = RequestMethod.POST)
	public String batchDelete(int[] ids, RedirectAttributes attributes){
		platformBankService.delete(ids);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/platform/bank/";
	}
	
}
