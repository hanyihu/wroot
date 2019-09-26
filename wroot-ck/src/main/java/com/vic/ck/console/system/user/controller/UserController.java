package com.vic.ck.console.system.user.controller;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vic.base.BaseConstant;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.base.pager.Remind;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.entity.User;
import com.vic.ck.console.system.area.service.SysAreaService;
import com.vic.ck.console.system.role.service.RoleService;
import com.vic.ck.console.system.user.service.UserService;


@Controller
@RequestMapping("/console")
public class UserController extends BaseConsoleController{
	
	
	@Resource
	private UserService userService;
	
	
	@Resource
	private RoleService roleService;
	
	@Resource
	private SysAreaService sysAreaService;
	
	
	@ModelAttribute
	private void bindSelect(Model model) {
		model.addAttribute("cityList", sysAreaService.opendCityList());
	}
	
	/**
	 * 列表
	 * @return
	 */
	@RequestMapping(value = "/system/user/", method = RequestMethod.GET)
	public String home(Model model){
		PageInfo<User> pager = userService.list(getLookup());
		model.addAttribute("pager",pager);
		return "console/system/user/list";
	}
	
	
	@Override
	protected Lookup instanceLookup(){
		return new UserLookup();
	}
	
	@RequestMapping(value = "/system/user/", method = RequestMethod.POST)
	public String sesrch(UserLookup lookup){
		setLookup(lookup);
		return "redirect:/console/system/user/"; 
	}
	
	/**前往新增页面*/
	@RequestMapping( value = "/system/user/0", method = RequestMethod.GET)
	public String add(Model model){
		return "console/system/user/form"; 
	}
	
	/**前往修改页面*/
	@RequestMapping( value = "/system/user/{id}", method = RequestMethod.GET)
	public String editor(@PathVariable int id, Model model){
		User entity = userService.getUserById(id);
		model.addAttribute("entity", entity);
		return "console/system/user/form"; 
	}
	
	
	/**save*/
	@RequestMapping( value = "/system/user/{id}", method = RequestMethod.POST)
	public String save(@PathVariable int id, User entity, RedirectAttributes attr){
		entity.setId(id);
		if(id == 0) {//to add
			entity.setPassword(DigestUtils.md5Hex(BaseConstant.DEFAULT_PASSWORD));
			userService.addUser(entity);
			attr.addFlashAttribute(Remind.success());
		}else if(id > 0 ){ //to update
			userService.updateUser(entity);
			attr.addFlashAttribute(Remind.success());
		}else {
			attr.addFlashAttribute(Remind.danger());
		}
		return "redirect:/console/system/user/"; 
	}
	
	/**管理员重置密码*/
	@RequestMapping( value = "/system/user/{id}/reset", method = RequestMethod.GET)
	public String reset(@PathVariable int id, RedirectAttributes attributes){
		User user = userService.getUserById(id);
		if(user == null) {
			attributes.addFlashAttribute(Remind.danger().setMessage("不存在的用户"));
		}else{
			userService.updatePassword(id, DigestUtils.md5Hex(BaseConstant.DEFAULT_PASSWORD));
			attributes.addFlashAttribute(Remind.success());
		}
		return "redirect:/console/system/user/"; 
	}
	
	
	
	/**前往修改密码页面*/
	@RequestMapping( value = "/update/user/password", method = RequestMethod.GET)
	public String password(Model model){
		return "console/system/user/password"; 
	}
	
	/**确认修改密码*/
	@RequestMapping( value = "/update/user/password", method = RequestMethod.POST)
	public String password(@RequestParam("old")String old, @RequestParam("password")String password, RedirectAttributes attributes){
		int id = getPrincipal().getUserId();
		User user = userService.getUserById(id);
		if(user == null) {
			attributes.addFlashAttribute(Remind.danger().setMessage("不存在的用户"));
		}else{
			old = DigestUtils.md5Hex(old);
			if(!old.equals(user.getPassword())) {
				attributes.addFlashAttribute(Remind.warning().setMessage("密码错误"));
			}else {
				userService.updatePassword(id, DigestUtils.md5Hex(password));
				attributes.addFlashAttribute(Remind.success());
			}
		}
		return "redirect:" + BaseConstant.CONSOLE_INDEX;//进入最后要跳转的页面
	}
	
	
	/**前往赋权页面*/
	@RequestMapping( value = "/system/user/{id}/role", method = RequestMethod.GET)
	public String role(@PathVariable int id, Model model){
		model.addAttribute("roles", roleService.findUserRole(id));
		return "console/system/user/role"; 
	}
	/**
	 * 保存用户角色
	 */
	@RequestMapping( value = "/system/user/{id}/role", method = RequestMethod.POST)
	public String role(@PathVariable int id, @RequestParam int[] roles, RedirectAttributes attributes){
		attributes.addFlashAttribute(Remind.success());
		roleService.updateUserRole(id, roles);
		return "redirect:/console/system/user/";
	}

	/**修改用户状态 激活|冻结*/
	@RequestMapping( value = "/system/user/{id}/status", method = RequestMethod.GET)
	public String updateStatus(@PathVariable int id, @RequestParam int status, RedirectAttributes attributes){
		userService.updateStatus(id, status);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/system/user/";
	}
	
	/**单个删除*/
	@RequestMapping( value = "/system/user/{id}/remove", method = RequestMethod.GET)
	public String delete(@PathVariable int id, RedirectAttributes attributes){
		userService.deleteUser(id);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/system/user/";
	}
	
	/**批量删除*/
	@RequestMapping( value = "/system/user/remove", method = RequestMethod.POST)
	public String batchDelete(int[] ids, RedirectAttributes attributes){
		userService.deleteUser(ids);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/system/user/";
	}
	
	
	/**检测名称是否重复*/
	@RequestMapping( value = "/system/user/check")
	@ResponseBody
	public boolean checkName(Integer id, String username){
		boolean isOk = userService.checkName(id,username);
		return isOk;
	}
	
}
