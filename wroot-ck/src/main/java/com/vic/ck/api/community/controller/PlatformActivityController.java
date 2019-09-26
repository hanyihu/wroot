package com.vic.ck.api.community.controller;

import com.vic.base.BaseApiController;
import com.vic.base.BaseApiLookup;
import com.vic.base.model.BaseModel;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.community.service.PhoneChargeBalanceService;
import com.vic.ck.api.community.service.PlatformActivityService;
import com.vic.ck.api.constant.FinalFiledParams;
import com.vic.ck.api.constant.ResultMsgEnum;
import com.vic.ck.api.system.service.CustomerBalanceService;
import com.vic.ck.console.customer.service.PhoneChargeFetchMoneyService;
import com.vic.ck.console.statistics.service.ActivitySurveyRedpacketService;
import com.vic.ck.entity.ActivitySurveyRedpacket;
import com.vic.ck.entity.PhoneChargeBalance;
import com.vic.ck.entity.PhoneChargeFetchMoney;
import com.vic.ck.entity.PlatformActivity;
import com.vic.ck.entity.PlatformActivity.PhoneChargeLevel;
import com.vic.ck.vo.PhoneChargeStaticticsVo;
import com.vic.wroot.common.util.JedisUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.naming.AuthenticationException;
import java.math.BigDecimal;
import java.util.List;

/**
 * 社区 活动相关
 *
 * @author VIC
 */
@RestController
@RequestMapping("/api/activity")
public class PlatformActivityController extends BaseApiController {

    @Resource
    private PlatformActivityService platformActivityService;

    @Resource
    private ActivitySurveyRedpacketService activitySurveyRedpacketService;

    @Resource
    private CustomerBalanceService customerBalanceService;

    @Resource
    private PhoneChargeBalanceService phoneChargeBalanceService;

    @Resource
    private PhoneChargeFetchMoneyService phoneChargeFetchMoneyService;

    /**
     * 活动说明
     * 1 活动列表-每种只能同时存在一种
     * 2 活动详情
     * 3 红包活动的领奖记录 15条 从用户余额记录表查询 和活动没有关联 是因为红包活动基本不会变
     * 4 查询可领取红包：昨日是否消费 传入当前红包活动的id
     * 5 领取红包活动的红包
     * 6 前15名枪到话费的用户
     * 7 抢话费的一些数据统计
     * 8 抢话费
     * 9 话费充值申请 - 当时就该减掉余额 不通过的时候再返回
     * 10 我的话费申请记录
     */
    /**
     * 4.2-01 当前活动列表 每种类型最多取一条
     */
    @RequestMapping(value = "/activities", method = RequestMethod.GET)
    public Object activities() {
        List<PlatformActivity> data = platformActivityService.currentAcitivities();
        return resultSuccess(data);
    }

    /**
     * 4.2-02活动详情
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object detail(@PathVariable int id, @RequestParam(defaultValue = "0") int userId) {
        PlatformActivity data = platformActivityService.activityDetail(id, userId);
        return resultSuccess(data);
    }

    /**
     * 4.2-03 红包活动的领奖记录 15条 暂时直接从用户余额记录表查询而没有和活动没有关联 是因为红包活动基本不会变
     */
    @RequestMapping(value = "/redpacketRecords", method = RequestMethod.GET)
    public Object redpacketRecords() {
        List<BaseModel> data = platformActivityService.redpacketRecords();
        return resultSuccess(data);
    }

    /**
     * 4.2-04 查询可领取红包：昨日是否消费 当前活动是否存在
     */
    @RequestMapping(value = "/checkRedpacket", method = RequestMethod.GET)
    public Object checkRedpacket(int userId, int activityId) throws AuthenticationException {
        checkLoggin(userId);
        //先从缓存中取
        BigDecimal cache = platformActivityService.getUserRedPacket(userId);
        if (cache != null && cache.compareTo(BigDecimal.ZERO) > 0) {
            return resultSuccess(cache);
        }

        PlatformActivity a = platformActivityService.findById(activityId);
        if (a == null || a.getType() != 1 || a.getEnabled() != 1) {
            return resultError(ResultMsgEnum.NOTEXISTED_ACTIVITY);
        }
        // 判断今日是否领取了红包
        boolean isReceived = platformActivityService.isReceived(userId);
        if (isReceived) {
            return resultError(ResultMsgEnum.ALREADY_RECEIVE_REDPACKET);
        }

        // 判断昨日是否消费
        boolean isConsume = platformActivityService.isConsume(userId);
        if (!isConsume) {
            return resultError(ResultMsgEnum.NOT_CONSUM_YESTODAY);
        }
        ActivitySurveyRedpacket survey = activitySurveyRedpacketService.findYestodayRedpacket();
        if (survey == null || survey.getEnabled() != 1
                || survey.getReceiveAmount().compareTo(survey.getTotalAmount()) >= 0) {// 没有红包 或者红包被领完了 或者 钱不足了
            return resultError(ResultMsgEnum.NONE_REDPACKTE);
        }
        BigDecimal redpacket = platformActivityService.generateRedpacket(userId, survey);
        // 生成后再判断一次
        if (redpacket == null || redpacket.compareTo(BigDecimal.ZERO) <= 0) {
            return resultError(ResultMsgEnum.NONE_REDPACKTE);
        }
        return resultSuccess(redpacket);
    }

    /**
     * 4.2-05 领取红包活动的红包
     */
    @RequestMapping(value = "/receiveRedpacket", method = RequestMethod.POST)
    public Object receiveRedpacket(int userId) {
        // 判断今日是否领取了红包
        boolean isReceived = platformActivityService.isReceived(userId);
        if (isReceived) {
            return resultError(ResultMsgEnum.ALREADY_RECEIVE_REDPACKET);
        }
        BigDecimal redpacket = platformActivityService.getUserRedPacket(userId);
        if (redpacket == null) {
            return resultError(ResultMsgEnum.NONE_REDPACKTE);
        }
        // 增加用户余额 删除红包的缓存
        customerBalanceService.addBalance(redpacket, FinalFiledParams.BALANCE_REDPACKET, userId);
        JedisUtil.del(platformActivityService.getRedpacketRedisKey(userId));
        return resultSuccess(redpacket);
    }

    /**
     * 4.2-06 15条话费领取记录
     */
    @RequestMapping(value = "/phoneChargeRecords", method = RequestMethod.GET)
    public Object phoneChargeRecords() {
        List<BaseModel> data = platformActivityService.phoneChargeRecords();
        return resultSuccess(data);
    }

    /**
     * 4.2-07 抢话费的一些数据统计
     */
    @RequestMapping(value = "/phoneChargeStatictics", method = RequestMethod.GET)
    public Object phoneChargeStatictics(int userId, int activityId) {
        PhoneChargeStaticticsVo data = platformActivityService.phoneChargeStatictics(userId);
        if (data != null) {
            int currentRecommentNum = platformActivityService.currentActivityRecommentNumber(activityId, userId);
            data.setCurrentRecommentNum(currentRecommentNum);
        }
        return resultSuccess(data);
    }

    /**
     * 4.2-08 抢话费-打开对应的红包
     *
     * @throws AuthenticationException
     */
    @RequestMapping(value = "/rob", method = RequestMethod.POST)
    public Object rob(int userId, int activityId, int level) throws AuthenticationException {
        checkLoggin(userId);
        PlatformActivity a = platformActivityService.activityDetail(activityId, userId);
        // 不存在的活动 或者不合法的活动
        if (a == null || a.getEnabled() != 1 || a.getType() != 2 || CollectionUtils.isEmpty(a.getPhoneChargeLevels())) {
            return resultError(ResultMsgEnum.NOTEXISTED_ACTIVITY);
        }
        List<PhoneChargeLevel> levels = a.getPhoneChargeLevels();
        PhoneChargeLevel cur = null;
        for (PhoneChargeLevel l : levels) {
            if (l.getLevel() == level) {
                cur = l;
                break;
            }
        }
        // 不存在的抢话费level(红包)
        if (cur == null) {
            return resultError(ResultMsgEnum.NOTEXISTED_REDPACKET);
        }
        if (cur.getCanOpendTime() > 0 && cur.getOpenedTime() >= cur.getCanOpendTime()) {
            return resultError(ResultMsgEnum.ALREADY_RECEIVE_REDPACKET);
        }
        if (!cur.isCanOpen()) {
            return resultError(ResultMsgEnum.CANNOT_OPEN_REDPACKET);
        }
        if (cur.isCanOpen()) {// 抢话费去
            BigDecimal redpacket = platformActivityService.rob(userId, level, a);
            if (redpacket != null) {
                return resultSuccess(redpacket);
            }
        }
        return resultError(ResultMsgEnum.CANNOT_OPEN_REDPACKET);
    }

    /**
     * 4.2-09 充话费申请
     */
    @RequestMapping(value = "/fetch", method = RequestMethod.POST)
    public Object fetch(int userId, double money, String mobile) throws AuthenticationException {
        checkLoggin(userId);
        BigDecimal balance = BigDecimal.ZERO;
        PhoneChargeBalance b = phoneChargeBalanceService.getCustomerBalance(userId);
        if (b != null) {
            balance = b.getBalance();
        }
        if (balance.compareTo(new BigDecimal(money)) < 0) {// 余额不足
            return resultError(ResultMsgEnum.BALANCE_NOT_ENOUGH);
        }
        phoneChargeBalanceService.fetchPhoneCharge(userId, new BigDecimal(mobile).setScale(2, BigDecimal.ROUND_UP), mobile);
        return resultSuccess();
    }

    /**
     * 4.2-10 充话费申请记录
     *
     * @throws AuthenticationException
     */
    @RequestMapping(value = "/fetchRecords", method = RequestMethod.GET)
    public Object fetchRecords(BaseApiLookup lookup) throws AuthenticationException {
        checkLoggin(lookup);
        PageInfo<PhoneChargeFetchMoney> data = phoneChargeFetchMoneyService.list(lookup);
        return resultSuccess(data);
    }

}
