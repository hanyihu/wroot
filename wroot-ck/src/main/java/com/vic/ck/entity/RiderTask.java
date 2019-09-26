package com.vic.ck.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/*骑手任务表*/
/**
 * @author hanyihu
 * @date 2019/4/20 13:37
 */
public class RiderTask implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;

    /*骑手名*/
    @ApiModelProperty(value = "骑手名")
    private String ridername;

    /*商家名称*/
    @ApiModelProperty(value = "商家名称")
    private String merchantname;

    /*单号*/
    @ApiModelProperty(value = "单号")
    private String orderno;

    /*送达时间*/
    @ApiModelProperty(value = "送达时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date serviceTime;

    /*送货地址*/
    @ApiModelProperty(value = "送货地址")
    private String sendaddress;

    /*取货地址*/
    @ApiModelProperty(value = "取货地址")
    private String pickaddress;

    /*费用*/
    @ApiModelProperty(value = "费用")
    private BigDecimal fee;

    /*接单状态  0已取消   1待取货 2 配送中 3已完成*/
    @ApiModelProperty(value = "接单状态  0已取消   1待取货 2 配送中 3已完成 4未接单")
    private int orderStatus;

    /*备注信息*/
    @ApiModelProperty(value = "备注信息")
    private String remarks;

    /*骑手id*/
    @ApiModelProperty(value = "骑手id")
    private int riderId;

    /*商家id*/
    @ApiModelProperty(value = "商家id")
    private int merchantId;

    /*图片*/
    @ApiModelProperty(value = "图片")
    private String inPic;

    /*创建时间*/
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date creationTime;

    /*订单集合信息*/
    @ApiModelProperty(value = "订单集合信息")
    private List<Order> orderList;

    /*附近骑手数量*/
    private Integer riderNum;

    /*骑手是否空闲  0是  1否*/
    private Integer isfree;

    /*骑手名单状态 0普通状态    1黑名单  2白名单*/
    private Integer listStatus;

    public Integer getRiderNum() {
        return riderNum;
    }

    public void setRiderNum(Integer riderNum) {
        this.riderNum = riderNum;
    }

    public Integer getIsfree() {
        return isfree;
    }

    public void setIsfree(Integer isfree) {
        this.isfree = isfree;
    }

    public Integer getListStatus() {
        return listStatus;
    }

    public void setListStatus(Integer listStatus) {
        this.listStatus = listStatus;
    }


    public String getInPic() {
        return inPic;
    }

    public void setInPic(String inPic) {
        this.inPic = inPic;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRidername() {
        return ridername;
    }

    public void setRidername(String ridername) {
        this.ridername = ridername;
    }

    public String getMerchantname() {
        return merchantname;
    }

    public void setMerchantname(String merchantname) {
        this.merchantname = merchantname;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public Date getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(Date serviceTime) {
        this.serviceTime = serviceTime;
    }

    public String getSendaddress() {
        return sendaddress;
    }

    public void setSendaddress(String sendaddress) {
        this.sendaddress = sendaddress;
    }

    public String getPickaddress() {
        return pickaddress;
    }

    public void setPickaddress(String pickaddress) {
        this.pickaddress = pickaddress;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getRiderId() {
        return riderId;
    }

    public void setRiderId(int riderId) {
        this.riderId = riderId;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }


}
