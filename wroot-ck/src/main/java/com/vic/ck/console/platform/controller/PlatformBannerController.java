package com.vic.ck.console.platform.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vic.base.model.BaseModel;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.base.pager.Remind;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.platform.lookup.PlatformBannerLookup;
import com.vic.ck.console.platform.service.PlatformBannerService;
import com.vic.ck.console.system.area.service.SysAreaService;
import com.vic.ck.entity.PlatformBanner;



/**
 * banner图
 * 
 */
@Controller
@RequestMapping("/console/platform/banner")
public class PlatformBannerController extends BaseConsoleController{
	

	@Resource
	private PlatformBannerService platformBannerService;
	
	@Resource
	private SysAreaService sysAreaService;
	
	
	@ModelAttribute(value="cityList")
	public List<BaseModel> bindCityList(){
		return sysAreaService.opendCityList();
	}
	
    /**列表页*/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String page(Model model) {
		PageInfo<PlatformBanner> pager =  platformBannerService.list(getLookup());
		model.addAttribute("pager",pager);
		
		return "console/platform/banner/list";
	}
	
	/**重写查询条件*/
	protected Lookup instanceLookup() {
		return new PlatformBannerLookup();
	}
	
	/**查询*/
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String search(PlatformBannerLookup lookup) {
		setLookup(lookup);
		return "redirect:/console/platform/banner/";
	}
	
	/**前往新增页面*/
	@RequestMapping( value = "/0", method = RequestMethod.GET)
	public String add(Model model){
		model.addAttribute("cityList", sysAreaService.opendCityList());
		return "console/platform/banner/form"; 
	}
	
	/**前往修改页面*/
	@RequestMapping( value = "/{id}", method = RequestMethod.GET)
	public String editor(@PathVariable int id, Model model){
		PlatformBanner entity = platformBannerService.findById(id);
		model.addAttribute("entity", entity);
		return "console/platform/banner/form"; 
	}
	
	/**保存*/
	@RequestMapping( value = "/{id}", method = RequestMethod.POST)
	public String save(@PathVariable int id, PlatformBanner entity, RedirectAttributes attr){
		entity.setId(id);
		if(id == 0) {//to add
			platformBannerService.insert(entity);
			attr.addFlashAttribute(Remind.success());
		}else if(id > 0 ){ //to update
			platformBannerService.update(entity);
			attr.addFlashAttribute(Remind.success());
		}else {
			attr.addFlashAttribute(Remind.danger());
		}
		return "redirect:/console/platform/banner/";
	}
	
	/**单个删除*/
	@RequestMapping( value = "/{id}/remove", method = RequestMethod.GET)
	public String delete(@PathVariable int id, RedirectAttributes attributes){
		platformBannerService.delete(id);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/platform/banner/";
	}
	
	/**批量删除*/
	@RequestMapping( value = "/remove", method = RequestMethod.POST)
	public String batchDelete(int[] ids, RedirectAttributes attributes){
		platformBannerService.delete(ids);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/platform/banner/";
	}
	
}
