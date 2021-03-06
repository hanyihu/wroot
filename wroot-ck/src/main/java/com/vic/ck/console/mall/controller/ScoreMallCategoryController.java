package com.vic.ck.console.mall.controller;

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
import com.vic.ck.console.mall.lookup.ScoreMallCategoryLookup;
import com.vic.ck.console.mall.service.ScoreMallCategoryService;
import com.vic.ck.entity.ScoreMallCategory;



/**
 * 积分商城分类
 * 
 */
@Controller
@RequestMapping("/console/mall/scoremallcategory")
public class ScoreMallCategoryController extends BaseConsoleController{
	

	@Resource
	private ScoreMallCategoryService scoreMallCategoryService;
	
    /**列表页*/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String page(Model model) {
		PageInfo<ScoreMallCategory> pager =  scoreMallCategoryService.list(getLookup());
		model.addAttribute("pager",pager);
		return "console/mall/mallCategory/list";
	}
	
	/**重写查询条件*/
	protected Lookup instanceLookup() {
		return new ScoreMallCategoryLookup();
	}
	
	/**查询*/
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String search(ScoreMallCategoryLookup lookup) {
		setLookup(lookup);
		return "redirect:/console/mall/scoremallcategory/";
	}
	
	/**前往新增页面*/
	@RequestMapping( value = "/0", method = RequestMethod.GET)
	public String add(Model model){
		return "console/mall/mallCategory/form"; 
	}
	
	/**前往修改页面*/
	@RequestMapping( value = "/{id}", method = RequestMethod.GET)
	public String editor(@PathVariable int id, Model model){
		ScoreMallCategory entity = scoreMallCategoryService.findById(id);
		model.addAttribute("entity", entity);
		return "console/mall/mallCategory/form"; 
	}
	
	/**保存*/
	@RequestMapping( value = "/{id}", method = RequestMethod.POST)
	public String save(@PathVariable int id, ScoreMallCategory entity, RedirectAttributes attr){
		entity.setId(id);
		if(id == 0) {//to add
			scoreMallCategoryService.insert(entity);
			attr.addFlashAttribute(Remind.success());
		}else if(id > 0 ){ //to update
			scoreMallCategoryService.update(entity);
			attr.addFlashAttribute(Remind.success());
		}else {
			attr.addFlashAttribute(Remind.danger());
		}
		return "redirect:/console/mall/scoremallcategory/";
	}
	
	/**单个删除*/
	@RequestMapping( value = "/{id}/remove", method = RequestMethod.GET)
	public String delete(@PathVariable int id, RedirectAttributes attributes){
		scoreMallCategoryService.delete(id);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/mall/scoremallcategory/";
	}
	
	/**批量删除*/
	@RequestMapping( value = "/remove", method = RequestMethod.POST)
	public String batchDelete(int[] ids, RedirectAttributes attributes){
		scoreMallCategoryService.delete(ids);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/mall/scoremallcategory/";
	}
	
}
