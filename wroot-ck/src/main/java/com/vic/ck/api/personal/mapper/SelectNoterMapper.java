package com.vic.ck.api.personal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vic.base.BaseApiLookup;
import com.vic.ck.entity.CustomerBankCard;
import com.vic.ck.entity.CustomerDeliveryAddress;
import com.vic.ck.entity.CustomerScoreRecord;
import com.vic.ck.entity.LookNoter;
import com.vic.ck.entity.SelectNoter;
import com.vic.ck.entity.CustomerFetchMoney;
import com.vic.ck.vo.RecommentSurveyVo;
import com.vic.ck.vo.RecommentVo;
import com.vic.wroot.common.annotation.MybatisMapper;

@MybatisMapper
public interface SelectNoterMapper {

  
	/**
	 * 搜索记录
	 */
	List<SelectNoter> ssjlList(BaseApiLookup lookup);
 
	

}
