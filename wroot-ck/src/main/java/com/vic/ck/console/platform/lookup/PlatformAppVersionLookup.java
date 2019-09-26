package com.vic.ck.console.platform.lookup;

import com.vic.base.pager.CommonLookup;

/**
 * 封装app版本管理 查询条件
 * 
 * @author VIC
 */
public class PlatformAppVersionLookup extends CommonLookup {
	
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

	private String version;

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getMachineType() {
		return machineType;
	}

	public void setMachineType(Integer machineType) {
		this.machineType = machineType;
	}

	public Integer getAppType() {
		return appType;
	}

	public void setAppType(Integer appType) {
		this.appType = appType;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	
	
}
