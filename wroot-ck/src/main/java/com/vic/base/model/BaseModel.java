
package com.vic.base.model;

import com.vic.ck.util.CommonUtils;

public class BaseModel {

	private int id;
	
	private String name;
	
	private Integer icon;
	
	public String getIconUrl(){
		return CommonUtils.getImageUrl(icon);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
	
	
}
