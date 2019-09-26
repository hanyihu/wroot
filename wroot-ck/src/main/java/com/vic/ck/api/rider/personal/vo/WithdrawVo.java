package com.vic.ck.api.rider.personal.vo;

import com.vic.ck.entity.CustomerBankCard;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 骑手端 提现表
 *
 * @author hanyihu
 * @date 2019/4/22 16:59
 */
public class WithdrawVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /*银行卡信息集合*/
    @ApiModelProperty(value = "银行卡信息集合")
    private List<CustomerBankCard> bankCardList;

    /*提现金额*/
   @ApiModelProperty(value = "提现金额")
    private BigDecimal withdrawMoney;

    /*可提现金额*/
    @ApiModelProperty(value = "可提现金额")
    private BigDecimal canWithdrawMoney;

    public List<CustomerBankCard> getBankCardList() {
        return bankCardList;
    }

    public void setBankCardList(List<CustomerBankCard> bankCardList) {
        this.bankCardList = bankCardList;
    }

    public BigDecimal getWithdrawMoney() {
        return withdrawMoney;
    }

    public void setWithdrawMoney(BigDecimal withdrawMoney) {
        this.withdrawMoney = withdrawMoney;
    }

    public BigDecimal getCanWithdrawMoney() {
        return canWithdrawMoney;
    }

    public void setCanWithdrawMoney(BigDecimal canWithdrawMoney) {
        this.canWithdrawMoney = canWithdrawMoney;
    }
}
