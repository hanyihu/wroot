package com.vic.ck.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vic.ck.api.constant.FinalFiledParams;
import com.vic.ck.util.CommonUtils;
import com.vic.wroot.common.util.DateUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单列表简单对象
 * @author VIC
 *
 */
public class OrderVo {

	private Integer id;
	/**
	 * 订单号
	 */
	private String orderno;
	/**
	 * 所属用户
	 */
	private Integer customerId;
	/**
	 * 所属商家
	 */
	private Integer merchantId;
	
	private String merchantName;
	/**
	 * 商品id ：团购id 或酒店房间id
	 */
	private Integer commodityId;
	/**
	 * 订单类型 1-外卖 2-自提 3-其他
	 */
	private Integer orderType;

	/**
	 * 订单总金额
	 */
	private BigDecimal totalFee;
	/**
	 * 订单实际支付金额
	 */
	private BigDecimal amount;
	
	/**
	 * 购买数量
	 */
	private Integer quantity;
	/**
	 * 订单筛选  0-下单  1-商家接单 2-骑手接单 3- 配送 4-签收 (已完成) 5-取消 6-退款 7-退货 8-已结算  9-未结算
	 * 订单状态  0-待支付 1-待销费 2-待确认 3- 待入住 4-待评价 (已完成) 5-已评价 6-退款中 7-已退款 8-已取消
	 * 直接购买：0-待支付 3-待评价 (已完成) 4-已评价 
	 * 团购：	 0-待支付 1-待销费 4-待评价 (已完成) 5-已评价 6-退款中 7-已退款 8-已取消 
	 * 预定：	 0-待支付 2-待确认 3- 待入住 4-待评价 (已完成) 5-已评价 6-退款中 7-已退款 8-已取消
	 * 
	 */
	private Integer status;
	
	/**
	 * 团购图片或商家图片
	 */
	private Integer orderImage;
	/**
	 * 团购名称或商家名称
	 */
	private String orderName;
	/**
	 * 商家手机号
	 */
	private String merchantMobile;
	/**
	 * 1-余额支付 2-支付宝支付 3-微信支付
	 */
	private Integer payType;
	/**
	 * 创建时间-购买时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;
	
	/**
	 * 类型 1-美食 2-酒店预订 3-其他
	 */
	private Integer merchantType;
	/**
	 * 酒店预订-房间数
	 */
	private Integer roomNumber;
	/**
	 * 酒店预订-入住人 多个逗号分隔
	 */
	private String checkPerson;
	/**
	 * 酒店预订-入住开始日期
	 */
	@JsonFormat(pattern = "MM-dd", timezone = "GMT+8")
	private Date checkStartDate;
	/**
	 * 酒店预订-入住结束日期
	 */
	@JsonFormat(pattern = "MM-dd",timezone = "GMT+8")
	private Date checkEndDate;
	
	@JsonIgnore
	private boolean isDelete;
	
	@JsonIgnore
	private String customerName;
	
	@JsonIgnore
	private String customerMobile;
	
	@JsonIgnore
	private String cityName;
	
	@JsonIgnore
	private String mobile;

	@JsonIgnore
	private String payOutNo;
	
	

	//图片
	public String getOrderImageUrl() {
		return CommonUtils.getImageUrl(orderImage);
	}
	
	//入住晚 数
	public int getdurationDay() {
		if(checkStartDate !=null && checkEndDate != null) {
			return DateUtils.daysBetween(checkStartDate, checkEndDate);
		}
		
		return 0;
	}
	
	//订单状态描述
	public String getStatusDesc() {
		return FinalFiledParams.ORDER_SCREENING_MAP.get(status);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public Integer getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public BigDecimal getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getOrderImage() {
		return orderImage;
	}
	public void setOrderImage(Integer orderImage) {
		this.orderImage = orderImage;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getMerchantMobile() {
		return merchantMobile;
	}

	public void setMerchantMobile(String merchantMobile) {
		this.merchantMobile = merchantMobile;
	}

	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getMerchantType() {
		return merchantType;
	}
	public void setMerchantType(Integer merchantType) {
		this.merchantType = merchantType;
	}
	public Integer getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}
	public String getCheckPerson() {
		return checkPerson;
	}
	public void setCheckPerson(String checkPerson) {
		this.checkPerson = checkPerson;
	}
	public Date getCheckStartDate() {
		return checkStartDate;
	}
	public void setCheckStartDate(Date checkStartDate) {
		this.checkStartDate = checkStartDate;
	}
	public Date getCheckEndDate() {
		return checkEndDate;
	}
	public void setCheckEndDate(Date checkEndDate) {
		this.checkEndDate = checkEndDate;
	}
	
	public boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPayOutNo() {
		return payOutNo;
	}

	public void setPayOutNo(String payOutNo) {
		this.payOutNo = payOutNo;
	}
	
	
	
	
	
}
