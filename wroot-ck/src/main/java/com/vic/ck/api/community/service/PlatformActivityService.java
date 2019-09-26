package com.vic.ck.api.community.service;

import com.vic.base.BaseService;
import com.vic.base.model.BaseModel;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.community.mapper.ActivitySurveyPhoneMoneyMapper;
import com.vic.ck.api.community.mapper.PhoneChargeBalanceMapper;
import com.vic.ck.api.community.mapper.PlatformActivityMapper;
import com.vic.ck.api.constant.FinalFiledParams;
import com.vic.ck.api.system.service.AuthService;
import com.vic.ck.api.system.service.CustomerBalanceService;
import com.vic.ck.console.statistics.service.ActivitySurveyRedpacketService;
import com.vic.ck.entity.*;
import com.vic.ck.entity.PlatformActivity.PhoneChargeLevel;
import com.vic.ck.vo.PhoneChargeStaticticsVo;
import com.vic.wroot.common.attachment.service.AttachmentService;
import com.vic.wroot.common.tool.RedpacketTools;
import com.vic.wroot.common.util.JedisUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 平台活动表
 */
@Service
public class PlatformActivityService extends BaseService {


    @Resource
    private PlatformActivityMapper platformActivityMapper;

    @Resource
    private AuthService authService;

    @Resource
    private PhoneChargeBalanceMapper phoneChargeBalanceRecordMapper;

    @Resource
    private ActivitySurveyRedpacketService activitySurveyRedpacketService;

    @Resource
    private ActivitySurveyPhoneMoneyMapper activitySurveyPhoneMoneyMapper;

    @Resource
    private PhoneChargeBalanceService phoneChargeBalanceService;

    @Resource
    private AttachmentService attachmentService;

    @Resource
    private CustomerBalanceService customerBalanceService;

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {

            BigDecimal redPacket = new BigDecimal(
                    RedpacketTools.getFirstRedpacket(300, 100)).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_UP);
            System.out.println(redPacket);
        }
    }

    /**
     * 查询列表
     */
    public PageInfo<PlatformActivity> list(Lookup lookup) {
        startPage(lookup);
        List<PlatformActivity> datas = platformActivityMapper.list(lookup);
        return PageInfo.instance(datas, lookup);
    }

    /**
     * 根据主键查询
     */
    public PlatformActivity findById(int id) {
        return platformActivityMapper.findById(id);
    }

    /**
     * 新增
     */
    @Transactional
    public int insert(PlatformActivity entity) {
        attachmentService.addAttachmengFromObj(entity);
        int result = platformActivityMapper.insert(entity);
        //抢话费  则生成抢话费活动概况(5个级别的红包概括)
        if (entity.getType() == 2 && entity.getPhoneChargeLevels() != null) {
            activitySurveyPhoneMoneyMapper.generatorSurveyPhoneMoney(entity);
        }

        return result;
    }

    /**
     * 修改
     */
    @Transactional
    public int update(PlatformActivity entity) {
        PlatformActivity old = findById(entity.getId());
        attachmentService.HandleOldAndNowAttachment(old, entity);
        return platformActivityMapper.update(entity);
    }

    /***
     * 删除
     */
    @Transactional
    public int delete(int... ids) {
        List<PlatformActivity> list = platformActivityMapper.findByIds(ids);
        attachmentService.deleteAttachmengFromObj(list);
        return platformActivityMapper.delete(ids);
    }

    /**
     * 当前活动 每个type最多取一条
     */
    public List<PlatformActivity> currentAcitivities() {
        return platformActivityMapper.currentAcitivities();
    }

    /***
     * 当前类型的活动
     * @param ：1-抢红包 2-抢话费 3-推荐为商家
     */
    public PlatformActivity currentAcitivityByType(int type) {
        return platformActivityMapper.currentAcitivityByType(type);
    }

    /**
     * 活动详情
     */
    public PlatformActivity activityDetail(int id, int userId) {
        PlatformActivity a = findById(id);
        if (a != null) {
            // 1-抢红包 2-抢话费
            if (1 == a.getType()) {
                // 暂无逻辑
            }
            if (2 == a.getType() && userId != 0) {// 查询用户是否领取抢话费活动
                List<PhoneChargeLevel> levels = a.getPhoneChargeLevels();
                if (levels != null && levels.size() == 5) {// 理论不应该为null

                    // 当前用户推荐的注册人数
                    int recommentNumer = currentActivityRecommentNumber(id, userId);

                    /** 之前的逻辑为红包领完就没了，后面的需求修改为 红包领完了 循环继续领取，故此处代码重新修改*/
                    boolean loop = true;

                    if (!loop) { //红包不循环领取的时候
                        PhoneChargeLevel currentLevel = a.findPhoneChargeLevel(recommentNumer);
                        int level = 0;// 用户当前所处的级别
                        if (currentLevel != null) {
                            level = currentLevel.getLevel();
                        }

                        // 用户是否可以打开这个红包
                        a.setCanOpen(level);

                        // 用户在当前活动抢话费的记录
                        List<PhoneChargeBalanceRecord> records = phoneChargeBalanceRecords(id, userId);

                        if (records != null) {
                            for (PhoneChargeBalanceRecord br : records) {
                                for (PhoneChargeLevel l : levels) {
                                    // 已经打开过的红包 记录为打开过 不可重新打开
                                    if (br.getLevel() == l.getLevel()) {
                                        l.setOpened(true);
                                        l.setCanOpen(false);
                                    }
                                }
                            }
                        }
                    } else { //逻辑修改为红包可循环领取

                        a.initCanOpened(recommentNumer);
                        //获得用户在此活动中获取到的不同级别的红包的次数
                        List<PhoneChargeBalanceRecord> times = phoneChargeBalanceLevelTimes(id, userId);
                        for (PhoneChargeBalanceRecord t : times) {
                            for (PhoneChargeLevel l : levels) {
                                // 可打开次数大于已打开次数的方可打开
                                if (t.getLevel() == l.getLevel()) {
                                    l.setOpenedTime(t.getTime());
                                }
                            }
                        }
                    }

                }
            }
        }
        return a;
    }

    /**
     * 获得用户在此活动中获取到的不同级别的红包的次数
     */
    public List<PhoneChargeBalanceRecord> phoneChargeBalanceLevelTimes(int activityId, int userId) {
        return phoneChargeBalanceRecordMapper.phoneChargeBalanceLevelTimes(activityId, userId);
    }

    /**
     * 获得用户在此活动中获取到的余额
     */
    public List<PhoneChargeBalanceRecord> phoneChargeBalanceRecords(int activityId, int userId) {
        return phoneChargeBalanceRecordMapper.phoneChargeBalanceRecords(activityId, userId);
    }

    /**
     * 用户在当前活动时间内推荐注册多用户数
     */
    public int currentActivityRecommentNumber(int activityId, int userId) {
        return phoneChargeBalanceRecordMapper.currentActivityRecommentNumber(activityId, userId);
    }

    /**
     * 15条红包领取记录
     */
    public List<BaseModel> redpacketRecords() {
        return platformActivityMapper.redpacketRecords();
    }

    /**
     * 15条话费领取记录
     */
    public List<BaseModel> phoneChargeRecords() {
        return platformActivityMapper.phoneChargeRecords();
    }

    /**
     * 判断今日是否领取了红包
     */
    public boolean isReceived(int customerId) {
        return platformActivityMapper.isReceived(customerId);
    }

    /**
     * 用户昨日是否消费
     */
    public boolean isConsume(int customerId) {
        return platformActivityMapper.isConsume(customerId);
    }

    /**
     * 生成红包 在调用此方法前需要做前置判断 是否可生成 是否有剩余红包
     */
    @Transactional
    public BigDecimal generateRedpacket(int userId, ActivitySurveyRedpacket survey) {
        String key = getRedpacketRedisKey(userId);
        Object cache = JedisUtil.getObject(key);
        if (cache != null) {
            return (BigDecimal) JedisUtil.getObject(key);
        }

        BigDecimal amount = survey.getTotalAmount().subtract(survey.getReceiveAmount())
                .setScale(2, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(100));

        BigDecimal yesterdayAmount = platformActivityMapper.yesterdayAmount(userId);
        if (yesterdayAmount == null || yesterdayAmount.compareTo(BigDecimal.ZERO) <= 0) {
            return null;
        }
        // 获得红包金额 并存入REDIS
        BigDecimal redPacket = new BigDecimal(RedpacketTools.getFirstRedpacket(amount.intValue(), survey.getTotalNumber() - survey.getReceiveNumber(), yesterdayAmount.intValue()))
                .divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
        JedisUtil.setObject(key, redPacket, 60 * 60 * 24);
        // 更新红包的概况
        ActivitySurveyRedpacket entity = new ActivitySurveyRedpacket();
        entity.setId(survey.getId());
        entity.setReceiveAmount(survey.getReceiveAmount().add(redPacket));
        entity.setReceiveNumber(survey.getReceiveNumber() + 1);
        // 红包都发完了
        if (entity.getReceiveNumber() >= survey.getTotalNumber()) {
            entity.setEnabled(0);
        }
        activitySurveyRedpacketService.update(entity);
        return redPacket;
    }

    /**
     * 从缓存获得用户的红包
     */
    public BigDecimal getUserRedPacket(int userId) {
        String key = getRedpacketRedisKey(userId);
        return (BigDecimal) JedisUtil.getObject(key);
    }

    /**
     * 获得用户存在REDIS里的红包的key
     */
    public String getRedpacketRedisKey(int userId) {
        return new StringBuffer("redpacket-").append(userId).append("-")
                .append(DateFormatUtils.format(new Date(), "yyyy-MM-dd")).toString();
    }

    /***
     * 话费充值的一些统计
     */
    public PhoneChargeStaticticsVo phoneChargeStatictics(int customerId) {
        return platformActivityMapper.phoneChargeStatictics(customerId);
    }

    /**
     * 抢话费
     */
    public BigDecimal rob(int userId, int level, PlatformActivity a) {
        ActivitySurveyPhoneMoney survey = activitySurveyPhoneMoneyMapper.findByActivityIdAndLevel(a.getId(), level);

        if (survey == null) return null;

        BigDecimal amount = survey.getAvailableAmount().multiply(new BigDecimal(100));

        BigDecimal redPacket = new BigDecimal(
                RedpacketTools.getFirstRedpacket(amount.intValue(), 100 - survey.getReceiveNumber())).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_UP);
        ActivitySurveyPhoneMoney entity = new ActivitySurveyPhoneMoney();
        entity.setId(survey.getId());
        //修改话费统计的一些情况
        // 已经发出的金额
        entity.setReceiveAmount(survey.getReceiveAmount().add(redPacket));
        //已经领取的总人数
        entity.setReceiveNumber(survey.getReceiveNumber() + 1);
        //当前组已经领取的人数
        entity.setCurrentReceiveNumber(survey.getCurrentReceiveNumber() + 1);
        //当前组可使用金额
        entity.setAvailableAmount(survey.getAvailableAmount().subtract(redPacket));

        //人数发完 || 钱发完 || 可使用余额不足
        if (100 == entity.getReceiveNumber() || entity.getReceiveAmount().compareTo(survey.getGroupAmount()) >= 0 || entity.getAvailableAmount().compareTo(BigDecimal.ZERO) <= 0) {
            entity.setReceiveNumber(0);
            entity.setReceiveGroup(entity.getReceiveGroup() + 1);
            entity.setAvailableAmount(survey.getGroupAmount());
            entity.setCurrentReceiveNumber(0);
        }
        activitySurveyPhoneMoneyMapper.update(entity);

        // 余额入话费余额表
        phoneChargeBalanceService.addBalance(redPacket, 1, userId, "抢话费", a.getId(), level);
        return redPacket;
    }

    /**
     * 结算当前活动 把话费余额转入用户余额
     */
    @Transactional
    public void settlement() {
        List<PhoneChargeBalance> list = phoneChargeBalanceService.balances();
        if (list == null || list.isEmpty()) {
            return;
        }

        for (PhoneChargeBalance b : list) {
            BigDecimal balance = b.getBalance();
            Integer customerId = b.getCustomerId();
            if (balance == null || customerId == null) {
                continue;
            }
            //新增用户余额
            customerBalanceService.addBalance(balance, FinalFiledParams.BALANCE_TELEPHONE_REMAIN, customerId);

            //减去话费余额
            phoneChargeBalanceService.addBalance(balance.negate(), 3, customerId, "转入余额");
        }


    }

}
