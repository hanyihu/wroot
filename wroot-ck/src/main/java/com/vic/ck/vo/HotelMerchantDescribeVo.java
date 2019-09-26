package com.vic.ck.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 酒店商家介绍
 * 
 * @author VIC
 *
 */
public class HotelMerchantDescribeVo {

	private int id;
	
	// 图文描述
	private String describe;

	// 入店时间
	@JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
	private Date hotelInTime;
	// 离店时间
	@JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
	private Date hotelOutTime;
	
	//加床信息
	private boolean hotelCanAddBed;
	
	private BigDecimal hotelAddBedPrice;
	
	//接待外宾
	private boolean hotelForeigned;
	//携带宠物
	private boolean hotelPeted;
	//酒店-是否需要押金
	private boolean hotelPledge;
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public Date getHotelInTime() {
		return hotelInTime;
	}
	public void setHotelInTime(Date hotelInTime) {
		this.hotelInTime = hotelInTime;
	}
	public Date getHotelOutTime() {
		return hotelOutTime;
	}
	public void setHotelOutTime(Date hotelOutTime) {
		this.hotelOutTime = hotelOutTime;
	}
	public boolean isHotelCanAddBed() {
		return hotelCanAddBed;
	}
	public void setHotelCanAddBed(boolean hotelCanAddBed) {
		this.hotelCanAddBed = hotelCanAddBed;
	}
	public BigDecimal getHotelAddBedPrice() {
		return hotelAddBedPrice;
	}
	public void setHotelAddBedPrice(BigDecimal hotelAddBedPrice) {
		this.hotelAddBedPrice = hotelAddBedPrice;
	}
	public boolean isHotelForeigned() {
		return hotelForeigned;
	}
	public void setHotelForeigned(boolean hotelForeigned) {
		this.hotelForeigned = hotelForeigned;
	}
	public boolean isHotelPeted() {
		return hotelPeted;
	}
	public void setHotelPeted(boolean hotelPeted) {
		this.hotelPeted = hotelPeted;
	}
	public boolean isHotelPledge() {
		return hotelPledge;
	}
	public void setHotelPledge(boolean hotelPledge) {
		this.hotelPledge = hotelPledge;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
	
}
