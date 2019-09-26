package com.vic.ck.api.handler;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;
import com.vic.ck.api.constant.FinalFiledParams;
import com.vic.ck.api.constant.ResultMsgEnum;
import com.vic.wroot.common.po.BaseResponse;
import com.vic.wroot.common.tool.Tools;
import com.vic.wroot.common.util.AESUtil;

/**
 * 参数解密过滤器
 *   需要servlet3.0+
 * @author QiuDong
 *
 */
@WebFilter(urlPatterns={"*.api"}, filterName = "paramDecryptFilter")
public class ParamDecryptFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(ParamDecryptFilter.class); 
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response1 = (HttpServletResponse) response;
		//跨域请求，*代表允许全部类型
		response1.setHeader("Access-Control-Allow-Origin", "*");
		//允许请求方式
		response1.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		//用来指定本次预检请求的有效期，单位为秒，在此期间不用发出另一条预检请求
		response1.setHeader("Access-Control-Max-Age", "3600");
		//请求包含的字段内容，如有多个可用哪个逗号分隔如下
		response1.setHeader("Access-Control-Allow-Headers", "content-type,x-requested-with,Authorization, x-ui-request,lang");
		//访问控制允许凭据，true为允许
		response1.setHeader("Access-Control-Allow-Credentials", "true");
		
		//是否开启请求加密
		if(!FinalFiledParams.START_ENCRYPT){
			chain.doFilter(request, response);
			return;
		}
		logger.info("into paramDecryptFilter");
		Map<String, String[]> others = request.getParameterMap();
		String enctype = request.getContentType();  
	    if (StringUtils.isNotBlank(enctype) && enctype.contains("multipart/form-data")){
	    	MultipartResolver resolver = new CommonsMultipartResolver(((HttpServletRequest) request).getSession().getServletContext());
			MultipartHttpServletRequest req = resolver.resolveMultipart((HttpServletRequest) request);  
			others = req.getParameterMap();
			request = req;
	    }
		if(others == null || others.isEmpty()){//如果参数为空 则不过滤
			chain.doFilter(request, response);
			return;
		}
		String param = request.getParameter(FinalFiledParams.REQUEST_PARAM);
		
		
		//如果有参数 则只能是指定的加密参数 不然就抛出参数错误异常（交由全局异常捕获）
		if(StringUtils.isEmpty(param)){
			Tools.writeJson(BaseResponse.error(ResultMsgEnum.PARAM_ERROR.getCode(), ResultMsgEnum.PARAM_ERROR.getMsg()), (HttpServletResponse)response);
			return;
		}
		logger.debug("param:{}" , param);
		JSONObject json = null;
		try {
			String decode = AESUtil.decrypt(param,FinalFiledParams.AES_KEY);
			logger.info("decode:{}" , decode);
			json = JSONObject.parseObject(decode);

		} catch (Exception e) {
			logger.error("解密失败！");
			Tools.writeJson(BaseResponse.error(ResultMsgEnum.PARAM_ERROR.getCode(), ResultMsgEnum.PARAM_ERROR.getMsg()), (HttpServletResponse)response);
			return;
		}
		request = new ParameterRequestWrapper((HttpServletRequest) request, json);
		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {

	}

	@Override  
	public void init(FilterConfig arg0) throws ServletException { 
		logger.info(" init ParamDecryptFilter ");
	}  

}
