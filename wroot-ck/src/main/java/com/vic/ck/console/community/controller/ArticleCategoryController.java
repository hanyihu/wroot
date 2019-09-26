package com.vic.ck.console.community.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vic.base.pager.CommonLookup;
import com.vic.base.pager.PageInfo;
import com.vic.base.pager.Remind;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.community.service.ArticleCategoryService;
import com.vic.ck.entity.ArticleCategory;

/**
 * 文章分类表
 * 
 */
@Controller
@RequestMapping("/console/community/articleCategory")
public class ArticleCategoryController extends BaseConsoleController {

	@Resource
	private ArticleCategoryService articleCategoryService;

	/** 列表页 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String page(Model model) {
		PageInfo<ArticleCategory> pager = articleCategoryService.list(getLookup());
		model.addAttribute("pager", pager);
		return "console/community/articleCategory/list";
	}

	/** 查询 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String search(CommonLookup lookup ) {
		setLookup(lookup);

		return "redirect:/console/community/articleCategory/";
	}

	/** 前往新增页面 */
	@RequestMapping(value = "/0", method = RequestMethod.GET)
	public String add(Model model) {
		return "console/community/articleCategory/form";
	}

	/** 前往修改页面 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String editor(@PathVariable int id, Model model) {
		ArticleCategory entity = articleCategoryService.findById(id);
		model.addAttribute("entity", entity);
		return "console/community/articleCategory/form";
	}

	/** 保存 */
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String save(@PathVariable int id, ArticleCategory entity, RedirectAttributes attr) {
		entity.setId(id);
		if (id == 0) {// to add
			articleCategoryService.insert(entity);
			attr.addFlashAttribute(Remind.success());
		} else if (id > 0) { // to update
			articleCategoryService.update(entity);
			attr.addFlashAttribute(Remind.success());
		} else {
			attr.addFlashAttribute(Remind.danger());
		}
		return "redirect:/console/community/articleCategory/";
	}

	/** 单个删除 */
	@RequestMapping(value = "/{id}/remove", method = RequestMethod.GET)
	public String delete(@PathVariable int id, RedirectAttributes attributes) {
		articleCategoryService.delete(id);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/community/articleCategory/";
	}

	/** 批量删除 */
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String batchDelete(int[] ids, RedirectAttributes attributes) {
		articleCategoryService.delete(ids);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/community/articleCategory/";
	}

}
