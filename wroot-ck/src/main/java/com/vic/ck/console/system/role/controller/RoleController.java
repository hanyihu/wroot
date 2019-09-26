package com.vic.ck.console.system.role.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.vic.base.Principal;
import com.vic.base.pager.CommonLookup;
import com.vic.base.pager.PageInfo;
import com.vic.base.pager.Remind;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.entity.Menu;
import com.vic.ck.console.entity.Role;
import com.vic.ck.console.system.menu.service.MenuService;
import com.vic.ck.console.system.role.po.AuthorityTree;
import com.vic.ck.console.system.role.service.RoleService;
/**
 * 角色管理controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/console")
public class RoleController extends BaseConsoleController{

	@Resource
	private RoleService roleService;
	
	
	@Resource
	private MenuService menuService;
	/**
	 * 跳转到列表页，并显示数据
	 * 根据查询条件查询
	 */
	@RequestMapping(value = "/system/role/", method = RequestMethod.GET)
	public String home(Model model){
		PageInfo<Role> pager =  roleService.list(getLookup());
		model.addAttribute("pager",pager);
		return "console/system/role/list";
	}
	
	
	/**
	 * 封装查询条件
	 */
	@RequestMapping(value = "/system/role/", method = RequestMethod.POST)
	public String search(CommonLookup lookup){
		setLookup(lookup);
		return "redirect:/console/system/role/";
	}
	
	/**
	 * 跳转到权限配置页面
	 * @return
	 */
	@RequestMapping( value = "/system/role/authority/{id}", method = RequestMethod.GET)
	public String authority(@PathVariable int id, Model model){
		model.addAttribute("roleId", id);
		List<AuthorityTree> tree = menuService.authorityTree(id);
		String treeJson = JSON.toJSONString(tree);
		model.addAttribute("treeJson", treeJson);
		return "console/system/role/authority"; 
	}
	@RequestMapping( value = "/system/role/authority/{id}", method = RequestMethod.POST)
	public String authority(@PathVariable int id, String menuIds, String btnIds,RedirectAttributes attributes){
		roleService.saveAuthority(id, menuIds, btnIds);
		attributes.addFlashAttribute(Remind.success());
		
		//重构当前用户的菜单和按钮权限
		Principal principal =getPrincipal();
		if(principal != null && principal.getUserId() != 0) {
			//构建菜单权限
			List<Menu> menus = menuService.getAuthorityMenu(principal.getUserId());
			
			//构建按钮权限
			Map<String, Map<String, String>> btns = menuService.getAuthorityBtn(principal.getUserId());
			principal.setMenus(menus);
			principal.setBtns(btns);
			
		}
		return "redirect:/console/system/role/";
	}
	
	
	
	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequestMapping( value = "/system/role/0", method = RequestMethod.GET)
	public String add(){
		return "console/system/role/form"; 
	}
	
	/**
	 * 跳转到编辑页面
	 * @return
	 */
	@RequestMapping( value = "/system/role/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable int id, Model model){
		Role entity = roleService.getRole(id);
		model.addAttribute("entity", entity);
		return "console/system/role/form"; 
	}
	
	/**
	 *检测名称是否重复
	 */
	@RequestMapping( value = "/system/role/check")
	@ResponseBody
	public boolean checkName(Integer id, String name){
		boolean isOk = roleService.checkName(id, name);
		return isOk;
	}
	
	/**
	 * 保存数据
	 */
	@RequestMapping( value = "/system/role/{id}", method = RequestMethod.POST)
	public String save(@PathVariable int id, Role entity, RedirectAttributes attributes){
		entity.setId(id);
		if(id == 0) {//新增
			roleService.add(entity);
			attributes.addFlashAttribute(Remind.success().setMessage("添加成功！"));
		}else if(id > 0) {//修改
			roleService.update(entity);
			attributes.addFlashAttribute(Remind.success().setMessage("修改成功！"));
		}else {//数据错误
			attributes.addFlashAttribute(Remind.danger());
		}
		return "redirect:/console/system/role/";
	}
	
	/**
	 * 单个删除
	 */
	@RequestMapping( value = "/system/role/{id}/remove", method = RequestMethod.GET)
	public String delete(@PathVariable int id, RedirectAttributes attributes){
		int count=roleService.checkRole(id);//检测角色是否被使用
		if(count>0){
			attributes.addFlashAttribute(Remind.warning().setMessage("当前角色被使用，不可删除"));
		}else{
			roleService.delete(id);
			attributes.addFlashAttribute(Remind.success());
		}
		return "redirect:/console/system/role/";
	}
	
	/**
	 * 批量删除
	 */
	@RequestMapping( value = "/system/role/remove", method = RequestMethod.POST)
	public String batchDelete(int[] pks, RedirectAttributes attributes){
		int count=roleService.checkRole(pks);//检测角色是否被使用
		if(count>0){
			attributes.addFlashAttribute(Remind.warning().setMessage("当前角色被使用，不可删除"));
		}else{
			roleService.delete(pks);
			attributes.addFlashAttribute(Remind.success());
		}
		return "redirect:/console/system/role/";
	}
}
