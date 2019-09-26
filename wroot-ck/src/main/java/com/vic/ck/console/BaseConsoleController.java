package com.vic.ck.console;

import com.vic.base.BaseController;
import com.vic.ck.vo.ConsoleBellTip.TipFlag;

public abstract class BaseConsoleController extends BaseController{
	

	/**登陆提示信息*/
	public static final String LOGIN_MSG = "loginMsg";
	

	public void removeTip(TipFlag flag) {
		if(getPrincipal() != null) {
			getPrincipal().removeTip(flag);
		}
	}
	
	
	
}
