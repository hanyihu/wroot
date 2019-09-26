package com.vic.ck.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 现金提现
 * 
 * @author VIC
 *
 */
public class CustomerFetchMoney {
	/**
	 * 
	 */
	private Integer id;

	/**
	 * 
	 */
	private Integer customerId;

	@JsonIgnore
	private String customerName;

	@JsonIgnore
	private String customerMobile;

	/**
	 * 金额
	 */
	private BigDecimal money;

	/**
	 * 申请时间
	 */
	private Date createTime;

	/**
	 * 用户银行卡
	 */
	private Integer cardId;

	/**
	 * 0-初始 1-已提现 2-审核不通过
	 */
	private Byte status;

	/**
	 * 打款日期
	 */
	private Date fetchTime;

	/**
	 * 不通过的原因
	 */
	private String denyReason;

	/** 银行卡细信息 */
	@JsonIgnore
	private CustomerBankCard bankCard;

	/**
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 
	 * @return customer_id
	 */
	public Integer getCustomerId() {
		return customerId;
	}

	/**
	 * 
	 * @param customerId
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	/**
	 * 金额
	 * 
	 * @return money 金额
	 */
	public BigDecimal getMoney() {
		return money;
	}

	/**
	 * 金额
	 * 
	 * @param money
	 *            金额
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	/**
	 * 申请时间
	 * 
	 * @return create_time 申请时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 申请时间
	 * 
	 * @param createTime
	 *            申请时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 用户银行卡
	 * 
	 * @return card_id 用户银行卡
	 */
	public Integer getCardId() {
		return cardId;
	}

	/**
	 * 用户银行卡
	 * 
	 * @param cardId
	 *            用户银行卡
	 */
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}

	/**
	 * 0-初始 1-已提现 2-审核不通过
	 * 
	 * @return status 0-初始 1-已提现 2-审核不通过
	 */
	public Byte getStatus() {
		return status;
	}

	/**
	 * 0-初始 1-已提现 2-审核不通过
	 * 
	 * @param status
	 *            0-初始 1-已提现 2-审核不通过
	 */
	public void setStatus(Byte status) {
		this.status = status;
	}

	/**
	 * 打款日期
	 * 
	 * @return fetch_time 打款日期
	 */
	public Date getFetchTime() {
		return fetchTime;
	}

	/**
	 * 打款日期
	 * 
	 * @param fetchTime
	 *            打款日期
	 */
	public void setFetchTime(Date fetchTime) {
		this.fetchTime = fetchTime;
	}

	/**
	 * 不通过的原因
	 * 
	 * @return deny_reason 不通过的原因
	 */
	public String getDenyReason() {
		return denyReason;
	}

	/**
	 * 不通过的原因
	 * 
	 * @param denyReason
	 *            不通过的原因
	 */
	public void setDenyReason(String denyReason) {
		this.denyReason = denyReason == null ? null : denyReason.trim();
	}

	public CustomerBankCard getBankCard() {
		return bankCard;
	}

	public void setBankCard(CustomerBankCard bankCard) {
		this.bankCard = bankCard;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}
}