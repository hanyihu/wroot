package com.vic.ck.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vic.ck.util.CommonUtils;
import com.vic.wroot.common.annotation.AttachmentFlag;

import java.io.Serializable;
import java.util.List;

/**
 * banner图
 * 
 * @author VIC
 */
public class PlatformBanner implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 
	 */
	//private Integer image;

	@AttachmentFlag(AttachmentFlag.AttachmenType.SIGNS)
	private String image;

	/**
	 * 类型 1-首页banner 2-百惠店 3-社区 4-积分商城 5-骑手二维码
	 */
	private Integer type;
	/**
	 * 跳转方式 1-页面，2-商家
	 */
	private Integer urlType;
	/**
	 * 跳转目标。url_type=1 则为页面url，2则为商家id
	 */
	private String target;
	
	/**
	 * 跳转目标。url_type=2时的商家的name
	 */
	@JsonIgnore
	private String merchantName;
	
	private String targetDesc;
	/**
	 * 城市id,0为全国
	 */
	private Integer cityId;
	
	private String cityName;
	
	/**
	 * 是否启用
	 */
	private Integer enabled;
	/**
	 * 排序字段
	 */
	private Integer sort;
	/**
	 * 说明
	 */
	private String title;
	
	public String getUrlTypeDesc() {
		return urlType == null?"页面" :(urlType ==2? "商家":"页面");
	}
	public String getTypeDesc() {
		return type == null?"首页" :(type ==2 ? "百惠店":type ==3 ? "社区":( type == 4 ?"积分商城":(type == 5 ?"骑手二维码图":"首页")));
	}
	
	public String getEnabledDesc() {
		return enabled == null?"不可用" :(enabled ==1? "可用":"不可用");
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * set：类型 1-首页banner 2-百惠店 3-社区
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * get：类型 1-首页banner 2-百惠店 3-社区
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * set：跳转方式 1-页面，2-商家
	 */
	public void setUrlType(Integer urlType) {
		this.urlType = urlType;
	}

	/**
	 * get：跳转方式 1-页面，2-商家
	 */
	public Integer getUrlType() {
		return urlType;
	}

	/**
	 * set：跳转目标。url_type=1 则为页面url，2则为商家id
	 */
	public void setTarget(String target) {
		this.target = target;
	}

	/**
	 * get：跳转目标。url_type=1 则为页面url，2则为商家id
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * set：城市id,0为全国
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	/**
	 * get：城市id,0为全国
	 */
	public Integer getCityId() {
		return cityId;
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
	 * set：排序字段
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * get：排序字段
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * set：说明
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * get：说明
	 */
	public String getTitle() {
		return title;
	}
	public String getTargetDesc() {
		return targetDesc;
	}
	public void setTargetDesc(String targetDesc) {
		this.targetDesc = targetDesc;
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

/*	public String getImageUrl() {
		if ((image == null || image == 0)) {
			return null;
		}
		return CommonUtils.getImageUrl(image);
	}*/
public List<String> getImageUrl() {
	if ((image == null )) {
		return null;
	}
	return CommonUtils.getImageUrls(image);
}

	
}
