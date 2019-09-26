package com.vic.ck.console.customer.mapper;

import com.vic.base.BaseMapper;
import com.vic.ck.entity.CustomerBankCard;
import com.vic.ck.entity.CustomerFetchMoney;
import com.vic.wroot.common.annotation.MybatisMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户提现申请表
 * 
 * @author VIC
 */
@MybatisMapper
public interface CustomerFetchMoneyMapper extends BaseMapper<CustomerFetchMoney> {
	
	
	/**银行卡信息*/
	CustomerBankCard bankCard(@Param("id")int id);

}
