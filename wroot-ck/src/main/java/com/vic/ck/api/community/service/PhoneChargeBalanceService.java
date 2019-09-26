package com.vic.ck.api.community.service;

import com.vic.base.BaseApiLookup;
import com.vic.base.BaseService;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.community.mapper.PhoneChargeBalanceMapper;
import com.vic.ck.console.customer.service.PhoneChargeFetchMoneyService;
import com.vic.ck.entity.PhoneChargeBalance;
import com.vic.ck.entity.PhoneChargeBalanceRecord;
import com.vic.ck.entity.PhoneChargeFetchMoney;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class PhoneChargeBalanceService extends BaseService {

    @Resource
    private PhoneChargeBalanceMapper phoneChargeBalanceMapper;

    @Resource
    private PhoneChargeFetchMoneyService phoneChargeFetchMoneyService;


    /**
     * 全部用户的余额
     */
    public List<PhoneChargeBalance> balances() {
        return phoneChargeBalanceMapper.balances();
    }


    /**
     * 修改余额 并新增明细 注意money是可以为负数的
     */
    @Transactional
    public boolean addBalance(BigDecimal money, int type, int customerId, String describe) {
        return addBalance(money, type, customerId, describe, null, null);
    }

    /**
     * 修改余额 并新增明细 注意money是可以为负数的
     */
    @Transactional
    public boolean addBalance(BigDecimal money, int type, int customerId, String describe, Integer activityId, Integer level) {
        PhoneChargeBalance balance = getCustomerBalance(customerId);
        if (balance == null) { //新增话费余额记录
            balance = new PhoneChargeBalance(customerId);
            phoneChargeBalanceMapper.inserBalance(balance);
        }

        // 1、修改余额
        BigDecimal b = money.add(balance.getBalance()).setScale(2, BigDecimal.ROUND_HALF_UP);
        balance.setBalance(b);
        if (updateCustomerBalance(balance)) {
            //2新增余额明细
            insertBalanceRecord(new PhoneChargeBalanceRecord(customerId, money, type, describe, activityId, level));
            return true;
        }
        return false;
    }


    /**
     * 话费余额
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public PhoneChargeBalance getCustomerBalance(int customerId) {
        return phoneChargeBalanceMapper.getCustomerBalance(customerId);
    }

    /**
     * 修改账户余额
     */
    public boolean updateCustomerBalance(PhoneChargeBalance balance) {
        return phoneChargeBalanceMapper.updateCustomerBalance(balance) > 0;
    }

    /**
     * 新增余额明细
     */
    public void insertBalanceRecord(PhoneChargeBalanceRecord record) {
        phoneChargeBalanceMapper.insert(record);
    }

    /**
     * 余额明细列表
     */
    public PageInfo<PhoneChargeBalanceRecord> balanceRecords(BaseApiLookup lookup) {
        startPage(lookup);
        List<PhoneChargeBalanceRecord> datas = phoneChargeBalanceMapper.list(lookup);
        return PageInfo.instance(datas);
    }

    /**
     * 话费充值申请
     */
    public void fetchPhoneCharge(int customerId, BigDecimal money, String mobile) {
        PhoneChargeFetchMoney entity = new PhoneChargeFetchMoney(customerId, money);
        //生成充值申請
        phoneChargeFetchMoneyService.insert(entity);
        //減少余额  余额变化记录
        addBalance(money, 2, customerId, "充话费");
    }

}
