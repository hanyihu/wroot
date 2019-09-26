package com.vic.ck.api.community.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vic.base.BaseMapper;
import com.vic.ck.entity.PhoneChargeBalance;
import com.vic.ck.entity.PhoneChargeBalanceRecord;
import com.vic.wroot.common.annotation.MybatisMapper;

/**
 * 用户抢话费余额记录明细
 * 
 * @author VIC
 */
@MybatisMapper
public interface PhoneChargeBalanceMapper extends BaseMapper<PhoneChargeBalanceRecord> {
	
	/**全部用户的余额*/
	List<PhoneChargeBalance> balances();
	
	
	/**获得用户话费余额*/
	PhoneChargeBalance getCustomerBalance(@Param("customerId")int customerId);
	
	/**新增话费记录*/
	void inserBalance(PhoneChargeBalance balance);
	
	/**修改余额*/
	int updateCustomerBalance(PhoneChargeBalance balance);

	/**用户在此活动中抢到的余额*/
	List<PhoneChargeBalanceRecord> phoneChargeBalanceRecords(@Param("activityId")int activityId, @Param("customerId")int customerId);

	/**用户在当前活动时间内推荐注册多用户数 */
	int currentActivityRecommentNumber(@Param("activityId")int activityId, @Param("customerId")int customerId);

	/**获得用户在此活动中获取到的不同级别的红包的次数*/
	List<PhoneChargeBalanceRecord> phoneChargeBalanceLevelTimes(@Param("activityId")int activityId, @Param("customerId")int customerId);
	
}
