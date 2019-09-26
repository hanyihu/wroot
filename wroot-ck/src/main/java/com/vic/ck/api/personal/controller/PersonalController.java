package com.vic.ck.api.personal.controller;

import com.vic.base.BaseApiController;
import com.vic.base.BaseApiLookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.constant.FinalFiledParams;
import com.vic.ck.api.constant.ResultMsgEnum;
import com.vic.ck.api.personal.service.*;
import com.vic.ck.api.platform.lookup.CommodityLookup;
import com.vic.ck.api.platform.lookup.MerchantLookup;
import com.vic.ck.api.platform.service.*;
import com.vic.ck.api.system.service.AuthService;
import com.vic.ck.api.system.service.CustomerBalanceService;
import com.vic.ck.entity.*;
import com.vic.ck.vo.RecommentSurveyVo;
import com.vic.ck.vo.RecommentVo;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.naming.AuthenticationException;
import java.math.BigDecimal;

/**
 * 个人中心的一些东西
 *
 * @author VIC
 */
@RestController
@RequestMapping("/api/personal")
public class PersonalController extends BaseApiController {

    private Logger logger = LoggerFactory.getLogger(PersonalController.class);

    @Resource
    private AuthService authService;

    @Resource
    private PersonalService personalService;

    @Resource
    private CustomerBalanceService customerBalanceService;

    @Resource
    private CollectionService collectionService;

    @Resource
    private CustomerDiscountTicketService customerDiscountTicketService;

    @Resource
    private OrderService orderService;

    @Resource
    private OrderCommentService orderCommentService;

    @Resource
    private CustomerGroupTicketService customerGroupTicketService;

    @Resource
    private PlatformCooperationService platformCooperationService;

    @Resource
    private JpushMsgService jpushMsgService;

    @Resource
    private LookNoterService lookNoterService;

    @Resource
    private SelectNoterService selectNoterService;

    @Resource
    private HotSelectService hotSelectService;

    public static void main(String[] args) {
        int status = 0;//订单状态  0-待支付 1-待消费 2-待确认 3- 待入住 4-待评价 (已完成) 5-已评价 6-退款中 7-已退款 8-已取消
        int orderType = 2; //订单类型 1-直接购买 2-团购 3-酒店预订
        //直接购买不存在退款 、团购 待消费的时候才能退  、酒店预订 待确认和待入住才能退款
        if (orderType == 1 || (orderType == 2 && status != 1) || (orderType == 3 && (status != 2 && status != 3))) {
            System.out.println(111);
        } else {
            System.out.println(333);
        }
    }

    /**
     * 2.08 编辑个人资料
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Object edit(Customer customer) {
        authService.updateCustomer(customer);
        return resultSuccess(authService.findCustomerById(customer.getId()));
    }

    /**
     * 2.09 我的收货地址列表 deliveries
     *
     * @throws AuthenticationException
     */
    @RequestMapping(value = "/deliveries", method = RequestMethod.GET)
    public Object deliveries(BaseApiLookup lookup) throws AuthenticationException {
        checkLoggin(lookup);
        PageInfo<CustomerDeliveryAddress> data = personalService.deliveries(lookup);
        return resultSuccess(data);
    }

    /**
     * 2.10 新增收货地址
     *
     * @throws AuthenticationException
     */
    @RequestMapping(value = "/delivery/add", method = RequestMethod.POST)
    public Object delivery(CustomerDeliveryAddress address) throws AuthenticationException {
        checkLoggin(address.getCustomerId());
        personalService.addDelivery(address);
        return resultSuccess();
    }

    /**
     * 2.10-2  修改收货地址
     *
     * @throws AuthenticationException
     */
    @RequestMapping(value = "/delivery/update", method = RequestMethod.POST)
    public Object deliveryUpdate(CustomerDeliveryAddress address) throws AuthenticationException {
        personalService.updateDelivery(address);
        return resultSuccess();
    }

    /**
     * 2.11 删除收货地址
     *
     * @throws AuthenticationException
     */
    @RequestMapping(value = "/delivery/{id}/delete", method = RequestMethod.POST)
    public Object deliveryDelete(@PathVariable int id) throws AuthenticationException {
        personalService.deleteDelivery(id);
        return resultSuccess();
    }

    /**
     * 2.12 我的余额 + 我的佣金
     */
    @RequestMapping(value = "/balance", method = RequestMethod.GET)
    public Object balance(@RequestParam int userId) {
        BigDecimal balance = personalService.balance(userId);
        return resultSuccess(balance);
    }

    /**
     * 2.13 我的余额明细  + (无佣金明细表，暂时用做佣金明细)
     *
     * @throws AuthenticationException
     */
    @RequestMapping(value = "/balances", method = RequestMethod.GET)
    public Object balances(BaseApiLookup lookup) throws AuthenticationException {
        checkLoggin(lookup);
        PageInfo<CustomerBalanceRecord> datas = customerBalanceService.balanceRecords(lookup);

        return resultSuccess(datas);
    }

    /**
     * 2.14 我的银行卡列表
     */
    @RequestMapping(value = "/bankCards", method = RequestMethod.GET)
    public Object bankCards(BaseApiLookup lookup) throws AuthenticationException {
        checkLoggin(lookup);
        PageInfo<CustomerBankCard> datas = personalService.bankCards(lookup);

        return resultSuccess(datas);
    }

    /**
     * 2.15 绑定银行卡 bankCard
     *
     * @throws AuthenticationException
     */
    @RequestMapping(value = "/bankCard/add", method = RequestMethod.POST)
    public Object bankCard(CustomerBankCard bankCard) throws AuthenticationException {
        checkLoggin(bankCard.getCustomerId());
        personalService.addBankCard(bankCard);
        return resultSuccess();
    }

    /**
     * 2.16 删除银行卡 /bankCard/{id}/delete`
     */
    @RequestMapping(value = "/bankCard/{id}/delete", method = RequestMethod.POST)
    public Object deleteBankCard(@PathVariable int id) {
        personalService.deleteBankCard(id);
        return resultSuccess();
    }

    /**
     * 2.17 提现申请 fetch
     *
     * @throws AuthenticationException
     */
    @RequestMapping(value = "/fetch", method = RequestMethod.POST)
    public Object fetch(CustomerFetchMoney money) throws AuthenticationException {
        checkLoggin(money.getCustomerId());

        //验证卡号和用户id
        if (!personalService.judgeCustomerCard(money)) {
            return resultError(ResultMsgEnum.NOTEXISTED_CARD);
        }
        BigDecimal balance = customerBalanceService.customerBalance(money.getCustomerId());
        if (balance.compareTo(money.getMoney()) < 0) {// 余额不足
            return resultError(ResultMsgEnum.BALANCE_NOT_ENOUGH);
        }
        personalService.fetch(money);
        return resultSuccess();
    }

    /**
     * 2.25 我的团购券列表
     */
    @RequestMapping(value = "/ticket/groupbuys", method = RequestMethod.GET)
    public Object ticketGroupbuys(BaseApiLookup lookup) throws AuthenticationException {
        checkLoggin(lookup);
        PageInfo<CustomerGroupTicket> data = customerGroupTicketService.list(lookup);
        return resultSuccess(data);
    }

    /**
     * 2.26我的优惠券列表
     */
    @RequestMapping(value = "/ticket/discounts", method = RequestMethod.GET)
    public Object ticketDiscounts(BaseApiLookup lookup) throws AuthenticationException {
        checkLoggin(lookup);
        PageInfo<CustomerDiscountTicket> data = customerDiscountTicketService.list(lookup);
        return resultSuccess(data);
    }

    /**
     * 2.27 我的积分 score
     *
     * @throws AuthenticationException
     */
    @RequestMapping(value = "/score", method = RequestMethod.GET)
    public Object score(int userId) throws AuthenticationException {
        checkLoggin(userId);
        Integer score = authService.getScore(userId);
        return resultSuccess(score);
    }

    /**
     * 2.28 我的积分明细
     *
     * @throws AuthenticationException
     */
    @RequestMapping(value = "/scores", method = RequestMethod.GET)
    public Object scores(BaseApiLookup lookup) throws AuthenticationException {
        checkLoggin(lookup);
        PageInfo<CustomerScoreRecord> data = personalService.scores(lookup);
        return resultSuccess(data);
    }

    /**
     * 2.29我的收藏-商家 团购
     *
     * @throws AuthenticationException
     */
    @RequestMapping(value = "/collections", method = RequestMethod.GET)
    public Object collections(CommodityLookup lookup) throws AuthenticationException {
        checkLoggin(lookup);
        PageInfo<CustomerCollection> data = collectionService.collections(lookup);
        return resultSuccess(data);
    }

    /**
     * 2.30 取消收藏
     */
    @RequestMapping(value = "/collection/{id}/delete", method = RequestMethod.POST)
    public Object deleteCollections(@PathVariable int id) throws AuthenticationException {
        collectionService.deleteCollection(id);
        return resultSuccess();
    }

    /**
     * 2.31 新增收藏|| 取消收藏
     *
     * @throws AuthenticationException
     */
    @RequestMapping(value = "/collection/add", method = RequestMethod.POST)
    public Object collection(CustomerCollection collection) throws AuthenticationException {
        checkLoggin(collection.getCustomerId());
        if (collectionService.isCollected(collection.getType(), collection.getTargetId(), collection.getCustomerId())) {
            //return resultError(ResultMsgEnum.ALREADY_COLLECT);
            //修改为：把已经收藏的删除
            collectionService.deleteCollection(collection.getType(), collection.getTargetId(), collection.getCustomerId());
        } else {
            collectionService.addCollection(collection);
        }
        return resultSuccess();
    }

    /**
     * 2.32 我的推荐概况
     */
    @RequestMapping(value = "/recomment/survey", method = RequestMethod.GET)
    public Object recommentSurvey(int userId) throws AuthenticationException {
        checkLoggin(userId);
        RecommentSurveyVo data = personalService.recommentSurvey(userId);
        return resultSuccess(data);
    }

    /**
     * 2.33 推荐的会员列表
     * type 1-用户 2-商户
     */
    @RequestMapping(value = "/recomment/users", method = RequestMethod.GET)
    public Object recomments(BaseApiLookup lookup) throws AuthenticationException {
        checkLoggin(lookup);
        PageInfo<RecommentVo> data = personalService.recomments(lookup);
        return resultSuccess(data);
    }

    /**
     * 2.34 推荐的奖励列表
     * type 1-用户 2-商户
     */
    @RequestMapping(value = "/recomment/rewards", method = RequestMethod.GET)
    public Object recommentRewards(BaseApiLookup lookup) throws AuthenticationException {
        checkLoggin(lookup);
        if (lookup.getType() == 1) {
            lookup.setType(FinalFiledParams.BALANCE_RECOMMENT_BUY);
        } else if (lookup.getType() == 2) {
            lookup.setType(FinalFiledParams.BALANCE_RECOMMENT_SELL);
        } else {
            lookup.setType(999);
        }
        PageInfo<CustomerBalanceRecord> data = customerBalanceService.balanceRecords(lookup);
        return resultSuccess(data);
    }

    /**
     * 2.35 申请合作
     */
    @RequestMapping(value = "/cooperation/add", method = RequestMethod.POST)
    public Object cooperation(PlatformCooperation cooperation) {
        platformCooperationService.insert(cooperation);
        return resultSuccess();
    }

    /**
     * 2.36 我的消息
     * status 1-用户 2-商户
     * type 1-好友信息 2-系统信息
     */
    @RequestMapping(value = "/msgs", method = RequestMethod.GET)
    public Object msgs(BaseApiLookup lookup) throws AuthenticationException {
        checkLoggin(lookup);
        PageInfo<JpushMsg> data = jpushMsgService.list(lookup);
        return resultSuccess(data);
    }

    /**
     * 2.37 消息详情(置为已读)
     */
    @RequestMapping(value = "/msg/{id}", method = RequestMethod.GET)
    public Object msgs(@PathVariable int id) {
        JpushMsg data = jpushMsgService.findById(id);
        return resultSuccess(data);
    }

    /**
     * 2.38 删除消息
     */
    @RequestMapping(value = "/msg/{id}/delete", method = RequestMethod.POST)
    public Object deleteMsg(@PathVariable int id) {
        jpushMsgService.update(new JpushMsg().setId(id).setIsDelete(1));
        return resultSuccess();
    }

    /**
     * 浏览记录
     */
    @RequestMapping(value = "/browsingHistory", method = RequestMethod.GET)
    public Object browsingHistory(BaseApiLookup lookup) throws AuthenticationException {
        checkLoggin(lookup);
        PageInfo<LookNoter> data = lookNoterService.lljlservice(lookup);
        return resultSuccess(data);
    }

    /**
     * 搜索记录
     *
     * @param
     */
    @RequestMapping(value = "/searchHistory", method = RequestMethod.GET)
    public Object searchHistory(BaseApiLookup lookup) throws AuthenticationException {
        checkLoggin(lookup);
        PageInfo<SelectNoter> data = selectNoterService.lljlservice(lookup);
        return resultSuccess(data);
    }

    /**
     * 热门商家搜索
     *
     * @param
     */
    @RequestMapping(value = "/hotMerchant", method = RequestMethod.GET)
    public Object hotMerchant(BaseApiLookup lookup) throws AuthenticationException {
//		checkLoggin(lookup);
        PageInfo<Merchant> data = hotSelectService.rmssService(lookup);
        return resultSuccess(data);
    }

    /**
     * 商品分类+模块商品分类+平台特色分类
     *
     * @param
     */
    @RequestMapping(value = "/commodityClassification", method = RequestMethod.GET)
    public Object commodityClassification(BaseApiLookup lookup) throws AuthenticationException {
//		checkLoggin(lookup);
        PageInfo<CommodityCategory> data = hotSelectService.spflService(lookup);
        return resultSuccess(data);
    }

    /**
     * 商品分类+模块商品分类+平台特色分类
     *
     * @param
     */
    @RequestMapping(value = "/moduleCommodityClassification", method = RequestMethod.GET)
    public Object moduleCommodityClassification(BaseApiLookup lookup) throws AuthenticationException {
//		checkLoggin(lookup);
        PageInfo<CommodityCategory> data = hotSelectService.mkspflService(lookup);
        return resultSuccess(data);
    }

    /**
     * 商品分类+模块商品分类+平台特色分类
     *
     * @param
     */
    @RequestMapping(value = "/featureClassification", method = RequestMethod.GET)
    public Object featureClassification(BaseApiLookup lookup) throws AuthenticationException {
//		checkLoggin(lookup);
        PageInfo<CommodityCategory> data = hotSelectService.tsflService(lookup);
        return resultSuccess(data);
    }

    /**
     * 商品查询——优惠产品：在优惠券时间内的
     *
     * @param
     */
    @RequestMapping(value = "/discountsCommodity", method = RequestMethod.GET)
    public Object discountsCommodity(BaseApiLookup lookup) throws AuthenticationException {
//		checkLoggin(lookup);
        PageInfo<Commodity> data = hotSelectService.yhcpService(lookup);
        return resultSuccess(data);
    }

    /**
     * 附近商家列表
     *
     * @throws AuthenticationException
     */
    @RequestMapping(value = "/nearbyMerchant", method = RequestMethod.GET)
    public Object nearbyMerchant(MerchantLookup lookup) throws AuthenticationException {
//		checkLoggin(lookup);
        PageInfo<Merchant> data = hotSelectService.fjsjService(lookup);
        return resultSuccess(data);
    }

    /**
     * 积分兑换商品
     *
     * @param lookup
     * @return
     * @throws AuthenticationException
     */
    @ApiOperation(value = "积分兑换商品", notes = "积分兑换商品", produces = "application/josn")
    @RequestMapping(value = "/integralConversion", method = RequestMethod.GET)
    public Object integralConversion(BaseApiLookup lookup) throws AuthenticationException {
//		checkLoggin(lookup);
        PageInfo<SwapGoods> data = personalService.jfdhService(lookup);
        return resultSuccess(data);
    }

    /**
     * 消息提醒 查询            用户推送消息表
     *
     * @param lookup
     * @return
     * @throws AuthenticationException
     */
    @RequestMapping(value = "/messageAlert", method = RequestMethod.GET)
    public Object messageAlert(BaseApiLookup lookup) throws AuthenticationException {
//		checkLoggin(lookup);
        PageInfo<CustomerMsg> data = personalService.xxtxService(lookup);
        return resultSuccess(data);
    }


}
