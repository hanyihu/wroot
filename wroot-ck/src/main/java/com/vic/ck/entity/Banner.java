package com.vic.ck.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vic.ck.util.CommonUtils;

/**
 * Banner
 * 
 * @author VIC
 *
 */
public class Banner {

	private int id;

	private int image;

	private String title;

	// 跳转方式 1-页面，2-商家
	private int urlType;
	// 跳转目标。url_type=1 则为页面url，2则为商家id
	private String target;

	// 类型 1-首页banner 2-百惠店 3-社区
	@JsonIgnore
	private int type;

	public String getImageUrl() {
		return CommonUtils.getImageUrl(image);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getUrlType() {
		return urlType;
	}

	public void setUrlType(int urlType) {
		this.urlType = urlType;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
