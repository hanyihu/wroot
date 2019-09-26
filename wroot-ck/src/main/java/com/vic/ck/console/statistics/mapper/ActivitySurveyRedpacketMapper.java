package com.vic.ck.console.statistics.mapper;

import com.vic.base.BaseMapper;
import com.vic.ck.entity.ActivitySurveyRedpacket;
import com.vic.wroot.common.annotation.MybatisMapper;

/**
 * 当日红包概况
 * 
 * @author VIC
 */
@MybatisMapper
public interface ActivitySurveyRedpacketMapper extends BaseMapper<ActivitySurveyRedpacket> {

	ActivitySurveyRedpacket findYestodayRedpacket();
	
}
