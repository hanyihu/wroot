package com.vic.ck.api.platform.controller;

import com.vic.base.BaseApiController;
import com.vic.ck.api.constant.ResultMsgEnum;
import com.vic.ck.api.merchant.service.MerchantActivityService;
import com.vic.ck.api.merchant.service.MerchantCommodityService;
import com.vic.ck.api.merchant.service.MerchantService;
import com.vic.ck.api.personal.service.CustomerDiscountTicketService;
import com.vic.ck.api.platform.service.OrderService;
import com.vic.ck.api.system.service.AuthService;
import com.vic.ck.api.system.service.CustomerBalanceService;
import com.vic.ck.console.activity.service.DiscountCouponService;
import com.vic.ck.entity.*;
import com.vic.ck.entity.MerchantActivity.RuleDesc;
import com.vic.ck.vo.DiscountVo;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.naming.AuthenticationException;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 商品相关的业务数据.
 * 
 * @author VIC
 *
 *         后续要求： 1、直接买单 2、有优惠券 3、立减 有优惠券
 *
 */
@RestController
@RequestMapping("/api/business")
public class BusinessController extends BaseApiController {

    @Resource
    private MerchantActivityService merchantActivityService;

    @Resource
    private CustomerDiscountTicketService customerDiscountTicketService;

    @Resource
    private AuthService authService;

    @Resource
    private MerchantService merchantService;

    @Resource
    private OrderService orderService;

    @Resource
    private MerchantCommodityService merchantCommodityService;

    @Resource
    private CustomerBalanceService customerBalanceService;

    @Resource
    private DiscountCouponService yhqService;

    public static void main(String[] args) {
        Order order = new Order();
        String s = String.format("%s满减:满%d元减%d元;", order.getDiscountsDesc(), 100, 20);
        System.out.println(s);

        String s2 = MessageFormat.format("{0}满减:满{1}元减{2}元;",
                StringUtils.isEmpty(order.getDiscountsDesc()) ? "" : order.getDiscountsDesc(), 100, 20);
        System.out.println(s2);

        BigDecimal b = new BigDecimal(100)
                .multiply(new BigDecimal(8.5).divide(new BigDecimal(10)).setScale(2, BigDecimal.ROUND_HALF_UP));
        System.out.println(b);
    }

    /**
     * 用户领取 优惠券
     */
    @RequestMapping(value = "/receiveTicket", method = RequestMethod.POST)
    public Object receiveTicket(int userId, int discountCouponId) {

        PlatformActivityDiscountcoupon activity = yhqService.discountCouponById(discountCouponId);

        // getEnabled 优惠券状态：123 未开始、进行中、已结束
        if (activity == null || activity.getEnabled() != 2) {
            return resultError(ResultMsgEnum.NOTEXISTED_ACTIVITY);// 807
        }
        // getNum 数量
        if (activity.getNum() < 1) {// 领完了
            return resultError(ResultMsgEnum.NO_MORE_LIMINT_ACTIVITY);// 808
        }
        CustomerDiscountTicket t = customerDiscountTicketService.receiveTicket(activity, userId);
        return resultSuccess(t);
    }

    /**
     * 5.5-02 直接购买 · 直接购买不可使用优惠券 无满减
     * <p>
     * 1、直接买单
     *
     * @return
     * @throws AuthenticationException
     */
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    @Transactional
    public Object buy(int merchantId, int userId, double totalFee, String commodityId, int addressId,
                      Date planGuessTime, @RequestParam(defaultValue = "") String remark,
                      @RequestParam(defaultValue = "") String taste, @RequestParam(defaultValue = "") String peoplenum)
            throws AuthenticationException {
        checkLoggin(userId);
        Customer c = authService.findCustomerById(userId);
        if (c == null) {// 不存在的用户
            return resultError(ResultMsgEnum.NOTEXISTED_USER);
        }
        Merchant merchant = merchantService.getMerchantById(merchantId);
        if (merchant == null) {// 不存在的商家
            return resultError(ResultMsgEnum.NOTEXISTED_MERCHANT);
        }

        Order order = new Order(userId, merchantId, new BigDecimal(totalFee), commodityId, addressId, planGuessTime,
                remark, taste, peoplenum);
        order.setOrderType(1);// 直接购买
        order.setMobile(c.getMobile());
        order.setMerchantType(merchant.getType());// 商家类型
        order.setOrderImage(merchant.getOutPic());// 团购图片或商家图片
        order.setOrderName(merchant.getName());// 团购名称或商家名称

//        // 判断订单余额是否充足
//        if (!judgeBlance(order)) {
//            return resultError(ResultMsgEnum.BALANCE_NOT_ENOUGH);
//        }

        // 生成订单
        orderService.generateOrder(order);

        return resultSuccess(orderService.orderDetail(order.getOrderno()));
    }

    /**
     * 3.5-04 选择优惠券支付
     */
    @RequestMapping(value = "/reserve", method = RequestMethod.POST)
    @Transactional
    public Object reserve(int merchantId, int userId, DiscountVo discountVo, String mobile,
                          @RequestParam(defaultValue = "0") int ticketId, String commodityId, int addressId, Date planGuessTime,
                          @RequestParam(defaultValue = "") String remark, @RequestParam(defaultValue = "") String taste,
                          @RequestParam(defaultValue = "") String peoplenum) throws AuthenticationException {

        checkLoggin(userId);
        Customer c = authService.findCustomerById(userId);
        if (c == null) {// 不存在的用户
            return resultError(ResultMsgEnum.NOTEXISTED_USER);
        }
        Merchant merchant = merchantService.getMerchantById(merchantId);
        if (merchant == null) {// 不存在的商家
            return resultError(ResultMsgEnum.NOTEXISTED_MERCHANT);
        }
//        MerchantHotel hotel = merchantCommodityService.selectHotelById(hotelId);
//
//        if (hotel == null || hotel.getStatus() != 1) {
//            return resultError(ResultMsgEnum.NOTEXISTED_HOTEL);
//        }

        Order order = new Order(userId, merchantId, discountVo.getAmount(), ticketId, commodityId, addressId,
                planGuessTime, remark, taste, peoplenum);

//        // 立减优惠的一些计算
//        calcMinus(order);

        // 插入 订单折扣相关
        order.setTicketId(ticketId);
        order.setAmount(discountVo.getAmount());
        order.setTicketDiscountMoney(discountVo.getDiscountFee());
        order.setDiscountsDesc(discountVo.getDiscountsDesc());
        order.setOrderType(2);// 订单类型 优惠券购买
        order.setMobile(StringUtils.isEmpty(mobile) ? c.getMobile() : mobile);
        order.setMerchantType(merchant.getType());// 商家类型

        orderService.generateOrder(order);
        return resultSuccess(orderService.orderDetail(order.getOrderno()));
    }

    /**
     * 选择优惠券后 计算金额
     */
    @RequestMapping(value = "/useDiscountCoupon", method = RequestMethod.POST)
    @Transactional
    public Object useDiscountCoupon(int userId, int merchantId, @RequestParam(defaultValue = "0") int ticketId,
                                    BigDecimal amount) throws AuthenticationException {
        checkLoggin(userId);
//        // 立减优惠的一些计算
//        calcMinus(order);

        // 判断折扣券情况 及处理
        if (ticketId != 0) {
            CustomerDiscountTicket ticket = customerDiscountTicketService.findById(ticketId);
            DiscountVo discountVo = new DiscountVo();
            if (ticket == null || ticket.getStatus() != 0) {// 不存在的折扣券或者非 未使用的
                return resultError(ResultMsgEnum.NOTEXISTED_TICKET);
            }
            if (DateUtils.truncatedCompareTo(ticket.getEndTime(), new Date(), Calendar.DATE) < 0) {// 优惠券过期
                return resultError(ResultMsgEnum.EXPIRE_TICKET);
            }

//			if (ticket.getMerchantId() != merchantId) {
//				return resultError(ResultMsgEnum.TICKET_MERCHANT_NOT_MATCH);
//			}
            //根据优惠券ID 先查询 是否对应商家 可以用
            PlatformActivityDiscountcoupon activity = yhqService.discountCouponById(ticketId);
            if (activity.getApply() != null && activity.getApply() == 2) {//1全部商家2指定商家
                //根据商家id 优惠券ID 查   当前商家 是否可用
                int countmerchantId = yhqService.discountCouponByMerchantId(ticketId, merchantId);
                if (countmerchantId == 0) {//0条数据 说明这个商家没有优惠券
                    return resultError(ResultMsgEnum.TICKET_MERCHANT_NOT_MATCH);
                }
            }

            // 根据优惠券类型 1 满减券 100：10
            if (ticket.getType() == 1) {
                // 100:10
                String[] manjian = ticket.getMinAmount().split(":");
                // 得到满额使用金额数
                BigDecimal man = new BigDecimal(manjian[0]);
                BigDecimal jian = new BigDecimal(manjian[1]);
                // 验证 总额 是否达到满减要求100
                if (amount.compareTo(man) == -1) {
                    return resultError(ResultMsgEnum.TICKET_AMOUNT_NOT_MATCH);
                }
                BigDecimal newAmount = amount.subtract(jian);
                discountVo.setTotalFee(amount);
                discountVo.setAmount(newAmount);
                discountVo.setDiscountFee(jian);
                discountVo.setDiscountsDesc(MessageFormat.format("{0}使用{1}满减券，抵现金{2}元;", "", man, jian));
                return resultSuccess(discountVo);
            }
            // 根据优惠券类型 2 折扣券 100：0.8
            if (ticket.getType() == 2) {
                // 100:10
                String[] manjian = ticket.getDiscount().split(":");
                // 得到满额使用金额数
                BigDecimal man = new BigDecimal(manjian[0]);
                BigDecimal zhekou = new BigDecimal(manjian[1]);
                // 验证 总额 是否达到满减要求100
                if (amount.compareTo(man) == -1) {
                    return resultError(ResultMsgEnum.TICKET_AMOUNT_NOT_MATCH);
                }
                BigDecimal newAmount = amount.multiply(zhekou).setScale(2, BigDecimal.ROUND_DOWN);
                discountVo.setTotalFee(amount);
                discountVo.setAmount(newAmount);
                discountVo.setDiscountFee(amount.subtract(newAmount));
                discountVo.setDiscountsDesc(MessageFormat.format("{0}使用{1}折扣券，抵现金{2}元;", "", ticket.getDiscount(),
                        amount.subtract(newAmount)));
                return resultSuccess(discountVo);
            }
            // 根据优惠券类型 3 随机减券 100:2:10
            if (ticket.getType() == 3) {
                // 100:10
                String[] manjian = ticket.getRandom().split(":");
                // 得到满额使用金额数
                BigDecimal man = new BigDecimal(manjian[0]);
                BigDecimal jian = new BigDecimal(manjian[1]);
                // 验证 总额 是否达到满减要求100
                if (amount.compareTo(man) == -1) {
                    return resultError(ResultMsgEnum.TICKET_AMOUNT_NOT_MATCH);
                }
                // 减掉随机金额
                BigDecimal newAmount = amount.subtract(jian);
                discountVo.setTotalFee(amount);
                discountVo.setAmount(newAmount);
                discountVo.setDiscountFee(jian);
                discountVo.setDiscountsDesc(MessageFormat.format("{0}使用{1}满减券，抵现金{2}元;", "", man, jian));
                return resultSuccess(discountVo);
            }

        }
        return resultError(ResultMsgEnum.NOTEXISTED_TICKET);

    }

    /**
     * 选择满减 支付
     */
    @RequestMapping(value = "/pullLimitn", method = RequestMethod.POST)
    @Transactional
    public Object pullLimitn(int merchantId, int userId, DiscountVo discountVo, String mobile, String commodityId,
                             int addressId, Date planGuessTime, @RequestParam(defaultValue = "") String remark,
                             @RequestParam(defaultValue = "") String taste, @RequestParam(defaultValue = "") String peoplenum)
            throws AuthenticationException {

        checkLoggin(userId);
        Customer c = authService.findCustomerById(userId);
        if (c == null) {// 不存在的用户
            return resultError(ResultMsgEnum.NOTEXISTED_USER);
        }
        Merchant merchant = merchantService.getMerchantById(merchantId);
        if (merchant == null) {// 不存在的商家
            return resultError(ResultMsgEnum.NOTEXISTED_MERCHANT);
        }

        Order order = new Order(userId, merchantId, discountVo.getAmount(), commodityId, addressId, planGuessTime,
                remark, taste, peoplenum);

        // 插入 订单折扣相关
        order.setAmount(discountVo.getAmount());
        order.setTicketDiscountMoney(discountVo.getDiscountFee());
        order.setDiscountsDesc(discountVo.getDiscountsDesc());
        order.setOrderType(3);// 订单类型 满减购买
        order.setMobile(StringUtils.isEmpty(mobile) ? c.getMobile() : mobile);
        order.setMerchantType(merchant.getType());// 商家类型

        orderService.generateOrder(order);
        return resultSuccess(orderService.orderDetail(order.getOrderno()));
    }

//  /**
//   * 3.5-03 购买团购 2、团购 只有优惠券
//   */
//  @RequestMapping(value = "/groupbuy", method = RequestMethod.POST)
//  @Transactional
//  public Object buyGroup(int merchantId, int userId, int groupbuyId, int quantity, double totalFee, int payType,
//      String mobile, @RequestParam(defaultValue = "") String remark, @RequestParam(defaultValue = "0") int ticketId)
//      throws AuthenticationException {
//      checkLoggin(userId);
//      Customer c = authService.findCustomerById(userId);
//      if (c == null) {// 不存在的用户
//          return resultError(ResultMsgEnum.NOTEXISTED_USER);
//      }
//      Merchant merchant = merchantService.getMerchantById(merchantId);
//      if (merchant == null) {// 不存在的商家
//          return resultError(ResultMsgEnum.NOTEXISTED_MERCHANT);
//      }
//      MerchantGroupbuy groupbuy = merchantCommodityService.selectGroupbuyById(groupbuyId);
//
//      if (groupbuy == null || groupbuy.getStatus() != 1) {
//          return resultError(ResultMsgEnum.NOTEXISTED_GROUPBUY);
//      }
//
//      // 验证总金额
//      boolean checkAmount =
//          groupbuy.getPrice().multiply(new BigDecimal(quantity)).setScale(2, BigDecimal.ROUND_HALF_UP)
//              .compareTo(new BigDecimal(totalFee).setScale(2, BigDecimal.ROUND_HALF_UP)) == 0;
//      if (!checkAmount) {
//          return resultError(ResultMsgEnum.ERROR_AMOUNT);
//      }
//
//      Order order = new Order(userId, merchantId, new BigDecimal(totalFee), payType, 2, quantity, "groupbuyId", remark);
//
//      // 判断折扣券情况 及处理
//      if (ticketId != 0) {
//          CustomerDiscountTicket ticket = customerDiscountTicketService.findById(ticketId);
//          if (ticket == null || ticket.getStatus() != 0) {// 不存在的折扣券或者非 未使用的
//              return resultError(ResultMsgEnum.NOTEXISTED_TICKET);
//          }
//          if (DateUtils.truncatedCompareTo(ticket.getEndTime(), new Date(), Calendar.DATE) < 0) {// 优惠券过期
//              return resultError(ResultMsgEnum.EXPIRE_TICKET);
//          }
//
//          if (ticket.getMerchantId() != merchantId) {
//              return resultError(ResultMsgEnum.TICKET_MERCHANT_NOT_MATCH);
//          }
//
//          if (ticket.getMinAmount() != null && ticket.getMinAmount().compareTo(new BigDecimal(totalFee)) > 0) {
//              return resultError(ResultMsgEnum.TICKET_AMOUNT_NOT_MATCH);
//          }
//          // 折扣券的一些计算
//          calcDiscountTicket(order, ticket);
//
//      }
////      // 判断订单余额是否充足
////      if (!judgeBlance(order)) {
////          return resultError(ResultMsgEnum.BALANCE_NOT_ENOUGH);
////      }
//
//      // 插入数订单
//      order.setOrderType(2);// 订单类型 团购
//      order.setMobile(StringUtils.isEmpty(mobile) ? c.getMobile() : mobile);
//      order.setMerchantType(merchant.getType());// 商家类型
//      order.setOrderImage(groupbuy.getIcon());// 团购图片或商家图片
//      order.setOrderName(groupbuy.getName());// 团购名称或商家名称
//
//      // 生成订单
//      orderService.generateOrder(order);
//      return resultSuccess(orderService.orderDetail(order.getOrderno()));
//  }

//    /**
//     * 3.5-05 支付金额计算. 由于本方法是后来追加的，故使用上面的订单中的验证逻辑，而未多加修改。
//     *
//     * @param orderType
//     *            1 -直接购买 2-团购 3-酒店预约 1、直接买单 只有满减 2、团购 只有优惠券 3、酒店 有立减 有优惠券
//     *
//     */
//    @RequestMapping(value = "/calcAmount", method = RequestMethod.POST)
//    public Object calcAmount(@RequestParam(defaultValue = "1") int orderType, int merchantId, double totalFee,
//        @RequestParam(defaultValue = "0") int ticketId) {
//        Merchant merchant = merchantService.getMerchantById(merchantId);
//        if (merchant == null) {// 不存在的商家
//            return resultError(ResultMsgEnum.NOTEXISTED_MERCHANT);
//        }
//        Order order = new Order(merchantId, new BigDecimal(totalFee));
//
//        if (orderType == 1) { // 直接买单 只有满减
//            // 判断满减情况及处理
//            judgeFullcut(order);
//        }
//
//        // 酒店才有立减
//        if (orderType == 3) {
//            // 立减优惠的一些计算
//            calcMinus(order);
//        }
//
//        // 判断折扣券情况 及处理 接购买不可使用优惠券。
//        if (orderType != 1 && ticketId != 0) {
//            CustomerDiscountTicket ticket = customerDiscountTicketService.findById(ticketId);
//            if (ticket == null || ticket.getStatus() != 0) {// 不存在的折扣券或者非 未使用的
//                return resultError(ResultMsgEnum.NOTEXISTED_TICKET);
//            }
//            if (DateUtils.truncatedCompareTo(ticket.getEndTime(), new Date(), Calendar.DATE) < 0) {// 优惠券过期
//                return resultError(ResultMsgEnum.EXPIRE_TICKET);
//            }
//
//            if (ticket.getMerchantId() != merchantId) {
//                return resultError(ResultMsgEnum.TICKET_MERCHANT_NOT_MATCH);
//            }
//
//            if (ticket.getMinAmount() != null && ticket.getMinAmount().compareTo(new BigDecimal(totalFee)) > 0) {
//                return resultError(ResultMsgEnum.TICKET_AMOUNT_NOT_MATCH);
//            }
//            // 折扣券的一些计算
//            calcDiscountTicket(order, ticket);
//        }
//
//        DiscountVo discountVo = new DiscountVo(new BigDecimal(totalFee), order.getAmount(),
//            new BigDecimal(totalFee).subtract(order.getAmount()).setScale(2, BigDecimal.ROUND_HALF_UP),
//            order.getDiscountsDesc());
//        return resultSuccess(discountVo);
//    }

    /**
     * 选择满减活动 计算金额
     */
    @RequestMapping(value = "/judgeFullcut", method = RequestMethod.POST)
    @Transactional
    public Object judgeFullcut(int userId, int merchantId, BigDecimal totalFee, int num)
            throws AuthenticationException {
        checkLoggin(userId);
        DiscountVo discountVo = new DiscountVo();
        List<MerchantActivity> merchantActivitys = merchantActivityService.currentActivities(merchantId);
        if (merchantActivitys == null || merchantActivitys.isEmpty()) {
            return resultSuccess(ResultMsgEnum.TICKET_AMOUNT_NOT_MATCH);
        }
        MerchantActivity merchantActivity = merchantActivitys.get(0);
        // 满减活动类型 1满额度减 2满额度打折 3满数量减 4满数量打折
        if (merchantActivity.getTypes() == 1) {// 当前存在满减活动
            RuleDesc r = merchantActivity.findRuleDesc(totalFee);
            if (r != null) {
                discountVo.setDiscountFee(new BigDecimal(r.getMinus()));// 订单的减少金额
                discountVo.setTotalFee(totalFee);
                BigDecimal amount = totalFee.subtract(new BigDecimal(r.getMinus())).setScale(2,
                        BigDecimal.ROUND_HALF_UP);
                discountVo.setAmount(amount);// 当前订单应付金额

                // 优惠描述
                discountVo.setDiscountsDesc(MessageFormat.format("{0}满减:满{1}元减{2}元;", "", r.getFill(), r.getMinus()));
            }
            return resultSuccess(discountVo);
        } else if (merchantActivity.getTypes() == 2) {// 当前存在满打折活动
            RuleDesc r = merchantActivity.findRuleDesc(totalFee);
            if (r != null) {
                discountVo.setDiscountFee(new BigDecimal(r.getMinus()));// 订单的折扣
                discountVo.setTotalFee(totalFee);
                BigDecimal amount = new BigDecimal(r.getFill()).multiply(new BigDecimal(r.getMinus())).setScale(2,
                        BigDecimal.ROUND_DOWN);
                discountVo.setAmount(amount);// 当前订单应付金额
                // 优惠描述
                discountVo.setDiscountsDesc(MessageFormat.format("{0}满打折:满{1}元打{2}折;", "", r.getFill(), r.getMinus()));
            }
            return resultSuccess(discountVo);
        } else if (merchantActivity.getTypes() == 3) {
            RuleDesc r = merchantActivity.findRuleDesc(num);
            if (r != null) {
                discountVo.setDiscountFee(new BigDecimal(r.getMinus()));// 订单的减少金额
                discountVo.setTotalFee(totalFee);
                BigDecimal amount = totalFee.subtract(new BigDecimal(r.getMinus())).setScale(2,
                        BigDecimal.ROUND_HALF_UP);
                discountVo.setAmount(amount);// 当前订单应付金额
                // 优惠描述
                discountVo.setDiscountsDesc(MessageFormat.format("{0}满减:满{1}件减{2}元;", "", r.getFill(), r.getMinus()));
            }
            return resultSuccess(discountVo);
        } else if (merchantActivity.getTypes() == 4) {// 当前存在满打折活动
            RuleDesc r = merchantActivity.findRuleDesc(num);
            if (r != null) {
                discountVo.setDiscountFee(new BigDecimal(r.getMinus()));// 订单的折扣
                discountVo.setTotalFee(totalFee);
                BigDecimal amount = new BigDecimal(r.getFill()).multiply(new BigDecimal(r.getMinus())).setScale(2,
                        BigDecimal.ROUND_DOWN);
                discountVo.setAmount(amount);// 当前订单应付金额
                // 优惠描述
                discountVo.setDiscountsDesc(MessageFormat.format("{0}满打折:满{1}元打{2}折;", "", r.getFill(), r.getMinus()));
            }
            return resultSuccess(discountVo);
        }
        return resultSuccess(discountVo);

    }

    /**
     * 3.5-05 支付金额计算. 如果前台不能计算 这里单纯的运算
     */
    @RequestMapping(value = "/calcAmount", method = RequestMethod.POST)
    public Object calcAmount(int merchantId, double totalFee, @RequestParam(defaultValue = "0") int ticketId) {

        return resultSuccess(totalFee);
    }

    /**
     * 选择支付类型 点击支付 页面
     *
     * @param
     */
    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public Object pay(Order order) {
        // 判断订单余额是否充足
        if (!judgeBlance(order)) {
            return resultError(ResultMsgEnum.BALANCE_NOT_ENOUGH);
        }
        //
        orderService.pay(order);

        // 修改订单状态 为1 支付完成 待消费
        orderService.alterOrderStatus(order.getOrderno(), 1);

        return resultSuccess();
    }

    /**
     * 当前商家 满减 立减优惠的计算
     */
    private void calcMinus(Order order) {
        MerchantActivity activity = merchantActivityService.currentTypeActivitie(order.getMerchantId(), 3);
        if (activity == null)
            return;
        BigDecimal amount = order.getTotalFee().subtract(activity.getMinus()).setScale(2, BigDecimal.ROUND_HALF_UP);
        // 万一减少成负数呢
        amount = amount.compareTo(BigDecimal.ZERO) > 0 ? amount : BigDecimal.ZERO;
        order.setAmount(amount);// 当前订单应付金额
        // TODO 立减为后续加的功能，故未在订单中额外追加字段，但是会计入优惠描述
        // 优惠描述
        order.setDiscountsDesc(MessageFormat.format("{0}立减金额{1}元;", order.getDiscountsDesc(), amount));

    }

    /**
     * 判断当前余额是否足够支付
     */
    private boolean judgeBlance(Order order) {

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

}
