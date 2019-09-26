package com.vic.ck.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * 骑手新订单
 */
@ApiModel(description= "骑手新订单")
public class RiderNewOrders {
    private static final long serialVersionUID = 1L;

    private int id;

    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号")
    private String orderno;

    /**
     * 收货人姓名
     */
    @ApiModelProperty(value = "收货人姓名")
    private String consigneeName;
    /**
     * 收货人地址
     */
    @ApiModelProperty(value = "收货人地址")
    private String consigneeAddress;
    /**
     * 收货人电话
     */
    @ApiModelProperty(value = "收货人电话")
    private String consigneePhone;
    /**
     * 店铺名称
     */
    @ApiModelProperty(value = "店铺名称")
    private String merchantName;
    /**
     * 店铺地址
     */
    @ApiModelProperty(value = "店铺地址")
    private String merchantAddress;
    /**
     * 店铺电话
     */
    @ApiModelProperty(value = "店铺电话")
    private String merchantTel;

    /**
     * 商家经度
     */
    @ApiModelProperty(value = "商家经度")
    private double mlongitude;
    /**
     * 商家维度
     */
    @ApiModelProperty(value = "商家维度")
    private double mlatitude;
    /**
     * 收货地址经度
     */
    @ApiModelProperty(value = "收货地址经度")
    private double dlongitude;
    /**
     * 收货地址维度
     */
    @ApiModelProperty(value = "收货地址维度")
    private double dlatitude;
    /**
     * 取货距离
     */
    @ApiModelProperty(value = "取货距离")
    private String merchantBistance;
    /**
     * 送货距离
     */
    @ApiModelProperty(value = "送货距离")
    private String bistance;
    /**
     * 预计送达时间
     */
    @ApiModelProperty(value = "预计送达时间")
    private String time;


    /**
     * 送达时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:MM:SS", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:MM:SS")
    private Date deliveryTime;
    /**
     * 本单收入
     */
    @ApiModelProperty(value = "本单收入")
    private String income;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    public String getConsigneePhone() {
        return consigneePhone;
    }

    public void setConsigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantAddress() {
        return merchantAddress;
    }

    public void setMerchantAddress(String merchantAddress) {
        this.merchantAddress = merchantAddress;
    }

    public String getMerchantTel() {
        return merchantTel;
    }

    public void setMerchantTel(String merchantTel) {
        this.merchantTel = merchantTel;
    }

    public double getMlongitude() {
        return mlongitude;
    }

    public void setMlongitude(double mlongitude) {
        this.mlongitude = mlongitude;
    }

    public double getMlatitude() {
        return mlatitude;
    }

    public void setMlatitude(double mlatitude) {
        this.mlatitude = mlatitude;
    }

    public double getDlongitude() {
        return dlongitude;
    }

    public void setDlongitude(double dlongitude) {
        this.dlongitude = dlongitude;
    }

    public double getDlatitude() {
        return dlatitude;
    }

    public void setDlatitude(double dlatitude) {
        this.dlatitude = dlatitude;
    }

    public String getMerchantBistance() {
        return merchantBistance;
    }

    public void setMerchantBistance(String merchantBistance) {
        this.merchantBistance = merchantBistance;
    }

    public String getBistance() {
        return bistance;
    }

    public void setBistance(String bistance) {
        this.bistance = bistance;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}
