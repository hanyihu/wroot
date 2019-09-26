package com.vic.ck.console.merchant.lookup;
import com.vic.base.pager.CommonLookup;

/**
 * 封装商户表-申请时产生数据查询条件 
 * 
 * @author VIC
 */
public class MerchantLookup extends CommonLookup {
	
	
	private Integer provinceId;
	
	private Integer areaId;
	
	private Integer wellChosened;

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Integer getWellChosened() {
		return wellChosened;
	}

	public void setWellChosened(Integer wellChosened) {
		this.wellChosened = wellChosened;
	}
	
	
	
	
}
