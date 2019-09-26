package com.vic.ck.api.merchantapi.vo;


import java.io.Serializable;

/**
 * 商家端 订单统计表
 *
 * @author hanyihu
 * @date 2019/4/20 13:37
 */
public class OrderStatisticsVo implements Serializable {
    private static final long serialVersionUID = 1L;



    /* *//*接单数*//*
   @ApiModelProperty(value = "接单数")
   private Integer orderNum;

   *//*完成订单数*//*
   @ApiModelProperty(value = "完成订单数")
   private Integer completeOrder;

   *//*取消订单数*//*
   @ApiModelProperty(value = "取消订单数")
   private  Integer cancelOrders;

   *//*收益*//*
   @ApiModelProperty(value = "收益")
   private BigDecimal profit;

    *//*活动奖励*//*
    @ApiModelProperty(value = "活动奖励")
    private BigDecimal activityAward;

    *//*其他收入*//*
    @ApiModelProperty(value = "其他收入")
    private BigDecimal otherIncome;

    *//*总收入*//*
    @ApiModelProperty(value = "总收入")
    private  BigDecimal totalIncome;*/

    /*id*/
    private Integer id;

    /**/


}
