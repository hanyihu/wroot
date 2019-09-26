package com.vic.ck.entity;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vic.ck.util.CommonUtils;
import com.vic.wroot.common.annotation.AttachmentFlag;
import com.vic.wroot.common.annotation.AttachmentFlag.AttachmenType;

import java.io.Serializable;
import java.util.Date;

/**
 * 平台商品分类商家广告位
 * 
 * @author VIC
 */
public class PlatformCategoryAdPosition implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 所属城市
	 */
	private Integer cityId;
	/**
	 * 所属平台分类
	 */
	private Integer merchantCategory;
	/**
	 * 商家id
	 */
	private Integer merchantId;
	
	
	/**
	 * 平台对商家的描述
	 */
	private String platformDesc;
	/**
	 * 平台上传的商家图标
	 */
	@AttachmentFlag(AttachmenType.SIGN)
	private Integer icon;
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
	
	// 分类名称
	@JsonIgnore
	private String merchantCategoryName;
	
	// 城市
	@JsonIgnore
	private String cityName;
	
	//商家名称
	private String merchantName;
	
	/**
	 * //商家类型 类型 1-美食 2-酒店预订 3-其他 
	 */
	private String merchantType;

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
	 * set：所属平台分类
	 */
	public void setMerchantCategory(Integer merchantCategory) {
		this.merchantCategory = merchantCategory;
	}

	/**
	 * get：所属平台分类
	 */
	public Integer getMerchantCategory() {
		return merchantCategory;
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
	 * set：平台上传的商家图标
	 */
	public void setIcon(Integer icon) {
		this.icon = icon;
	}

	/**
	 * get：平台上传的商家图标
	 */
	public Integer getIcon() {
		return icon;
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

	public String getMerchantCategoryName() {
		return merchantCategoryName;
	}

	public void setMerchantCategoryName(String merchantCategoryName) {
		this.merchantCategoryName = merchantCategoryName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}
	
	
}
