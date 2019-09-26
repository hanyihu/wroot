package com.vic.ck.api.platform.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.constant.FinalFiledParams;
import com.vic.ck.api.jpush.service.MerchantJpushService;
import com.vic.ck.api.merchant.mapper.MerchantCommodityMapper;
import com.vic.ck.api.merchant.service.MerchantBalanceService;
import com.vic.ck.api.merchant.service.MerchantService;
import com.vic.ck.api.personal.service.CustomerDiscountTicketService;
import com.vic.ck.api.personal.service.CustomerGroupTicketService;
import com.vic.ck.api.personal.service.PersonalService;
import com.vic.ck.api.platform.lookup.OrderLookup;
import com.vic.ck.api.platform.mapper.OrderMapper;
import com.vic.ck.api.system.service.AuthService;
import com.vic.ck.api.system.service.CustomerBalanceService;
import com.vic.ck.entity.Commodity;
import com.vic.ck.entity.Customer;
import com.vic.ck.entity.Order;
import com.vic.ck.entity.OrderStatusRecord;
import com.vic.ck.util.GeneratorNoUtils;
import com.vic.ck.vo.HotelDetailVo;
import com.vic.ck.vo.OrderDetailVo;
import com.vic.ck.vo.OrderVo;
import com.vic.wroot.common.util.CommonUtils;
import com.vic.wroot.third.pay.AlipayUtils;
import com.vic.wroot.third.pay.WechatRefundApiResult;
import com.vic.wroot.third.pay.WechatpayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.List;

/**
 * 订单表
 *
 */
@Service
public class OrderService extends BaseService {

	@Resource
	private OrderMapper orderMapper;

	@Resource
	private MerchantService merchantService;

	@Resource
	private PersonalService personalService;

	@Resource
	private CustomerBalanceService customerBalanceService;

	@Resource
	private MerchantBalanceService merchantBalanceService;

	@Resource
	private PlatformCommonService platformCommonService;

	@Resource
	private CustomerDiscountTicketService customerDiscountTicketService;

	@Resource
	private CustomerGroupTicketService customerGroupTicketService;

	@Resource
	private CommodityService commodityService;
	
	@Resource
	private MerchantCommodityMapper merchantCommodityMapper;
	
	@Resource
	private MerchantJpushService merchantJpushService;
	
	@Resource
	private AuthService authService;

	private Logger logger = LoggerFactory.getLogger(OrderService.class);

	/**
	 * 查询列表
	 */
	public PageInfo<Order> list(Lookup lookup) {
		startPage(lookup);
		List<Order> datas = orderMapper.list(lookup);
		return PageInfo.instance(datas, lookup);
	}

	/**
	 * 后台列表页 
	 */
	public PageInfo<Order> orderList(Lookup lookup) {
		startPage(lookup);
		List<Order> datas = orderMapper.orderList(lookup);
		return PageInfo.instance(datas, lookup);
	}
	
	/**
	 * 订单记录时间轴
	 */
	public List<OrderStatusRecord> orderStatusRecord(String orderno){
		return orderMapper.orderStatusRecords(orderno);
	}

	/**
	 * 根据主键查询
	 */
	public Order findById(int id) {
		return orderMapper.findById(id);
	}

	/**
	 * 新增
	 */
	public int insert(Order entity) {
		return orderMapper.insert(entity);
	}

	/**
	 * 修改
	 */
	public int update(Order entity) {
		return orderMapper.update(entity);
	}

	/** 插入第三方支付单号 */
	public void alterOrderPayOutNo(Order order) {
		orderMapper.alterOrderPayOutNo(order);
	}
	
	/**插入退款原因*/
	public void updateRefundReason(String orderno, String refundReason) {
		orderMapper.updateRefundReason(orderno, refundReason);
	}

	/***
	 * 删除
	 */
	public int delete(int... ids) {
		return orderMapper.delete(ids);
	}

	/*******************************************************************************/

	/**
	 * 订单状态 0-待支付 1-待消费 2-待确认 3- 待入住 4-待评价 (已完成) 5-已评价 6-退款中 7-已退款 8-已取消 直接购买：0-待支付
	 * 4-待评价 (已完成) 5-已评价 团购： 0-待支付 1-待消费 4-待评价 (已完成) 5-已评价 6-退款中 7-已退款 8-已取消 预定：
	 * 0-待支付 2-待确认 3- 待入住 4-待评价 (已完成) 5-已评价 6-退款中 7-已退款 8-已取消
	 */
	/**
	 * 生成订单 在controller层组装好基础数据
	 */
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public void generateOrder(Order order) {
		String orderno = GeneratorNoUtils.generatorOrderNo();
		int status = FinalFiledParams.ORDER_STATUS_NOPAY;

		order.setStatus(status);
		order.setOrderno(orderno);

		// 1 插入订单表
		insert(order);
		// 2 插入订单记录表
		orderMapper.insertOrderStatusRecord(
				new OrderStatusRecord(orderno, status, FinalFiledParams.ORDER_STATUS_RECORD_MAP.get(status)));

//		if (order.getPayType() == 1) {// 余额支付
//			BigDecimal balance = personalService.balance(order.getCustomerId());
//			if (balance.compareTo(order.getAmount()) >= 0) {// 余额足够
//				// 减少我的余额 新增余额明细
//				boolean flag =	customerBalanceService.addBalance(order.getAmount().multiply(new BigDecimal(-1)),
//						FinalFiledParams.BALANCE_CONSUME, order.getCustomerId());
//				if(flag) {
//					afterPaySuccess(order);
//				}
//
//			}
//		}
		//  把折扣券置为已使用
		if (order.getTicketId() != null && order.getTicketId() > 0) {
			customerDiscountTicketService.updateStatus(order.getTicketId(), 1);
		}
	}


	/**
	 * 选择支付类型  点击支付 页面
	 */
	public void pay(Order order) {

		if (order.getPayType() == 1) {// 余额支付
			BigDecimal balance = personalService.balance(order.getCustomerId());
			if (balance.compareTo(order.getAmount()) >= 0) {// 余额足够
				// 减少我的余额 新增余额明细
				boolean flag = customerBalanceService.addBalance(order.getAmount().multiply(new BigDecimal(-1)),
						FinalFiledParams.BALANCE_CONSUME, order.getCustomerId());
				if (flag) {
					//选择支付类型  点击支付 页面
					afterPaySuccess(order);
				}
			}
		}

		if (order.getPayType() == 2) {

		}
		if (order.getPayType() == 3) {
		}
		// 8把折扣券置为已使用
		if (order.getTicketId() != null && order.getTicketId() > 0) {
			customerDiscountTicketService.updateStatus(order.getTicketId(), 1);
		}

	}

	/**
	 * 判断当前余额是否足够支付
	 */
	public boolean judgeBlance(Order order) {

		if (order == null || order.getCustomerId() == null || order.getAmount() == null) {
			return false;
		}
		if (order.getPayType() == 1) { // 支付方式为余额的时候
			BigDecimal balance = customerBalanceService.customerBalance(order.getCustomerId());
			if (balance.compareTo(order.getAmount()) < 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 订单支付成功之后的操作 · 前置判断 判断后调用此方法 如订单状态 等
	 * 
	 * @param order
	 */
	public void afterPaySuccess(Order order) {
		// 3 修改订单状态
		int alterStatus = 0;
		switch (order.getOrderType()) {
		case 1:// 直接购买
			alterStatus = FinalFiledParams.ORDER_STATUS_NOCOMMENT;
			// 直接完成订单
			afterFinishOrder(order);
			break;
		case 2:// 团购
			alterStatus = FinalFiledParams.ORDER_STATUS_NOCONSUME;
			// 生成团购券
//			customerGroupTicketService.generateGroupbuyTicket(order);
			break;
		case 3:// 酒店预约
			alterStatus = FinalFiledParams.ORDER_STATUS_NOCONFIRM;
			break;
		default:
			break;
		}
		alterOrderStatus(order.getOrderno(), alterStatus);
		
		Customer merchantCustomer = authService.findMerchantCustomer(order.getMerchantId());
		if(merchantCustomer != null && StringUtils.isNotEmpty(merchantCustomer.getMerchantMobileCode())) {
			//推送消息
			merchantJpushService.create(MessageFormat.format("订单号{0}已支付{1}元，注意查看!", order.getOrderno(), order.getAmount())).to(merchantCustomer.getMerchantMobileCode()).push();
		}
		
		

	}

	/**
	 * 订单完成的时候触发的一些金额计算 之所以不放在支付完成，是因为订单可能会被取消、退款等
	 */
	public void afterFinishOrder(Order order) {
		
		int type = 0;
		switch (order.getOrderType()) {
		case 1:
			type = FinalFiledParams.MERCHAN_BALANCE_CONSUMER_PAY;// 用户付款
			//新增商家销量
			merchantCommodityMapper.addMerchantSellNum(order.getMerchantId(),1);
			break;
		case 2:
			type = FinalFiledParams.MERCHAN_BALANCE_GROUBUY_SALE;// 团购收入
			if(order.getQuantity() != null && order.getCommodityId() != null){
				//新增团购销量
//				merchantCommodityMapper.addGroupbuySellNum(order.getCommodityId(),order.getQuantity());
				//新增商家销量
				merchantCommodityMapper.addMerchantSellNum(order.getMerchantId(),order.getQuantity());
			}
			break;
		case 3:
			type = FinalFiledParams.MERCHAN_BALANCE_HOTEL;// 酒店收入
			merchantCommodityMapper.addMerchantSellNum(order.getMerchantId(),1);
			break;
		default:
			break;
		}
		if(type == 0) return;
		// 1 增加商家余额
		merchantBalanceService.addBalance(order.getAmount(), type, order.getMerchantId());
		// 2 触发平台的返点结算
		platformCommonService.rebateCalculation(order);
		// 3 用户消费获得积分
		personalService.addCustomerScoreRecord(order.getAmount().intValue(), FinalFiledParams.SCORE_BUY,
				order.getCustomerId());
		
	}

	/** 修改订单状态 */
	@Transactional
	public void alterOrderStatus(String orderno, int status) {
		// 修改订单状态 当修改为 1 2 3的时候为支付成功的时机
		orderMapper.alterOrderStatus(orderno, status);
		// 订单状态变更记录
		orderMapper.insertOrderStatusRecord(
				new OrderStatusRecord(orderno, status, FinalFiledParams.ORDER_STATUS_RECORD_MAP.get(status)));

	}

	/**
	 * 添加用户退款申请
	 */
	public void insertorderout(String orderno_id, int customer_id, int merchant_id, String refundReason) {
		// 修改订单状态 当修改为 1 2 3的时候为支付成功的时机
		orderMapper.insertorderout(orderno_id, customer_id, merchant_id, refundReason);

	}
	

	/**
	 * 我的订单列表
	 */
	public PageInfo<OrderVo> orders(OrderLookup lookup) {
		startPage(lookup);
		List<OrderVo> datas = orderMapper.orders(lookup);
		return PageInfo.instance(datas);
	}

	/**
	 * 订单详情
	 */
	public OrderDetailVo orderDetail(String orderno) {
		OrderDetailVo detail = orderDetailBase(orderno);
		if (detail != null && detail.getOrderType() == 3) {
			HotelDetailVo hotel = commodityService.roomDetail(detail.getCommodityId());
			detail.setHotelDetail(hotel);
		}
		if(detail != null && detail.getOrderType() == 2){// 团购券号
			detail.setGroupbutTicketNo(orderMapper.findGroupbutTicketNo(orderno));
		}
		return detail;
	}

	/** 订单详情基本情况 */
	public OrderDetailVo orderDetailBase(String orderno) {
		return orderMapper.orderDetailBase(orderno);
	}

	/**
	 * 订单退款操作
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean refund(OrderDetailVo order) throws Exception {
		logger.info("*****************订单退款*************订单号{}*******第三方订单号{}****", order.getOrderno(), order.getPayOutNo());
		boolean result = false;
		// 1-余额支付 2-支付宝支付 3-微信支付
		int payType = order.getPayType();
//		int merchantId = order.getMerchantId();
		int customerId = order.getCustomerId();
		BigDecimal money = order.getAmount();
		switch (payType) {
		case 1:// 直接原路返回到用户的账户 
			customerBalanceService.addBalance(money, FinalFiledParams.BALANCE_REFUND, customerId);
			//这个时候钱还没到商户的账 因此不存在减少商户余额
			/*merchantBalanceService.addBalance(money.multiply(new BigDecimal(-1)),
					FinalFiledParams.MERCHAN_BALANCE_REFUND, merchantId);*/
			result = true;
			break;
		case 2:
			AlipayTradeRefundResponse ar = AlipayUtils.refundAlipay(order.getOrderno(), order.getPayOutNo(),
					money.doubleValue(), "用户退款");
			if (ar.isSuccess() && ar.getCode().compareTo("10000") == 0) {
				/*
				merchantBalanceService.addBalance(money.multiply(new BigDecimal(-1)),
						FinalFiledParams.MERCHAN_BALANCE_REFUND, merchantId);
						*/
				logger.warn("支付宝退款成功------------------");
				result = true;
				break;
			} else {
				logger.warn("支付宝退款失败 {}", CommonUtils.toJson(ar));
			}
			break;
		case 3:// 3-微信支付
			WechatRefundApiResult wr = WechatpayUtils.wxRefund(1, order.getOrderno(), money.doubleValue(),
					money.doubleValue());
			if (wr.refundSuccess()) {
				/*
				merchantBalanceService.addBalance(money.multiply(new BigDecimal(-1)),
						FinalFiledParams.MERCHAN_BALANCE_REFUND, merchantId);
						*/
				logger.warn("微信退款成功------------------");
				result = true;
			} else {
				logger.warn("微信退款失败 {}", CommonUtils.toJson(wr));
			}
			break;
		default:
			result = false;
			break;
		}
		
		return result;

	}

	/**
	 * 修改订单支付方式
	 *
	 * @param
	 * @param
	 */
	public void alterOrderPayType(Order order) {
		orderMapper.alterOrderPayType(order);
		if (order.getPayType() == 1) {// 余额支付
			BigDecimal balance = personalService.balance(order.getCustomerId());
			if (balance.compareTo(order.getAmount()) >= 0) {// 余额足够
				// 减少我的余额 新增余额明细
				boolean flag =	customerBalanceService.addBalance(order.getAmount().multiply(new BigDecimal(-1)),
						FinalFiledParams.BALANCE_CONSUME, order.getCustomerId());
				if(flag) {
					afterPaySuccess(order);
				}

			}
		}
	}

	public Order findOrderByOrderno(String orderno) {
		return orderMapper.findOrderByOrderno(orderno);
	}

	/**
	 * 删除订单
	 * 
	 * @param orderno
	 */
	public int deleteOrder(String orderno) {
		return orderMapper.deleteOrder(orderno);
	}

	/**
	 * 根据id查询订单详细
	 * @param id
	 * @return
	 */
	public OrderVo orderById(Integer id) {
		return orderMapper.orderById(id);
	}


	/**
	 * 新增订单商品
	 */
	public int insertddsp(String orderno_id, int commodity_id, int number, int merchant_id, int customer_id) {
		return orderMapper.insertddsp(orderno_id, commodity_id, number, merchant_id, customer_id);
	}

	/**
	 * 查询订单商品
	 */
	public List<Commodity> listddsp(String orderno_id) {
//		startPage(lookup);
		List<Commodity> datas = orderMapper.listddsp(orderno_id);
		return datas;
	}


	public static void main(String[] args) throws AlipayApiException {
		AlipayTradeRefundResponse ar = AlipayUtils.refundAlipay("201803211626168847", "2018032121001004140547798607",	75.00, "用户退款");
		System.out.println( CommonUtils.toJson(ar));
		if (ar.isSuccess() && ar.getCode().compareTo("10000") == 0) {
			System.out.println("ok");
		} 
	}

}
