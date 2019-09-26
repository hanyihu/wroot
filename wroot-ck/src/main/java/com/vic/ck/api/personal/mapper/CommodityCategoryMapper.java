package com.vic.ck.api.personal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vic.base.BaseApiLookup;
import com.vic.ck.entity.Commodity;
import com.vic.ck.entity.CommodityCategory;
import com.vic.ck.entity.CustomerBankCard;
import com.vic.ck.entity.CustomerDeliveryAddress;
import com.vic.ck.entity.CustomerScoreRecord;
import com.vic.ck.entity.LookNoter;
import com.vic.ck.entity.CustomerFetchMoney;
import com.vic.ck.vo.RecommentSurveyVo;
import com.vic.ck.vo.RecommentVo;
import com.vic.wroot.common.annotation.MybatisMapper;

@MybatisMapper
public interface CommodityCategoryMapper {

  
	/**
	 * 商品分类查询
	 */
	List<CommodityCategory> spflList(BaseApiLookup lookup);
	
	List<CommodityCategory> mkspfList(BaseApiLookup lookup);

	List<CommodityCategory> tsflList(BaseApiLookup lookup);

	
	 
	
		/**
		 *  商品查询——优惠产品：在优惠券时间内的
		 */
	List<Commodity> yhcpList(BaseApiLookup lookup);

	

}
