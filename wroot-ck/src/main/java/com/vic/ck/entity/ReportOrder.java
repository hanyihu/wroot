package com.vic.ck.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 订单的一些统计报表
 * 
 * @author VIC
 */
public class ReportOrder implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 统计的是那一天
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date atDate;
	/**
	 * 下单总数
	 */
	private Integer orderNumber;
	/**
	 * 下单成功的总数
	 */
	private Integer orderSuccessNumber;
	/**
	 * 下单的总人数
	 */
	private Integer totalNumber;
	/**
	 * 收入总金额
	 */
	private BigDecimal totalAmount;
	/**
	 * 直接购买数量
	 */
	private Integer buyNumber;
	/**
	 * 直接购买成功数量
	 */
	private Integer buySuccessNumber;
	/**
	 * 团购数量
	 */
	private Integer groupNumber;
	/**
	 * 团购成功数量
	 */
	private Integer groupSuccessNumber;
	/**
	 * 酒店预订数量
	 */
	private Integer hotelNumber;
	/**
	 * 预定预定成数量
	 */
	private Integer hotelSuccessNumber;

	/**
	 * set：
	 */
	public ReportOrder setId(Integer id) {
		this.id = id;
		return this;
	}

	/**
	 * get：
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * set：统计的是那一天
	 */
	public ReportOrder setAtDate(Date atDate) {
		this.atDate = atDate;
		return this;
	}

	/**
	 * get：统计的是那一天
	 */
	public Date getAtDate() {
		return atDate;
	}

	/**
	 * set：下单总数
	 */
	public ReportOrder setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
		return this;
	}

	/**
	 * get：下单总数
	 */
	public Integer getOrderNumber() {
		return orderNumber;
	}

	/**
	 * set：下单成功的总数
	 */
	public ReportOrder setOrderSuccessNumber(Integer orderSuccessNumber) {
		this.orderSuccessNumber = orderSuccessNumber;
		return this;
	}

	/**
	 * get：下单成功的总数
	 */
	public Integer getOrderSuccessNumber() {
		return orderSuccessNumber;
	}

	/**
	 * set：下单的总人数
	 */
	public ReportOrder setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
		return this;
	}

	/**
	 * get：下单的总人数
	 */
	public Integer getTotalNumber() {
		return totalNumber;
	}

	/**
	 * set：收入总金额
	 */
	public ReportOrder setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
		return this;
	}

	/**
	 * get：收入总金额
	 */
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	/**
	 * set：直接购买数量
	 */
	public ReportOrder setBuyNumber(Integer buyNumber) {
		this.buyNumber = buyNumber;
		return this;
	}

	/**
	 * get：直接购买数量
	 */
	public Integer getBuyNumber() {
		return buyNumber;
	}

	/**
	 * set：直接购买成功数量
	 */
	public ReportOrder setBuySuccessNumber(Integer buySuccessNumber) {
		this.buySuccessNumber = buySuccessNumber;
		return this;
	}

	/**
	 * get：直接购买成功数量
	 */
	public Integer getBuySuccessNumber() {
		return buySuccessNumber;
	}

	/**
	 * set：团购数量
	 */
	public ReportOrder setGroupNumber(Integer groupNumber) {
		this.groupNumber = groupNumber;
		return this;
	}

	/**
	 * get：团购数量
	 */
	public Integer getGroupNumber() {
		return groupNumber;
	}

	/**
	 * set：团购成功数量
	 */
	public ReportOrder setGroupSuccessNumber(Integer groupSuccessNumber) {
		this.groupSuccessNumber = groupSuccessNumber;
		return this;
	}

	/**
	 * get：团购成功数量
	 */
	public Integer getGroupSuccessNumber() {
		return groupSuccessNumber;
	}

	/**
	 * set：酒店预订数量
	 */
	public ReportOrder setHotelNumber(Integer hotelNumber) {
		this.hotelNumber = hotelNumber;
		return this;
	}

	/**
	 * get：酒店预订数量
	 */
	public Integer getHotelNumber() {
		return hotelNumber;
	}

	/**
	 * set：预定预定成数量
	 */
	public ReportOrder setHotelSuccessNumber(Integer hotelSuccessNumber) {
		this.hotelSuccessNumber = hotelSuccessNumber;
		return this;
	}

	/**
	 * get：预定预定成数量
	 */
	public Integer getHotelSuccessNumber() {
		return hotelSuccessNumber;
	}
}
