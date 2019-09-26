package com.vic.ck.entity;

import java.io.Serializable;

/**
 * 合作申请表
 * 
 * @author VIC
 */
public class PlatformCooperation implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 公司名称
	 */
	private String companyName;
	/**
	 * 公司地址
	 */
	private String companyAddress;
	
	/**
	 * 联系电话
	 */
	private String mobile;
	/**
	 * 公司负责人
	 */
	private String companyPerson;
	/**
	 * 投放产品
	 */
	private String product;
	/**
	 * 1-商城 2-城市代理
	 */
	private Integer type;
	/**
	 * 意向代理城市
	 */
	private String city;
	/**
	 * 意向投资金额
	 */
	private Double amount;
	/**
	 * 
	 */
	private Integer customerId;
	/**
	 * 状态 0-初始 1-已处理
	 */
	private Integer status;
	
	
	public String getStatusDesc() {
		return status == null ? "未处理" :(status == 1 ? "已处理":"未处理");
	}
	
	public String getTypeDesc() {
		return type == null ? "商城" :(type == 1 ? "商城":"城市代理");
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
	 * set：公司名称
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * get：公司名称
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * set：公司地址
	 */
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	/**
	 * get：公司地址
	 */
	public String getCompanyAddress() {
		return companyAddress;
	}

	/**
	 * set：公司负责人
	 */
	public void setCompanyPerson(String companyPerson) {
		this.companyPerson = companyPerson;
	}

	/**
	 * get：公司负责人
	 */
	public String getCompanyPerson() {
		return companyPerson;
	}

	/**
	 * set：投放产品
	 */
	public void setProduct(String product) {
		this.product = product;
	}

	/**
	 * get：投放产品
	 */
	public String getProduct() {
		return product;
	}

	/**
	 * set：1-商城 2-城市代理
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * get：1-商城 2-城市代理
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * set：意向代理城市
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * get：意向代理城市
	 */
	public String getCity() {
		return city;
	}

	/**
	 * set：意向投资金额
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * get：意向投资金额
	 */
	public Double getAmount() {
		return amount;
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
	 * set：状态 0-初始 1-已处理
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * get：状态 0-初始 1-已处理
	 */
	public Integer getStatus() {
		return status;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
