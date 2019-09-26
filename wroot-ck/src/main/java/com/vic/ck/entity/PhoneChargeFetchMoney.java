package com.vic.ck.entity;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 用户话费充值申请表
 * 
 * @author VIC
 */
public class PhoneChargeFetchMoney implements Serializable {

	private static final long serialVersionUID = 1L;

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
	 * 充值手机
	 */
	private String mobile;
	/**
	 * 申请时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime;
	/**
	 * 0-初始 1-已充值 2-审核不通过
	 */
	private Integer status;
	/**
	 * 充值日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fetchTime;
	/**
	 * 不通过的原因
	 */
	private String denyReason;
	
	

	public PhoneChargeFetchMoney(Integer customerId, BigDecimal money) {
		super();
		this.customerId = customerId;
		this.money = money;
	}

	public PhoneChargeFetchMoney() {
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
	 * set：
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	/**
	 * get：
	 */
	public Integer getCustomerId() {
		return customerId;
	}

	/**
	 * set：金额
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	/**
	 * get：金额
	 */
	public BigDecimal getMoney() {
		return money;
	}

	/**
	 * set：申请时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * get：申请时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * set：0-初始 1-已充值 2-审核不通过
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * get：0-初始 1-已充值 2-审核不通过
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * set：充值日期
	 */
	public void setFetchTime(Date fetchTime) {
		this.fetchTime = fetchTime;
	}

	/**
	 * get：充值日期
	 */
	public Date getFetchTime() {
		return fetchTime;
	}

	/**
	 * set：不通过的原因
	 */
	public void setDenyReason(String denyReason) {
		this.denyReason = denyReason;
	}

	/**
	 * get：不通过的原因
	 */
	public String getDenyReason() {
		return denyReason;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
}
