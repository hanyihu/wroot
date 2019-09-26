package com.vic.ck.entity;

import java.io.Serializable;

import com.vic.ck.util.CommonUtils;
import com.vic.wroot.common.annotation.AttachmentFlag;
import com.vic.wroot.common.annotation.AttachmentFlag.AttachmenType;

/**
 * 积分商城分类
 * 
 * @author VIC
 */
public class ScoreMallCategory implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 分类名称
	 */
	private String name;
	
	@AttachmentFlag(AttachmenType.SIGN)
	private Integer icon;
	
	public String getIconUrl(){
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

	public Integer getIcon() {
		return icon;
	}

	public void setIcon(Integer icon) {
		this.icon = icon;
	}
}
