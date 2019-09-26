package com.vic.base.pager;

import com.vic.base.BaseConstant;

/**
 * 查询参数 默认带分页信息
 * @author VIC
 *
 */
public class Lookup {
	
	private int page = 1;
	
	private int size = BaseConstant.PAGE_SIZE;
	
	/**
	 * 后续为了后台管理用户分城市管理所用的   ，当前用户管辖的城市
	 */
	private Integer managerCityId;
	
	
	public Lookup() {
		
	}
	
	public Lookup(int page, int size) {
		this.page = page;
		this.size = size;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Integer getManagerCityId() {
		return managerCityId;
	}

	public void setManagerCityId(Integer managerCityId) {
		this.managerCityId = managerCityId;
	}

	
	
}
