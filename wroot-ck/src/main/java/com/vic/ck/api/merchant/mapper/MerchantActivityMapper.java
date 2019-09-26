package com.vic.ck.api.merchant.mapper;

import com.vic.base.BaseApiLookup;
import com.vic.ck.entity.MerchantActivity;
import com.vic.wroot.common.annotation.MybatisMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MybatisMapper
public interface MerchantActivityMapper {

	List<MerchantActivity> activities(BaseApiLookup lookup);

	int insertActivity(MerchantActivity activity);

	int updateActivity(MerchantActivity activity);
	
	MerchantActivity findMerchantActivityById(@Param("id") int id);
	
	int deleteMerchantActivity(@Param("id") int id);

	void updateStatus(@Param("id")int id, @Param("status")int status);

	/**当前商家的活动每个type最多取一条*/
	List<MerchantActivity> currentActivities(@Param("merchantId")int merchantId);
	
	/**当前商家的当前类型的活动*/
	MerchantActivity currentTypeActivitie(@Param("merchantId") int merchantId, @Param("types") int types);

	int minusStock(@Param("id")int id);

	/** 更新商家活动是否过期*/
	int autoUpdateStatus();
	
	/**根据当前商家的活动更新商家表里的当前活动字段 传空 则全量更新*/
	int updateMerchantCurrentActivity(@Param("merchantId")Integer merchantId);

	void closeOthers(@Param("id")int id);
}