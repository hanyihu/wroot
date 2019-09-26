package com.vic.ck.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 系统的推送信息表
 * 
 * @author VIC
 */
public class PlatformMsg implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 1-用户 2-商家 3-骑手
	 */
	private Integer role;

	/*标题*/
	private String title;

	/**
	 * 
	 */
	private String content;
	/**
	 * 
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date pushTime;
	/**
	 * 0-初始 1-已推送
	 */
	private Integer status;
	/**
	 * 1-全平台 2-城市 3-个人
	 */
	private Integer targtType;
	/**
	 * targt_type为2时城市id
	 */
	private Integer cityId;
	
	private String cityName;
	/**
	 * targt_type为3时目标电话
	 */
	private String mobile;
	
	public String getTargetDesc(){
		String desc = "全平台";
		if(targtType == null || targtType ==1){
			desc = "全平台";
		}else if(targtType ==2){
			desc = cityName;
		}else {
			desc = mobile;
		}
		return desc;
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
	 * set：1-用户 2-商家
	 */
	public void setRole(Integer role) {
		this.role = role;
	}

	/**
	 * get：1-用户 2-商家
	 */
	public Integer getRole() {
		return role;
	}

	/**
	 * set：
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * get：
	 */
	public String getContent() {
		return content;
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

	/**
	 * set：0-初始 1-已推送
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * get：0-初始 1-已推送
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * set：1-全平台 2-城市 3-个人
	 */
	public void setTargtType(Integer targtType) {
		this.targtType = targtType;
	}

	/**
	 * get：1-全平台 2-城市 3-个人
	 */
	public Integer getTargtType() {
		return targtType;
	}

	/**
	 * set：targt_type为2时城市id
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	/**
	 * get：targt_type为2时城市id
	 */
	public Integer getCityId() {
		return cityId;
	}

	/**
	 * set：targt_type为3时目标电话
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * get：targt_type为3时目标电话
	 */
	public String getMobile() {
		return mobile;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Date getPushTime() {
		return pushTime;
	}

	public void setPushTime(Date pushTime) {
		this.pushTime = pushTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
