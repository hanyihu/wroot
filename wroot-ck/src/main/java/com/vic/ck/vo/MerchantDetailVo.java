package com.vic.ck.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vic.ck.entity.MerchantActivity;
import com.vic.ck.util.CommonUtils;

/**
 * 商家详情
 * @author VIC
 *
 */
public class MerchantDetailVo {

	private int id;

	private int type;
	
	private String name;
	
	//内景图 多张
	private String inPic;
	
	
	private double star;
	
	//口味评分
	private double flavorStar;
	
	//环境评分
	private double environmentStar;
	
	//服务评星
	private double serviceStar;
	
	
	//所在区
	private String areaName;
	
	//地址
	private String address;
	
	//附件地标
	private String landmarks;
	
	private double longitude;
	
	private double latitude;
	
	//电话
	private String mobile;
	
	//商家优惠活动 两种 每种最多一个
	private List<MerchantActivity> activities = new ArrayList<MerchantActivity>();
	
	//评论数
	private int commentNum;
	
	// 评论列表 2 条
	private List<CommentVo> comments = new ArrayList<CommentVo>();
	
	//来过 签到人数
	private int viewNum;
	
	//营业时间
	private String businessHours;
	
	//商家设施  多个逗号分隔
	private String facility;
	
	//是否签到 - 今日
	private boolean signed;
	
	//是否收藏
	private boolean collected;
	
	//团购套餐2个
	private List<CommodityVo>  commodities = new ArrayList<CommodityVo>();

	
	/******酒店的商家详情会多两个字段  但是去掉团购套餐 start ******/
	//入店时间
	
	@JsonFormat(pattern="HH:mm", timezone = "GMT+8")
	private Date hotelInTime;
	//离店时间
	@JsonFormat(pattern="HH:mm", timezone = "GMT+8")
	private Date hotelOutTime;
	
	//是否需要押金
	private boolean hotelPledge;
	
	/******酒店的商家详情会多两个字段  但是去掉团购套餐 end******/
	
	
	public List<String> getInPicUrls(){
		return CommonUtils.getImageUrls(inPic);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInPic() {
		return inPic;
	}

	public void setInPic(String inPic) {
		this.inPic = inPic;
	}

	public double getStar() {
		return star;
	}

	public void setStar(double star) {
		this.star = star;
	}

	public double getFlavorStar() {
		return flavorStar;
	}

	public void setFlavorStar(double flavorStar) {
		this.flavorStar = flavorStar;
	}

	public double getEnvironmentStar() {
		return environmentStar;
	}

	public void setEnvironmentStar(double environmentStar) {
		this.environmentStar = environmentStar;
	}

	public double getServiceStar() {
		return serviceStar;
	}

	public void setServiceStar(double serviceStar) {
		this.serviceStar = serviceStar;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLandmarks() {
		return landmarks;
	}

	public void setLandmarks(String landmarks) {
		this.landmarks = landmarks;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public List<MerchantActivity> getActivities() {
		return activities;
	}

	public void setActivities(List<MerchantActivity> activities) {
		this.activities = activities;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	public List<CommentVo> getComments() {
		return comments;
	}

	public void setComments(List<CommentVo> comments) {
		this.comments = comments;
	}

	public int getViewNum() {
		return viewNum;
	}

	public void setViewNum(int viewNum) {
		this.viewNum = viewNum;
	}

	public String getBusinessHours() {
		return businessHours;
	}

	public void setBusinessHours(String businessHours) {
		this.businessHours = businessHours;
	}

	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	public boolean isSigned() {
		return signed;
	}

	public void setSigned(boolean signed) {
		this.signed = signed;
	}

	public boolean isCollected() {
		return collected;
	}

	public void setCollected(boolean collected) {
		this.collected = collected;
	}

	public List<CommodityVo> getCommodities() {
		return commodities;
	}

	public void setCommodities(List<CommodityVo> commodities) {
		this.commodities = commodities;
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

	public boolean isHotelPledge() {
		return hotelPledge;
	}

	public void setHotelPledge(boolean hotelPledge) {
		this.hotelPledge = hotelPledge;
	}  
	
	
	

}
