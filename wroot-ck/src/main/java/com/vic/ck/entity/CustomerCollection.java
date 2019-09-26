package com.vic.ck.entity;

import java.math.BigDecimal;

import com.vic.ck.util.CommonUtils;

/**
 * 我的收藏
 * @author VIC
 *
 */
public class CustomerCollection {

	private int id;
	
	private int customerId;
	
	private String name;
	
	//1-商家 2-团购 3-酒店
	private int type;
	
	//商家或者团购id
	private int targetId;
	
	
	/**************以下是展示的时候的字段*********************/
	
	//类型为团购的时候 所对应的商家的id
	private int merchantId;
	
	private int image;
	
	private double star;
	
	private BigDecimal price = new BigDecimal(0.0);
	
	private int sellNum;
	
	//当前活动 1-满减 2-优惠券，3-立减 都有则逗号分隔
	private String  activity;
	
	//分类
	private String categoryName;

	//地区
	private String areaName;
	
	//距离
	private BigDecimal distance;
	
	/**
	 * type=1时  商家的类型
	 */
	private int merchantType;
	
	/**
	 * 商家好评数
	 */
	private int niceCommentNum;
	
	/**
	 * 酒店星级 1-客栈 2-公寓 3-经济舒适型 4-高档豪华型 5-经济连锁 6-二星酒店 7-三星酒店 8-四星 9-五星
	 */
	private Integer hotelLevel;
	
	//距离的描述
	public String getDistanceDesc() {
		return CommonUtils.getDistanceDesc(distance);
	}
	

	public CustomerCollection(int customerId, int type, int targetId) {
		super();
		this.customerId = customerId;
		this.type = type;
		this.targetId = targetId;
	}


	public CustomerCollection() {
		super();
	}


	public String getImageUrl(){
		return CommonUtils.getImageUrl(image);
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getTargetId() {
		return targetId;
	}

	public void setTargetId(int targetId) {
		this.targetId = targetId;
	}

	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}

	public double getStar() {
		return star;
	}

	public void setStar(double star) {
		this.star = star;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getSellNum() {
		return sellNum;
	}

	public void setSellNum(int sellNum) {
		this.sellNum = sellNum;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getActivity() {
		return activity;
	}


	public void setActivity(String activity) {
		this.activity = activity;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public String getAreaName() {
		return areaName;
	}


	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}


	public BigDecimal getDistance() {
		return distance;
	}


	public void setDistance(BigDecimal distance) {
		this.distance = distance;
	}


	public int getMerchantType() {
		return merchantType;
	}


	public void setMerchantType(int merchantType) {
		this.merchantType = merchantType;
	}


	public int getNiceCommentNum() {
		return niceCommentNum;
	}


	public void setNiceCommentNum(int niceCommentNum) {
		this.niceCommentNum = niceCommentNum;
	}


	public Integer getHotelLevel() {
		return hotelLevel;
	}


	public void setHotelLevel(Integer hotelLevel) {
		this.hotelLevel = hotelLevel;
	}


	public int getMerchantId() {
		return merchantId;
	}


	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}
	
	
	
	
	
}
