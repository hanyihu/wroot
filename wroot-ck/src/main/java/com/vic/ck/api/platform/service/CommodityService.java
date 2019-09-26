package com.vic.ck.api.platform.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vic.base.BaseApiLookup;
import com.vic.base.BaseService;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.merchant.service.MerchantActivityService;
import com.vic.ck.api.platform.lookup.CommodityLookup;
import com.vic.ck.api.platform.lookup.HotelLookup;
import com.vic.ck.api.platform.lookup.MerchantHotelLookup;
import com.vic.ck.api.platform.mapper.CommodityMapper;
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

@Service
public class CommodityService extends BaseService{
	
	@Resource
	private CommodityMapper commodityMapper;
	
	@Resource
	private MerchantActivityService merchantActivityService;
	
	@Resource
	private CollectionService collectionService;
	
	@Resource
	private PlatformCommonService platformCommonService;

	/***
	 * 平台特色商家列表 跟着城市走
	 */
	public PageInfo<PlatformCategory> platformCategories(BaseApiLookup lookup){
		startPage(lookup);
		List<PlatformCategory> datas = commodityMapper.platformCategories(lookup);
		return PageInfo.instance(datas);
	}
	
	/**广告位 列表*/
	public List<PlatformAdPosition> adPositions(BaseApiLookup lookup){
		List<PlatformAdPosition> datas = commodityMapper.adPositions(lookup);
		return datas;
	}
	
	/**平台分类商家列表*/
	public PlatformCategoryAdPosition categoryAdPosition(BaseApiLookup lookup) {
		return commodityMapper.categoryAdPosition(lookup) ;
	}

	/**平台分类商家列表*/
	public PageInfo<MerchantVo> merchants(CommodityLookup lookup) {
		startPage(lookup);
		List<MerchantVo> datas = commodityMapper.merchants(lookup);
		return PageInfo.instance(datas);
	}

	/**平台特色分类商家*/
	public PageInfo<MerchantVo> uniqueMerchants(CommodityLookup lookup) {
		startPage(lookup);
		List<MerchantVo> datas = commodityMapper.uniqueMerchants(lookup);
		return PageInfo.instance(datas);
	}
	
	/**镗镗锣精选列表*/
	public PageInfo<MerchantVo> wellChosenes(CommodityLookup lookup) {
		startPage(lookup);
		List<MerchantVo> datas = commodityMapper.wellChosenes(lookup);
		return PageInfo.instance(datas);
	}
	
	/**为你推荐商家列表*/
	public PageInfo<MerchantVo> recommends(CommodityLookup lookup) {
		startPage(lookup);
		List<MerchantVo> datas = commodityMapper.recommends(lookup);
		return PageInfo.instance(datas);
	}

	/**人气榜单*/
	public PageInfo<MerchantVo> bestBills(CommodityLookup lookup) {
		startPage(lookup);
		List<MerchantVo> datas = commodityMapper.bestBills(lookup);
		return PageInfo.instance(datas);
	}

	/**猜你喜欢*/
	public PageInfo<MerchantVo> guesses(CommodityLookup lookup) {
		startPage(lookup);
		List<MerchantVo> datas = commodityMapper.guesses(lookup);
		return PageInfo.instance(datas);
	}

	/**搜索商家*/
	public PageInfo<MerchantVo> search(CommodityLookup lookup) {
		startPage(lookup);
		List<MerchantVo> datas = commodityMapper.search(lookup);
		return PageInfo.instance(datas);
	}

	/**商家详情*/
	public MerchantDetailVo merchantDetail(int id, int userId) {
		MerchantDetailVo detail = merchantDetailBase(id);
		if(detail == null ) return null;
		//查询当前活动
		detail.setActivities(merchantActivityService.currentActivities(id));
		//判断当前用户是否收藏本商家
		detail.setCollected(collectionService.isCollected(1, id, userId));
		//判断当前用户是否已签到
		detail.setSigned(platformCommonService.isSign(userId, id));
		//评论列表
		detail.setComments(platformCommonService.merchantComments(id, userId));
		//团购列表
		detail.setCommodities(twoGroupBuys(id));
		return detail;
	}
	
	/**酒店商家详情 - 去掉团购列表*/
	public MerchantDetailVo hotelMerchantDetail(int id, int userId) {
		MerchantDetailVo detail = merchantDetailBase(id);
		if(detail == null ) return null;
		//查询当前活动
		detail.setActivities(merchantActivityService.currentActivities(id));
		//判断当前用户是否收藏本商家
		detail.setCollected(collectionService.isCollected(1, id, userId));
		//判断当前用户是否已签到
		detail.setSigned(platformCommonService.isSign(userId, id));
		//评论列表
		detail.setComments(platformCommonService.merchantComments(id, userId));
		return detail;
	}
	
	/**商家详情的一些基本信息*/
	public MerchantDetailVo merchantDetailBase(int id) {
		return commodityMapper.merchantDetailBase(id);
	}
	
	/**商家团购列表 取2条----后来改为3条*/
	public List<CommodityVo> twoGroupBuys(int merchantId){
		return commodityMapper.twoGroupBuys(merchantId);
	}
	
	/**
	 * 商家团购列表
	 */
	public PageInfo<CommodityVo> groupbuys(BaseApiLookup lookup){
		startPage(lookup);
		List<CommodityVo> datas = commodityMapper.groupbuys(lookup);
		return PageInfo.instance(datas);
	}
	
	

	/**商家图文描述*/
	public String merchantDescribe(int id) {
		return commodityMapper.merchantDescribe(id);
	}

	/**商家详情*/
	public GroupbuyDetailVo groupbuyDetail(int id) {
		return commodityMapper.groupbuyDetail(id);
	}

	public PageInfo<MerchantVo> hotels(MerchantHotelLookup lookup) {
		startPage(lookup);
		List<MerchantVo> datas = commodityMapper.hotels(lookup);
		return PageInfo.instance(datas);
	}

	/**酒店房间列表*/
	public PageInfo<HotelVo> rooms(HotelLookup lookup) {
		startPage(lookup);
		List<HotelVo> datas = commodityMapper.rooms(lookup);
		return PageInfo.instance(datas);
	}
	
	/**酒店房间详情*/
	public HotelDetailVo roomDetail(int id) {
		return commodityMapper.roomDetail(id);
	}

	/**酒店商家描述详情*/
	public HotelMerchantDescribeVo hotelDescribe(int id) {
		return commodityMapper.hotelDescribe(id);
	}


	
	
}
