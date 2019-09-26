package com.vic.ck.console.msg.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vic.base.pager.CommonLookup;
import com.vic.base.pager.PageInfo;
import com.vic.base.pager.Remind;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.msg.service.PlatformMsgService;
import com.vic.ck.console.system.area.service.SysAreaService;
import com.vic.ck.entity.PlatformMsg;



/**
 * 系统的信息表
 * 
 */
@Controller
@RequestMapping("/console/msg/jpush")
public class PlatformMsgController extends BaseConsoleController{
	

	@Resource
	private PlatformMsgService platformMsgService;
	
	@Resource
	private SysAreaService sysAreaService;
	
	@ModelAttribute
	private void bindSelect(Model model) {
		model.addAttribute("cityList", sysAreaService.opendCityList());
	}
	
    /**列表页*/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String page(Model model) {
		PageInfo<PlatformMsg> pager =  platformMsgService.list(getLookup());
		model.addAttribute("pager",pager);
		return "console/msg/jpush/list";
	}
	
	
	/**查询*/
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String search(CommonLookup lookup) {
		setLookup(lookup);
		return "redirect:/console/msg/jpush/";
	}
	
	/**前往新增页面*/
	@RequestMapping( value = "/0", method = RequestMethod.GET)
	public String add(Model model){
		return "console/msg/jpush/form"; 
	}
	
	/**前往修改页面*/
	@RequestMapping( value = "/{id}", method = RequestMethod.GET)
	public String editor(@PathVariable int id, Model model){
		PlatformMsg entity = platformMsgService.findById(id);
		model.addAttribute("entity", entity);
		return "console/msg/jpush/form"; 
	}
	
	/**保存*/
	@RequestMapping( value = "/{id}", method = RequestMethod.POST)
	public String save(@PathVariable int id, PlatformMsg entity, RedirectAttributes attr){
		entity.setId(id);
		if(id == 0) {//to add
			platformMsgService.insert(entity);
			attr.addFlashAttribute(Remind.success());
		}else if(id > 0 ){ //to update
			platformMsgService.update(entity);
			attr.addFlashAttribute(Remind.success());
		}else {
			attr.addFlashAttribute(Remind.danger());
		}
		return "redirect:/console/msg/jpush/";
	}
	
	/**单个删除*/
	@RequestMapping( value = "/{id}/remove", method = RequestMethod.GET)
	public String delete(@PathVariable int id, RedirectAttributes attributes){
		platformMsgService.delete(id);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/msg/jpush/";
	}
	
	
	/**推送消息*/
	@RequestMapping( value = "/{id}/push", method = RequestMethod.GET)
	public String push(@PathVariable int id, RedirectAttributes attributes){
		platformMsgService.pushMsg(id);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/msg/jpush/";
	}
	
	
	/**批量删除*/
	@RequestMapping( value = "/remove", method = RequestMethod.POST)
	public String batchDelete(int[] ids, RedirectAttributes attributes){
		platformMsgService.delete(ids);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/msg/jpush/";
	}
	
}
