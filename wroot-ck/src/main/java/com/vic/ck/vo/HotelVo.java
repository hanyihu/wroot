package com.vic.ck.vo;

import com.vic.ck.util.CommonUtils;

/**
 * 酒店商家下的预定列表
 * @author VIC
 *
 */
public class HotelVo {
	
	private int id;
	
	private String name;
	
	private int icon;
	
	//价格
	private double price;
	
	//窗户 1-无窗 2-有窗 3-部分有窗
	private int roomWindow;
	
	//早餐 1-无早餐 2-含早餐 3-双份早餐
	private int roomBreakfast;
	
	//床型 1-单人床 2-豪华大床 3-双人床 4-三人床
	private int roomBed;
	
	//可住几人
	private int roomPersonNum;
	
	//退订政策 1-当天18：00前可免费取消订单 2 预定成功后 不可变更取消
	private int unsubscribeWay;
	
	//订单确认时间 1-1小时内确认 2-立即确认
	private int verifyWay;
	
	public String getIconUrl() {
		return CommonUtils.getImageUrl(icon);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getRoomWindow() {
		return roomWindow;
	}

	public void setRoomWindow(int roomWindow) {
		this.roomWindow = roomWindow;
	}

	public int getRoomBreakfast() {
		return roomBreakfast;
	}

	public void setRoomBreakfast(int roomBreakfast) {
		this.roomBreakfast = roomBreakfast;
	}

	public int getRoomBed() {
		return roomBed;
	}

	public void setRoomBed(int roomBed) {
		this.roomBed = roomBed;
	}

	public int getRoomPersonNum() {
		return roomPersonNum;
	}

	public void setRoomPersonNum(int roomPersonNum) {
		this.roomPersonNum = roomPersonNum;
	}

	public int getUnsubscribeWay() {
		return unsubscribeWay;
	}

	public void setUnsubscribeWay(int unsubscribeWay) {
		this.unsubscribeWay = unsubscribeWay;
	}

	public int getVerifyWay() {
		return verifyWay;
	}

	public void setVerifyWay(int verifyWay) {
		this.verifyWay = verifyWay;
	}
	
	
}
