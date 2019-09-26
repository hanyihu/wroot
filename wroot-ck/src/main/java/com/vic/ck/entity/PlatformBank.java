package com.vic.ck.entity;

import java.io.Serializable;

import com.vic.wroot.common.annotation.AttachmentFlag;
import com.vic.wroot.common.annotation.AttachmentFlag.AttachmenType;

/**
 * 银行表
 * 
 * @author VIC
 */
public class PlatformBank implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 银行名
	 */
	private String name;

	/*
	* 银行图标
	* */
	@AttachmentFlag(AttachmenType.SIGN)
	private Integer icon;

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
	 * set：
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * get：
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
