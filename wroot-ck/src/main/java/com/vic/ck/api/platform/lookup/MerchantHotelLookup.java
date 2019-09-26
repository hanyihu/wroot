package com.vic.ck.api.platform.lookup;

import com.vic.base.BaseApiLookup;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * 酒店商家的查询条件
 * @author VIC
 *
 */
public class MerchantHotelLookup extends BaseApiLookup{
	//排序字段 0-价格 1- 距离  2-好评 3-销量
	private Integer sortType;
	
	//排序 方式 1-升序 2-降序
	private Integer orderType;
	
	//经度
	@NotNull(message = "需传入当前经度")
	private Double longitude;
	
	//纬度
	@NotNull(message = "需传入当前纬度")
	private Double latitude;
	
	//距离
	private Double distance;
	
	//价格上限
	private Double upPrice;
	
	//价格下限
	private Double downPrice;
	
	/**查询的关键字*/
	private String keywords;
	
	//酒店星级 1-客栈 2-公寓 3-经济舒适型 4-高档豪华型 5-经济连锁 6-二星酒店 7-三星酒店 8-四星 9-五星
	private Integer hotelLevel;

	public String getSortType() {
		String sort = SORT_COLUMN.get(sortType);
		return StringUtils.isBlank(sort) ? "distance" : sort;
	}

	public void setSortType(Integer sortType) {
		this.sortType = sortType;
	}

	public String getOrderType() {
		return orderType == null ? "DESC" : (orderType==1? "ASC" :"DESC");
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	
	
	
	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude; 
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}



	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}


    @Override
    public Integer getCityId() {
        return cityId;
    }

    @Override
    public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}






	public Double getUpPrice() {
		return upPrice;
	}

	public void setUpPrice(Double upPrice) {
		this.upPrice = upPrice;
	}

	public Double getDownPrice() {
		return downPrice;
	}

	public void setDownPrice(Double downPrice) {
		this.downPrice = downPrice;
	}

	public Integer getHotelLevel() {
		return hotelLevel;
	}

	public void setHotelLevel(Integer hotelLevel) {
		this.hotelLevel = hotelLevel;
	}






	public static Map<Integer, String> SORT_COLUMN =  new HashMap<Integer, String>();
	
	static {
		SORT_COLUMN.put(0, "price");
		SORT_COLUMN.put(1, "distance");
		SORT_COLUMN.put(2, "star");
		SORT_COLUMN.put(3, "sell_num");
	}
}
