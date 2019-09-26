package com.vic.ck.console.merchant.lookup;
import com.vic.base.pager.CommonLookup;

/**
 * 封装商户余额明细表查询条件 
 * 
 * @author VIC
 */
public class MerchantBalanceLookup extends CommonLookup {

	private Integer merchantId;

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	
	
}
