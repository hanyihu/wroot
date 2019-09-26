package com.vic.ck.console.system;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vic.base.BaseConstant;
import com.vic.base.Principal;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.entity.Menu;
import com.vic.ck.console.entity.User;
import com.vic.ck.console.system.menu.service.MenuService;
import com.vic.ck.console.system.user.service.UserService;


/**
 * 理应最新进入的controller
 * @author VIC
 *
 */
@Controller
@RequestMapping("/console")
public class IndexController extends BaseConsoleController{

	@Resource
	private UserService userService;
	
	@Resource
	private MenuService menuService;
	
	/**
	 * 后台理应最先进入的页面
	 */
	@RequestMapping(value = {"/",""})
	public String home(){
		return "console/index";
	}
	
	@RequestMapping(value = "/index")
	public String index(){
		return "console/index";
	}
	/**
	 *  进入登录页面
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(){
		return "console/login";
	}
	
	/**
	 * 登录操作
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String username, String password, RedirectAttributes attr){
		User user = userService.getUserByUsername(username);
		
		if(user == null) {
			attr.addFlashAttribute(LOGIN_MSG, "不存在的用户");
			return "redirect:/console/login";
		}
		
		if(!user.getPassword().equals(DigestUtils.md5Hex(password))) {
			attr.addFlashAttribute(LOGIN_MSG, "密码错误");
			return "redirect:/console/login";
		}
		//构建菜单权限
		List<Menu> menus = menuService.getAuthorityMenu(user.getId());
		
		//构建按钮权限
		Map<String, Map<String, String>> btns = menuService.getAuthorityBtn(user.getId());
		
		Principal principal = new Principal(user, menus);
		principal.setBtns(btns);
		
		//提醒信息
		principal.setTips(userService.findTips().getTips());
		
		//登陆相关信息存入session
		setSessionAttribute(BaseConstant.USER_SESSION_KEY, principal);
//		return "redirect:" + ConsoleTools.pickLasrRequestPage();//进入最后要跳转的页面
		return  "redirect:" + BaseConstant.CONSOLE_INDEX;
	}
	
	//退出登录
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(){
		setSessionAttribute(BaseConstant.USER_SESSION_KEY, null);
		if(currentSession() != null) {
			currentSession().invalidate();
		}
		return "redirect:" + BaseConstant.CONSOLE_INDEX;
	}
	
}
