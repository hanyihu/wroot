package com.vic.wroot.common.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.vic.wroot.common.po.BaseResponse;
import com.vic.wroot.common.tool.Tools;

/**
 * 手机的token拦截器 保证一个账号只能在一个手机上登陆 后登陆的账号覆盖先登陆的
 * （此时先登陆的手机后续操作会被提醒其他地方登陆，需重新登陆） 时效 暂时7天
 * @author QiuDong
 *
 */
public class PhoneTokenInterceptor extends HandlerInterceptorAdapter {

	public static String TOKEN_PARAM = "token";// token 参数值

	private static int code = 701;

	private static String msg = "当前账户已在其他手机登陆，您已被迫下线，请重新登陆";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String token = request.getHeader(TOKEN_PARAM);

		boolean isValid = PhoneToken.isValidToken(token);

		if (!isValid) {
			Tools.writeJson(BaseResponse.error(code, msg), response);
			return false;
		}

		return true;
	}

}

