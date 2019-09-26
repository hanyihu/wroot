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
import com.vic.ck.api.platform.service.PlatformCommonService;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.platform.service.PlatformCategoryAdPositionService;
import com.vic.ck.console.system.area.service.SysAreaService;
import com.vic.ck.entity.PlatformCategoryAdPosition;



/**
 * 平台商品分类商家广告位
 * 
 */
@Controller
@RequestMapping("/console/platform/merchantCategoryAd")
public class PlatformCategoryAdPositionController extends BaseConsoleController{
	
	
	
	@Resource
	private PlatformCommonService platformCommonService;

	@Resource
	private PlatformCategoryAdPositionService platformCategoryAdPositionService;
	
	@Resource
	private SysAreaService sysAreaService;
	
	
	@ModelAttribute
	private void bindSelect(Model model) {
		model.addAttribute("cityList", sysAreaService.opendCityList());
		model.addAttribute("categoryList", platformCommonService.merchantCategories());
	}
	
	
    /**列表页*/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String page(Model model) {
		PageInfo<PlatformCategoryAdPosition> pager =  platformCategoryAdPositionService.list(getLookup());
		model.addAttribute("pager",pager);
		return "console/platform/merchantCategoryAd/list";
	}
	
	
	/**查询*/
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String search(CommonLookup lookup) {
		setLookup(lookup);
		return "redirect:/console/platform/merchantCategoryAd/";
	}
	
	/**前往新增页面*/
	@RequestMapping( value = "/0", method = RequestMethod.GET)
	public String add(Model model){
		return "console/platform/merchantCategoryAd/form"; 
	}
	
	/**前往修改页面*/
	@RequestMapping( value = "/{id}", method = RequestMethod.GET)
	public String editor(@PathVariable int id, Model model){
		PlatformCategoryAdPosition entity = platformCategoryAdPositionService.findById(id);
		model.addAttribute("entity", entity);
		return "console/platform/merchantCategoryAd/form"; 
	}
	
	/**保存*/
	@RequestMapping( value = "/{id}", method = RequestMethod.POST)
	public String save(@PathVariable int id, PlatformCategoryAdPosition entity, RedirectAttributes attr){
		entity.setId(id);
		if(id == 0) {//to add
			platformCategoryAdPositionService.insert(entity);
			attr.addFlashAttribute(Remind.success());
		}else if(id > 0 ){ //to update
			platformCategoryAdPositionService.update(entity);
			attr.addFlashAttribute(Remind.success());
		}else {
			attr.addFlashAttribute(Remind.danger());
		}
		return "redirect:/console/platform/merchantCategoryAd/";
	}
	
	/**单个删除*/
	@RequestMapping( value = "/{id}/remove", method = RequestMethod.GET)
	public String delete(@PathVariable int id, RedirectAttributes attributes){
		platformCategoryAdPositionService.delete(id);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/platform/merchantCategoryAd/";
	}
	
	/**批量删除*/
	@RequestMapping( value = "/remove", method = RequestMethod.POST)
	public String batchDelete(int[] ids, RedirectAttributes attributes){
		platformCategoryAdPositionService.delete(ids);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/platform/merchantCategoryAd/";
	}
	
	/**启用禁用*/
	@RequestMapping( value = "/{id}/enabled", method = RequestMethod.GET)
	public String enabled(@PathVariable int id, int enabled, RedirectAttributes attributes){
		PlatformCategoryAdPosition entity = new PlatformCategoryAdPosition();
		entity.setId(id); entity.setEnabled(enabled);
		platformCategoryAdPositionService.update(entity);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/platform/merchantCategoryAd/";
	}
	
}
