package com.vic.service.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.vic.wroot.common.tool.Tools;
import com.vic.wroot.common.util.PropertiesUtil;
/**
 * 对于附件访问的一些拦截
 * @author VIC
 *
 */
public class AttachmentInterceptor extends HandlerInterceptorAdapter{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		logger.info("进入拦截器  referer={}", request.getHeader("referer"));
		if(0 == PropertiesUtil.getIntByKey("start.intercept")){//是否启用拦截
			return true;
		}
		
		boolean flag = false;//是否通过验证
		if("POST".equals(request.getMethod())){//窃以为POST请求就是提交或者修改资源  需要验证资格：项目名：密码
			String module = request.getHeader("module");
			String  password = request.getHeader("password");
			String ps = PropertiesUtil.getStringByKey("module." + module);
			if(StringUtils.isNotBlank(ps) && DigestUtils.md5Hex(ps).equals(password)){
				flag = true;
			}
		}else if("GET".equals(request.getMethod())) {//窃以为GET请求就是访问资源， 需要验证是否能访问：是否来自指定的页面
			flag = canVisit(request.getHeader("referer"));
		}
		
		logger.info("是否通过拦截：{}", flag);
		if(!flag){
			Tools.writeJson("what are you 弄啥哩?", response);
		}
		return flag;
		
	}
	
	/**
	 * 是否可以访问
	 * @return
	 */
	private boolean canVisit(String referer){
		String modulesStr = PropertiesUtil.getStringByKey("attachment.visit.modules");
		if(StringUtils.isBlank(referer)  || modulesStr.split(",").length <= 0) {
			return false;
		}
		String[] modules = modulesStr.split(",");
		for(int i=0; i< modules.length; i++){
			if(referer.contains(modules[i])){
				return true;
			}
		}
		return false;
	}


}
