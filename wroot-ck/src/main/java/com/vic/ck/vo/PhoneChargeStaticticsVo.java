package com.vic.ck.vo;

import java.math.BigDecimal;

/**
 * 抢话费的一些统计信息
 * @author VIC
 *
 */
public class PhoneChargeStaticticsVo {

	/**
	 * 历史抢到的总额
	 */
	private BigDecimal totalAmount;
	
	/**
	 * 历史充值话费
	 */
	private BigDecimal chargeAmount;
	
	/**
	 * 本活动内邀请到的人数
	 */
	private int currentRecommentNum;
	
	/**
	 * 本次抢到的话费
	 */
	private BigDecimal currentAmount = new BigDecimal(0.0);

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(BigDecimal chargeAmount) {
		this.chargeAmount = chargeAmount;
	}

	public int getCurrentRecommentNum() {
		return currentRecommentNum;
	}

	public void setCurrentRecommentNum(int currentRecommentNum) {
		this.currentRecommentNum = currentRecommentNum;
	}

	public BigDecimal getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(BigDecimal currentAmount) {
		this.currentAmount = currentAmount;
	}
	
	
	
	
	
}
