package com.vic.ck.api.merchant.controller;

import com.vic.base.BaseApiController;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.constant.FinalFiledParams;
import com.vic.ck.api.constant.ResultMsgEnum;
import com.vic.ck.api.platform.lookup.OrderLookup;
import com.vic.ck.api.platform.service.OrderService;
import com.vic.ck.entity.Order;
import com.vic.ck.vo.OrderDetailVo;
import com.vic.ck.vo.OrderVo;
import com.vic.wroot.common.po.BaseResponse;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 商家订单相关
 *
 * @author VIC
 */

@Api(tags = "商家端订单")
@RestController
@RequestMapping("/api/merchant/order")
public class MerchantOrderController extends BaseApiController {

    /*
     * * 6 订单相关 ### ### 交易明细列表-非酒店 ### 交易明细列表-酒店 ### 确认订单-酒店 ### 已入住-酒店
     */

    @Resource
    private OrderService orderService;

    /**
     * 5.4-01订单列表
     * orderStatus 4-退款/售后（退款：6 7） 5- 收到订单(1) 6- 完成订单(2)
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    @ApiOperation(value = "订单列表", notes = "获取商家的订单列表", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PageInfo<OrderVo>> orders(OrderLookup lookup) {
        PageInfo<OrderVo> data = orderService.orders(lookup);
        return resultSuccess(data);
    }

    /**
     * 5.4-02 订单详情
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/{orderno}", method = RequestMethod.GET)
    @ApiOperation(value = "订单详情", notes = "获取选定订单的详情信息", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<OrderDetailVo> detail(@PathVariable @ApiParam(value = "订单号", required = true) String orderno) {
        OrderDetailVo data = orderService.orderDetail(orderno);
        return resultSuccess(data);
    }

    /**
     * 订单状态 预定：  0-待支付 1-待消费 2-待确认 3- 待入住 4-待评价 (已完成) 5-已评价 6-退款中 7-已退款 8-已取消
     * @See com.vic.ck.api.constant.FinalFiledParams.ORDER_STATUS_MAP
     */
    /**
     * 5.4-03确认订单
     */
    @RequestMapping(value = "/{orderno}/confirm", method = RequestMethod.GET)
    @ApiOperation(value = "确认订单", notes = "确认订单并修改订单状态", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 811, message = "不存在的订单"),
            @ApiResponse(code = 1005, message = "订单不属于该商家"),
            @ApiResponse(code = 1016, message = "当前订单状态不可确认")
    })
    public Object confirm(
            @PathVariable @ApiParam(value = "订单号", required = true) String orderno,
            @RequestParam @ApiParam(value = "商家ID", required = true) int merchantId) {
        Order order = orderService.findOrderByOrderno(orderno);
        if (order == null) {
            return resultError(ResultMsgEnum.NOTEXISTED_ORDER);
        }
        if (merchantId != order.getMerchantId()) {
            return resultError(ResultMsgEnum.ORDER_MERCHANT_NOT_MATCH);
        }

        //酒店订单 且为待确认
        if (order.getOrderType() != 3 || FinalFiledParams.ORDER_STATUS_NOCONFIRM != order.getStatus()) {
            return resultError(ResultMsgEnum.CANNOT_CONFIRML_ORDER);
        }
        orderService.alterOrderStatus(orderno, FinalFiledParams.ORDER_STATUS_NOSTAY);
        return resultSuccess();

    }

    /**
     * 5.4-04入住酒店
     */
    @RequestMapping(value = "/{orderno}/stay", method = RequestMethod.GET)
    @ApiOperation(value = "入住酒店", notes = "入住酒店并修改订单状态,还需要完成订单的一些结算", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 811, message = "不存在的订单"),
            @ApiResponse(code = 1005, message = "订单不属于该商家"),
            @ApiResponse(code = 1017, message = "当前订单状态不可入住")
    })
    @Transactional
    public Object stay(
            @PathVariable @ApiParam(value = "订单号", required = true) String orderno,
            @RequestParam @ApiParam(value = "商家ID", required = true) int merchantId) {
        Order order = orderService.findOrderByOrderno(orderno);
        if (order == null) {
            return resultError(ResultMsgEnum.NOTEXISTED_ORDER);
        }
        if (merchantId != order.getMerchantId()) {
            return resultError(ResultMsgEnum.ORDER_MERCHANT_NOT_MATCH);
        }
        // 酒店预订 订单状态为待确认
        if (order.getOrderType() != 3 || FinalFiledParams.ORDER_STATUS_NOSTAY != order.getStatus()) {
            return resultError(ResultMsgEnum.CANNOT_STAY_ORDER);
        }
        // 修改订单状态
        orderService.alterOrderStatus(orderno, FinalFiledParams.ORDER_STATUS_NOCOMMENT);
        // 完成订单的一些结算
        orderService.afterFinishOrder(order);
        return resultSuccess();

    }

}
