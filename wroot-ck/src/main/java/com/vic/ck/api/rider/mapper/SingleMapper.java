package com.vic.ck.api.rider.mapper;

import com.vic.base.BaseApiLookup;
import com.vic.ck.entity.OrderDetails;
import com.vic.ck.entity.RiderNewOrders;
import com.vic.ck.entity.Version;
import com.vic.wroot.common.annotation.MybatisMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MybatisMapper
public interface SingleMapper {
    /**查询新订单*/
    List<RiderNewOrders> selectNewOrders(BaseApiLookup lookup);

    /**查询订单的状态*/
    List<Integer> selectOrderStatus(@Param("orderno") int orderno);

    /**版本号*/
    Version selectVersion(@Param("orderno") int orderno);

    /**修改订单状态*/
    int updateOrder(@Param("orderno") int orderno,@Param("version") int version,@Param("status") int status);

    /**添加骑手新订单*/
    int riderNewOrder(@Param("orderno") int orderno,@Param("riderId") int riderId);

    /**取货订单列表*/
    List<RiderNewOrders> pickupOrder(@Param("riderId") int riderId,@Param("orderStatus") int orderStatus);

    /**配送订单列表*/
    List<RiderNewOrders> shippingOrder(@Param("riderId") int riderId);

    /**修改骑手状态*/
    int riderStatus(@Param("ordernoStatus") int ordernoStatus,@Param("orderno") int orderno,@Param("riderId") int riderId);

    /**根据订单号查询*/
    List<RiderNewOrders> selectByOrder(@Param("orderno") int orderno);

    /**订单详情*/
    List<OrderDetails> selectByOrderNoDetails(@Param("orderno") int orderno);

    /**图片上传*/
    int insertRiderPic(@Param("inPic") String inPic,@Param("orderno") int orderno,@Param("riderId") int riderId);
}
