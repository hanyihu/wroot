package com.vic.ck.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 分享记录表
 * 
 * @author VIC
 */
public class CustomerShareRecord implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 
	 */
	private Integer customerId;
	/**
	 * 类型0-平台 1-团购 2-商家
	 */
	private Integer type;
	/**
	 * 分享的具体目标
	 */
	private String target;
	/**
	 * 
	 */
	@JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime;
	
	

	public CustomerShareRecord(Integer customerId, Integer type, String target) {
		super();
		this.customerId = customerId;
		this.type = type;
		this.target = target;
	}

	public CustomerShareRecord() {
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
	 * set：类型0-平台 1-团购 2-商家
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * get：类型0-平台 1-团购 2-商家
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * set：分享的具体目标
	 */
	public void setTarget(String target) {
		this.target = target;
	}

	/**
	 * get：分享的具体目标
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * set：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * get：
	 */
	public Date getCreateTime() {
		return createTime;
	}
}
