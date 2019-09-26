package com.vic.ck.api.platform.lookup;

import java.util.HashMap;
import java.util.Map;

import com.vic.base.BaseApiLookup;

/**
 * 订单的查询条件
 * 
 * @author VIC
 *
 */
public class OrderLookup extends BaseApiLookup {

	/**
	 * 
	 */
	private static Map<Integer, Integer[]> STATUS_MAP = new HashMap<Integer, Integer[]>() {
		private static final long serialVersionUID = 1L;

		{
			
			put(1, new Integer[] { 0 });//用户订单列表-待付款
			put(2, new Integer[] { 1, 3 });//用户订单列表-可使用
			put(3, new Integer[] { 4 });//用户订单列表-待评价
			put(4, new Integer[] { 6, 7 });//用户订单列表-退款    ||  商户酒店订单-退款
			/*
				收到的订单：待支付，待确认
				完成的订单：待入住，待评价，已完成

			*/
			put(5, new Integer[] { 0, 2 });//商户酒店订单-收到订单
			put(6, new Integer[] { 3, 4 });//商户酒店订单-完成订单
		}
	};
	
	
	

	/**
	 * 订单状态 0-待支付 1-待消费 2-待确认 3- 待入住 4-待评价 (已完成) 5-已评价 6-退款中 7-已退款 8-已取消 全部 空 1
	 * 用于用户订单列表：  1-待付款：0，2-可使用：1 3; 3-待评价：4; 4-退款：6 7
	 * 用于商户酒店订单列表：orderStatus 5- 收到订单(0, 2 ) 6- 完成订单(3,4)  4-退款/售后（退款：6 7）
	 */
	private Integer orderStatus;

	public Integer[] getStatuses() {
		return STATUS_MAP.get(getOrderStatus());
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

}
