package com.vic.ck.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vic.ck.util.CommonUtils;

/**
 * 用户团购券表
 * 
 * @author VIC
 */
public class CustomerGroupTicket implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 金额
	 */
	private BigDecimal money;
	/**
	 * 团购名称
	 */
	private String name;
	/**
	 * 券码
	 */
	private String ticketNo;
	/**
	 * 所属订单
	 */
	private String orderno;
	/**
	 * 所属商家
	 */
	private Integer merchantId;
	/**
	 * 生成时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/**
	 * 生效时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	/**
	 * 失效时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	/**
	 * 使用时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date usedTime;
	/**
	 * 0-未使用 1-已使用 2-已过期 3-已删除
	 */
	private Integer status;
	/**
	 * 当前代金券的二维码
	 */
	private Integer qrcode;
	
	public Integer customerId;
	
	public String getQrcodeUrl() {
		return CommonUtils.getImageUrl(qrcode);
	}
	
	
	
	public CustomerGroupTicket() {
		super();
	}



	public CustomerGroupTicket(BigDecimal money, String name, String ticketNo, String orderno, Integer merchantId,
			Date startTime, Date endTime, Integer status, Integer customerId) {
		super();
		this.money = money;
		this.name = name;
		this.ticketNo = ticketNo;
		this.orderno = orderno;
		this.merchantId = merchantId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
		this.customerId = customerId;
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
	 * set：金额
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	/**
	 * get：金额
	 */
	public BigDecimal getMoney() {
		return money;
	}

	/**
	 * set：团购名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * get：团购名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * set：券码
	 */
	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	/**
	 * get：券码
	 */
	public String getTicketNo() {
		return ticketNo;
	}

	/**
	 * set：所属订单
	 */
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	/**
	 * get：所属订单
	 */
	public String getOrderno() {
		return orderno;
	}

	/**
	 * set：所属商家
	 */
	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	/**
	 * get：所属商家
	 */
	public Integer getMerchantId() {
		return merchantId;
	}

	/**
	 * set：生成时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * get：生成时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * set：生效时间
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * get：生效时间
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * set：失效时间
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * get：失效时间
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * set：使用时间
	 */
	public void setUsedTime(Date usedTime) {
		this.usedTime = usedTime;
	}

	/**
	 * get：使用时间
	 */
	public Date getUsedTime() {
		return usedTime;
	}

	/**
	 * set：0-未使用 1-已使用 2-已过期 3-已删除
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * get：0-未使用 1-已使用 2-已过期 3-已删除
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * set：当前代金券的二维码
	 */
	public void setQrcode(Integer qrcode) {
		this.qrcode = qrcode;
	}

	/**
	 * get：当前代金券的二维码
	 */
	public Integer getQrcode() {
		return qrcode;
	}



	public Integer getCustomerId() {
		return customerId;
	}



	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	
}
