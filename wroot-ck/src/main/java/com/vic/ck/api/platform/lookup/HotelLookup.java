package com.vic.ck.api.platform.lookup;

import com.vic.base.BaseApiLookup;

/**
 * 酒店的查询条件
 * 
 * @author VIC
 *
 */
public class HotelLookup extends BaseApiLookup {


	// 早餐 1-无早餐 2-含早餐 3-双份早餐
	private Integer roomBreakfast;

	// 床型 1-单人床 2-豪华大床 3-双人床 4-三人床
	private Integer roomBed;


	// 退订政策 1-当天18：00前可免费取消订单 2 预定成功后 不可变更取消
	private Integer unsubscribeWay;

	// 订单确认时间 1-1小时内确认 2-立即确认
	private Integer verifyWay;

	public Integer getRoomBreakfast() {
		return roomBreakfast;
	}

	public void setRoomBreakfast(Integer roomBreakfast) {
		this.roomBreakfast = roomBreakfast;
	}

	public Integer getRoomBed() {
		return roomBed;
	}

	public void setRoomBed(Integer roomBed) {
		this.roomBed = roomBed;
	}

	public Integer getUnsubscribeWay() {
		return unsubscribeWay;
	}

	public void setUnsubscribeWay(Integer unsubscribeWay) {
		this.unsubscribeWay = unsubscribeWay;
	}

	public Integer getVerifyWay() {
		return verifyWay;
	}

	public void setVerifyWay(Integer verifyWay) {
		this.verifyWay = verifyWay;
	}
	
	

}
