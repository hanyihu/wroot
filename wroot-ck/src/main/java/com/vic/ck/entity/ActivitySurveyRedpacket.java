package com.vic.ck.entity;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 当日红包概况
 * 
 * @author VIC
 */
public class ActivitySurveyRedpacket implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 所统计的日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date atDate;
	/**
	 * 总金额-根据平台柳流水和活动算来的
	 */
	private BigDecimal totalAmount;
	/**
	 * 关联的活动
	 */
	private Integer activityId;
	/**
	 * 总人数
	 */
	private Integer totalNumber;
	/**
	 * 已经领取的金额
	 */
	private BigDecimal receiveAmount;
	/**
	 * 已领取的人数
	 */
	private Integer receiveNumber;
	/**
	 * 1-有效 0-无效
	 */
	private Integer enabled;
	/**
	 * 
	 */
	@JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8") 
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

	
	
	public ActivitySurveyRedpacket() {
		super();
	}

	public ActivitySurveyRedpacket(Date atDate, BigDecimal totalAmount, Integer activityId, Integer totalNumber,
			Integer enabled) {
		super();
		this.atDate = atDate;
		this.totalAmount = totalAmount;
		this.activityId = activityId;
		this.totalNumber = totalNumber;
		this.enabled = enabled;
	}

	/**
	 * set：所统计的日期
	 */
	public void setAtDate(Date atDate) {
		this.atDate = atDate;
	}

	/**
	 * get：所统计的日期
	 */
	public Date getAtDate() {
		return atDate;
	}

	/**
	 * set：总金额-根据平台柳流水和活动算来的
	 */
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * get：总金额-根据平台柳流水和活动算来的
	 */
	public BigDecimal getTotalAmount() {
		return totalAmount == null ? BigDecimal.ZERO : totalAmount;
	}

	/**
	 * set：关联的活动
	 */
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	/**
	 * get：关联的活动
	 */
	public Integer getActivityId() {
		return activityId;
	}

	/**
	 * set：总人数
	 */
	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}

	/**
	 * get：总人数
	 */
	public Integer getTotalNumber() {
		return totalNumber;
	}

	/**
	 * set：已经领取的金额
	 */
	public void setReceiveAmount(BigDecimal receiveAmount) {
		this.receiveAmount = receiveAmount;
	}

	/**
	 * get：已经领取的金额
	 */
	public BigDecimal getReceiveAmount() {
		return receiveAmount;
	}

	/**
	 * set：已领取的人数
	 */
	public void setReceiveNumber(Integer receiveNumber) {
		this.receiveNumber = receiveNumber;
	}

	/**
	 * get：已领取的人数
	 */
	public Integer getReceiveNumber() {
		return receiveNumber;
	}

	/**
	 * set：1-有效 0-无效
	 */
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	/**
	 * get：1-有效 0-无效
	 */
	public Integer getEnabled() {
		return enabled;
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
