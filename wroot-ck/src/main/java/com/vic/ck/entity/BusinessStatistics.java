package com.vic.ck.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商家统计
 */
public class BusinessStatistics {


    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Integer id;
    /**
     * 商家名称
     */
    private String merchantsName;
    /**
     * 统计的是那一天
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date atDate;
    /**
     * 订单数
     */
    private Integer orderNumber;
    /**
     * 交易额
     */
    private BigDecimal totalAmount;
    /**
     * 有效订单数
     */
    private Integer orderSuccessNumber;
    /**
     * 有效订单额
     */
    private Integer orderSuccessAmount;
    /**
     * 下单的总人数
     */
    private Integer totalNumber;
    /**
     * 人均单价
     */
    private Integer capitaPrice;
    /**
     * 商家排名
     */
    private Integer merchantsRanking;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMerchantsName() {
        return merchantsName;
    }

    public void setMerchantsName(String merchantsName) {
        this.merchantsName = merchantsName;
    }

    public Date getAtDate() {
        return atDate;
    }

    public void setAtDate(Date atDate) {
        this.atDate = atDate;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getOrderSuccessNumber() {
        return orderSuccessNumber;
    }

    public void setOrderSuccessNumber(Integer orderSuccessNumber) {
        this.orderSuccessNumber = orderSuccessNumber;
    }

    public Integer getOrderSuccessAmount() {
        return orderSuccessAmount;
    }

    public void setOrderSuccessAmount(Integer orderSuccessAmount) {
        this.orderSuccessAmount = orderSuccessAmount;
    }

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public Integer getCapitaPrice() {
        return capitaPrice;
    }

    public void setCapitaPrice(Integer capitaPrice) {
        this.capitaPrice = capitaPrice;
    }

    public Integer getMerchantsRanking() {
        return merchantsRanking;
    }

    public void setMerchantsRanking(Integer merchantsRanking) {
        this.merchantsRanking = merchantsRanking;
    }
}
