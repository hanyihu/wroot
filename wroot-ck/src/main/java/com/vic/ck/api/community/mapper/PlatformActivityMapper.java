package com.vic.ck.api.community.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vic.base.BaseMapper;
import com.vic.base.model.BaseModel;
import com.vic.ck.entity.PlatformActivity;
import com.vic.ck.vo.PhoneChargeStaticticsVo;
import com.vic.wroot.common.annotation.MybatisMapper;

/**
 * 平台活动表
 * 
 * @author VIC
 */
@MybatisMapper
public interface PlatformActivityMapper extends BaseMapper<PlatformActivity> {

	/**当前活动 每个type最多取一条*/
	List<PlatformActivity> currentAcitivities();
	
	/**当前类型的活动*/
	PlatformActivity currentAcitivityByType(@Param("type") int type);

	List<BaseModel> redpacketRecords();
	
	/**15条抢话费的记录*/
	List<BaseModel> phoneChargeRecords();


	/**判断今日是否领取了红包*/
	boolean isReceived(@Param("customerId")int customerId);

	/**昨日是否消费*/
	boolean isConsume(@Param("customerId")int customerId);
	
	/**昨日的消费总金额*/
	BigDecimal yesterdayAmount(@Param("customerId")int customerId);

	/**话费充值的一些统计*/
	PhoneChargeStaticticsVo phoneChargeStatictics(@Param("customerId")int customerId);
	
	List<PlatformActivity> findByIds(@Param("ids")Integer[] ids);
	
	

}
