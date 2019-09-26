package com.vic.ck.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vic.ck.util.CommonUtils;

/**
 * 
 * @author VIC
 *
 */
public class PlatformMerchantCategory {

	private Integer id;
	
	private String name;
	
	private Integer icon;

    //类型 1-餐饮美食 2-商超便利 3-时尚达人 4-母婴亲子 5-同城便利 6-本地医疗 7-学习培训  8-生活服务 9-其他
	private Integer type;
	
	@JsonIgnore
	private Integer enabled;
	
	@JsonIgnore
	private Integer sort;
	
	//服务费百分比
	@JsonIgnore
	private Integer servicePercent = 6;

	
	public String getIconUrl() {
		return CommonUtils.getImageUrl(icon);
	}
	
	public String getTypeDesc() {
        return type == null ? "其他" : (type == 1 ? "餐饮美食" : (type == 2 ? "商超便利" : (type == 3 ? "时尚达人" : (type == 4 ? "母婴亲子" : (type == 5 ? "同城便利" : (type == 6 ? "本地医疗" : (type == 7 ? "学习培训" : (type == 8 ? "生活服务" : "其他"))))))));
	}
	
	public String getEnabledDesc() {
		return enabled == null?"不可用" :(enabled ==1? "可用":"不可用");
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

	public Integer getIcon() {
		return icon;
	}

	public void setIcon(Integer icon) {
		this.icon = icon;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getServicePercent() {
		return servicePercent;
	}

	public void setServicePercent(Integer servicePercent) {
		this.servicePercent = servicePercent;
	}
	
	
	
}
