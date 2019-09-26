package com.vic.ck.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/*结算统计*/

/**
 * @author hanyihu
 * @date 2019/4/20 13:37
 */
public class Settlement implements Serializable {
    private static final long serialVersionUID = 1L;

  /*订单号*/
    private String orderno;

    /*商家名称*/
    private String merchantName;

    /*订单金额*/
    private BigDecimal amount ;

    /*订单实付金额*/
    private BigDecimal payAmount;

    /*结算金额*/
    private BigDecimal settlementAmount;

    /*结算详情*/
    private String  settlementDetail;

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getSettlementAmount() {
        return settlementAmount;
    }

    public void setSettlementAmount(BigDecimal settlementAmount) {
        this.settlementAmount = settlementAmount;
    }

    public String getSettlementDetail() {
        return settlementDetail;
    }

    public void setSettlementDetail(String settlementDetail) {
        this.settlementDetail = settlementDetail;
    }
}
