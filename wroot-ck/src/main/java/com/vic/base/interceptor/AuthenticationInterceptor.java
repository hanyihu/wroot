package com.vic.base.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.vic.base.BaseConstant;
import com.vic.base.Principal;
import com.vic.ck.console.entity.Menu;
import com.vic.wroot.common.tool.Tools;
/**
 * 登录权限验证
 * @author VIC
 *
 */
public class AuthenticationInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Principal principal = (Principal) WebUtils.getSessionAttribute(request, BaseConstant.USER_SESSION_KEY);
		if(principal != null) {
			//TODO 一些权限判断
			List<Menu> authority = principal.getMenus();
        	/** 1 根目录不用拦截*/
        	/** 2 查询用用户的所用能访问的url和当前url，不匹配则拦截 中断请求*/
        	
        	boolean flag = true;//默认拦截
        	String currentUrl = Tools.getRequestUrl(request);//当前访问的url 
        	if(currentUrl.startsWith("/")){//根目录不用拦截
        		flag = false;
        	}else{
        		List<String> customerUrls = new ArrayList<String>();//当前用户能访问的URL集合
            	for(Menu menu :authority){
            		customerUrls.add(menu.getUrl());
            		List<Menu> subMenus = menu.getChildren();
            		if(subMenus !=null){
            			for(Menu m : subMenus) {
            				customerUrls.add(m.getUrl());
            			}
            		}
            	}
            	//判断当前用户资源访问权限  能访问则不拦截
            	for(String u : customerUrls){
            		if(currentUrl.startsWith(u)){
            			flag = false;
            		}
            	}
        	}
        	
        	if(flag){//拦截
        		 //response.sendRedirect(request.getContextPath());
        		response.sendRedirect(BaseConstant.CONSOLE_INDEX);
                 return false;
        	}
			
			//有访问权限的时候把当前URL存入session
			if(request.getMethod().equals(HttpMethod.GET.toString())){
				principal.setLocation(currentUrl);//用户菜单选中状态
				principal.setPosition(currentUrl);//用于展示当前位置
			}
			
			return true;
		}else {
			//拦截 前往登录页面
			request.getRequestDispatcher(BaseConstant.LOGIN_PAGE).forward(request, response);
			return false;
		}
		
	}


}
