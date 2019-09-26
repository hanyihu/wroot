package com.vic.ck.console.order.lookup;
import com.vic.base.pager.CommonLookup;

/**
 * 封装订单表查询条件 
 * 
 * @author VIC
 */
public class OrderLookup extends CommonLookup {
	
	private String orderno;
	
	private String merchantName;
	
	private String merchantMobile;
	
	private Boolean isDelete;
	
	private Integer payType;

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getMerchantMobile() {
		return merchantMobile;
	}

	public void setMerchantMobile(String merchantMobile) {
		this.merchantMobile = merchantMobile;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	
	
	
	
}
