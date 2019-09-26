package com.vic.ck.console.sysmanagement.lookup;

import com.vic.base.pager.CommonLookup;

/**
 * 封装留言管理查询条件
 */
public class MerchantMsgLookup extends CommonLookup {
    /**
     * 商家名称
     */
    private String merchant_name;
    /**
     * 处理状态
     */
    private Integer status;

    public String getMerchant_name() {
        return merchant_name;
    }

    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name;
    }

    @Override
    public Integer getStatus() {
        return status;
    }

    @Override
    public void setStatus(Integer status) {
        this.status = status;
    }
}
