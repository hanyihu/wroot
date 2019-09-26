package com.vic.ck.vo;

import java.util.List;

import com.vic.ck.util.CommonUtils;

public class HotelDetailVo {
	
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
	
	//电话 1-无电话 2-免费电话 3-收费电话
	private int roomTel;
	
	//可住几人
	private int roomPersonNum;
	
	//退订政策 1-当天18：00前可免费取消订单 2 预定成功后 不可变更取消
	private int unsubscribeWay;
	
	//订单确认时间 1-1小时内确认 2-立即确认
	private int verifyWay;
	
	private String images;
	
	//库存   押金
	private int stock;
	//面积 
	private double roomArea;
	
	//宽带 1-无网络 2-有线宽带 3-无线WIFI
	private int roomNetwork;
	
	//楼层
	private int roomFloor;
	
	//窗景：1-无 2-风景3-城景 4-花园景 5-地标景 6-无敌海景
	private int roomWindowmView;
	
	
	 /**
     * 可否加床
     */
    private Boolean roomAddBeded;

    /**
     * 无烟 1- 可吸烟2-该房无烟处理
     */
    private Integer roomSmoke;
    
    /**
     *床宽1-圆形床 2-1.5米大床 3-1.8米大床 3-2.0米大床
     */
    private Integer roomBedWide;
	
	public String getIconUrl() {
		return CommonUtils.getImageUrl(icon);
	}
	
	public List<String> getImageUrls(){
		return CommonUtils.getImageUrls(images);
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

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getRoomArea() {
		return roomArea;
	}

	public void setRoomArea(double roomArea) {
		this.roomArea = roomArea;
	}

	public int getRoomNetwork() {
		return roomNetwork;
	}

	public void setRoomNetwork(int roomNetwork) {
		this.roomNetwork = roomNetwork;
	}

	public int getRoomFloor() {
		return roomFloor;
	}

	public void setRoomFloor(int roomFloor) {
		this.roomFloor = roomFloor;
	}

	public int getRoomWindowmView() {
		return roomWindowmView;
	}

	public void setRoomWindowmView(int roomWindowmView) {
		this.roomWindowmView = roomWindowmView;
	}

	public int getRoomTel() {
		return roomTel;
	}

	public void setRoomTel(int roomTel) {
		this.roomTel = roomTel;
	}

	public Boolean getRoomAddBeded() {
		return roomAddBeded;
	}

	public void setRoomAddBeded(Boolean roomAddBeded) {
		this.roomAddBeded = roomAddBeded;
	}

	public Integer getRoomSmoke() {
		return roomSmoke;
	}

	public void setRoomSmoke(Integer roomSmoke) {
		this.roomSmoke = roomSmoke;
	}

	public Integer getRoomBedWide() {
		return roomBedWide;
	}

	public void setRoomBedWide(Integer roomBedWide) {
		this.roomBedWide = roomBedWide;
	}
	
	

}
