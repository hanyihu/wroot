package com.vic.ck.console.platform.controller;

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
import com.vic.ck.api.platform.service.PlatformCooperationService;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.platform.lookup.PlatformCooperationLookup;
import com.vic.ck.entity.PlatformCooperation;



/**
 * 合作申请表
 * 
 */
@Controller
@RequestMapping("/console/platform/cooperation")
public class PlatformCooperationController extends BaseConsoleController{
	

	@Resource
	private PlatformCooperationService platformCooperationService;
	
    /**列表页*/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String page(Model model) {
		PageInfo<PlatformCooperation> pager =  platformCooperationService.list(getLookup());
		model.addAttribute("pager",pager);
		return "console/platform/cooperation/list";
	}
	
	/**重写查询条件*/
	protected Lookup instanceLookup() {
		return new PlatformCooperationLookup();
	}
	
	/**查询*/
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String search(PlatformCooperationLookup lookup) {
		setLookup(lookup);
		return "redirect:/console/platform/cooperation/";
	}
	
	/**前往新增页面*/
	@RequestMapping( value = "/0", method = RequestMethod.GET)
	public String add(Model model){
		return "console/platform/cooperation/form"; 
	}
	
	/**前往修改页面*/
	@RequestMapping( value = "/{id}", method = RequestMethod.GET)
	public String editor(@PathVariable int id, Model model){
		PlatformCooperation entity = platformCooperationService.findById(id);
		model.addAttribute("entity", entity);
		return "console/ platform/cooperation/form"; 
	}
	
	/**保存*/
	@RequestMapping( value = "/{id}", method = RequestMethod.POST)
	public String save(@PathVariable int id, PlatformCooperation entity, RedirectAttributes attr){
		entity.setId(id);
		if(id == 0) {//to add
			platformCooperationService.insert(entity);
			attr.addFlashAttribute(Remind.success());
		}else if(id > 0 ){ //to update
			platformCooperationService.update(entity);
			attr.addFlashAttribute(Remind.success());
		}else {
			attr.addFlashAttribute(Remind.danger());
		}
		return "redirect:/console/platform/cooperation/";
	}
	
	/**单个删除*/
	@RequestMapping( value = "/{id}/remove", method = RequestMethod.GET)
	public String delete(@PathVariable int id, RedirectAttributes attributes){
		platformCooperationService.delete(id);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/platform/cooperation/";
	}
	
	/**修改处理状态*/
	@RequestMapping( value = "/{id}/status", method = RequestMethod.GET)
	public String status(@PathVariable int id, int status, RedirectAttributes attributes){
		PlatformCooperation entity = new PlatformCooperation();
		entity.setId(id); entity.setStatus(status);
		platformCooperationService.update(entity);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/platform/cooperation/";
	}
	
	/**批量删除*/
	@RequestMapping( value = "/remove", method = RequestMethod.POST)
	public String batchDelete(int[] ids, RedirectAttributes attributes){
		platformCooperationService.delete(ids);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/platform/cooperation/";
	}
	
}
