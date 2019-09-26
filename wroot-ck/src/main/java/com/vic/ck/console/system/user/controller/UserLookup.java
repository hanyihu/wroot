package com.vic.ck.console.system.user.controller;

import com.vic.base.pager.CommonLookup;

/**
 * 用户相关查询条件
 * @author VIC
 *
 */
public class UserLookup extends CommonLookup {

	private String username;
	
	private String mobile;
	
	private Integer status;
	

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
