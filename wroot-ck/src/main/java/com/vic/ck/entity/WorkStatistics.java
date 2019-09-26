package com.vic.ck.entity;


import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/*
* 骑手端个人中心工作统计类
* */
/**
 * @author hanyihu
 * @date 2019/4/20 13:37
 */
public class WorkStatistics implements Serializable {
    private static final long serialVersionUID = 1L;
   /*接单数*/
   @ApiModelProperty(value = "接单数")
   private Integer orderNum;

   /*完成订单数*/
   @ApiModelProperty(value = "完成订单数")
   private Integer completeOrder;

   /*取消订单数*/
   @ApiModelProperty(value = "取消订单数")
   private  Integer cancelOrders;

   /*收益*/
   @ApiModelProperty(value = "收益")
   private BigDecimal profit;

    /*活动奖励*/
    @ApiModelProperty(value = "活动奖励")
    private BigDecimal activityAward;

    /*其他收入*/
    @ApiModelProperty(value = "其他收入")
    private BigDecimal otherIncome;

    /*总收入*/
    @ApiModelProperty(value = "总收入")
    private  BigDecimal totalIncome;




    public BigDecimal getActivityAward() {
        return activityAward;
    }

    public void setActivityAward(BigDecimal activityAward) {
        this.activityAward = activityAward;
    }

    public BigDecimal getOtherIncome() {
        return otherIncome;
    }

    public void setOtherIncome(BigDecimal otherIncome) {
        this.otherIncome = otherIncome;
    }

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(Integer completeOrder) {
        this.completeOrder = completeOrder;
    }

    public Integer getCancelOrders() {
        return cancelOrders;
    }

    public void setCancelOrders(Integer cancelOrders) {
        this.cancelOrders = cancelOrders;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }
}
