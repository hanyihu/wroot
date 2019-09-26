package com.vic.ck.console.entity;

import org.apache.commons.lang3.StringUtils;

/**
 * 用户对象
 * 	table:sys_user
 * @author VIC
 *
 */
public class User extends BaseEntity{

	private String username;
	
	private String password;
	
	private String nickname;
	
	private String mobile;
	
	
	/**状态:0-禁用1-启用*/
	private int status;
	
	/**头像-附件表id*/
	private int portrait;

	/**
	 * 管辖城市
	 */
	private Integer cityId;
	
	private String cityName;
	
	public String getStatusStr(){
		return 1 == status ? "激活" : "冻结";
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getPortrait() {
		return portrait;
	}

	public void setPortrait(int portrait) {
		this.portrait = portrait;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		if(StringUtils.isBlank(cityName)) {
			return "全国";
		}
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	

	

}
