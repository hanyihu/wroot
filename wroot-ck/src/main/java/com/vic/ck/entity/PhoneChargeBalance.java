package com.vic.ck.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 用户话费余额表
 * 
 * @author VIC
 */
public class PhoneChargeBalance implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 余额
	 */
	private BigDecimal balance;
	/**
	 * 用户id
	 */
	private Integer customerId;
	/**
	 * 版本，用于修改时的判断
	 */
	private Integer version;
	
	
	public PhoneChargeBalance(Integer customerId) {
		super();
		this.customerId = customerId;
		this.version = 1;
		this.balance = new BigDecimal(0.00);
	}

	public PhoneChargeBalance() {
		super();
	}

	/**
	 * set：
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * get：
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * set：余额
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	/**
	 * get：余额
	 */
	public BigDecimal getBalance() {
		return balance;
	}

	/**
	 * set：用户id
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	/**
	 * get：用户id
	 */
	public Integer getCustomerId() {
		return customerId;
	}

	/**
	 * set：版本，用于修改时的判断
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * get：版本，用于修改时的判断
	 */
	public Integer getVersion() {
		return version;
	}
}
