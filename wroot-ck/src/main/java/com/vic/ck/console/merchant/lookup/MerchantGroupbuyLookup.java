package com.vic.ck.console.merchant.lookup;
import com.vic.base.pager.CommonLookup;

/**
 * 封装商户团购商品表查询条件 
 * 
 * @author VIC
 */
public class MerchantGroupbuyLookup extends CommonLookup {
	
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
