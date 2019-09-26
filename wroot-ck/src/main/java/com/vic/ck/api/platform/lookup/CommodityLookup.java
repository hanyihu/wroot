package com.vic.ck.api.platform.lookup;

import com.vic.base.BaseApiLookup;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

public class CommodityLookup extends BaseApiLookup{


	//平台商品分类id
	private Integer MerchantCategory;
	
	//排序字段 0-价格 1- 距离 2-签到  3-好评 4-销量 
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
	
	/**查询的关键字*/
	private String keywords;
	
	//团购的id
	private Integer groupbuyId;
	

	public Integer getMerchantCategory() {
		return MerchantCategory;
	}

	public void setMerchantCategory(Integer merchantCategory) {
		MerchantCategory = merchantCategory;
	}

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



	public Integer getGroupbuyId() {
		return groupbuyId;
	}

	public void setGroupbuyId(Integer groupbuyId) {
		this.groupbuyId = groupbuyId;
	}



	public static Map<Integer, String> SORT_COLUMN =  new HashMap<Integer, String>();
	
	static {
		SORT_COLUMN.put(0, "price");
		SORT_COLUMN.put(1, "distance");
		SORT_COLUMN.put(2, "view_num");
		SORT_COLUMN.put(3, "star");
		SORT_COLUMN.put(4, "sell_num");
	}
}
