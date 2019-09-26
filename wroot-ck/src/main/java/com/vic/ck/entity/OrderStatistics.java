package com.vic.ck.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/*
* 商品订单统计表
* */

/**
 * @author hanyihu
 * @date 2019/4/20 13:37
 */
public class OrderStatistics implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 创建时间-购买时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm" ,timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    /*订单状态*/
    private Integer status;



    /*订单数*/
    private String  orderNumber;

    /*订单额*/
    private BigDecimal orderAmount;

    /*有效订单数*/
    private String validOrderNum;

    /*有效订单额*/
    private BigDecimal validOrderAmount;

    /*下单会员数*/
    private  Integer orderMember;

    /*配送支出*/
    private  BigDecimal deliveryMoney;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getValidOrderNum() {
        return validOrderNum;
    }

    public void setValidOrderNum(String validOrderNum) {
        this.validOrderNum = validOrderNum;
    }

    public BigDecimal getValidOrderAmount() {
        return validOrderAmount;
    }

    public void setValidOrderAmount(BigDecimal validOrderAmount) {
        this.validOrderAmount = validOrderAmount;
    }

    public Integer getOrderMember() {
        return orderMember;
    }

    public void setOrderMember(Integer orderMember) {
        this.orderMember = orderMember;
    }

    public BigDecimal getDeliveryMoney() {
        return deliveryMoney;
    }

    public void setDeliveryMoney(BigDecimal deliveryMoney) {
        this.deliveryMoney = deliveryMoney;
    }




}
