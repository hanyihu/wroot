package com.vic.ck.vo;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vic.ck.api.constant.FinalFiledParams;
import com.vic.ck.util.CommonUtils;
import com.vic.wroot.common.util.DateUtils;

/**
 * 订单详情简单对象
 * @author VIC
 *
 */
public class OrderDetailVo {

	private Integer id;
	/**
	 * 订单号
	 */
	private String orderno;
	/**
	 * 所属用户
	 */
	private Integer customerId;
	
	@JsonIgnore
	private String customerName;
	/**
	 * 所属商家
	 */
	private Integer merchantId;
	
	private String merchantName;
	
	@JsonIgnore
	private String cityName;
	/**
	 * 商品id ：团购id 或酒店房间id
	 */
	private Integer commodityId;
	/**
	 * 订单类型 1-直接购买 2-团购 3-酒店预订
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
	@JsonFormat(pattern = "MM-dd", timezone = "GMT+8")
	private Date checkEndDate;
	
	private String mobile;
	
	
	/**团购券号*/
	private String groupbutTicketNo;
	
	@JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
	public Date checkLastTime;
	
	/**酒店详情*/
	private HotelDetailVo hotelDetail;
	
	private Integer refundReason; 
	
	//第三方支付单号
	@JsonIgnore
	private String payOutNo;
	
	//支付时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date payTime;
	
	private boolean isDelete;
	
	/**
	 *优惠信息
	 */
	private String discountsDesc;
	
	//商家地址
	private String merchantAddress;
	
	/**
	 * 最后到店时间描述 yyyy-MM-dd HH:mm
	 */
	public String getCheckLastTimeDesc(){
		StringBuilder result = new StringBuilder();
		try{
			if(checkStartDate != null) {
				result.append(DateFormatUtils.format(checkStartDate, "yyyy-MM-dd"));
			}
			if(checkLastTime != null) {
				result.append(" ").append(DateFormatUtils.format(checkLastTime, "HH:mm"));
			}
		}catch(Exception e){}
		
		return result.toString();
	}
	
	
	
	/**
	 *  退款理由
	 */
	public String getRefundReasonDesc() {
		return FinalFiledParams.REFUND_REASON_MAP.get(refundReason);
	}
	
	
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
		return FinalFiledParams.ORDER_STATUS_MAP.get(status);
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getGroupbutTicketNo() {
		return groupbutTicketNo;
	}

	public void setGroupbutTicketNo(String groupbutTicketNo) {
		this.groupbutTicketNo = groupbutTicketNo;
	}

	public Date getCheckLastTime() {
		return checkLastTime;
	}

	public void setCheckLastTime(Date checkLastTime) {
		this.checkLastTime = checkLastTime;
	}

	public HotelDetailVo getHotelDetail() {
		return hotelDetail;
	}

	public void setHotelDetail(HotelDetailVo hotelDetail) {
		this.hotelDetail = hotelDetail;
	}

	public Integer getRefundReason() {
		return refundReason;
	}

	public void setRefundReason(Integer refundReason) {
		this.refundReason = refundReason;
	}

	public String getPayOutNo() {
		return payOutNo;
	}

	public void setPayOutNo(String payOutNo) {
		this.payOutNo = payOutNo;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getCityName() {
		return cityName;
	}


	public void setCityName(String cityName) {
		this.cityName = cityName;
	}


	public Date getPayTime() {
		return payTime;
	}


	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}


	public boolean isDelete() {
		return isDelete;
	}


	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}


	public String getDiscountsDesc() {
		return discountsDesc;
	}


	public void setDiscountsDesc(String discountsDesc) {
		this.discountsDesc = discountsDesc;
	}



	public String getMerchantAddress() {
		return merchantAddress;
	}



	public void setMerchantAddress(String merchantAddress) {
		this.merchantAddress = merchantAddress;
	}
	
	
	
	
	
	
	
}
