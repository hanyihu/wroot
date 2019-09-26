package com.vic.ck.console.system.menu.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vic.base.pager.CommonLookup;
import com.vic.base.pager.PageInfo;
import com.vic.base.pager.Remind;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.entity.Menu;
import com.vic.ck.console.system.menu.service.MenuService;


/**
 * 菜单管理controller
 * @author VIC
 *
 */
@Controller
@RequestMapping("/console")
public class MenuController extends BaseConsoleController{

	@Resource
	private MenuService menuService;
	
	/**列表页 不分页*/
	@RequestMapping(value = "/system/menu/", method = RequestMethod.GET)
	public String home(Model model){
		PageInfo<Menu> pager =  menuService.list(getLookup());
		model.addAttribute("pager",pager);
		return "console/system/menu/list";
	}
	
	/**search*/
	@RequestMapping(value = "/system/menu/", method = RequestMethod.POST)
	public String search(CommonLookup lookup){
		setLookup(lookup);
		return "redirect:/console/system/menu/";
	}
	
	
	/**前往新增一级模块页面*/
	@RequestMapping( value = "/system/menu/0", method = RequestMethod.GET)
	public String add(Model model){
		return "console/system/menu/form"; 
	}
	
	/**前往编辑一级模块页面*/
	@RequestMapping( value = "/system/menu/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable int id, Model model){
		Menu entity = menuService.getMeunById(id);
		model.addAttribute("entity", entity);
		return "console/system/menu/form"; 
	}
	
	/**保存一级模块数据*/
	@RequestMapping( value = "/system/menu/{id}", method = RequestMethod.POST)
	public String save(@PathVariable int id, Menu entity, RedirectAttributes attributes){
		entity.setId(id);
		if(id == 0) {//新增
			menuService.add(entity);
			attributes.addFlashAttribute(Remind.success().setMessage("新增成功！"));
		}else if(id > 0) {//修改
			menuService.update(entity);
			attributes.addFlashAttribute(Remind.success().setMessage("修改成功！"));
		}else {//数据错误
			attributes.addFlashAttribute(Remind.danger().setMessage("数据错误！"));
		}
		return "redirect:/console/system/menu/";
	}
	
	/**前往新增二级模块页面*/
	@RequestMapping( value = "/system/menu/sub/0", method = RequestMethod.GET)
	public String addSub(@RequestParam(defaultValue="0") Integer pId, Model model){
		Menu parent = menuService.getMeunById(pId);
		if(parent == null) {
			throw new IllegalArgumentException("不存在的父模块");
		}
		Menu entity = new Menu(parent.getId(), parent.getName());
		entity.setBtnList(menuService.findMenuBtn(0));
		model.addAttribute("entity", entity);
		return "console/system/menu/sub_form"; 
	}
	
	/**前往编辑二级模块页面*/
	@RequestMapping( value = "/system/menu/sub/{id}", method = RequestMethod.GET)
	public String editSub(@PathVariable int id, Model model){
		Menu entity = menuService.getMeunById(id);
		entity.setBtnList(menuService.findMenuBtn(id));
		model.addAttribute("entity", entity);
		return "console/system/menu/sub_form"; 
	}
	
	/**保存二级模块数据*/
	@RequestMapping( value = "/system/menu/sub/{id}", method = RequestMethod.POST)
	public String saveSub(@PathVariable int id, Menu entity, RedirectAttributes attributes){
		entity.setId(id);
		if(id == 0) {//新增
			menuService.add(entity);
			attributes.addFlashAttribute(Remind.success().setMessage("新增成功！"));
		}else if(id > 0) {//修改
			menuService.update(entity);
			attributes.addFlashAttribute(Remind.success().setMessage("修改成功！"));
		}else {//数据错误
			attributes.addFlashAttribute(Remind.danger().setMessage("数据错误！"));
		}
		return "redirect:/console/system/menu/";
	}
	
	
	/**单个删除*/
	@RequestMapping( value = "/system/menu/{id}/remove", method = RequestMethod.GET)
	public String delete(@PathVariable int id, RedirectAttributes attributes){
		menuService.delete(id);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/system/menu/";
	}
	
	/**批量删除*/
	@RequestMapping( value = "/system/menu/remove", method = RequestMethod.POST)
	public String batchDelete(int[] pks, RedirectAttributes attributes){
		menuService.delete(pks);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/system/menu/";
	}
	
	/**检测名称是否重复*/
	@RequestMapping( value = "/system/menu/check")
	@ResponseBody
	public boolean checkName(Integer id, String name, @RequestParam(required=false) Integer parentId){
		boolean isOk = menuService.checkName(id, name,parentId);
		return isOk;
	}

}
