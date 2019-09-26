package com.vic.ck.vo;

import java.math.BigDecimal;

/**
 * 对账
 * @author VIC
 *
 */
public class CompareBillVo {

	//数量
	private int nums;
	
	//金额
	private BigDecimal amount;

	public int getNums() {
		return nums;
	}

	public void setNums(int nums) {
		this.nums = nums;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	
}
