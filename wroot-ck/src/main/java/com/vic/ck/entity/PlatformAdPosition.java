package com.vic.ck.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vic.ck.util.CommonUtils;
import com.vic.wroot.common.annotation.AttachmentFlag;
import com.vic.wroot.common.annotation.AttachmentFlag.AttachmenType;

/**
 * 平台广告位表
 * 
 * @author VIC
 */
public class PlatformAdPosition implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 所属城市
	 */
	private Integer cityId;
	
	private String cityName;
	/**
	 * 商家id
	 */
	private Integer merchantId;
	
	private String merchantName;
	/**
	 * 平台对商家的描述
	 */
	private String platformDesc;
	/**
	 * 展示位置：1-首页 2-百惠店
	 */
	private Integer type;
	/**
	 * 状态 1-启用 0-禁用
	 */
	private Boolean enabled;
	
	/**
	 * 排序
	 */
	private Integer  sort;
	/**
	 * 
	 */
	@JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime;
	
	/**
	 * 商家类型 类型 1-美食 2-酒店预订 3-其他                  
	 */
	private Integer merchantType;
	
	
	/**
	 * 平台上传的图标
	 */
	@AttachmentFlag(AttachmenType.SIGN)
	private Integer icon;
	
	
	
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
	 * set：商家id
	 */
	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	/**
	 * get：商家id
	 */
	public Integer getMerchantId() {
		return merchantId;
	}

	/**
	 * set：平台对商家的描述
	 */
	public void setPlatformDesc(String platformDesc) {
		this.platformDesc = platformDesc;
	}

	/**
	 * get：平台对商家的描述
	 */
	public String getPlatformDesc() {
		return platformDesc;
	}

	/**
	 * set：展示位置：1-首页 2-百惠店
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * get：展示位置：1-首页 2-百惠店
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * set：状态 1-启用 0-禁用
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * get：状态 1-启用 0-禁用
	 */
	public Boolean getEnabled() {
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

	/**
	 * set：平台上传的图标
	 */
	public void setIcon(Integer icon) {
		this.icon = icon;
	}

	/**
	 * get：平台上传的图标
	 */
	public Integer getIcon() {
		return icon;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(Integer merchantType) {
		this.merchantType = merchantType;
	}
	
	
	
	
}
