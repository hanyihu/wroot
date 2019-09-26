package com.vic.ck.console.merchant.lookup;
import com.vic.base.pager.CommonLookup;

/**
 * 封装酒店表查询条件 
 * 
 * @author VIC
 */
public class MerchantHotelLookup extends CommonLookup {
	
	private Integer isDelete;
	
	private String merchantName;

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
}
