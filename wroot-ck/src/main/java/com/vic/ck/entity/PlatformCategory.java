package com.vic.ck.entity;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.vic.ck.util.CommonUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 平台特色商家分类表
 * 
 * @author VIC
 */
public class PlatformCategory implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 1-首页 2-百惠店
	 */
	private Integer type;
	/**
	 * 分类名称
	 */
	private String name;
	/**
	 * 图标
	 */
	private Integer icon;
	/**
	 * 所属城市
	 */
	private Integer cityId;
	private String cityName;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 是否启用
	 */
	private Integer enabled;
	
	
	/**
	 * 
	 */
	@JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime;

	public String getIconUrl() {
		return CommonUtils.getImageUrl(icon);
	}
	
	
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
	 * set：1-首页 2-百惠店
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * get：1-首页 2-百惠店
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * set：分类名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * get：分类名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * set：图标
	 */
	public void setIcon(Integer icon) {
		this.icon = icon;
	}

	/**
	 * get：图标
	 */
	public Integer getIcon() {
		return icon;
	}

	/**
	 * set：所属城市
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	/**
	 * get：所属城市
	 */
	public Integer getCityId() {
		return cityId;
	}

	/**
	 * set：排序
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * get：排序
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * set：是否启用
	 */
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	/**
	 * get：是否启用
	 */
	public Integer getEnabled() {
		return enabled;
	}

	/**
	 * set：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * get：
	 */
	public Date getCreateTime() {
		return createTime;
	}


	public String getCityName() {
		return cityName;
	}


	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
}
