package com.vic.ck.console.platform.controller;

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
import com.vic.ck.console.platform.service.PlatformAdPositionService;
import com.vic.ck.console.system.area.service.SysAreaService;
import com.vic.ck.entity.PlatformAdPosition;



/**
 * 平台广告位表
 * 
 */
@Controller
@RequestMapping("/console/platform/ad")
public class PlatformAdPositionController extends BaseConsoleController{
	

	@Resource
	private PlatformAdPositionService platformAdPositionService;
	
	@Resource
	private SysAreaService sysAreaService;
	
	@ModelAttribute
	private void bindSelect(Model model) {
		model.addAttribute("cityList", sysAreaService.opendCityList());
	}
	
    /**列表页*/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String page(Model model) {
		PageInfo<PlatformAdPosition> pager =  platformAdPositionService.list(getLookup());
		model.addAttribute("pager",pager);
		return "console/platform/ad/list";
	}
	
	
	/**查询*/
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String search(CommonLookup lookup) {
		setLookup(lookup);
		return "redirect:/console/platform/ad/";
	}
	
	/**前往新增页面*/
	@RequestMapping( value = "/0", method = RequestMethod.GET)
	public String add(Model model){
		return "console/platform/ad/form"; 
	}
	
	/**前往修改页面*/
	@RequestMapping( value = "/{id}", method = RequestMethod.GET)
	public String editor(@PathVariable int id, Model model){
		PlatformAdPosition entity = platformAdPositionService.findById(id);
		model.addAttribute("entity", entity);
		return "console/platform/ad/form"; 
	}
	
	/**保存*/
	@RequestMapping( value = "/{id}", method = RequestMethod.POST)
	public String save(@PathVariable int id, PlatformAdPosition entity, RedirectAttributes attr){
		entity.setId(id);
		if(id == 0) {//to add
			platformAdPositionService.insert(entity);
			attr.addFlashAttribute(Remind.success());
		}else if(id > 0 ){ //to update
			platformAdPositionService.update(entity);
			attr.addFlashAttribute(Remind.success());
		}else {
			attr.addFlashAttribute(Remind.danger());
		}
		return "redirect:/console/platform/ad/";
	}
	
	/**单个删除*/
	@RequestMapping( value = "/{id}/remove", method = RequestMethod.GET)
	public String delete(@PathVariable int id, RedirectAttributes attributes){
		platformAdPositionService.delete(id);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/platform/ad/";
	}
	
	/**批量删除*/
	@RequestMapping( value = "/remove", method = RequestMethod.POST)
	public String batchDelete(int[] ids, RedirectAttributes attributes){
		platformAdPositionService.delete(ids);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/platform/ad/";
	}
	
	/**启用 禁用*/
	@RequestMapping( value = "/{id}/enabled", method = RequestMethod.GET)
	public String enabled(@PathVariable int id, boolean enabled, RedirectAttributes attributes){
		PlatformAdPosition entity = new PlatformAdPosition();
		entity.setId(id); entity.setEnabled(enabled);
		platformAdPositionService.update(entity);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/platform/ad/";
	}
	
	
	
	
}
