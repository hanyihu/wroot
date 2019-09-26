package com.vic.ck.entity;

import java.math.BigDecimal;

/**
 * 会员统计
 */
public class MemberStatistics {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 会员名称
     */
    private String membersName;

    /**
     * 订单数
     */
    private Integer orders;

    /**
     * 订单额
     */
    private BigDecimal orderAmount;

    /**
     * 退款额
     */
    private BigDecimal refundAmounts;

    /**
     * 订单均价
     */
    private BigDecimal orderPrice;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMembersName() {
        return membersName;
    }

    public void setMembersName(String membersName) {
        this.membersName = membersName;
    }

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getRefundAmounts() {
        return refundAmounts;
    }

    public void setRefundAmounts(BigDecimal refundAmounts) {
        this.refundAmounts = refundAmounts;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }
}
