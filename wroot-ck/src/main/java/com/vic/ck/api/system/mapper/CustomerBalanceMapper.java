package com.vic.ck.api.system.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vic.base.BaseMapper;
import com.vic.base.pager.Lookup;
import com.vic.ck.entity.CustomerBalance;
import com.vic.ck.entity.CustomerBalanceRecord;
import com.vic.wroot.common.annotation.MybatisMapper;

@MybatisMapper
public interface CustomerBalanceMapper extends BaseMapper<CustomerBalance>{

	//获得余额详情
	CustomerBalance getCustomerBalance(@Param("customerId")int customerId);

	//修改余额
	int updateCustomerBalance(CustomerBalance balance);

	//新增明细
	void insertBalanceRecord(CustomerBalanceRecord record);
	
	//收入明细列表
	List<CustomerBalanceRecord> balanceRecords(Lookup lookup);

	/**当前明细是否存在*/
	boolean existBalanceRecord(@Param("customerId")int customerId, @Param("type")int type, @Param("date")Date date);




}
