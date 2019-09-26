package com.vic.ck.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 推送消息表
 * 
 * @author VIC
 */
public class JpushMsg implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 谁的消息
	 */
	private Integer customerId;
	/**
	 * 1-用户 2-商家
	 */
	private Integer role;
	/**
	 * 消息内容
	 */
	private String content;
	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime;
	/**
	 * 是否阅读
	 */
	private Integer readed;
	/**
	 * 1-非系统消息 2-系统信息
	 */
	private Integer type;
	/**
	 * 是否删除
	 */
	private Integer isDelete;
	/**
	 * 对应的系统信息的id
	 */
	private Integer msgId;
	
	/**
	 * 用于批量插入的时候的 城市的id 
	 */
	@JsonIgnore
	private int cityId; 
	
	
	
	/**
	 * role 1-用户 2-商家
	 * type 1-非系统消息 2-系统信息
	 */
	public JpushMsg(Integer customerId, Integer role, String content, Integer type) {
		super();
		this.customerId = customerId;
		this.role = role;
		this.content = content;
		this.type = type;
	}

	public JpushMsg() {
		super();
	}

	/**
	 * set：
	 */
	public JpushMsg setId(Integer id) {
		this.id = id;
		return this;
	}

	/**
	 * get：
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * set：谁的消息
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	/**
	 * get：谁的消息
	 */
	public Integer getCustomerId() {
		return customerId;
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
	 * set：消息内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * get：消息内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * set：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * get：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * set：是否阅读
	 */
	public JpushMsg setReaded(Integer readed) {
		this.readed = readed;
		return this;
	}

	/**
	 * get：是否阅读
	 */
	public Integer getReaded() {
		return readed;
	}

	/**
	 * set：1-非系统消息 2-系统信息
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * get：1-非系统消息 2-系统信息
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * set：是否删除
	 */
	public JpushMsg setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
		return this;
	}

	/**
	 * get：是否删除
	 */
	public Integer getIsDelete() {
		return isDelete;
	}

	/**
	 * set：对应的系统信息的id
	 */
	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}

	/**
	 * get：对应的系统信息的id
	 */
	public Integer getMsgId() {
		return msgId;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	
	
}
