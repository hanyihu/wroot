package com.vic.ck.api.community.mapper;

import org.apache.ibatis.annotations.Param;

import com.vic.base.BaseMapper;
import com.vic.ck.entity.ActivitySurveyPhoneMoney;
import com.vic.ck.entity.PlatformActivity;
import com.vic.wroot.common.annotation.MybatisMapper;

/**
 * 抢话费活动概况
 * 
 * @author VIC
 */
@MybatisMapper
public interface ActivitySurveyPhoneMoneyMapper extends BaseMapper<ActivitySurveyPhoneMoney> {

	
	ActivitySurveyPhoneMoney findByActivityIdAndLevel(@Param("activityId")Integer idactivityId, @Param("level")int level);

	/**生成抢话费活动概况(5个级别的红包概括)*/
	void generatorSurveyPhoneMoney(PlatformActivity entity);
	
}
