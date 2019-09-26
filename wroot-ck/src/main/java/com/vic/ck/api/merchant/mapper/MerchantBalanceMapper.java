package com.vic.ck.api.merchant.mapper;

import com.vic.base.BaseMapper;
import com.vic.base.pager.Lookup;
import com.vic.ck.entity.MerchantBalance;
import com.vic.ck.entity.MerchantBalanceRecord;
import com.vic.ck.vo.CompareBillVo;
import com.vic.wroot.common.annotation.MybatisMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@MybatisMapper
public interface MerchantBalanceMapper extends BaseMapper<MerchantBalance>{

	
	int insertMerchantBalance(@Param("id")int merchantId);
	//获得余额详情
	MerchantBalance getMerchantBalance(@Param("merchantId")int merchantId);

	//修改余额
	int updateMerchantBalance(MerchantBalance balance);

	//新增明细
	void insertBalanceRecord(MerchantBalanceRecord record);
	
	//收入明细列表
	List<MerchantBalanceRecord> balanceRecords(Lookup lookup);

	/**获得对账信息*/
	CompareBillVo findMerchantBill(@Param("merchantId")int merchantId, @Param("startDate")Date startDate, @Param("endDate")Date endDate);


}
