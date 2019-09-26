package com.vic.ck.console.contant;
/**
 * 系统级常量
 * @author VIC
 *
 */
public class ConsoleBaseConstant {

	/**
	 * 最后访问页面的 仅限GET请求
	 */
	public static final String LAST_PAGE = "com.vic.blog.last.page";
	
	/**
	 * 编码
	 */
	public static final String BASE_ENCODE = "UTF-8";
	
	/**
	 * 登录信息
	 */
	public static final String USER_SESSION_KEY = "principal";
	
	/**
	 * 登录页面
	 */
	public static final String LOGIN_PAGE = "/page/jsp/console/login.jsp";
	
	/**
	 * 后台首页URL
	 */
	public static final String CONSOLE_INDEX = "/console/index";
	
	/**
	 * 默认分页size
	 */
	public static final int PAGE_SIZE = 10;
	
	/**
	 * 每个controller的查询条件的session中的后缀
	 */
	public static final String SESSIONKEY_SUFFIX_LOOKUP = "lookup";
	
	/**新增用户的默认密码*/
	public static final String DEFAULT_PASSWORD = "123456";
	
	static{
		
	}

}
