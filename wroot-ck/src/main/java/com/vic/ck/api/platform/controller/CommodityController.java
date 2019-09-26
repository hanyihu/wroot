package com.vic.ck.api.platform.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.naming.AuthenticationException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vic.base.BaseApiController;
import com.vic.base.BaseApiLookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.constant.ResultMsgEnum;
import com.vic.ck.api.platform.lookup.CommodityLookup;
import com.vic.ck.api.platform.lookup.HotelLookup;
import com.vic.ck.api.platform.lookup.MerchantHotelLookup;
import com.vic.ck.api.platform.service.CollectionService;
import com.vic.ck.api.platform.service.CommodityService;
import com.vic.ck.api.platform.service.OrderCommentService;
import com.vic.ck.api.platform.service.PlatformCommonService;
import com.vic.ck.entity.CommentPraise;
import com.vic.ck.entity.PlatformAdPosition;
import com.vic.ck.entity.PlatformCategory;
import com.vic.ck.entity.PlatformCategoryAdPosition;
import com.vic.ck.vo.CommentVo;
import com.vic.ck.vo.CommodityVo;
import com.vic.ck.vo.GroupbuyDetailVo;
import com.vic.ck.vo.HotelDetailVo;
import com.vic.ck.vo.HotelMerchantDescribeVo;
import com.vic.ck.vo.HotelVo;
import com.vic.ck.vo.MerchantDetailVo;
import com.vic.ck.vo.MerchantVo;
import com.vic.ck.vo.SignVo;

/**
 * 商品相关的基础数据
 * 
 * @author VIC
 *
 */
@RestController
@RequestMapping("/api/commodity")
public class CommodityController extends BaseApiController {

	@Resource
	private CommodityService commodityService;

	@Resource
	private PlatformCommonService platformCommonService;

	@Resource
	private CollectionService collectionService;
	
	@Resource
	private OrderCommentService orderCommentService;
	/**
	 * 3.01 平台特色分类 城市相关 分首页和百惠店
	 */
	@RequestMapping(value = "/platformCategories", method = RequestMethod.GET)
	public Object platformCategories(BaseApiLookup lookup) {
		PageInfo<PlatformCategory> data = commodityService.platformCategories(lookup);
		return resultSuccess(data);
	}

	/**
	 * 3.02 平台商家广告位 分首页和百惠店
	 */
	@RequestMapping(value = "/adPositions", method = RequestMethod.GET)
	public Object adPositions(BaseApiLookup lookup) {
		List<PlatformAdPosition> data = commodityService.adPositions(lookup);
		return resultSuccess(data);
	}

	/**
	 * 3.03 平台分类商家广告位-列表上方
	 */
	@RequestMapping(value = "/categoryAdPosition", method = RequestMethod.GET)
	public Object categoryAdPosition(CommodityLookup lookup) {
		PlatformCategoryAdPosition data = commodityService.categoryAdPosition(lookup);
		return resultSuccess(data);
	}

	/**
	 * 3.04平台分类商家列表
	 */
	@RequestMapping(value = "/merchants", method = RequestMethod.GET)
	public Object merchants(CommodityLookup lookup) {
		vaildateModel(lookup);
		PageInfo<MerchantVo> data = commodityService.merchants(lookup);
		return resultSuccess(data);
	}

	/**
	 * 3.05 特色分类商家列表
	 */
	@RequestMapping(value = "/uniqueMerchants", method = RequestMethod.GET)
	public Object uniqueMerchants(CommodityLookup lookup) {
		vaildateModel(lookup);
		PageInfo<MerchantVo> data = commodityService.uniqueMerchants(lookup);
		return resultSuccess(data);
	}

	/**
	 * 3.06 镗镗锣精选商家列表
	 */
	@RequestMapping(value = "/wellChosenes", method = RequestMethod.GET)
	public Object wellChosenes(CommodityLookup lookup) {
		vaildateModel(lookup);
		PageInfo<MerchantVo> data = commodityService.wellChosenes(lookup);
		return resultSuccess(data);
	}

	/**
	 * 3.07 为你推荐 只显示当前定位方圆5000m的所有商家 排序：距离升序
	 * 
	 * @param lookup
	 * @return
	 */
	@RequestMapping(value = "/recommends", method = RequestMethod.GET)
	public Object recommends(CommodityLookup lookup) {
		vaildateModel(lookup);
		PageInfo<MerchantVo> data = commodityService.recommends(lookup);
		return resultSuccess(data);
	}

	/**
	 * 3.08 人气榜单 根据 人气数或销量数 排序 也需要传经纬度
	 */
	@RequestMapping(value = "/bestBills", method = RequestMethod.GET)
	public Object bestBills(CommodityLookup lookup) {
		vaildateModel(lookup);
		PageInfo<MerchantVo> data = commodityService.bestBills(lookup);
		return resultSuccess(data);
	}

	/**
	 * 3.09 猜你喜欢 去过最多的店 方圆10千米 由近到远
	 * 
	 * @throws AuthenticationException
	 */
	@RequestMapping(value = "/guesses", method = RequestMethod.GET)
	public Object guesses(CommodityLookup lookup) throws AuthenticationException {
		checkLoggin(lookup);
		vaildateModel(lookup);
		PageInfo<MerchantVo> data = commodityService.guesses(lookup);
		return resultSuccess(data);
	}

	/**
	 * 3.10 商家搜索
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public Object search(CommodityLookup lookup) throws AuthenticationException {
		vaildateModel(lookup);
		PageInfo<MerchantVo> data = commodityService.search(lookup);
		return resultSuccess(data);
	}

	/**
	 * 3.11 商家详情
	 */
	@RequestMapping(value = "/merchant/{id}", method = RequestMethod.GET)
	public Object merchantDetail(@PathVariable int id, @RequestParam(defaultValue = "0") int userId) {
		MerchantDetailVo data = commodityService.merchantDetail(id, userId);
		return resultSuccess(data);
	}

	/**
	 * 3.12 商家图文介绍
	 */
	@RequestMapping(value = "/merchant/{id}/describe", method = RequestMethod.GET)
	public Object merchantDescribe(@PathVariable int id) {
		String data = commodityService.merchantDescribe(id);
		return resultSuccess(data);
	}

	/**
	 * 3.13 商家团购列表
	 */
	@RequestMapping(value = "/groupbuys", method = RequestMethod.GET)
	public Object groupbuys(BaseApiLookup lookup) {
		PageInfo<CommodityVo> data = commodityService.groupbuys(lookup);
		return resultSuccess(data);
	}

	/**
	 * 3.14 团购详情
	 */
	@RequestMapping(value = "/groupbuy/{id}", method = RequestMethod.GET)
	public Object groupbuyDetail(@PathVariable int id, @RequestParam(defaultValue = "0") int userId) {
		GroupbuyDetailVo data = commodityService.groupbuyDetail(id);
		if(data != null && userId != 0) {
			data.setCollected(collectionService.isCollected(2, id, userId));
		}
		return resultSuccess(data);
	}

	/**
	 * 3.15 商家/团购评论列表
	 */
	@RequestMapping(value = "/comments", method = RequestMethod.GET)
	public Object comments(CommodityLookup lookup) {
		PageInfo<CommentVo> data = platformCommonService.comments(lookup);
		return resultSuccess(data);
	}

	/**
	 * 3.16 酒店商家列表
	 */
	@RequestMapping(value = "/hotelMerchants", method = RequestMethod.GET)
	public Object hotelMerchants(MerchantHotelLookup lookup) {
		vaildateModel(lookup);
		PageInfo<MerchantVo> data = commodityService.hotels(lookup);
		return resultSuccess(data);
	}

	/**
	 * 3.17酒店商家详情 
	 *  用的是 @see MerchantDetailVo 但是去掉商品(团购)列表  另多一个入店时间和离店时间
	 */
	@RequestMapping(value = "/hotel/{id}", method = RequestMethod.GET)
	public Object hotelMerchantDetail(@PathVariable int id, @RequestParam(defaultValue = "0") int userId) {
		MerchantDetailVo data = commodityService.hotelMerchantDetail(id, userId);
		return resultSuccess(data);
	}
	
	/**
	 * 3.18 酒店商家介绍
	 */
	@RequestMapping(value = "/hotel/{id}/describe", method = RequestMethod.GET)
	public Object hotelDescribe(@PathVariable int id) {
		HotelMerchantDescribeVo data = commodityService.hotelDescribe(id);
		return resultSuccess(data);
	}
	
	
	/**
	 * 3.19 房间列表
	 */
	@RequestMapping(value = "/hotel/rooms", method = RequestMethod.GET)
	public Object rooms(HotelLookup lookup) {
		PageInfo<HotelVo> data = commodityService.rooms(lookup);
		return resultSuccess(data);
	}
	/**
	 *  3.20酒店房间详情
	 */
	@RequestMapping(value = "/hotel/room/{id}", method = RequestMethod.GET)
	public Object roomDetail(@PathVariable int id) {
		HotelDetailVo data = commodityService.roomDetail(id);
		return resultSuccess(data);
	}
	
	
	/**
	 * 3.21 商家的签到列表
	 */
	@RequestMapping(value = "/signs", method = RequestMethod.GET)
	public Object signs(BaseApiLookup lookup) {
		PageInfo<SignVo> data = platformCommonService.signs(lookup);
		return resultSuccess(data);
	}

	/**
	 * 3.22 签到商家
	 */
	@RequestMapping(value = "/sign/add", method = RequestMethod.POST)
	public Object sign(@RequestParam int userId, @RequestParam int merchantId) {
		boolean isSigned = platformCommonService.isSign(userId, merchantId);
		if (isSigned) {
			return resultError(ResultMsgEnum.ALREADY_SIGN);
		}
		platformCommonService.sign(userId, merchantId);
		return resultSuccess();
	}

	/**
	 * 3.23 对评论点赞
	 */
	@RequestMapping(value = "/praise/add", method = RequestMethod.POST)
	public Object praise(@RequestParam int userId, @RequestParam int commentId) {
		boolean isPraise = platformCommonService.isPraise(commentId, userId);
		if (isPraise) {
			return resultError(ResultMsgEnum.ALREADY_PRAISE);
		}
		platformCommonService.insertPraise(new CommentPraise(userId, commentId, 1));
		orderCommentService.calcPraiseNum(commentId);
		return resultSuccess();
	}

}
