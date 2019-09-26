package com.vic.ck.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vic.ck.util.CommonUtils;
import com.vic.wroot.common.annotation.AttachmentFlag;
import com.vic.wroot.common.annotation.AttachmentFlag.AttachmenType;

/**
 * app版本管理
 * 
 * 
 * @author VIC
 */
public class PlatformAppVersion implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private Integer id;
	/**
	 * 版本名称
	 */
	private String name;
	/**
	 * 版本
	 */
	private String version;
	/**
	 * 数字编号，高版本始终大于低版本
	 */
	private Integer number;
	/**
	 * 手机类型：1-安卓 2-ios
	 */
	private Integer machineType;
	/**
	 * app类型 1-用户端 2-商家端
	 */
	private Integer appType;
	/**
	 * 更新说明
	 */
	private String log;
	/**
	 * ios下载地址 应指向苹果商店
	 */
	@JsonIgnore
	private String iosUrl;
	/**
	 * 安卓下载地址 附件表id
	 */
	@JsonIgnore
	@AttachmentFlag(AttachmenType.SIGN)
	private Integer androidUrl;
	/**
	 * 
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime;

	/**
	 * 下载地址
	 * 
	 * @return
	 */
	public String getDownloadUrl() {
		if (machineType == null) {
			return "";
		}
		String url = "";
		// 手机类型：1-安卓 2-ios
		switch (machineType) {
		case 1:
			url = CommonUtils.getImageUrl(androidUrl);
			break;
		case 2:
			url = iosUrl;
			break;
		default:
			break;
		}
		return url;
	}

	/**
	 * set：id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * get：id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * set：版本名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * get：版本名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * set：版本
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * get：版本
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * set：数字编号，高版本始终大于低版本
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}

	/**
	 * get：数字编号，高版本始终大于低版本
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * set：手机类型：1-安卓 2-ios
	 */
	public void setMachineType(Integer machineType) {
		this.machineType = machineType;
	}

	/**
	 * get：手机类型：1-安卓 2-ios
	 */
	public Integer getMachineType() {
		return machineType;
	}

	/**
	 * set：app类型 1-用户端 2-商家端
	 */
	public void setAppType(Integer appType) {
		this.appType = appType;
	}

	/**
	 * get：app类型 1-用户端 2-商家端
	 */
	public Integer getAppType() {
		return appType;
	}

	/**
	 * set：更新说明
	 */
	public void setLog(String log) {
		this.log = log;
	}

	/**
	 * get：更新说明
	 */
	public String getLog() {
		return log;
	}

	/**
	 * set：ios下载地址 应指向苹果商店
	 */
	public void setIosUrl(String iosUrl) {
		this.iosUrl = iosUrl;
	}

	/**
	 * get：ios下载地址 应指向苹果商店
	 */
	public String getIosUrl() {
		return iosUrl;
	}

	/**
	 * set：安卓下载地址 附件表id
	 */
	public void setAndroidUrl(Integer androidUrl) {
		this.androidUrl = androidUrl;
	}

	/**
	 * get：安卓下载地址 附件表id
	 */
	public Integer getAndroidUrl() {
		return androidUrl;
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
}
