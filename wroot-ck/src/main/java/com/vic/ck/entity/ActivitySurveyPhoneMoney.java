package com.vic.ck.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 抢话费活动概况
 * 
 * @author VIC
 */
public class ActivitySurveyPhoneMoney implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 所属活动
	 */
	private Integer activityId;
	/**
	 * 是否有效
	 */
	private Integer enabled;
	/**
	 * 一个抢话费活动对应的5个级别
	 */
	private Integer level;
	/**
	 * 已经发出的总额
	 */
	private BigDecimal receiveAmount;
	/**
	 * 已经领取的总人数
	 */
	private Integer receiveNumber;
	/**
	 * 发出的第几组(每组发完了就组数+1，当前可发金额和已领取人数重置)
	 */
	private Integer receiveGroup;
	/**
	 * 当前组初始金额
	 */
	private BigDecimal groupAmount;
	
	/**
	 * 当前组可使用金额
	 */
	private BigDecimal availableAmount;
	/**
	 * 当前组已经领取的人数
	 */
	private Integer currentReceiveNumber;
	/**
	 * 
	 */
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime;

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
	 * set：所属活动
	 */
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	/**
	 * get：所属活动
	 */
	public Integer getActivityId() {
		return activityId;
	}

	/**
	 * set：是否有效
	 */
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	/**
	 * get：是否有效
	 */
	public Integer getEnabled() {
		return enabled;
	}

	/**
	 * set：一个抢话费活动对应的5个级别
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * get：一个抢话费活动对应的5个级别
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * set：已经发出的总额
	 */
	public void setReceiveAmount(BigDecimal receiveAmount) {
		this.receiveAmount = receiveAmount;
	}

	/**
	 * get：已经发出的总额
	 */
	public BigDecimal getReceiveAmount() {
		return receiveAmount == null ? new BigDecimal(0.00) : receiveAmount;
	}

	/**
	 * set：已经领取的总人数
	 */
	public void setReceiveNumber(Integer receiveNumber) {
		this.receiveNumber = receiveNumber;
	}

	/**
	 * get：已经领取的总人数
	 */
	public Integer getReceiveNumber() {
		return receiveNumber;
	}

	/**
	 * set：发出的第几组(每组发完了就组数+1，当前可发金额和已领取人数重置)
	 */
	public void setReceiveGroup(Integer receiveGroup) {
		this.receiveGroup = receiveGroup;
	}

	/**
	 * get：发出的第几组(每组发完了就组数+1，当前可发金额和已领取人数重置)
	 */
	public Integer getReceiveGroup() {
		return receiveGroup;
	}

	/**
	 * set：当前组初始金额
	 */
	public void setGroupAmount(BigDecimal groupAmount) {
		this.groupAmount = groupAmount;
	}

	/**
	 * get：当前组初始金额
	 */
	public BigDecimal getGroupAmount() {
		return groupAmount;
	}

	/**
	 * set：当前组可使用金额
	 */
	public void setAvailableAmount(BigDecimal availableAmount) {
		this.availableAmount = availableAmount;
	}

	/**
	 * get：当前组可使用金额
	 */
	public BigDecimal getAvailableAmount() {
		return availableAmount == null ? new BigDecimal(0.00) : availableAmount;
	}

	/**
	 * set：当前组已经领取的人数
	 */
	public void setCurrentReceiveNumber(Integer currentReceiveNumber) {
		this.currentReceiveNumber = currentReceiveNumber;
	}

	/**
	 * get：当前组已经领取的人数
	 */
	public Integer getCurrentReceiveNumber() {
		return currentReceiveNumber;
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
