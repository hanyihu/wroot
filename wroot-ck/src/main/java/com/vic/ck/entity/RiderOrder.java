package com.vic.ck.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author xbw
 * @time 2019/4/17 16:30
 * 骑手所有订单
 */
public class RiderOrder {

    private Integer id;
    /**
     * 订单号
     */
    private Integer orderno;
    /**
     * 骑手名称
     */
    private String name;
    /**
     * 骑手电话
     */
    private String phone;
    /**
     * 订单状态 1 已下单 2 骑手已接单 3 骑手配送中 4 已完成
     */
    private Integer status;
    /**
     * 订单收货地址
     */
    private String shippingAddress;
    /**
     * 订单商家名称
     */
    private String merchantName;
    /**
     * 下单时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date time;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderno() {
        return orderno;
    }

    public void setOrderno(Integer orderno) {
        this.orderno = orderno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
