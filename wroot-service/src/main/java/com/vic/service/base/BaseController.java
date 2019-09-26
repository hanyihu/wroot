package com.vic.service.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.util.WebUtils;

import com.vic.wroot.common.tool.Tools;

/**
 * controller的基类，理应所用的controller都集成此抽象类
 * 
 * @author VIC
 *
 */
public abstract class BaseController {

	/**
	 * 当前线程中的request 2016年10月10日 by VIC
	 * 
	 * @return
	 */
	protected HttpServletRequest currentRequest() {
		return Tools.currentRequest();
	}

	/**
	 * 当前线程中的session 2016年10月10日 by VIC
	 * 
	 * @return
	 */
	protected HttpSession currentSession() {
		return Tools.currentSession();
	}

	/**
	 * 设置session 2016年10月10日 by VIC
	 * 
	 * @return
	 */
	protected void setSessionAttribute(String key, Object obj) throws IllegalStateException {
		WebUtils.setSessionAttribute(currentRequest(), key, obj);
	}

	/**
	 * 获取session中的对象  若当前无session则创建一个
	 * 2016年10月10日 by VIC
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected <T> T getSessionAttribute(String key) throws IllegalStateException {
		return (T) WebUtils.getSessionAttribute(currentRequest(), key);
	}

	/**
	 * 获得session中的对象
	 * 2016年10月10日 by VIC
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected <T> T getOrCreateSessionAttribute(String key, Class<T> clazz) throws IllegalStateException {
		HttpSession session = currentRequest().getSession();
		return (T) WebUtils.getOrCreateSessionAttribute(session, key, clazz);
	}

}
