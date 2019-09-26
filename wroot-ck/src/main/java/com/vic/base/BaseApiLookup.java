package com.vic.base;

import com.vic.base.pager.Lookup;

/**
 * 接口中一些常用的 通用的分页查询
 * @author VIC
 *
 */
public class BaseApiLookup extends Lookup{
	
	protected Integer categoryId;
	
	protected int userId = 0;
	
	protected int type = 0;
	
	protected int status = 0;
	
	/**商家id*/
	protected Integer merchantId;
	
	/**城市id*/
	protected Integer cityId = 0;
	
	protected String name;
	
	private Integer enabled;
	
	private String swapno;


    public Integer getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSwapno() {
		return swapno;
	}

	public void setSwapno(String swapno) {
		this.swapno = swapno;
	}
	
	
	
	
	

}
