package com.vic.ck.console.mall.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vic.base.pager.CommonLookup;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.base.pager.Remind;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.mall.lookup.ScoreMallGoodsLookup;
import com.vic.ck.console.mall.service.ScoreMallCategoryService;
import com.vic.ck.console.mall.service.ScoreMallGoodsService;
import com.vic.ck.entity.ScoreMallCategory;
import com.vic.ck.entity.ScoreMallGoods;



/**
 * 积分商城商品
 * 
 */
@Controller
@RequestMapping("/console/mall/goods")
public class ScoreMallGoodsController extends BaseConsoleController{
	

	@Resource
	private ScoreMallGoodsService scoreMallGoodsService;
	
	
	@Resource
	private ScoreMallCategoryService categoryService;
	
	@ModelAttribute(value="categoryList")
	public List<ScoreMallCategory> bindCategoryList() {
		return categoryService.categorys(new CommonLookup());
	}
	
    /**列表页*/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String page(Model model) {
		PageInfo<ScoreMallGoods> pager =  scoreMallGoodsService.list(getLookup());
		model.addAttribute("pager",pager);
		return "console/mall/goods/list";
	}

	/**重写查询条件*/
	protected Lookup instanceLookup() {
		return new ScoreMallGoodsLookup();
	}
	
	/**查询*/
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String search(ScoreMallGoodsLookup lookup) {
		setLookup(lookup);
		return "redirect:/console/mall/goods/";
	}
	
	/**前往新增页面*/
	@RequestMapping( value = "/0", method = RequestMethod.GET)
	public String add(Model model){
		return "console/mall/goods/form"; 
	}
	
	/**前往修改页面*/
	@RequestMapping( value = "/{id}", method = RequestMethod.GET)
	public String editor(@PathVariable int id, Model model){
		ScoreMallGoods entity = scoreMallGoodsService.findById(id);
		model.addAttribute("entity", entity);
		return "console/mall/goods/form"; 
	}
	
	/**保存*/
	@RequestMapping( value = "/{id}", method = RequestMethod.POST)
	public String save(@PathVariable int id, ScoreMallGoods entity, RedirectAttributes attr){
		entity.setId(id);
		if(id == 0) {//to add
			scoreMallGoodsService.insert(entity);
			attr.addFlashAttribute(Remind.success());
		}else if(id > 0 ){ //to update
			scoreMallGoodsService.update(entity);
			attr.addFlashAttribute(Remind.success());
		}else {
			attr.addFlashAttribute(Remind.danger());
		}
		return "redirect:/console/mall/goods/";
	}
	
	/**上下架*/
	@RequestMapping( value = "/{id}/enabled", method = RequestMethod.GET)
	public String enabled(@PathVariable int id, int enabled, RedirectAttributes attributes){
		ScoreMallGoods entity = new ScoreMallGoods();
		entity.setId(id); entity.setEnabled(enabled);
		scoreMallGoodsService.updateStatus(entity);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/mall/goods/";
	}
	
	/**单个删除*/
	@RequestMapping( value = "/{id}/remove", method = RequestMethod.GET)
	public String delete(@PathVariable int id, RedirectAttributes attributes){
		scoreMallGoodsService.delete(id);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/mall/goods/";
	}
	
	/**批量删除*/
	@RequestMapping( value = "/remove", method = RequestMethod.POST)
	public String batchDelete(int[] ids, RedirectAttributes attributes){
		scoreMallGoodsService.delete(ids);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/mall/goods/";
	}
	
}
