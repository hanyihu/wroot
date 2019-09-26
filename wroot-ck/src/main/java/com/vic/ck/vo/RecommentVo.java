package com.vic.ck.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vic.ck.util.CommonUtils;

/**
 * 推荐列表
 * @author VIC
 *
 */
public class RecommentVo {

	private int id;
	
	//商家或用户名称
	private String name;
	
	//手机号
	private String mobile;
	
	//图标-头像或图标
	private int icon;
	
	//注册时间
	@JsonFormat( pattern="yyyy-MM-dd", timezone = "GMT+8")
	private Date createTime;
	
	public String getIconUrl() {
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
}
