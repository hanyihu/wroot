package com.vic.ck.api.platform.mapper;

import com.vic.base.BaseMapper;
import com.vic.base.pager.Lookup;
import com.vic.ck.api.platform.lookup.OrderLookup;
import com.vic.ck.entity.Commodity;
import com.vic.ck.entity.Order;
import com.vic.ck.entity.OrderStatusRecord;
import com.vic.ck.vo.OrderDetailVo;
import com.vic.ck.vo.OrderVo;
import com.vic.wroot.common.annotation.MybatisMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单表
 * 
 * @author VIC
 */
@MybatisMapper
public interface OrderMapper extends BaseMapper<Order> {
	
	/**新增订单记录*/
	int insertOrderStatusRecord(OrderStatusRecord record);
	
	/**订单的订单记录*/
	List<OrderStatusRecord> orderStatusRecords(@Param("orderno")String orderno);

	/**修改订单状态*/
	void alterOrderStatus(@Param("orderno")String orderno, @Param("status")int status);

    void updateRefundReason(@Param("orderno") String orderno, @Param("refundReason") String refundReason);

	/**我的订单列表*/
	List<OrderVo> orders(OrderLookup lookup);

	/**订单详情基本情况*/
	OrderDetailVo orderDetailBase(@Param("orderno")String orderno);

	/**修改订单支付方式*/
	void alterOrderPayType(Order order);

	Order findOrderByOrderno(@Param("orderno")String orderno);

	/**删除订单*/
	int deleteOrder(@Param("orderno")String orderno);
	
	/**插入第三方支付单号*/
	void alterOrderPayOutNo(Order order);

	/**后台列表页*/
	List<Order> orderList(Lookup lookup);

	/**查询订单团购券号*/
	String findGroupbutTicketNo(@Param("orderno")String orderno);

	/**根据id查询订单详情*/
	OrderVo orderById(@Param("id") Integer id);

    //新增订单商品表
    int insertddsp(@Param("orderno_id") String orderno_id, @Param("commodity_id") int commodity_id, @Param("number") int number, @Param("merchant_id") int merchant_id, @Param("customer_id") int customer_id);

    /**
     * 订单的订单记录
     */
    List<Commodity> listddsp(@Param("orderno_id") String orderno_id);

    // 添加用户退款申请
    int insertorderout(@Param("orderno_id") String orderno_id, @Param("customer_id") int customer_id, @Param("merchant_id") int merchant_id, @Param("refundReason") String refundReason);

	
}
