package com.vic.ck.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 骑手端接单中心
 */
@ApiModel(description= "骑手端接单中心")
public class GrabSingleCenter {
    private static final long serialVersionUID = 1L;


    /**
     * 总订单
     */
    @ApiModelProperty(value = "总订单")
    private Integer orders;
    /**
     * 订单信息
     */
    @ApiModelProperty(value = "订单信息")
    private List<RiderNewOrders> riderNewOrders;
    /**
     * 订单详情
     */
    @ApiModelProperty(value = "订单详情")
    private List<OrderDetails> orderDetails;

    /**
     * 配送费
     */
    @ApiModelProperty(value = "配送费")
    private Double shippingFee;
    /**
     * 餐盒费
     */
    @ApiModelProperty(value = "餐盒费")
    private Double mealsFee;

    /**
     * 订单总价
     */
    @ApiModelProperty(value = "订单总价")
    private Double totalPrice;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    public List<RiderNewOrders> getRiderNewOrders() {
        return riderNewOrders;
    }

    public void setRiderNewOrders(List<RiderNewOrders> riderNewOrders) {
        this.riderNewOrders = riderNewOrders;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Double getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(Double shippingFee) {
        this.shippingFee = shippingFee;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getMealsFee() {
        return mealsFee;
    }

    public void setMealsFee(Double mealsFee) {
        this.mealsFee = mealsFee;
    }
}
