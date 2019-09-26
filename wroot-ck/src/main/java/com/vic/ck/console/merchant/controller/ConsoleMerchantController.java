package com.vic.ck.console.merchant.controller;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.vic.ck.entity.Merchant;
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
import com.vic.ck.api.platform.service.PlatformCommonService;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.merchant.lookup.MerchantLookup;
import com.vic.ck.console.merchant.service.ConsoleMerchantService;
import com.vic.ck.console.system.area.service.SysAreaService;
import com.vic.ck.entity.ConsoleMerchant;
import com.vic.ck.entity.MerchantAuthentication;
import com.vic.ck.entity.PlatformMerchantCategory;

/**
 * 商户表-申请时产生数据
 * 
 */
@Controller
@RequestMapping("/console/merchant/merchant")
public class ConsoleMerchantController extends BaseConsoleController {

	@Resource
	private ConsoleMerchantService consoleMerchantService;

	@Resource
	private SysAreaService sysAreaService;

	@Resource
	private PlatformCommonService platformCommonService;

	@ModelAttribute
	public void bindSelect(Model model) {
		// 全部省
		List<BaseModel> provinceList = sysAreaService.provinces();
		model.addAttribute("provinceList", provinceList);
		// 商家分类
		List<PlatformMerchantCategory> categoryList = platformCommonService.merchantCategories();
		model.addAttribute("categoryList", categoryList);
	}

	/** 列表页 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String page(Model model) {
		PageInfo<ConsoleMerchant> pager = consoleMerchantService.list(getLookup());
		model.addAttribute("pager", pager);
		return "console/merchant/merchant/list";
	}

	/** 重写查询条件 */
	protected Lookup instanceLookup() {
		return new MerchantLookup();
	}

	/** 查询 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String search(MerchantLookup lookup) {
		setLookup(lookup);
		return "redirect:/console/merchant/merchant/";
	}

	/** 前往详情页面 */
	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable int id, Model model) {

		ConsoleMerchant entity = consoleMerchantService.findById(id);
		model.addAttribute("entity", entity);
		//认证信息
		MerchantAuthentication auth = consoleMerchantService.findMerchantAuthentication(id);
		model.addAttribute("auth", auth);
		return "console/merchant/merchant/detail";
	}
	
	/** 前往编辑页面 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable int id, Model model) {
		if (id ==0){

		}else if (id>0){
			Merchant entity = consoleMerchantService.findByIdAll(id);
			model.addAttribute("entity", entity);
		}

		return "console/merchant/merchant/form";
	}
	/** 商家信息批量删除*/
	@RequestMapping("/remove")
	public String removeAll(int[] ids,RedirectAttributes attr){
		int i = consoleMerchantService.deleteAll(ids);
		if (i>0){
			attr.addFlashAttribute(Remind.success());
		}else {
			attr.addFlashAttribute(Remind.danger());
		}
		return "redirect:/console/merchant/merchant/";
	}

	/**
	 * 商家回收站
	 * @param model
	 * @return
	 */
	@RequestMapping("/0/state")
	public String theRecycleBin(Model model){
		PageInfo<ConsoleMerchant> states = consoleMerchantService.findByState(getLookup(), 0);
		model.addAttribute("pager",states);
		return "console/merchant/merchant/list";
	}


	/** 保存 */
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String save(@PathVariable int id, ConsoleMerchant entity, RedirectAttributes attr) {
		entity.setId(id);
		if (id == 0) {// to add
			entity.setState(1);
			consoleMerchantService.insert(entity);
			attr.addFlashAttribute(Remind.success());
		} else if (id > 0) { // to update
			consoleMerchantService.update(entity);

			attr.addFlashAttribute(Remind.success());
		} else {
			attr.addFlashAttribute(Remind.danger());
		}
		return "redirect:/console/merchant/merchant/";
	}
	
	/*************************************************************/

	/** 商家审核 */
	@RequestMapping(value = "/{id}/status", method = RequestMethod.GET)
	public String status(@PathVariable int id, int status, RedirectAttributes attributes) {
		ConsoleMerchant entity = new ConsoleMerchant();
		entity.setId(id);
		entity.setStatus(status);
		consoleMerchantService.update(entity);
		if (status == 2) {
			consoleMerchantService.afterThroughAudit(id);
		}

		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/merchant/merchant/";
	}

	/************************************** lookup 提供给其他页面选择商家 ********************************************************/
	
	/**
	 * 从首页广告位跳转到商家列表选择商家; 携带过来城市id
	 */
	@RequestMapping(value = "/toLookup/", method = RequestMethod.GET)
	public String toLookup(MerchantLookup lookup) {
		lookup.setStatus(2);//已经审核的
		setLookup(lookup, "lookup");
		return "redirect:/console/merchant/merchant/lookup/";
	}
	
	
	/** 列表页 for首页广告位 */
	@RequestMapping(value = "/lookup/", method = RequestMethod.GET)
	public String pageLookup(Model model) {
		PageInfo<ConsoleMerchant> pager = consoleMerchantService.list(getLookup("lookup"));
		model.addAttribute("pager", pager);
		return "console/merchant/merchant/lookup";
	}

	/** 查询 for首页广告位*/
	@RequestMapping(value = "/lookup/", method = RequestMethod.POST)
	public String searchLookup(MerchantLookup lookup) {
		setLookup(lookup, "lookup");
		return "redirect:/console/merchant/merchant/lookup/";
	}


	/** ----------------------------------------------------------------------------- */
	
	/**
	 *  从商家分类广告位跳转到商家列表选择商家; 携带过来城市id和分类id
	 */
	@RequestMapping(value = "/toLookup2/", method = RequestMethod.GET)
	public String toLookup2(MerchantLookup lookup) {
		lookup.setStatus(2);//已经审核的
		setLookup(lookup, "lookup2");
		return "redirect:/console/merchant/merchant/lookup2/";
	}
	
	/** 列表页 for商家分类广告位 */
	@RequestMapping(value = "/lookup2/", method = RequestMethod.GET)
	public String pageLookup2(Model model) {
		PageInfo<ConsoleMerchant> pager = consoleMerchantService.list(getLookup("lookup2"));
		model.addAttribute("pager", pager);
		return "console/merchant/merchant/lookup2";
	}

	/** 查询 for商家分类广告位*/
	@RequestMapping(value = "/lookup2/", method = RequestMethod.POST)
	public String searchLookup2(MerchantLookup lookup) {
		setLookup(lookup, "lookup2");
		return "redirect:/console/merchant/merchant/lookup2/";
	}
	
	
	

}
