package com.vic.ck.api.personal.controller;

import com.vic.base.BaseApiController;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.constant.FinalFiledParams;
import com.vic.ck.api.constant.ResultMsgEnum;
import com.vic.ck.api.merchant.service.MerchantService;
import com.vic.ck.api.personal.service.CustomerGroupTicketService;
import com.vic.ck.api.personal.service.PersonalService;
import com.vic.ck.api.platform.lookup.OrderLookup;
import com.vic.ck.api.platform.service.JpushMsgService;
import com.vic.ck.api.platform.service.OrderCommentService;
import com.vic.ck.api.platform.service.OrderService;
import com.vic.ck.api.system.service.AuthService;
import com.vic.ck.entity.*;
import com.vic.ck.util.Distance;
import com.vic.ck.vo.OrderDetailVo;
import com.vic.ck.vo.OrderVo;
import com.vic.wroot.common.exception.BadParamException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.naming.AuthenticationException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 订单支付的一些东西
 *
 * @author VIC
 */

@RestController
@RequestMapping("/api/order")
public class OrdersController extends BaseApiController {

    private Logger logger = LoggerFactory.getLogger(PersonalController.class);

    @Resource
    private PersonalService personalService;

    @Resource
    private CustomerGroupTicketService customerGroupTicketService;

    @Resource
    private OrderService orderService;

    @Resource
    private OrderCommentService orderCommentService;

    @Resource
    private JpushMsgService jpushMsgService;

    @Resource
    private AuthService authService;

    @Resource
    private MerchantService merchantService;

    public static void main(String[] args) {
//			int status = 0;//订单状态  0-待支付 1-待消费 2-待确认 3- 待入住 4-待评价 (已完成) 5-已评价 6-退款中 7-已退款 8-已取消
//			int orderType = 2; //订单类型 1-直接购买 2-团购 3-酒店预订
//			//直接购买不存在退款 、团购 待消费的时候才能退  、酒店预订 待确认和待入住才能退款
//			if(orderType == 1 || (orderType==2 && status != 1) || (orderType ==3 && (status !=2 && status !=3))) {
//				System.out.println(111);
//			}else {
//				System.out.println(333);
//			}

    }

    /**
     * 2.18 我的订单列表
     * <p>
     * 订单状态 0-待支付 1-待消费 2-待确认 3- 待入住 4-待评价 (已完成) 5-已评价 6-退款中 7-已退款 8-已取消 全部 待付款：0
     * 可使用：1 3 待评价：5 退款：6 7
     */
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public Object orders(OrderLookup lookup) throws AuthenticationException {
        checkLoggin(lookup);
        PageInfo<OrderVo> data = orderService.orders(lookup);
        return resultSuccess(data);
    }

    /**
     * 2.19 订单详情
     */
    @RequestMapping(value = "/order/{orderno}", method = RequestMethod.GET)
    public Object orderDetail(@PathVariable String orderno) {
        OrderDetailVo data = orderService.orderDetail(orderno);
        return resultSuccess(data);
    }


//	/**
//	 * 2.20 申请退款
//	 */
//	@RequestMapping(value = "/order/refund", method = RequestMethod.POST)
//	public Object refund(String orderno, int refundReason) {
//		OrderDetailVo order = orderService.orderDetailBase(orderno);
//		if (order == null) {
//			return resultError(ResultMsgEnum.NOTEXISTED_ORDER);
//		}
//
//		synchronized (order) {
//
//			// 判断当前订单可否退款
//			int status = order.getStatus();// 订单状态 0-待支付 1-待消费 2-待确认 3- 待入住 4-待评价 (已完成) 5-已评价 6-退款中 7-已退款 8-已取消
//			int orderType = order.getOrderType(); // 订单类型 1-直接购买 2-团购 3-酒店预订
//			// 直接购买不存在退款 、团购 待消费的时候才能退 、酒店预订 待确认和待入住才能退款
//			if (orderType == 1 || (orderType == 2 && status != 1) || (orderType == 3 && (status != 2 && status != 3))) {
//				return resultError(ResultMsgEnum.CANNOT_REFUND_STATUS);
//			}
//			// 酒店预定 18点之后不能退款（后来加了个当天 和已确认）
//			if (orderType == 3 && status == 3 && (DateUtils.daysBetween(new Date(), order.getCreateTime()) != 0
//					|| Calendar.getInstance().get(Calendar.HOUR_OF_DAY) > 18)) {
//				return resultError(ResultMsgEnum.CANNOT_REFUND_HOTEL);
//			}
//			// 没有找到第三方支付流水
//			if (1 != order.getPayType() && StringUtils.isEmpty(order.getPayOutNo())) {
//				return resultError(ResultMsgEnum.CANNOT_FIND_PAYNO);
//			}
//
//			// 退款操作
//			try {
//				boolean result = orderService.refund(order);
//				if (result) {
//					// 把订单状态置为已退款
//					orderService.alterOrderStatus(orderno, FinalFiledParams.ORDER_STATUS_REFUNDED);
//					orderService.updateRefundReason(orderno, refundReason);
//					if (orderType == 2) { // 团购的应该删除对应的团购券
//						customerGroupTicketService.deleteByOrdeno(orderno);
//					}
//				} else {
//					return resultError(ResultMsgEnum.REFUND_FAIL);
//				}
//
//			} catch (Exception e) {
//				logger.error(ExceptionUtils.getStackTrace(e));
//				return resultError(ResultMsgEnum.REFUND_FAIL);
//			}
//		}
//		return resultSuccess(orderService.orderDetail(orderno));
//	}

    /**
     * 催单
     * 新订单状态   2-商家接单 3- 骑手接单
     */
    @RequestMapping(value = "/order/{orderno}/reminder", method = RequestMethod.GET)
    public Object reminder(@PathVariable String orderno) {
        OrderDetailVo data = orderService.orderDetail(orderno);
        Integer status = data.getStatus();
        Customer c = authService.findCustomerById(data.getMerchantId());
        if (c == null || StringUtils.isEmpty(c.getMerchantMobileCode())) {
            return resultSuccess("商家没有绑定设备号：无法推送消息！");
        }
        // 1-支付完成 2-商家接单 3- 骑手接单
        if (status == 1) {
            jpushMsgService.pushToMerchant(c.getId(), "您有新订单未能及时处理，用户催单！", c.getMobileCode());
        } else if (status == 2) {
            return resultSuccess("当前骑手太忙，请耐心等候！");
        } else if (status == 3) {
            //这里骑手设备号 也是用户设备号        rider_task   状态 1为接单状态
            Customer findCustomerByRider = authService.findCustomerByRider(orderno);
            if (findCustomerByRider == null || StringUtils.isEmpty(findCustomerByRider.getMerchantMobileCode())) {
                return resultSuccess("骑手没有绑定设备号：无法推送消息！");
            }
            jpushMsgService.pushToCustomer(findCustomerByRider.getId(), "用户催单！请您注意送达时间！", findCustomerByRider.getMobileCode());
        } else {
            return resultSuccess("当前订单状态无法催单！");
        }
        return resultSuccess();
    }

    /**
     * 2.20 用户提交申请 退款
     */
    @RequestMapping(value = "/order/refund", method = RequestMethod.POST)
    public Object refund(String orderno, String refundReason) {
        OrderDetailVo order = orderService.orderDetailBase(orderno);
        if (order == null) {
            return resultError(ResultMsgEnum.NOTEXISTED_ORDER);
        }
        synchronized (order) {
            // 判断当前订单可否退款
            int status = order.getStatus();// 订单状态 0-待支付 1-待消费 2-待确认 3- 待入住 4-待评价 (已完成) 5-已评价 6-申请退款  7-已退款 8-已取消  71-拒绝退款
            int orderType = order.getOrderType(); // 订单类型 1-直接购买 2-团购 3-酒店预订
            //6-退款中 7-已退款 8-已取消
            if (status == 6 || status == 7 || status == 8) {
                return resultError(ResultMsgEnum.CANNOT_REFUND_STATUS);
            }

            // 添加退款申请记录
            orderService.insertorderout(orderno, order.getCustomerId(), order.getMerchantId(), refundReason);

            // 把订单状态置为 申请退款
            orderService.alterOrderStatus(orderno, FinalFiledParams.ORDER_STATUS_REFUNDING);
            orderService.updateRefundReason(orderno, refundReason);//订单表
            if (orderType == 2) { // 团购的应该删除对应的团购券
                customerGroupTicketService.deleteByOrdeno(orderno);
            }

            //推送给商家退款申请消息
            Merchant merchant = merchantService.getMerchantById(order.getMerchantId());
            Customer c = authService.findCustomerById(merchant.getCustomerId());
            if (c == null || StringUtils.isEmpty(c.getMerchantMobileCode())) {
                return resultSuccess("商家用户未绑定设备，无法接收到信息。请联系客服！");
            }
            jpushMsgService.pushToMerchant(order.getCustomerId(), "有用户申请退款，请及时处理！", c.getMobileCode());

        }
        return resultSuccess(orderService.orderDetail(orderno));
    }

    /**
     * 2.21 修改订单支付方式 1-余额支付 2-支付宝支付 3-微信支付
     */
    @RequestMapping(value = "/order/changePayType", method = RequestMethod.POST)
    public Object changePayType(String orderno, int payType) {
        if (payType < 1 || payType > 3) {
            throw new BadParamException("错误的支付方式");
        }
        Order order = orderService.findOrderByOrderno(orderno);
        if (order == null) {
            return resultError(ResultMsgEnum.NOTEXISTED_ORDER);
        }
        if (order.getStatus() != 0) {
            return resultError(ResultMsgEnum.ALREADY_PAYED);
        }

        order.setPayType(payType);
        orderService.alterOrderPayType(order);
        return resultSuccess(orderService.orderDetail(orderno));
    }

    /**
     * 2.22 删除订单 未支付 已取消 已完成 已评价 已退款 ---->可删除 订单状态 0-待支付 1-待消费 2-待确认 3- 待入住 4-待评价
     * (已完成) 5-已评价 6-退款中 7-已退款 8-已取消
     */
    @RequestMapping(value = "/order/{orderno}/delete", method = RequestMethod.POST)
    public Object deleteOrder(@PathVariable String orderno) {
        Order order = orderService.findOrderByOrderno(orderno);
        if (order == null) {
            return resultError(ResultMsgEnum.NOTEXISTED_ORDER);
        }
        int status = order.getStatus();
        if (status != 0 && status != 4 && status != 5 && status != 7 && status != 8) {
            return resultError(ResultMsgEnum.CANNOT_DELETE_ORDER);
        }

        orderService.deleteOrder(orderno);
        return resultSuccess();
    }

    /**
     * 2.23 取消订单
     */
    @RequestMapping(value = "/order/{orderno}/cancel", method = RequestMethod.POST)
    public Object cancel(@PathVariable String orderno) {
        Order order = orderService.findOrderByOrderno(orderno);
        if (order == null) {
            return resultError(ResultMsgEnum.NOTEXISTED_ORDER);
        }
        int status = order.getStatus();
        if (status != 0) {
            return resultError(ResultMsgEnum.CANNOT_CANCEL_ORDER);
        }

        orderService.alterOrderStatus(orderno, FinalFiledParams.ORDER_STATUS_CANCELED);
        return resultSuccess();
    }

    /**
     * 2.24 对订单进行评价
     *
     * @param comment
     * @return
     * @throws AuthenticationException
     */
    @RequestMapping(value = "/order/comment", method = RequestMethod.POST)
    public Object comment(OrderComment comment) throws AuthenticationException {
        checkLoggin(comment.getCustomerId());
        vaildateModel(comment);
        Order order = orderService.findOrderByOrderno(comment.getOrderno());
        if (order == null) {
            return resultError(ResultMsgEnum.NOTEXISTED_ORDER);
        }
        if (orderCommentService.findByOrderno(comment.getOrderno()) != null) {
            return resultError(ResultMsgEnum.ALREADY_COMMENT);
        }

        comment.setType(order.getOrderType());
        orderCommentService.insert(comment);
        // 评论后的一些计算
        orderCommentService.afterComment(comment);
        orderService.alterOrderStatus(order.getOrderno(), FinalFiledParams.ORDER_STATUS_COMMENTED);
        personalService.addCustomerScoreRecord(FinalFiledParams.SCORE_COMMENT_NUMBER, FinalFiledParams.SCORE_COMMENT,
                order.getCustomerId());
        return resultSuccess();
    }

    /**
     * 任意 修改订单状态    0-待支付 1已支付 2-商家接单 3- 骑手接单  4-待评价 (已完成) 5-已评价 6-退款中 7-已退款 8-已取消
     */
    @RequestMapping(value = "/order/status", method = RequestMethod.POST)
    public Object status(String orderno, int status) {
        Order order = orderService.findOrderByOrderno(orderno);
        if (order == null) {
            return resultError(ResultMsgEnum.NOTEXISTED_ORDER);
        }
//		int statusold = order.getStatus();
//		if (statusold != 0) { /** 待支付 */
//			return resultError(ResultMsgEnum.CANNOT_CANCEL_ORDER);
//		}
        // 修改订单状态
        // FinalFiledParams.ORDER_STATUS_CANCELED
        orderService.alterOrderStatus(orderno, status);
        return resultSuccess();
    }

    /**
     * 根据用户、商家坐标返回 送达时间
     */
    @RequestMapping(value = "/order/guessTime", method = RequestMethod.POST)
    public Object GuessTime(double long1, double lat1, double long2, double lat2) {
        // KM
        double abcd = Distance.GetDistance(long1, lat1, long2, lat2);
        // 假设 起手速度：30km/h
        int qs = 30;
        // +了10分钟
        int h = (int) ((abcd / qs) * 60 + 10);

        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, h);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        String dateStr = sdf.format(now.getTimeInMillis());

//		System.out.println("-----------"+dateStr);
        return resultSuccess(dateStr);
    }

    /**
     * 选择的商品新增到订单商品表
     *
     * @throws AuthenticationException
     */
    @RequestMapping(value = "/order/insertOrderCommodity", method = RequestMethod.POST)
    public Object insertOrderCommodity(Map<Integer, Integer> map, int merchant_id, int customer_id) {
        // 生成订单商品所属ID
        Date date = new Date();
        String prefix = DateFormatUtils.format(date, "yyyyMMddHHmmssSSS");
        int suffix = new Random().nextInt(5);
        String orderno_id = prefix + suffix;
        // 插入  商品ID +数量
        for (Integer in : map.keySet()) {
            orderService.insertddsp(orderno_id, in, map.get(in), merchant_id, customer_id);
        }

        return resultSuccess(orderno_id);
    }

    /**
     * 订单包含商品列表查询
     */
    @RequestMapping(value = "/orders/selectOrderCommodity", method = RequestMethod.GET)
    public Object selectOrderCommodity(String orderno_id) throws AuthenticationException {
        List<Commodity> data = orderService.listddsp(orderno_id);
        return resultSuccess(data);
    }

}
