package com.vic.ck.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 城市订单流水
 * 
 * @author VIC
 */
public class ReportCityOrder implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 统计的是哪一天
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date atDate;
	/**
	 * 城市id
	 */
	private Integer cityId;
	/**
	 * 城市name
	 */
	private String cityName;
	/**
	 * 总流水
	 */
	private BigDecimal totalAmount;
	/**
	 * 直接购买流水
	 */
	private BigDecimal buyAmount;
	/**
	 * 团购流水
	 */
	private BigDecimal groupbuyAmount;
	/**
	 * 酒店预订流水
	 */
	private BigDecimal hotelAmount;
	/**
	 * 总订单数
	 */
	private Integer totalNumber;
	/**
	 * 直接购买的订单数量
	 */
	private Integer buyNumber;
	/**
	 * 团购订单数量
	 */
	private Integer groupbuyNumber;
	/**
	 * 酒店预订订单数量
	 */
	private Integer hotelNumber;
	/**
	 * 1-城市 2-平台全部(此时city_id为0)
	 */
	private Integer type;

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
	 * set：统计的是哪一天
	 */
	public void setAtDate(Date atDate) {
		this.atDate = atDate;
	}

	/**
	 * get：统计的是哪一天
	 */
	public Date getAtDate() {
		return atDate;
	}

	/**
	 * set：城市id
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	/**
	 * get：城市id
	 */
	public Integer getCityId() {
		return cityId;
	}

	/**
	 * set：城市name
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * get：城市name
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * set：总流水
	 */
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * get：总流水
	 */
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	/**
	 * set：直接购买流水
	 */
	public void setBuyAmount(BigDecimal buyAmount) {
		this.buyAmount = buyAmount;
	}

	/**
	 * get：直接购买流水
	 */
	public BigDecimal getBuyAmount() {
		return buyAmount;
	}

	/**
	 * set：团购流水
	 */
	public void setGroupbuyAmount(BigDecimal groupbuyAmount) {
		this.groupbuyAmount = groupbuyAmount;
	}

	/**
	 * get：团购流水
	 */
	public BigDecimal getGroupbuyAmount() {
		return groupbuyAmount;
	}

	/**
	 * set：酒店预订流水
	 */
	public void setHotelAmount(BigDecimal hotelAmount) {
		this.hotelAmount = hotelAmount;
	}

	/**
	 * get：酒店预订流水
	 */
	public BigDecimal getHotelAmount() {
		return hotelAmount;
	}

	/**
	 * set：总订单数
	 */
	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}

	/**
	 * get：总订单数
	 */
	public Integer getTotalNumber() {
		return totalNumber;
	}

	/**
	 * set：直接购买的订单数量
	 */
	public void setBuyNumber(Integer buyNumber) {
		this.buyNumber = buyNumber;
	}

	/**
	 * get：直接购买的订单数量
	 */
	public Integer getBuyNumber() {
		return buyNumber;
	}

	/**
	 * set：团购订单数量
	 */
	public void setGroupbuyNumber(Integer groupbuyNumber) {
		this.groupbuyNumber = groupbuyNumber;
	}

	/**
	 * get：团购订单数量
	 */
	public Integer getGroupbuyNumber() {
		return groupbuyNumber;
	}

	/**
	 * set：酒店预订订单数量
	 */
	public void setHotelNumber(Integer hotelNumber) {
		this.hotelNumber = hotelNumber;
	}

	/**
	 * get：酒店预订订单数量
	 */
	public Integer getHotelNumber() {
		return hotelNumber;
	}

	/**
	 * set：1-城市 2-平台全部(此时city_id为0)
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * get：1-城市 2-平台全部(此时city_id为0)
	 */
	public Integer getType() {
		return type;
	}
}
