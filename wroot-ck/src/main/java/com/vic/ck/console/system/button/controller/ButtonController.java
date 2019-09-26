package com.vic.ck.console.system.button.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vic.base.BaseController;
import com.vic.base.pager.CommonLookup;
import com.vic.base.pager.PageInfo;
import com.vic.base.pager.Remind;
import com.vic.ck.console.entity.Button;
import com.vic.ck.console.system.button.service.ButtonService;

/**
 *按钮列表controller
 * @author VIC
 *
 */
@Controller
@RequestMapping("/console")
public class ButtonController extends BaseController {
	@Resource
	private ButtonService buttonService;

	/**
	 * 列表
	 * @return
	 */
	@RequestMapping(value = "/system/btn/", method = RequestMethod.GET)
	public String home(Model model){
		PageInfo<Button> pager = buttonService.list(getLookup()); 
		model.addAttribute("pager",pager);
		return "console/system/button/list";
	}
	
	
	@RequestMapping(value = "/system/btn/", method = RequestMethod.POST)
	public String sesrch(CommonLookup lookup){
		setLookup(lookup);
		return "redirect:/console/system/btn/"; 
	}
	
	/**前往新增页面*/
	@RequestMapping( value = "/system/btn/0", method = RequestMethod.GET)
	public String add(Model model){
		return "console/system/button/form"; 
	}
	
	/**前往修改页面*/
	@RequestMapping( value = "/system/btn/{id}", method = RequestMethod.GET)
	public String editor(@PathVariable int id, Model model){
		Button entity = buttonService.getButtonId(id);
		model.addAttribute("entity", entity);
		return "console/system/button/form"; 
	}
	
	/**添加按钮*/
	@RequestMapping( value = "/system/btn/{id}", method = RequestMethod.POST)
	public String save(@PathVariable int id, Button entity, RedirectAttributes attr){
		entity.setId(id);
		if(id == 0) {//to add
			buttonService.addButton(entity);
			attr.addFlashAttribute(Remind.success());
		}else if(id > 0 ){ //to update
			buttonService.updateButton(entity);
			attr.addFlashAttribute(Remind.success());
		}else {
			attr.addFlashAttribute(Remind.danger());
		}
		
		return "redirect:/console/system/btn/"; 
	}
	
	/**单个删除*/
	@RequestMapping( value = "/system/btn/{id}/remove", method = RequestMethod.GET)
	public String delete(@PathVariable int id, RedirectAttributes attributes){
		int count=buttonService.checkID(id);
		if(count>0){
			attributes.addFlashAttribute(Remind.danger().setMessage("禁止删除"));
		}else{
			buttonService.deleteButton(id);
			attributes.addFlashAttribute(Remind.success());
		}
		return "redirect:/console/system/btn/";
	}
	
	/**批量删除*/
	@RequestMapping( value = "/system/btn/remove", method = RequestMethod.POST)
	public String batchDelete(int[] ids, RedirectAttributes attributes){
		buttonService.deleteButton(ids);
		int count=buttonService.checkID(ids);
		if(count>0){
			attributes.addFlashAttribute(Remind.danger().setMessage("禁止删除"));
		}else{
			attributes.addFlashAttribute(Remind.success());
		}		
		return "redirect:/console/system/btn/";
	}
	
	/**检测名称是否重复*/
	@RequestMapping( value = "/system/btn/check")
	@ResponseBody
	public boolean checkName(Integer id, String name){
		boolean isOk = buttonService.checkName(id,name);
		return isOk;
	}
	
}
