package com.vic.ck.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.github.pagehelper.StringUtil;
import com.vic.ck.util.CommonUtils;

/**
 * 一般商家列表数据
 * @author VIC
 *
 */

public class MerchantVo {

	private Integer id;
	
	private String name;
	
	private Double star;
	
	private BigDecimal distance;
	
	private Double price;
	
	private Integer icon;
	
	//销售量  酒店没有此字段
	private Integer sellNum;
	
	//当前活动 1-满减 2-优惠券，3-立减 都有则逗号分隔
	private String activity;
	
	private Integer categoryId;
	
	private String categoryName;
	
	private String areaName;
	
	private Integer type;
	
	/*********酒店新增和不查找的字段**************/
	
	//好评数
	private Integer niceCommentNum;
	
	//酒店星级 1-客栈 2-公寓 3-经济舒适型 4-高档豪华型 5-经济连锁 6-二星酒店 7-三星酒店 8-四星 9-五星
	private Integer hotelLevel;
	
	//距离的描述
	public String getDistanceDesc() {
		return CommonUtils.getDistanceDesc(distance);
	}
	
	
	//图标
	public String getIconUrl() {
		return CommonUtils.getImageUrl(icon);
	}
	
	//活动描述
	public List<String> getActivities(){
		List<String> result = new ArrayList<String>();
		if(StringUtil.isNotEmpty(activity)) {
			if(activity.contains("1")) {
				result.add("满减");
			}
			if(activity.contains("2")) {
				result.add("优惠券");
			}
			if(activity.contains("3")) {
				result.add("立减");
			}
		}
		return result;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getStar() {
		return star;
	}

	public void setStar(Double star) {
		this.star = star;
	}

	public BigDecimal getDistance() {
		return distance;
	}

	public void setDistance(BigDecimal distance) {
		this.distance = distance;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getIcon() {
		return icon;
	}

	public void setIcon(Integer icon) {
		this.icon = icon;
	}

	public Integer getSellNum() {
		return sellNum;
	}

	public void setSellNum(Integer sellNum) {
		this.sellNum = sellNum;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}


	public Integer getNiceCommentNum() {
		return niceCommentNum;
	}


	public void setNiceCommentNum(Integer niceCommentNum) {
		this.niceCommentNum = niceCommentNum;
	}


	public Integer getHotelLevel() {
		return hotelLevel;
	}


	public void setHotelLevel(Integer hotelLevel) {
		this.hotelLevel = hotelLevel;
	}
	
	
	
	
	
}
