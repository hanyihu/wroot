package com.vic.ck.entity;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 订单状态记录表
 * 
 * @author VIC
 */
public class OrderStatusRecord implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 订单号
	 */
	private String orderno;
	/**
	 * 订单状态
	 */
	private Integer status;
	/**
	 * 简单描述
	 */
	private String describe;
	/**
	 * 
	 */
	@JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime;
	
	
	public String getDate() {
		return DateFormatUtils.format(createTime, "yyyy-MM-dd");
	}
	
	public String getTime() {
		return DateFormatUtils.format(createTime, "HH:mm:ss");
	}
	
	

	public OrderStatusRecord() {
		super();
	}

	public OrderStatusRecord(String orderno, Integer status, String describe) {
		super();
		this.orderno = orderno;
		this.status = status;
		this.describe = describe;
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
	 * set：订单号
	 */
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	/**
	 * get：订单号
	 */
	public String getOrderno() {
		return orderno;
	}

	/**
	 * set：订单状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * get：订单状态
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * set：简单描述
	 */
	public void setDescribe(String describe) {
		this.describe = describe;
	}

	/**
	 * get：简单描述
	 */
	public String getDescribe() {
		return describe;
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
