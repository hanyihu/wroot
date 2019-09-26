package com.vic.ck.api.rider.service;

import com.vic.base.BaseApiLookup;
import com.vic.ck.api.constant.FinalFiledParams;
import com.vic.ck.api.rider.mapper.SingleMapper;
import com.vic.ck.entity.OrderDetails;
import com.vic.ck.entity.RiderNewOrders;
import com.vic.ck.entity.Version;
import com.vic.ck.util.CalculationDistance;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
public class SingleService {
    @Resource
    SingleMapper singleMapper;

    /**
     * 推送给骑手的新订单
     *
     * @param lookup
     * @return
     */
    public List<RiderNewOrders> selectNewOrders(BaseApiLookup lookup, Double lat, Double lng) {
        List<RiderNewOrders> riderNewOrders = singleMapper.selectNewOrders(lookup);
        for (RiderNewOrders newOrders : riderNewOrders) {
            String time = String.format("%.0f", (CalculationDistance.getDistance(lat, lng,
                    newOrders.getDlatitude(), newOrders.getDlongitude()) / 1000) / 25 + 10);
            newOrders.setMerchantBistance("" + String.format("%.1f", CalculationDistance.getDistance(lat, lng,
                    newOrders.getMlatitude(), newOrders.getMlongitude()) / 1000));
            newOrders.setBistance("" + String.format("%.1f", CalculationDistance.getDistance(lat, lng,
                    newOrders.getDlatitude(), newOrders.getDlongitude()) / 1000));
            newOrders.setIncome("6.5");
            newOrders.setTime(time);
        }
        return riderNewOrders;
    }

    /**
     * 新订单
     *
     * @param lookup
     * @return
     */
    public List<RiderNewOrders> selectNewOrders(BaseApiLookup lookup) {
        return singleMapper.selectNewOrders(lookup);
    }

    /**
     * 查询订单是否已经被人接单
     *
     * @param orderno 订单号
     * @return
     */
    public List<Integer> selectOrderStatus(int orderno) {
        return singleMapper.selectOrderStatus(orderno);
    }

    /**
     * 版本号
     *
     * @param orderno 订单号
     * @return
     */
    public Version selectVersion(int orderno) {
        return singleMapper.selectVersion(orderno);
    }

    /**
     * 修改订单状态
     *
     * @param orderno 订单号
     * @param version 订单版本号
     * @param status  订单状态
     * @return
     */
    public int updateOrder(int orderno, int version, int status) {
        return singleMapper.updateOrder(orderno, version, status);
    }

    /**
     * 骑手新订单
     *
     * @param orderno 订单号
     * @param riderId 骑手id
     * @return
     */
    public int riderNewOrder(int orderno, int riderId) {
        return singleMapper.riderNewOrder(orderno, riderId);
    }

    /**
     * 取货订单
     *
     * @param riderId 骑手id
     * @return
     */
    public List<RiderNewOrders> pickupOrder(int riderId, int orderStatus, Double lat, Double lng) {
        List<RiderNewOrders> orders = singleMapper.pickupOrder(riderId, orderStatus);
        for (RiderNewOrders riderNewOrders : orders) {
            double distance = CalculationDistance.getDistance(lat, lng,
                    riderNewOrders.getMlatitude(), riderNewOrders.getMlongitude());
            double distances = CalculationDistance.getDistance(lat, lng,
                    riderNewOrders.getDlatitude(), riderNewOrders.getDlongitude());
            String time = String.format("%.0f", (distances / 1000) / 25 + 10);
            riderNewOrders.setMerchantBistance("" + String.format("%.1f", distance / 1000));
            riderNewOrders.setBistance("" + String.format("%.1f", distances / 1000));
            riderNewOrders.setIncome("6.5");
            riderNewOrders.setTime(time);
        }
        return orders;
    }

    /**
     * 已完成
     *
     * @param riderId
     * @param orderStatus
     * @return
     */
    public List<RiderNewOrders> pickupOrder(int riderId, int orderStatus) {
        List<RiderNewOrders> orders = singleMapper.pickupOrder(riderId, orderStatus);
        for (RiderNewOrders riderNewOrders : orders) {
            riderNewOrders.setIncome("6.5");
        }
        return orders;

    }

    /**
     * 配送订单
     *
     * @param riderId 骑手Id
     * @return
     */
    public List<RiderNewOrders> shippingOrder(int riderId) {
        List<RiderNewOrders> orders = singleMapper.shippingOrder(riderId);
        for (RiderNewOrders newOrders : orders) {
            double distance = CalculationDistance.getDistance(newOrders.getMlatitude(), newOrders.getMlongitude(),
                    newOrders.getDlatitude(), newOrders.getDlongitude());
            String time = String.format("%.0f", (distance / 1000) / 25 + 10);
            newOrders.setBistance("" + String.format("%.1f", distance / 1000));
            newOrders.setTime(time);
        }
        return orders;
    }

    /**
     * 修改骑手已接单状态
     *
     * @param orderno 订单号
     * @param riderId 骑手id
     * @return
     */
    public int riderStatus(int orderno, int riderId) {
        return singleMapper.riderStatus(FinalFiledParams.RIDER_ORDER_STATUS, orderno, riderId);
    }

    /**
     * 确认送达
     *
     * @param orderno
     * @param riderId
     * @return
     */
    @Transactional
    public Object confirmDelivery(int orderno, int riderId) {
        if (selectVersion(orderno) != null) {
            updateOrder(orderno, selectVersion(orderno).getVersionNumber(), FinalFiledParams.ORDER_STATUS);
            singleMapper.riderStatus(FinalFiledParams.RIDER_ORDER_COMPLETE, orderno, riderId);
            return "操作成功";
        }
        return "操作失败";
    }


    /**
     * 订单详情
     *
     * @param orderno
     * @return
     */
    public List<OrderDetails> selectByOrderNoDetails(int orderno) {
        return singleMapper.selectByOrderNoDetails(orderno);
    }

    /**
     * 抢单
     *
     * @param orderno
     * @param riderId
     * @return
     */
    @Transactional
    public Object riderSingle(int orderno, int riderId) {
        List<Integer> integers = selectOrderStatus(orderno);
        if (integers != null && integers.get(0) == 1) {
            Version version = selectVersion(orderno);
            int ver = version.getVersionNumber();
            updateOrder(orderno, ver, FinalFiledParams.RIDER_ORDER_SINGLE);
            riderNewOrder(orderno, riderId);
            return "抢单成功";
        }
        return "抢单失败";
    }

    /**
     * 骑手取到货物，准备配送
     *
     * @param orderno
     * @param riderId
     * @return
     */
    @Transactional
    public Object startSend(int orderno, int riderId) {
        if (selectVersion(orderno) != null) {
            updateOrder(orderno, selectVersion(orderno).getVersionNumber(), FinalFiledParams.RIDER_ORDER_COMPLETE);
            riderStatus(orderno, riderId);
            return "成功";
        }
        return "失败";
    }


    /**
     * 根据订单号查询
     *
     * @param orderno
     * @return
     */
    public List<RiderNewOrders> selectByOrder(int orderno, Double lat, Double lng) {
        List<RiderNewOrders> riderNewOrders = singleMapper.selectByOrder(orderno);
        for (RiderNewOrders orders : riderNewOrders) {
            orders.setMerchantBistance("" + String.format("%.1f", CalculationDistance.getDistance(lat, lng,
                    orders.getMlatitude(), orders.getMlongitude()) / 1000));
            orders.setBistance("" + String.format("%.1f", CalculationDistance.getDistance(lat, lng,
                    orders.getDlatitude(), orders.getDlongitude()) / 1000));
            orders.setIncome("6.5");
            String time = String.format("%.0f", (CalculationDistance.getDistance(lat, lng,
                    orders.getDlatitude(), orders.getDlongitude()) / 1000) / 25 + 10);
            orders.setTime(time);
        }
        return riderNewOrders;
    }


    /**
     * 骑手拍照图片
     *
     * @param orderno
     * @param riderId
     * @param inPic
     * @return
     */
    public int insertRiderPic(String inPic, int orderno, int riderId) {
        return singleMapper.insertRiderPic(inPic, orderno, riderId);
    }

}
