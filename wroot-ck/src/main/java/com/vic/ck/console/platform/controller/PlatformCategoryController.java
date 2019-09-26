package com.vic.ck.console.platform.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vic.base.model.BaseModel;
import com.vic.base.pager.CommonLookup;
import com.vic.base.pager.PageInfo;
import com.vic.base.pager.Remind;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.platform.service.PlatformCategoryService;
import com.vic.ck.console.system.area.service.SysAreaService;
import com.vic.ck.entity.PlatformCategory;
import com.vic.ck.entity.PlatformCategoryMerchant;
import com.vic.wroot.common.po.BaseResponse;

/**
 * 平台特色商家分类表 以及维系特色分类和商家的关系
 * 
 */
@Controller
@RequestMapping("/console/platform")
public class PlatformCategoryController extends BaseConsoleController {

	@Resource
	private PlatformCategoryService platformCategoryService;

	@Resource
	private SysAreaService sysAreaService;

	@ModelAttribute
	private void bindSelect(Model model) {
		List<BaseModel> cityList = sysAreaService.opendCityList();
		model.addAttribute("cityList", cityList);
	}

	/** 列表页 */
	@RequestMapping(value = "/category/", method = RequestMethod.GET)
	public String page(Model model) {
		PageInfo<PlatformCategory> pager = platformCategoryService.list(getLookup());
		model.addAttribute("pager", pager);
		return "console/platform/category/list";
	}

	/** 查询 */
	@RequestMapping(value = "/category/", method = RequestMethod.POST)
	public String search(CommonLookup lookup) {
		setLookup(lookup);
		return "redirect:/console/platform/category/";
	}

	/** 前往新增页面 */
	@RequestMapping(value = "/category/0", method = RequestMethod.GET)
	public String add(Model model) {
		return "console/platform/category/form";
	}

	/** 前往修改页面 */
	@RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
	public String editor(@PathVariable int id, Model model) {
		PlatformCategory entity = platformCategoryService.findById(id);
		model.addAttribute("entity", entity);
		return "console/platform/category/form";
	}

	/** 保存 */
	@RequestMapping(value = "/category/{id}", method = RequestMethod.POST)
	public String save(@PathVariable int id, PlatformCategory entity, RedirectAttributes attr) {
		entity.setId(id);
		if (id == 0) {// to add
			platformCategoryService.insert(entity);
			attr.addFlashAttribute(Remind.success());
		} else if (id > 0) { // to update
			platformCategoryService.update(entity);
			attr.addFlashAttribute(Remind.success());
		} else {
			attr.addFlashAttribute(Remind.danger());
		}
		return "redirect:/console/platform/category/";
	}

	/** 单个删除 */
	@RequestMapping(value = "/category/{id}/remove", method = RequestMethod.GET)
	public String delete(@PathVariable int id, RedirectAttributes attributes) {
		platformCategoryService.delete(id);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/platform/category/";
	}

	/** 批量删除 */
	@RequestMapping(value = "/category/remove", method = RequestMethod.POST)
	public String batchDelete(int[] ids, RedirectAttributes attributes) {
		platformCategoryService.delete(ids);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/platform/category/";
	}

	/**
	 * 启用/禁用
	 */
	@RequestMapping(value = "/category/{id}/enabled", method = RequestMethod.GET)
	public String enabled(@PathVariable int id, int enabled, RedirectAttributes attributes) {
		PlatformCategory entity = new PlatformCategory();
		entity.setId(id);
		entity.setEnabled(enabled);
		platformCategoryService.update(entity);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/platform/category/";
	}

	/**
	 * 获取当前城市下的特色分类
	 */
	@RequestMapping(value = "/category/cityCategoryList", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse findCityCategoryList(int id) {
		List<BaseModel> list = platformCategoryService.findCityCategoryList(id);
		return BaseResponse.success(list);
	}

	/*************************** 分类和商家的关系的维系 ↓ ↓ ***************************************************************************/
	
	/** 列表页 */
	@RequestMapping(value = "/categoryRel/", method = RequestMethod.GET)
	public String pageRel(Model model) {
		PageInfo<PlatformCategoryMerchant> pager = platformCategoryService.relList(getLookup("rel"));
		model.addAttribute("pager", pager);
		return "console/platform/category/rellist";
	}

	/** 查询 */
	@RequestMapping(value = "/categoryRel/", method = RequestMethod.POST)
	public String searchRel(CommonLookup lookup) {
		setLookup(lookup, "rel");
		return "redirect:/console/platform/categoryRel/"; 
	}

	/** 前往新增页面 */
	@RequestMapping(value = "/categoryRel/0", method = RequestMethod.GET)
	public String addRel(Model model) {
		return "console/platform/category/relform";
	}

	/** 前往修改页面 */
	@RequestMapping(value = "/categoryRel/{id}", method = RequestMethod.GET)
	public String editorRel(@PathVariable int id, Model model) {
		PlatformCategoryMerchant entity = platformCategoryService.findByIdRel(id);
		model.addAttribute("entity", entity);
		return "console/platform/category/relform";
	}

	/** 保存 */
	@RequestMapping(value = "/categoryRel/{id}", method = RequestMethod.POST)
	public String saveRel(@PathVariable int id, PlatformCategoryMerchant entity, RedirectAttributes attr) {
		entity.setId(id);
		if (id == 0) {// to add
			platformCategoryService.insertRel(entity);
			attr.addFlashAttribute(Remind.success());
		} else if (id > 0) { // to update
			platformCategoryService.updateRel(entity);
			attr.addFlashAttribute(Remind.success());
		} else {
			attr.addFlashAttribute(Remind.danger());
		}
		return "redirect:/console/platform/categoryRel/";
	}

	/** 单个删除 */
	@RequestMapping(value = "/categoryRel/{id}/remove", method = RequestMethod.GET)
	public String deleteRel(@PathVariable int id, RedirectAttributes attributes) {
		platformCategoryService.deleteRel(id);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/platform/categoryRel/";
	}

	/** 批量删除 */
	@RequestMapping(value = "/categoryRel/remove", method = RequestMethod.POST)
	public String batchDeleteRel(int[] ids, RedirectAttributes attributes) {
		platformCategoryService.deleteRel(ids);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/platform/categoryRel/";
	}

}
