package com.vic.ck.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 开通骑手信息临时存放表
 *
 * @author ：hanyihu
 * @date ：2019/5/11 9:31
 */
public class RiderExamine implements Serializable {

    private static final long serialVersionUID = 1L;

    /*id*/
    private int id;

    /*骑手id*/
    @ApiModelProperty(value = "骑手id")
    private int riderId;

    /*配送城市*/
    @ApiModelProperty(value = "配送城市")
    private String sendcity;

    /*配送详细地址*/
    @ApiModelProperty(value = "配送详细地址")
    private String address;

    /*手机号码 （可更改）*/
    @ApiModelProperty(value = "手机号码 （可更改）")
    private String phone;

    /*健康证*/
    @ApiModelProperty(value = "健康证")
    private String healthCard;

    /*健康证号*/
    @ApiModelProperty(value = "健康证号")
    private String healthCardNo;

    /*健康证号有效期*/
    @ApiModelProperty(value = "健康证号有效期")
    private String healthValidity;

    /*身份证*/
    @ApiModelProperty(value = "身份证")
    private String idCard;

    /*审核状态 0未审核  1审核中  2审核成功  3审核失败*/
    @ApiModelProperty(value = "审核状态 0未审核  1审核中  2审核成功  3审核失败")
    private Integer status;

    /*审核失败说明*/
    @ApiModelProperty(value = "审核失败说明")
    private String examineRemark;

    /*创建时间*/
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /*短信验证码*/
    @ApiModelProperty(value = "短信验证码")
    private String smsCode;

    /*骑手昵称*/
    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRiderId() {
        return riderId;
    }

    public void setRiderId(int riderId) {
        this.riderId = riderId;
    }

    public String getSendcity() {
        return sendcity;
    }

    public void setSendcity(String sendcity) {
        this.sendcity = sendcity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHealthCard() {
        return healthCard;
    }

    public void setHealthCard(String healthCard) {
        this.healthCard = healthCard;
    }

    public String getHealthCardNo() {
        return healthCardNo;
    }

    public void setHealthCardNo(String healthCardNo) {
        this.healthCardNo = healthCardNo;
    }

    public String getHealthValidity() {
        return healthValidity;
    }

    public void setHealthValidity(String healthValidity) {
        this.healthValidity = healthValidity;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getExamineRemark() {
        return examineRemark;
    }

    public void setExamineRemark(String examineRemark) {
        this.examineRemark = examineRemark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
