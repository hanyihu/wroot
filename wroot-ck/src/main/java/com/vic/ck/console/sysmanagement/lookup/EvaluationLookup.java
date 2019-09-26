package com.vic.ck.console.sysmanagement.lookup;

import com.vic.base.pager.CommonLookup;

/**
 * 封装商品评价查询条件
 */
public class EvaluationLookup extends CommonLookup {
    /**
     * 商品名称
     */
    private String commodity_name;
    /**
     * 处理状态
     */
    private Integer status;

    public String getCommodity_name() {
        return commodity_name;
    }

    public void setCommodity_name(String commodity_name) {
        this.commodity_name = commodity_name;
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
