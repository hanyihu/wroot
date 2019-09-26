package com.vic.ck.entity;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.vic.ck.api.constant.FinalFiledParams;
import com.vic.ck.util.CommonUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 我的兑换-一般为积分兑换
 * 
 * @author VIC
 */
public class CustomerSwap implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 兑换号
	 */
	private String swapno;
	/**
	 * 用户
	 */
	private Integer customerId;
	
	//用户名
	private String customerName;
	
	//电话
	private String mobile;
	/**
	 * 兑换的商品id
	 */
	private Integer swapMallId;
	
	//商品名称
	private String swapMallName;
	/**
	 * 兑换的数量
	 */
	private Integer quantity;
	/**
	 * 兑换的单个商品积分
	 */
	private Integer unitScore;
	/**
	 * 所使用的总积分
	 */
	private Integer score;
	/**
	 * 收获地址id
	 */
	private Integer addressId;
	/**
	 * 0-未发货 1-配送中 2-已完成
	 */
	private Integer status;
	
	/**
	 * 备注
	 */
	private String remark;
	
	
	/**
	 * 
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm" ,timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime;
	
	public CustomerDeliveryAddress address;
	/**
	 * 商家名称
	 */
	private String merchantName;
	/**
	 * 商品图片
	 */
	private Integer icon;
	
	
	public String getIconUrl() {
		return CommonUtils.getImageUrl(icon);
	}
	
	public String getStatusDesc() {
		return FinalFiledParams.SWAP_STATUS_MAP.get(status);
	}
	
	
	
	

	public CustomerSwap(Integer customerId, Integer swapMallId, Integer quantity, Integer unitScore, Integer score,
			Integer addressId, Integer status, String merchantName, Integer icon, String remark, String customerName, String mobile, String swapMallName) {
		super();
		this.customerId = customerId;
		this.swapMallId = swapMallId;
		this.quantity = quantity;
		this.unitScore = unitScore;
		this.score = score;
		this.addressId = addressId;
		this.status = status;
		this.merchantName = merchantName;
		this.icon = icon;
		this.remark = remark;
		this.customerName = customerName;
		this.mobile = mobile;
		this.swapMallName = swapMallName;
	}

	public CustomerSwap() {
		super();
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
	 * set：兑换号
	 */
	public void setSwapno(String swapno) {
		this.swapno = swapno;
	}

	/**
	 * get：兑换号
	 */
	public String getSwapno() {
		return swapno;
	}

	/**
	 * set：用户
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	/**
	 * get：用户
	 */
	public Integer getCustomerId() {
		return customerId;
	}

	/**
	 * set：兑换的商品id
	 */
	public void setSwapMallId(Integer swapMallId) {
		this.swapMallId = swapMallId;
	}

	/**
	 * get：兑换的商品id
	 */
	public Integer getSwapMallId() {
		return swapMallId;
	}

	/**
	 * set：兑换的数量
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * get：兑换的数量
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * set：兑换的单个商品积分
	 */
	public void setUnitScore(Integer unitScore) {
		this.unitScore = unitScore;
	}

	/**
	 * get：兑换的单个商品积分
	 */
	public Integer getUnitScore() {
		return unitScore;
	}

	/**
	 * set：所使用的总积分
	 */
	public void setScore(Integer score) {
		this.score = score;
	}

	/**
	 * get：所使用的总积分
	 */
	public Integer getScore() {
		return score;
	}

	/**
	 * set：收获地址id
	 */
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	/**
	 * get：收获地址id
	 */
	public Integer getAddressId() {
		return addressId;
	}

	/**
	 * set：0-未发货 1-配送中 2-已完成
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * get：0-未发货 1-配送中 2-已完成
	 */
	public Integer getStatus() {
		return status;
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
	 * set：商家名称
	 */
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	/**
	 * get：商家名称
	 */
	public String getMerchantName() {
		return merchantName;
	}

	/**
	 * set：商品图片
	 */
	public void setIcon(Integer icon) {
		this.icon = icon;
	}

	/**
	 * get：商品图片
	 */
	public Integer getIcon() {
		return icon;
	}

	public CustomerDeliveryAddress getAddress() {
		return address;
	}

	public void setAddress(CustomerDeliveryAddress address) {
		this.address = address;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSwapMallName() {
		return swapMallName;
	}

	public void setSwapMallName(String swapMallName) {
		this.swapMallName = swapMallName;
	}
	
	
	
	
}
