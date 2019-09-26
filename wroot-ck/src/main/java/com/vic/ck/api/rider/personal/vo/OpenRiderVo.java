package com.vic.ck.api.rider.personal.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 开通骑手提交申请表
 *
 * @author hanyihu
 * @date 2019/4/20 14:48
 */
public class OpenRiderVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /*id*/
    @ApiModelProperty(value = "骑手id")
    private int id;

    /*工作城市*/
    @ApiModelProperty(value = "工作城市")
    private String sendcity;

    /*工作区域详细地址*/
    @ApiModelProperty(value = "工作区域详细地址")
    private String sendarea;

    /*联系电话*/
    @ApiModelProperty(value = "联系电话（可更改）")
    private String ttlAccounts;

    /*短信验证码*/
    @ApiModelProperty(value = "短信验证码")
    private String smsCode;

    public String getSendcity() {
        return sendcity;
    }

    public void setSendcity(String sendcity) {
        this.sendcity = sendcity;
    }

    public String getSendarea() {
        return sendarea;
    }

    public void setSendarea(String sendarea) {
        this.sendarea = sendarea;
    }

    public String getTtlAccounts() {
        return ttlAccounts;
    }

    public void setTtlAccounts(String ttlAccounts) {
        this.ttlAccounts = ttlAccounts;
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
}
