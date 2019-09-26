package com.vic.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.vic.ck.console.tool.ConsoleTools;

/**
 * 保存请求的拦截器
 * @author VIC
 *
 */
public class SaveRequestInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if("GET".equalsIgnoreCase(request.getMethod())) {
			ConsoleTools.saveLastPageToSession(request);
		}
		return true;
	}
	


}
