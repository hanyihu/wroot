package com.vic.ck.api.rider.controller;

import com.vic.base.BaseApiController;
import com.vic.base.BaseApiLookup;
import com.vic.ck.api.constant.FinalFiledParams;
import com.vic.ck.api.rider.service.SingleService;
import com.vic.ck.entity.GrabSingleCenter;
import com.vic.ck.entity.OrderDetails;
import com.vic.ck.entity.RiderNewOrders;
import com.vic.wroot.common.po.BaseResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 * 骑手端 订单中心
 */
@RestController
@RequestMapping("/api/single")
public class SingleCenterController extends BaseApiController {

    @Resource
    SingleService singleService;


    GrabSingleCenter grabSingleCenter = new GrabSingleCenter();

    /**
     * 最新订单推送给骑手端
     *
     * @param lookup
     * @return
     */
    @ApiOperation(value = "最新订单推送给骑手端", notes = "lat:维度,lng：经度", produces = "application/josn")
    @RequestMapping(value = "/center", method = RequestMethod.GET)
    public BaseResponse<GrabSingleCenter>
    platformCategories(BaseApiLookup lookup, Double lat, Double lng) {
        List<RiderNewOrders> riderNewOrders = singleService.selectNewOrders(lookup, lat, lng);
        grabSingleCenter.setOrders(riderNewOrders.size());
        grabSingleCenter.setRiderNewOrders(riderNewOrders);
        return resultSuccess(grabSingleCenter);
    }


    /**
     * 骑手抢单
     *
     * @param orderno 订单号
     * @return
     */
    @ApiOperation(value = "骑手抢单", notes = "orderno:订单号,riderId：骑手id", produces = "application/josn")
    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public Object orderStatus(int orderno, int riderId) {
        return singleService.riderSingle(orderno, riderId);
    }

    /**
     * 骑手待取货列表
     *
     * @param riderId
     * @param lat
     * @param lng
     * @return
     */
    @ApiOperation(value = "待取货", notes = "riderId：骑手id;lat:骑手当前经度;lng:骑手当前维度", produces = "application/josn")
    @RequestMapping(value = "/pickup", method = RequestMethod.GET)
    public BaseResponse<GrabSingleCenter> pickupOrder(int riderId, Double lat, Double lng) {
        List<RiderNewOrders> orders = singleService.pickupOrder(riderId, FinalFiledParams.RIDER_ORDER_TAKE, lat, lng);
        grabSingleCenter.setOrders(orders.size());
        grabSingleCenter.setRiderNewOrders(orders);
        return resultSuccess(grabSingleCenter);
    }

    /**
     * 已完成
     *
     * @param riderId
     * @return
     */
    @ApiOperation(value = "已完成", notes = "riderId：骑手id", produces = "application/josn")
    @RequestMapping(value = "/completed", method = RequestMethod.GET)
    public BaseResponse<GrabSingleCenter> completedOrder(int riderId) {
        List<RiderNewOrders> orders = singleService.pickupOrder(riderId, FinalFiledParams.RIDER_ORDER_COMPLETE);
        grabSingleCenter.setOrders(orders.size());
        grabSingleCenter.setRiderNewOrders(orders);
        return resultSuccess(grabSingleCenter);
    }

    /**
     * 配送订单列表
     *
     * @param riderId
     * @return
     */
    @ApiOperation(value = "配送中订单", notes = "riderId：骑手id", produces = "application/josn")
    @RequestMapping(value = "/distribution", method = RequestMethod.GET)
    public BaseResponse<GrabSingleCenter> shippingOrder(int riderId) {
        List<RiderNewOrders> orders = singleService.shippingOrder(riderId);
        grabSingleCenter.setOrders(orders.size());
        grabSingleCenter.setRiderNewOrders(orders);
        return resultSuccess(grabSingleCenter);
    }

    /**
     * 订单详情
     *
     * @param orderno
     * @param lat
     * @param lng
     * @return
     */
    @ApiOperation(value = "订单详情", notes = "orderno：订单号;lat:骑手当前经度;lng:骑手当前维度", produces = "application/josn")
    @RequestMapping(value = "/orderDetails", method = RequestMethod.GET)
    public BaseResponse<GrabSingleCenter> orderDetails(int orderno, Double lat, Double lng) {
        List<RiderNewOrders> orders = singleService.selectByOrder(orderno, lat, lng);
        List<OrderDetails> orderDetails = singleService.selectByOrderNoDetails(orderno);
        grabSingleCenter.setRiderNewOrders(orders);
        grabSingleCenter.setOrderDetails(orderDetails);
        if (orderDetails != null && orderDetails.size() > 0) {
            grabSingleCenter.setShippingFee(orderDetails.get(0).getShippingFee());
            grabSingleCenter.setMealsFee(3.5);
            grabSingleCenter.setTotalPrice(orderDetails.get(0).getSinglePrice());
        }
        return resultSuccess(grabSingleCenter);
    }

    /**
     * 骑手取到货物，准备配送
     *
     * @param orderno
     * @param riderId
     * @return
     */
    @ApiOperation(value = "骑手取到货物，准备配送", notes = "orderno:订单号 ;riderId：骑手id", produces = "application/josn")
    @RequestMapping(value = "/arrival ", method = RequestMethod.GET)
    public Object startSend(int orderno, int riderId) {
        return singleService.startSend(orderno, riderId);
    }

    /**
     * 确认送达
     *
     * @param orderno
     * @param riderId
     * @return
     */
    @ApiOperation(value = "确认送达", notes = "orderno:订单号 ;riderId：骑手id", produces = "application/josn")
    @RequestMapping(value = "/confirm ", method = RequestMethod.GET)
    public Object confirmDelivery(int orderno, int riderId) {
        return singleService.confirmDelivery(orderno, riderId);
    }

    /**
     * 骑手拍照图片
     *
     * @param orderno
     * @param riderId
     * @param inPic
     * @return
     */
    @ApiOperation(value = "骑手拍照图片", notes = "orderno:订单号 ;riderId：骑手id;inPic 图片id", produces = "application/josn")
    @RequestMapping(value = "/riderPic ", method = RequestMethod.GET)
    public Object insertRiderPic(String inPic, int orderno, int riderId) {
        return singleService.insertRiderPic(inPic, orderno, riderId);
    }
}
