package com.vic.ck.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 订单详情，包含的商品
 */
@ApiModel(description= "订单详情，包含的商品")
public class OrderDetails {
    private static final long serialVersionUID = 1L;
    private Integer id;
    /**
     * 订单商品名称
     */
    @ApiModelProperty(value = "订单商品名称")
    private String name;
    /**
     * 订单商品价格
     */
    @ApiModelProperty(value = "订单商品价格")
    private BigDecimal price;
    /**
     * 订单商品数量
     */
    @ApiModelProperty(value = "订单商品数量")
    private Integer number;

    /**
     * 配送费
     */
    @ApiModelProperty(value = "配送费")
    @JsonIgnore
    private Double shippingFee;

    /**
     * 单个商品总价
     */
    @ApiModelProperty(value = "单个商品总价")
    @JsonIgnore
    private Double singlePrice;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(Double shippingFee) {
        this.shippingFee = shippingFee;
    }

    public Double getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(Double singlePrice) {
        this.singlePrice = singlePrice;
    }

}
