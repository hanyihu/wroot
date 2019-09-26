package com.vic.ck.api.rider.personal.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author hanyihu
 * @date 2019/4/17 13:14
 *
 * 骑手端个人中心 补充我的头像，余额，昵称
 */
public class SupplementVo  implements Serializable {
    private static final long serialVersionUID = 1L;

    /*头像*/
    @ApiModelProperty(value = "头像")
    private String headImage;

    /*余额*/
    @ApiModelProperty(value = "余额")
    private BigDecimal balance;

    /*昵称*/
    @ApiModelProperty(value = "昵称")
    private String nickname;

    /*手机号*/
    @ApiModelProperty(value = "手机号")
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
