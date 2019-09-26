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
import com.vic.ck.console.platform.lookup.PlatformFeedbackLookup;
import com.vic.ck.console.platform.service.PlatformFeedbackService;
import com.vic.ck.entity.PlatformFeedback;



/**
 * 意见反馈表
 * 
 */
@Controller
@RequestMapping("/console/platform/feedback/")
public class PlatformFeedbackController extends BaseConsoleController{
	

	@Resource
	private PlatformFeedbackService platformFeedbackService;
	
    /**列表页*/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String page(Model model) {
		PageInfo<PlatformFeedback> pager =  platformFeedbackService.list(getLookup());
		model.addAttribute("pager",pager);
		return "console/platform/feedback/list";
	}
	
	/**重写查询条件*/
	protected Lookup instanceLookup() {
		return new PlatformFeedbackLookup();
	}
	
	/**查询*/
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String search(PlatformFeedbackLookup lookup) {
		setLookup(lookup);
		return "redirect:/console/platform/feedback/";
	}
	
	/**前往新增页面*/
	@RequestMapping( value = "/0", method = RequestMethod.GET)
	public String add(Model model){
		return "console/platform/feedback/form"; 
	}
	
	/**前往修改页面*/
	@RequestMapping( value = "/{id}", method = RequestMethod.GET)
	public String editor(@PathVariable int id, Model model){
		PlatformFeedback entity = platformFeedbackService.findById(id);
		model.addAttribute("entity", entity);
		return "console/platform/feedback/form"; 
	}
	
	/**保存*/
	@RequestMapping( value = "/{id}", method = RequestMethod.POST)
	public String save(@PathVariable int id, PlatformFeedback entity, RedirectAttributes attr){
		entity.setId(id);
		if(id == 0) {//to add
			platformFeedbackService.insert(entity);
			attr.addFlashAttribute(Remind.success());
		}else if(id > 0 ){ //to update
			entity.setStatus(1);
			platformFeedbackService.update(entity);
			attr.addFlashAttribute(Remind.success());
		}else {
			attr.addFlashAttribute(Remind.danger());
		}
		return "redirect:/console/platform/feedback/";
	}
	
	/**单个删除*/
	@RequestMapping( value = "/{id}/remove", method = RequestMethod.GET)
	public String delete(@PathVariable int id, RedirectAttributes attributes){
		platformFeedbackService.delete(id);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/platform/feedback/";
	}
	
	/**批量删除*/
	@RequestMapping( value = "/remove", method = RequestMethod.POST)
	public String batchDelete(int[] ids, RedirectAttributes attributes){
		platformFeedbackService.delete(ids);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/platform/feedback/";
	}
	
}
