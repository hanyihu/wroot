package com.vic.ck.api.platform.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vic.base.BaseApiLookup;
import com.vic.ck.api.platform.lookup.CommodityLookup;
import com.vic.ck.api.platform.lookup.HotelLookup;
import com.vic.ck.api.platform.lookup.MerchantHotelLookup;
import com.vic.ck.entity.PlatformAdPosition;
import com.vic.ck.entity.PlatformCategory;
import com.vic.ck.entity.PlatformCategoryAdPosition;
import com.vic.ck.vo.CommodityVo;
import com.vic.ck.vo.GroupbuyDetailVo;
import com.vic.ck.vo.HotelDetailVo;
import com.vic.ck.vo.HotelMerchantDescribeVo;
import com.vic.ck.vo.HotelVo;
import com.vic.ck.vo.MerchantDetailVo;
import com.vic.ck.vo.MerchantVo;
import com.vic.wroot.common.annotation.MybatisMapper;

@MybatisMapper
public interface CommodityMapper {

	/** 特色商家分类*/
	List<PlatformCategory> platformCategories(BaseApiLookup lookup);
	
	/**广告位*/
	List<PlatformAdPosition> adPositions(BaseApiLookup lookup);
	
	/**平台商品分类商家广告位*/
	PlatformCategoryAdPosition categoryAdPosition(BaseApiLookup lookup);

	/**平台分类商家列表*/
	List<MerchantVo> merchants(CommodityLookup lookup);

	/**平台特色分类商家*/
	List<MerchantVo> uniqueMerchants(CommodityLookup lookup);

	/**镗镗锣精选列表*/
	List<MerchantVo> wellChosenes(CommodityLookup lookup);

	/**为你推荐*/
	List<MerchantVo> recommends(CommodityLookup lookup);

	/**人气榜单*/
	List<MerchantVo> bestBills(CommodityLookup lookup);

	/**猜你喜欢*/
	List<MerchantVo> guesses(CommodityLookup lookup);
	
	/**搜索商家*/
	List<MerchantVo> search(CommodityLookup lookup);

	/**商家基本信息*/
	MerchantDetailVo merchantDetailBase(@Param("id")int id);

	/**团购列表 2条----------后来改为3条*/
	List<CommodityVo> twoGroupBuys(@Param("merchantId")int merchantId);

	/**商家图文描述*/
	String merchantDescribe(@Param("id")int id);

	/**商家团购列表*/
	List<CommodityVo> groupbuys(BaseApiLookup lookup);

	/**商家团购详情*/
	GroupbuyDetailVo groupbuyDetail(@Param("id")int id);

	/**酒店商家列表*/
	List<MerchantVo> hotels(MerchantHotelLookup lookup);

	/**酒店房间列表*/
	List<HotelVo> rooms(HotelLookup lookup);
	
	/**酒店房间详情*/
	HotelDetailVo roomDetail(@Param("id") int id);

	/**酒店商家描述详情*/
	HotelMerchantDescribeVo hotelDescribe(@Param("id")int id);

	

}