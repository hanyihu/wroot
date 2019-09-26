package com.vic.ck.console.system.area.controller;

import com.vic.base.pager.CommonLookup;

/**
 * 封装区域查询条件 
 * 
 * @author VIC
 */
public class SysAreaLookup extends CommonLookup {
	
	private Integer hot ;
	
	private Integer opened;
	
	private Integer pid;

	public Integer getHot() {
		return hot;
	}

	public void setHot(Integer hot) {
		this.hot = hot;
	}

	public Integer getOpened() {
		return opened;
	}

	public void setOpened(Integer opened) {
		this.opened = opened;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
	
}
