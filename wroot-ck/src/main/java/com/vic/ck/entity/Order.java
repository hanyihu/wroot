package com.vic.ck.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单详情vo
 * 
 * @author VIC
 */
public class Order implements Serializable {

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
     * 所属用户
     */
    private Integer customerId;
    /**
     * 所属商家
     */
    private Integer merchantId;
    /**
     * 商品id ：团购id
     */
    private String commodityId;
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
     * 达到满减 减少的金额
     */
    private BigDecimal minusMoney;

    /**
     * 达到的满减金额
     */
    private BigDecimal reachMoney;

    /**
     * 优惠券id
     */
    private Integer ticketId;

    /**
     * 优惠券打的折扣
     */
    private BigDecimal ticketDiscount;

    /**
     * 优惠券打折优惠的金额
     */
    private BigDecimal ticketDiscountMoney;


    /**
     * 优惠简单说明
     */
    private String discountsDesc;
    /**
     * 购买数量
     */
    private Integer quantity;
    /**
     * 订单状态  0-待支付 1-待销费 2-待确认 3- 待入住 4-待评价 (已完成) 5-已评价 6-退款中 7-已退款 8-已取消
     * 直接购买：0-待支付 3-待评价 (已完成) 4-已评价
     * 团购：	 0-待支付 1-待销费 4-待评价 (已完成) 5-已评价 6-退款中 7-已退款 8-已取消
     * 预定：	 0-待支付 2-待确认 3- 待入住 4-待评价 (已完成) 5-已评价 6-退款中 7-已退款 8-已取消
     */
    private Integer status;


    /**
     * 是否评价
     */
    private Integer isComment;
    /**
     * 团购图片或商家图片
     */
    private Integer orderImage;
    /**
     * 团购名称或商家名称
     */
    private String orderName;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 1-余额支付 2-支付宝支付 3-微信支付
     */
    private Integer payType;
    /**
     * 支付成功时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date payTime;
    /**
     * 第三方支付单号
     */
    private String payOutNo;
    /**
     * 创建时间-购买时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /**
     * 退款理由 1.买错了， 2计划有变，没时间消费 3去过了，不满意 4预约不上 5商家停业维修转让 6商家营业，但不接待 6商家说可以直接以团购价到店里消费
     * 7网友评价不好 19-其他原因
     */
    private Integer refundReason;
    /**
     * 是否删除
     */
    private Integer isDelete;
    /**
     * 类型 1-美食 2-酒店预订 3-其他
     */
    private Integer merchantType;
    /**
     * 用户备注信息
     */
    private String remark;
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
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkStartDate;
    /**
     * 酒店预订-入住结束日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkEndDate;
    /**
     * 酒店预订-预计最晚到店时间
     */
    @JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
    @DateTimeFormat(pattern = "HH:mm")
    private Date checkLastTime;

    //送货地址
    private int addressId;

    //预计送达时间
    private Date planGuessTime;
    //口味
    private String taste;
    //人数
    private String peoplenum;

    public Order() {
        super();
    }

    /**
     * 用于计算支付金额
     */
    public Order(Integer merchantId, BigDecimal totalFee) {
        this.merchantId = merchantId;
        this.totalFee = totalFee;
        this.amount = totalFee;
    }

    public Order(Integer customerId, Integer merchantId, BigDecimal totalFee, Integer payType, Integer orderType, int quantity, String commodityId, String remark) {
        super();
        this.customerId = customerId;
        this.merchantId = merchantId;
        this.totalFee = totalFee;
        this.amount = totalFee;
        this.payType = payType;
        this.orderType = orderType;
        this.quantity = quantity;
        this.commodityId = commodityId;
        this.remark = remark;
    }


    public Order(Integer customerId, Integer merchantId, BigDecimal totalFee, Integer payType, Integer orderType) {
        super();
        this.customerId = customerId;
        this.merchantId = merchantId;
        this.totalFee = totalFee;
        this.amount = totalFee;
        this.payType = payType;
        this.orderType = orderType;
    }

    //满减
    public Order(Integer customerId, Integer merchantId, BigDecimal totalFee, String commodityId
            , int addressId, Date planGuessTime, String remark, String taste, String peoplenum
    ) {
        super();
        this.customerId = customerId;
        this.merchantId = merchantId;
        this.totalFee = totalFee;
        this.amount = totalFee;
        this.commodityId = commodityId;
        this.addressId = addressId;
        this.planGuessTime = planGuessTime;
        this.remark = remark;
        this.taste = taste;
        this.peoplenum = peoplenum;
    }

    //优惠券订单支付
    public Order(Integer customerId, Integer merchantId, BigDecimal totalFee, int ticketId, String commodityId,
                 int addressId, Date planGuessTime, String remark, String taste, String peoplenum) {
        super();
        this.customerId = customerId;
        this.merchantId = merchantId;
        this.totalFee = totalFee;
        this.amount = totalFee;
        this.ticketId = ticketId;
        this.addressId = addressId;
        this.commodityId = commodityId;
        this.remark = remark;
        this.taste = taste;
        this.peoplenum = peoplenum;
        this.planGuessTime = planGuessTime;

    }


    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public String getPeoplenum() {
        return peoplenum;
    }

    public void setPeoplenum(String peoplenum) {
        this.peoplenum = peoplenum;
    }

    /**
     * get：
     */
    public Integer getId() {
        return id;
    }

    /**
     * set：
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * get：订单号
     */
    public String getOrderno() {
        return orderno;
    }

    /**
     * set：订单号
     */
    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    /**
     * get：所属用户
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * set：所属用户
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * get：所属商家
     */
    public Integer getMerchantId() {
        return merchantId;
    }

    /**
     * set：所属商家
     */
    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * get：商品对应表 id
     */
    public String getCommodityId() {
        return commodityId;
    }

    /**
     * set：商品id ：团购id 或酒店房间id
     */
    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    /**
     * get：订单类型 1-直接购买 2-优惠券购买 3-满减购买
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * set：订单类型 1-直接购买 2-优惠券购买 3-满减购买
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * get：订单总金额
     */
    public BigDecimal getTotalFee() {
        return totalFee;
    }

    /**
     * set：订单总金额
     */
    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    /**
     * get：订单实际支付金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * set：订单实际支付金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * get：优惠简单说明
     */
    public String getDiscountsDesc() {
        return discountsDesc == null ? "" : discountsDesc;
    }

    /**
     * set：优惠简单说明
     */
    public void setDiscountsDesc(String discountsDesc) {
        this.discountsDesc = discountsDesc;
    }

    /**
     * get：购买数量
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * set：购买数量
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * get：订单状态 0-待支付 1-待销费 2-待确认 3- 待入住 4-待评价 (已完成) 5-已评价 6-退款中 7-已退款 8-已取消
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * set：订单状态 0-待支付 1-待销费 2-待确认 3- 待入住 4-待评价 (已完成) 5-已评价 6-退款中 7-已退款 8-已取消
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * get：是否评价
     */
    public Integer getIsComment() {
        return isComment;
    }

    /**
     * set：是否评价
     */
    public void setIsComment(Integer isComment) {
        this.isComment = isComment;
    }

    /**
     * get：团购图片或商家图片
     */
    public Integer getOrderImage() {
        return orderImage;
    }

    /**
     * set：团购图片或商家图片
     */
    public void setOrderImage(Integer orderImage) {
        this.orderImage = orderImage;
    }

    /**
     * get：团购名称或商家名称
     */
    public String getOrderName() {
        return orderName;
    }

    /**
     * set：团购名称或商家名称
     */
    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    /**
     * get：手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * set：手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * get：1-余额支付 2-支付宝支付 3-微信支付
     */
    public Integer getPayType() {
        return payType;
    }

    /**
     * set：1-余额支付 2-支付宝支付 3-微信支付
     */
    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    /**
     * get：支付成功时间
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * set：支付成功时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * get：第三方支付单号
     */
    public String getPayOutNo() {
        return payOutNo;
    }

    /**
     * set：第三方支付单号
     */
    public void setPayOutNo(String payOutNo) {
        this.payOutNo = payOutNo;
    }

    /**
     * get：创建时间-购买时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * set：创建时间-购买时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * get：优惠券id
     */
    public Integer getTicketId() {
        return ticketId;
    }

    /**
     * set：优惠券id
     */
    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    /**
     * get：退款理由 1.买错了， 2计划有变，没时间消费 3去过了，不满意 4预约不上 5商家停业维修转让 6商家营业，但不接待
     * 6商家说可以直接以团购价到店里消费 7网友评价不好 19-其他原因
     */
    public Integer getRefundReason() {
        return refundReason;
    }

    /**
     * set：退款理由 1.买错了， 2计划有变，没时间消费 3去过了，不满意 4预约不上 5商家停业维修转让 6商家营业，但不接待
     * 6商家说可以直接以团购价到店里消费 7网友评价不好 19-其他原因
     */
    public void setRefundReason(Integer refundReason) {
        this.refundReason = refundReason;
    }

    /**
     * get：是否删除
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * set：是否删除
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public Date getPlanGuessTime() {
        return planGuessTime;
    }

    public void setPlanGuessTime(Date planGuessTime) {
        this.planGuessTime = planGuessTime;
    }

    /**
     * get：用户备注信息
     */
    public String getRemark() {
        return remark;
    }

    /**
     * set：用户备注信息
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * get：酒店预订-房间数
     */
    public Integer getRoomNumber() {
        return roomNumber;
    }

    /**
     * set：酒店预订-房间数
     */
    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * get：酒店预订-入住人 多个逗号分隔
     */
    public String getCheckPerson() {
        return checkPerson;
    }

    /**
     * set：酒店预订-入住人 多个逗号分隔
     */
    public void setCheckPerson(String checkPerson) {
        this.checkPerson = checkPerson;
    }

    /**
     * get：酒店预订-入住开始日期
     */
    public Date getCheckStartDate() {
        return checkStartDate;
    }

    /**
     * set：酒店预订-入住开始日期
     */
    public void setCheckStartDate(Date checkStartDate) {
        this.checkStartDate = checkStartDate;
    }

    /**
     * get：酒店预订-入住结束日期
     */
    public Date getCheckEndDate() {
        return checkEndDate;
    }

    /**
     * set：酒店预订-入住结束日期
     */
    public void setCheckEndDate(Date checkEndDate) {
        this.checkEndDate = checkEndDate;
    }

    /**
     * get：酒店预订-预计最晚到店时间
     */
    public Date getCheckLastTime() {
        return checkLastTime;
    }

    /**
     * set：酒店预订-预计最晚到店时间
     */
    public void setCheckLastTime(Date checkLastTime) {
        this.checkLastTime = checkLastTime;
    }

    public BigDecimal getMinusMoney() {
        return minusMoney;
    }

    public void setMinusMoney(BigDecimal minusMoney) {
        this.minusMoney = minusMoney;
    }

    public BigDecimal getReachMoney() {
        return reachMoney;
    }

    public void setReachMoney(BigDecimal reachMoney) {
        this.reachMoney = reachMoney;
    }

    public BigDecimal getTicketDiscount() {
        return ticketDiscount;
    }

    public void setTicketDiscount(BigDecimal ticketDiscount) {
        this.ticketDiscount = ticketDiscount;
    }

    public BigDecimal getTicketDiscountMoney() {
        return ticketDiscountMoney;
    }

    public void setTicketDiscountMoney(BigDecimal ticketDiscountMoney) {
        this.ticketDiscountMoney = ticketDiscountMoney;
    }

    public Integer getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(Integer merchantType) {
        this.merchantType = merchantType;
    }
	
	
}
