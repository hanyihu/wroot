package com.vic.ck.vo;
/**
 * 推荐概况
 * @author VIC
 *
 */

import java.math.BigDecimal;

public class RecommentSurveyVo {

	//推荐的用户
	private int customerNum;
	
	//推荐的商家
	private int merchantNum;
	
	//推荐的奖励总额
	private BigDecimal amount;

	public int getCustomerNum() {
		return customerNum;
	}

	public void setCustomerNum(int customerNum) {
		this.customerNum = customerNum;
	}

	public int getMerchantNum() {
		return merchantNum;
	}

	public void setMerchantNum(int merchantNum) {
		this.merchantNum = merchantNum;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	
}
