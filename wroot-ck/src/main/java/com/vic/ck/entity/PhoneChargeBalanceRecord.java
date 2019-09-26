package com.vic.ck.entity;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 用户抢话费余额记录明细
 * 
 * @author VIC
 */
public class PhoneChargeBalanceRecord implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 用户id
	 */
	private Integer customerId;
	/**
	 * 金额，可为负，表示支出
	 */
	private BigDecimal money;
	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime;
	/**
	 * 1-抢话费 2-提取
	 */
	private Integer type;
	/**
	 * 简单说明
	 */
	private String describe;
	/**
	 * type =1 的时候，抢话费活动的id
	 */
	private Integer activityId;
	/**
	 * type =1 的时候，抢话费活动的level
	 */
	private Integer level;
	
	/**
	 * 次数
	 */
	@JsonIgnore
	private int time;
	
	
	

	public PhoneChargeBalanceRecord(Integer customerId, BigDecimal money, Integer type, String describe,
			Integer activityId, Integer level) {
		super();
		this.customerId = customerId;
		this.money = money;
		this.type = type;
		this.describe = describe;
		this.activityId = activityId;
		this.level = level;
	}

	public PhoneChargeBalanceRecord() {
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
	 * set：金额，可为负，表示支出
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	/**
	 * get：金额，可为负，表示支出
	 */
	public BigDecimal getMoney() {
		return money;
	}

	/**
	 * set：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * get：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * set：1-抢话费 2-提取
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * get：1-抢话费 2-提取
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * set：简单说明
	 */
	public void setDescribe(String describe) {
		this.describe = describe;
	}

	/**
	 * get：简单说明
	 */
	public String getDescribe() {
		return describe;
	}

	/**
	 * set：type =1 的时候，抢话费活动的id
	 */
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	/**
	 * get：type =1 的时候，抢话费活动的id
	 */
	public Integer getActivityId() {
		return activityId;
	}

	/**
	 * set：type =1 的时候，抢话费活动的level
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * get：type =1 的时候，抢话费活动的level
	 */
	public Integer getLevel() {
		return level;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
}
