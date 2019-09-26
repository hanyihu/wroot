package com.vic.ck.api.handler;

import com.vic.ck.api.constant.FinalFiledParams;
import com.vic.wroot.common.util.AESUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 接口的出入参日志记录
 * 
 * @author VIC
 *
 */
@Aspect
@Component
public class ApiParamsLogs {

	@Resource
	private HttpServletRequest request;

	public Logger logger = LoggerFactory.getLogger(ApiParamsLogs.class);

	private static final String CURRENT_REQUET_TIME = "current_requet_time";
	
	private static final String LOG_NAME = "镗镗锣APP";

	/**
	 * 日志记录的切面
	 */                    
	@Pointcut("(execution(* com.vic.ck.api.*.controller.*.*(..))) && (@annotation(org.springframework.web.bind.annotation.RequestMapping))")
	public void log() {

	}

	/**
	 * 进入请求的时候拼装相关信息
	 * 
	 * @param joinPoint
	 */
	@Before("log()")
	public void before(JoinPoint joinPoint) {
		//本次请求的日志标识
        StringBuffer flag = new StringBuffer(System.currentTimeMillis() + RandomStringUtils.random(2, "1234567890"));
		String controller = joinPoint.getTarget().getClass().getSimpleName();
		String method = joinPoint.getSignature().getName();
		flag.append("-").append(controller).append(".").append(method);
		this.request.setAttribute(CURRENT_REQUET_TIME, flag);// 把日志标识放入request,request是线程安全的
		
		StringBuffer requestInfo = new StringBuffer().append(LOG_NAME).append("入参日志(").append(flag).append(") ");
		StringBuilder sb = new StringBuilder();
		Enumeration<String> e = request.getParameterNames();
		if (e.hasMoreElements()) {
			while (e.hasMoreElements()) {
				String name = e.nextElement();
				String[] values = request.getParameterValues(name);
				if (values.length == 1) {
					sb.append(name).append("=").append(values[0]);
				} else {
					sb.append(name).append("[]={").append(StringUtils.join(values, ",")).append("}");
				}
				sb.append(",");
			}
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		requestInfo.append(", 请求参数:{").append(sb.toString()).append("}");
		logger.info(requestInfo.toString());

	}

	/**
	 * 打印请求以及返回值
	 * 
	 * @param joinPoint
	 * @param returnValue
	 */
	@AfterReturning(pointcut = "log()", returning = "returnValue")
	public void afterReturning(JoinPoint joinPoint, Object returnValue) {
		if (FinalFiledParams.START_ENCRYPT) {
			try {
				returnValue = AESUtil.decrypt((String) returnValue, FinalFiledParams.AES_KEY);
			} catch (Exception e) {
				logger.info("返回值解密失败");
			}
		}
		StringBuffer requestInfo = new StringBuffer().append(LOG_NAME).append("出参日志(")
				.append(request.getAttribute(CURRENT_REQUET_TIME)).append(") ");
		logger.info("{}, 返回值为:{}", new Object[] { requestInfo.toString(), returnValue });

	}
	
}