package com.vic.ck.console.tool;

import com.vic.ck.console.contant.ConsoleBaseConstant;
import com.vic.wroot.common.tool.Tools;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

/**
 * 后台tool
 * @author VIC
 *
 */
public class ConsoleTools {

	private static final Logger logger = LoggerFactory.getLogger(ConsoleTools.class);
	
	private static final Base64 base64 = new Base64(true);
	/**
	 * 保存最后一次get请求的地址到session
	 */
	public static void saveLastPageToSession(HttpServletRequest request){
		request.getSession().setAttribute(ConsoleBaseConstant.LAST_PAGE, getLasrRequestPage(request));
	}
	
	/**
	 * 获得最后一次get请求的地址
	 */
	public static String getLasrRequestPage(HttpServletRequest request){
		String reqUri = request.getRequestURI();//去掉项目名之前的请求 127.0.0.1:80/project/model.com-->/project/model.com
		String query = request.getQueryString();//请求中的参数
		if(query != null) {
			reqUri += "?" + query;
		}
		logger.info("最后一次get请求为：{}", reqUri);
		
		String targetUrl = null;
		try{
			targetUrl = base64.encodeAsString(reqUri.getBytes(ConsoleBaseConstant.BASE_ENCODE));
		}catch(UnsupportedEncodingException ex){
			//相信这是不会发生的
		}
		return targetUrl;
	}

	/**最后一次请求的地址*/
	public static String pickLasrRequestPage() {
		HttpSession session = Tools.currentSession();
		if(session == null) {
			return ConsoleBaseConstant.CONSOLE_INDEX;
		}
		
		String uri = (String) session.getAttribute(ConsoleBaseConstant.LAST_PAGE);
		if(uri != null) {
			byte[] decode = base64.decode(uri);
			String lastUri;
			try {
				lastUri = new String(decode, ConsoleBaseConstant.BASE_ENCODE);
				return lastUri.substring(lastUri.indexOf("/", 1)); 
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		return ConsoleBaseConstant.CONSOLE_INDEX;
	}
	

}
