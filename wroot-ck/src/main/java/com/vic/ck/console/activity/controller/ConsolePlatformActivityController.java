package com.vic.ck.console.activity.controller;

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
import com.vic.ck.api.community.service.PlatformActivityService;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.activity.lookup.PlatformActivityLookup;
import com.vic.ck.entity.PlatformActivity;



/**
 * 平台活动
 * 
 */
@Controller
@RequestMapping("/console/activity/activity")
public class ConsolePlatformActivityController extends BaseConsoleController{
	

	@Resource
	private PlatformActivityService platformActivityService;
	
    /**列表页*/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String page(Model model) {
		PageInfo<PlatformActivity> pager =  platformActivityService.list(getLookup());
		model.addAttribute("pager",pager);
		return "console/activity/activity/list";
	}
	
	/**重写查询条件*/
	protected Lookup instanceLookup() {
		return new PlatformActivityLookup();
	}
	
	/**查询*/
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String search(PlatformActivityLookup lookup) {
		setLookup(lookup);
		return "redirect:/console/activity/activity/";
	}
	
	/**前往新增页面*/
	@RequestMapping( value = "/0", method = RequestMethod.GET)
	public String add(Model model){
		return "console/activity/activity/form"; 
	}
	
	/**前往修改页面*/
	@RequestMapping( value = "/{id}", method = RequestMethod.GET)
	public String editor(@PathVariable int id, Model model){
		PlatformActivity entity = platformActivityService.findById(id);
		model.addAttribute("entity", entity);
		return "console/activity/activity/form"; 
	}
	
	/**保存*/
	@RequestMapping( value = "/{id}", method = RequestMethod.POST)
	public String save(@PathVariable int id, PlatformActivity entity, RedirectAttributes attr){
		entity.setId(id);
		if(id == 0) {//to add
			platformActivityService.insert(entity);
			attr.addFlashAttribute(Remind.success());
		}else if(id > 0 ){ //to update
			platformActivityService.update(entity);
			attr.addFlashAttribute(Remind.success());
		}else {
			attr.addFlashAttribute(Remind.danger());
		}
		return "redirect:/console/activity/activity/";
	}
	
	/**
	 * 启用禁用
	 */
	@RequestMapping( value = "/{id}/enabled", method = RequestMethod.GET)
	public String status(@PathVariable int id, int enabled, RedirectAttributes attributes){
		PlatformActivity entity = new PlatformActivity();
		entity.setId(id); entity.setEnabled(enabled);
		platformActivityService.update(entity);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/activity/activity/";
	}
	
	/**
	 * 结算当前活动 把话费余额转入用户余额
	 */
	@RequestMapping( value = "/{id}/settlement", method = RequestMethod.GET)
	public String settlement(@PathVariable int id, RedirectAttributes attributes){
		platformActivityService.settlement();
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/activity/activity/";
	}
	
	
	
	/**单个删除*/
	@RequestMapping( value = "/{id}/remove", method = RequestMethod.GET)
	public String delete(@PathVariable int id, RedirectAttributes attributes){
		platformActivityService.delete(id);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/activity/activity/";
	}
	
	/**批量删除*/
	@RequestMapping( value = "/remove", method = RequestMethod.POST)
	public String batchDelete(int[] ids, RedirectAttributes attributes){
		platformActivityService.delete(ids);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/activity/activity/";
	}
	
}
