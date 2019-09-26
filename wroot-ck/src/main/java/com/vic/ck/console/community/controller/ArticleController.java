package com.vic.ck.console.community.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.base.pager.Remind;
import com.vic.ck.api.community.service.ArticleService;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.community.lookup.ArticleLookup;
import com.vic.ck.console.community.service.ArticleCategoryService;
import com.vic.ck.console.system.area.service.SysAreaService;
import com.vic.ck.entity.Article;

import java.util.Date;


/**
 * 文章表
 * 
 */
@Controller("consoleArticleController")
@RequestMapping("/console/community/article")
public class ArticleController extends BaseConsoleController{
	

	@Resource
	private ArticleService articleService;
	
	@Resource
	private SysAreaService sysAreaService;
	
	@Resource
	private ArticleCategoryService articleCategoryService;
	
	
	@ModelAttribute
	public void bindForSelect(Model model){
		model.addAttribute("cityList", sysAreaService.opendCityList());
		model.addAttribute("categoryList", articleCategoryService.all());
	}
	
	
    /**列表页*/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String page(Model model) {
		PageInfo<Article> pager =  articleService.list(getLookup());
		model.addAttribute("pager",pager);
		return "console/community/article/list";
	}
	
	/**重写查询条件*/
	protected Lookup instanceLookup() {
		return new ArticleLookup();
	}
	
	/**查询*/
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String search(ArticleLookup lookup) {
		setLookup(lookup);
		return "redirect:/console/community/article/";
	}
	
	/**前往新增页面*/
	@RequestMapping( value = "/0", method = RequestMethod.GET)
	public String add(Model model){
		return "console/community/article/form"; 
	}
	
	/**前往修改页面*/
	@RequestMapping( value = "/{id}", method = RequestMethod.GET)
	public String editor(@PathVariable int id, Model model){
		Article entity = articleService.findById(id);
		model.addAttribute("entity", entity);
		return "console/community/article/form"; 
	}
	
	/**保存*/
	@RequestMapping( value = "/{id}", method = RequestMethod.POST)
	public String save(@PathVariable int id, Article entity, RedirectAttributes attr){
		entity.setId(id);
		Integer cityId = getManageCityId();
		if(cityId  != null) {
			entity.setCityId(cityId);
		}
		if(id == 0) {//to add
			entity.setCreateTime(new Date());
			articleService.insert(entity);
			attr.addFlashAttribute(Remind.success());
		}else if(id > 0 ){ //to update
			articleService.update(entity);
			attr.addFlashAttribute(Remind.success());
		}else {
			attr.addFlashAttribute(Remind.danger());
		}
		return "redirect:/console/community/article/";
	}
	
	/**单个删除*/
	@RequestMapping( value = "/{id}/remove", method = RequestMethod.GET)
	public String delete(@PathVariable int id, RedirectAttributes attributes){
		articleService.delete(id);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/community/article/";
	}
	
	/**批量删除*/
	@RequestMapping( value = "/remove", method = RequestMethod.POST)
	public String batchDelete(int[] ids, RedirectAttributes attributes){
		articleService.delete(ids);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/community/article/";
	}
	
}
