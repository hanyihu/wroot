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
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.platform.lookup.PlatformAppVersionLookup;
import com.vic.ck.console.platform.service.PlatformAppVersionService;
import com.vic.ck.entity.PlatformAppVersion;



/**
 * app版本管理

 * 
 */
@Controller
@RequestMapping("/console/platform/version")
public class PlatformAppVersionController extends BaseConsoleController{
	

	@Resource
	private PlatformAppVersionService platformAppVersionService;
	
    /**列表页*/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String page(Model model) {
		PageInfo<PlatformAppVersion> pager =  platformAppVersionService.list(getLookup());
		model.addAttribute("pager",pager);
		return "console/platform/version/list";
	}
	
	/**重写查询条件*/
	protected Lookup instanceLookup() {
		return new PlatformAppVersionLookup();
	}
	
	/**查询*/
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String search(PlatformAppVersionLookup lookup) {
		setLookup(lookup);
		return "redirect:/console/platform/version/";
	}
	
	/**前往新增页面*/
	@RequestMapping( value = "/0", method = RequestMethod.GET)
	public String add(Model model){
		return "console/platform/version/form"; 
	}
	
	/**前往修改页面*/
	@RequestMapping( value = "/{id}", method = RequestMethod.GET)
	public String editor(@PathVariable int id, Model model){
		PlatformAppVersion entity = platformAppVersionService.findById(id);
		model.addAttribute("entity", entity);
		return "console/platform/version/form"; 
	}
	
	/**保存*/
	@RequestMapping( value = "/{id}", method = RequestMethod.POST)
	public String save(@PathVariable int id, PlatformAppVersion entity, RedirectAttributes attr){
		entity.setId(id);
		if(id == 0) {//to add
			platformAppVersionService.insert(entity);
			attr.addFlashAttribute(Remind.success());
		}else if(id > 0 ){ //to update
			platformAppVersionService.update(entity);
			attr.addFlashAttribute(Remind.success());
		}else {
			attr.addFlashAttribute(Remind.danger());
		}
		return "redirect:/console/platform/version/";
	}
	
	/**单个删除*/
	@RequestMapping( value = "/{id}/remove", method = RequestMethod.GET)
	public String delete(@PathVariable int id, RedirectAttributes attributes){
		platformAppVersionService.delete(id);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/platform/version/";
	}
	
	/**批量删除*/
	@RequestMapping( value = "/remove", method = RequestMethod.POST)
	public String batchDelete(int[] ids, RedirectAttributes attributes){
		platformAppVersionService.delete(ids);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/platform/version/";
	}
	
}
