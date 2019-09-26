package com.vic.ck.api.handler;

import java.util.Locale;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.vic.base.BaseApiController;
import com.vic.ck.api.constant.ResultMsgEnum;
import com.vic.ck.api.exception.CommonRuntimeException;
import com.vic.wroot.common.exception.BadParamException;
import com.vic.wroot.common.po.BaseResponse;

/**
 * 接口的全局异常捕获
 * 
 * @author VIC
 *
 */
@ControllerAdvice(basePackages = "com.vic.ck.api")
public class ApiGlobalExceptionHandler extends BaseApiController {

	private Logger logger = LoggerFactory.getLogger(ApiGlobalExceptionHandler.class);

	@ExceptionHandler(CommonRuntimeException.class)
	@ResponseBody
	public Object commonRuntimeExceptionHandler(HttpServletRequest req, Locale locale, CommonRuntimeException ex)
			throws Exception {
		BaseResponse response = new BaseResponse();
		response.setCode(ex.getStatusCode().value());
		response.setMsg(translateMessage(ex, locale));
		logger.error(response.getMsg(), ex);
		return result(response);
	}

	@ExceptionHandler(AuthenticationException.class)
	@ResponseBody
	public Object authenticationExceptionHandler(HttpServletRequest req, Locale locale, AuthenticationException ex)
			throws Exception {
		logger.error(ex.getMessage(), ex);
		return resultError(ResultMsgEnum.NOT_LOGIN);
	}

	/**参数错误*/
	@ExceptionHandler(BadParamException.class)
	@ResponseBody
	public Object badParamExceptionHandler(HttpServletRequest req, Locale locale, BadParamException ex)
			throws Exception {
		logger.error("请求参数绑定异常", ex);
		if(ex.isValidated()) {
			return resultError(ResultMsgEnum.PARAM_ERROR.getCode(), ex.getMessage());
		}
		return  resultError(ResultMsgEnum.PARAM_ERROR);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseBody
	public Object illegalArgumentExceptionHandler(HttpServletRequest req, Locale locale, IllegalArgumentException ex)
			throws Exception {
		logger.error(ex.getMessage(), ex);
		return resultError(ResultMsgEnum.PARAM_ERROR);
	}

	@ExceptionHandler
	@ResponseBody
	public Object defaultErrorHandler(HttpServletRequest req, Locale locale, Exception ex) throws Exception {
		if (AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class) != null) {
			throw ex;
		}
		if (ex instanceof ServletRequestBindingException || (ex instanceof TypeMismatchException)
				|| (ex instanceof HttpRequestMethodNotSupportedException)) {
			// throw ex;
			logger.error("参数绑定相关的错误捕捉", ex);
			// throw new BadParamException(ex);
			return resultError(ResultMsgEnum.PARAM_ERROR);
		}
		logger.error(ex.getMessage(), ex);
		return resultError(ResultMsgEnum.INTERNAL_SERVER_ERROR);
	}

	private String translateMessage(CommonRuntimeException ex, Locale locale) {
		String statusText = ex.getStatusText();
		String[] texts = statusText.split("[|]");
		if (texts.length == 1) {
			return ex.getStatusText();
		} else {
			StringBuilder builder = new StringBuilder();
			for (String text : texts) {
				builder.append(text).append("<br />");
			}
			return builder.toString();
		}
	}

}
