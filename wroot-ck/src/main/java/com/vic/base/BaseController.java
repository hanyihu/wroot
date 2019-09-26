package com.vic.base;

import com.vic.base.pager.CommonLookup;
import com.vic.base.pager.Lookup;
import com.vic.wroot.common.tool.Tools;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * controller的基类，理应所用的controller都集成此抽象类
 * 
 * @author VIC
 *
 */
public abstract class BaseController {
	
	/**
	 * 获得本controller的查询条件
	 */
	protected Lookup getLookup() throws IllegalStateException{
		String lookupKey = getClass().getName() + "." + BaseConstant.SESSIONKEY_SUFFIX_LOOKUP;
		Lookup lookup = getSessionAttribute(lookupKey);
		if(lookup == null) {
			lookup = instanceLookup();
			/************后续为了后台管理用户分城市管理所用的**************/
			lookup.setManagerCityId(getManageCityId());
			/*************************/
			setSessionAttribute(lookupKey, lookup);
		}
		return lookup;
	}
	/**
	 * 获得本controller的查询条件
	 */
	protected Lookup getLookup(String prefix) throws IllegalStateException{
		String lookupKey = getClass().getName() + "." + BaseConstant.SESSIONKEY_SUFFIX_LOOKUP + prefix;
		Lookup lookup = getSessionAttribute(lookupKey);
		if(lookup == null) {
			lookup = instanceLookup();
			/************后续为了后台管理用户分城市管理所用的**************/
			lookup.setManagerCityId(getManageCityId());
			/*************************/
			setSessionAttribute(lookupKey, lookup);
		}
		return lookup;
	}
	
	/**
	 * 保存提交的查询条件
	 */
	protected void setLookup(Lookup lookup) {
		setSessionAttribute(getClass().getName() + "." + BaseConstant.SESSIONKEY_SUFFIX_LOOKUP, lookup);
	}
	
	protected void setLookup(Lookup lookup, String prefix) {
		setSessionAttribute(getClass().getName() + "." + BaseConstant.SESSIONKEY_SUFFIX_LOOKUP + prefix, lookup);
	}
	/**
	 * 每个需要查询的controller都应该重写的方法 返回Lookup的子类
	 */
	protected Lookup instanceLookup() {
		return new CommonLookup();
	}


	/**
	 * 当前线程中的request  
	 */
	protected HttpServletRequest currentRequest() {
		return Tools.currentRequest();
	}

	/**
	 * 当前线程中的session 
	 */
	protected HttpSession currentSession() {
		return Tools.currentSession();
	}

	/**
	 * 设置session 
	 */
	protected void setSessionAttribute(String key, Object obj) throws IllegalStateException {
		WebUtils.setSessionAttribute(currentRequest(), key, obj);
	}

	/**
	 * 获取session中的对象  若当前无session则创建一个
	 */
	@SuppressWarnings("unchecked")
	protected <T> T getSessionAttribute(String key) throws IllegalStateException {
		return (T) WebUtils.getSessionAttribute(currentRequest(), key);
	}

	/**
	 * 获得session中的对象
	 */
	@SuppressWarnings("unchecked")
	protected <T> T getOrCreateSessionAttribute(String key, Class<T> clazz) throws IllegalStateException {
		HttpSession session = currentRequest().getSession();
		return (T) WebUtils.getOrCreateSessionAttribute(session, key, clazz);
	}
	
	public Map<String, String> getParamsMap(){
		Map<String, String> paramsMap = new HashMap<String, String>();
		HttpServletRequest request = currentRequest();
		if(request != null) {
			Map<String, String[]> params = request.getParameterMap();
			for(String key :params.keySet()) {
				paramsMap.put(key, StringUtils.join(params.get(key), ","));
			}
		}
		return paramsMap;
	}
	
	
	/**
	 * 获得当前线程中的登陆用户信息
	 * @return  有可能为null
	 */
	protected Principal getPrincipal() {
		try{
			return (Principal) currentSession().getAttribute(BaseConstant.USER_SESSION_KEY);
		}catch(Exception e) {
			return null;
		}
	}
	
	/**
	 * 获得当前用户中的管辖城市
	 * @return
	 */
	protected Integer getManageCityId() {
		Principal  p = getPrincipal();
		if(p != null &&  p.getCityId() != null) {
			return p.getCityId() == 0 ? null : p.getCityId();
		}
		return null;
	}

}
