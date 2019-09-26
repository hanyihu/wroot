package com.vic.ck.api.merchant.controller;

import com.vic.base.BaseApiController;
import com.vic.base.BaseApiLookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.merchant.service.MerchantCommodityService;
import com.vic.ck.entity.MerchantGroupBuy;
import com.vic.ck.entity.MerchantHotel;
import com.vic.wroot.common.po.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 商家端 商品相关
 * @author VIC
 *
 */
@Api(tags = "商家端商品")
@RestController
@RequestMapping("/api/merchant/commodity")
public class MerchantCommodityController extends BaseApiController{

	@Resource
	private MerchantCommodityService merchantCommodityService;


	/* ******************** 团购**************************************************/
	/**
	 * 5.19 团购列表
	 */
	@RequestMapping( value = "/groupBuys", method = RequestMethod.GET)
	@ApiOperation(value = "商家团购列表", notes = "获取商家的团购列表", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object groupBuy(BaseApiLookup lookup) {
		PageInfo<MerchantGroupBuy> data = merchantCommodityService.groupbuys(lookup);
		return resultSuccess(data);
	}

	/***
	 * 5.20 团购详情
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping( value = "/groupBuy/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "团购详情", notes = "获取相应团购的详情信息", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object groupBuyDetail(@PathVariable @ApiParam(value = "团购ID", required = true) int id) {
		MerchantGroupBuy data = merchantCommodityService.selectGroupbuyById(id);
		return resultSuccess(data);
	}

	/***
	 * 5.21 新增团购
	 */
	@RequestMapping( value = "/groupBuy/add", method = RequestMethod.POST)
	@ApiOperation(value = "新增团购", notes = "新增团购", produces = MediaType.APPLICATION_JSON_VALUE)
	public Object addGroupBuy(@RequestBody MerchantGroupBuy groupBuy) {
		merchantCommodityService.insertGroupbuy(groupBuy);
		return resultSuccess();
	}
	/***
	 * 5.22 修改团购
	 */
	@RequestMapping( value = "/groupBuy/update", method = RequestMethod.POST)
	@ApiOperation(value = "修改团购", notes = "修改团购详情信息", produces = MediaType.APPLICATION_JSON_VALUE)
	public Object updateGroupBuy(@RequestBody MerchantGroupBuy groupBuy) {
		merchantCommodityService.updateGroupbuy(groupBuy);
		return resultSuccess();
	}

	/**
	 * 5.23 删除团购
	 */
	@RequestMapping( value = "/groupBuy/{id}/delete", method = RequestMethod.GET)
	@ApiOperation(value = "删除团购", notes = "删除选中的团购", produces = MediaType.APPLICATION_JSON_VALUE)
	public Object deleteGroupBuy(@PathVariable @ApiParam(value = "团购ID", required = true) int id) {
		merchantCommodityService.deleteGroupbuy(id);
		return resultSuccess();
	}

	/**
	 * 5.24 上架团购
	 */
	@RequestMapping( value = "/groupBuy/{id}/publish", method = RequestMethod.GET)
	@ApiOperation(value = "上架团购", notes = "上架团购", produces = MediaType.APPLICATION_JSON_VALUE)
	public Object publishGroupBuy(@PathVariable @ApiParam(value = "团购ID", required = true) int id) {
		merchantCommodityService.updateGoupbuyStatus(id, 1);
		return resultSuccess();
	}

	/**
	 * 5.25 下架团购
	 */
	@RequestMapping( value = "/groupBuy/{id}/close", method = RequestMethod.GET)
	@ApiOperation(value = "下架团购", notes = "下架团购", produces = MediaType.APPLICATION_JSON_VALUE)
	public Object closeGroupBuy(@PathVariable @ApiParam(value = "团购ID", required = true) int id) {
		merchantCommodityService.updateGoupbuyStatus(id, 2);
		return resultSuccess();
	}

	/* ******************** 酒店**************************************************/

	/**
	 * 5.26 酒店列表
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping( value = "/hotels", method = RequestMethod.GET)
	@ApiOperation(value = "酒店列表", notes = "获取商家的酒店列表", produces = MediaType.APPLICATION_JSON_VALUE)
	public BaseResponse<PageInfo<MerchantHotel>> hotels(BaseApiLookup lookup) {
		PageInfo<MerchantHotel> data = merchantCommodityService.hotels(lookup);
		return resultSuccess(data);
	}

	/***
	 * 5.27 酒店详情
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping( value = "/hotel/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "酒店详情", notes = "获取相应酒店的详情信息", produces = MediaType.APPLICATION_JSON_VALUE)
	public BaseResponse<MerchantHotel> hotelDetail(@PathVariable @ApiParam(value = "酒店ID", required = true) int id) {
		MerchantHotel data = merchantCommodityService.selectHotelById(id);
		return resultSuccess(data);
	}

	/***
	 * 5.28 新增酒店
	 */
	@RequestMapping( value = "/hotel/add", method = RequestMethod.POST)
	@ApiOperation(value = "新增酒店", notes = "新增酒店", produces = MediaType.APPLICATION_JSON_VALUE)
	public Object addHotel(@RequestBody @ApiParam(value = "酒店信息") MerchantHotel hotel) {
		merchantCommodityService.insertHotel(hotel);
		return resultSuccess();
	}
	/***
	 * 5.29 修改酒店
	 */
	@RequestMapping( value = "/hotel/update", method = RequestMethod.POST)
	@ApiOperation(value = "修改酒店", notes = "修改酒店的详情信息", produces = MediaType.APPLICATION_JSON_VALUE)
	public Object updateHotel(@RequestBody @ApiParam(value = "酒店信息") MerchantHotel hotel) {
		merchantCommodityService.updateHotel(hotel);
		return resultSuccess();
	}

	/**
	 * 5.30 删除酒店
	 */
	@RequestMapping( value = "/hotel/{id}/delete", method = RequestMethod.GET)
	@ApiOperation(value = "删除酒店", notes = "删除酒店", produces = MediaType.APPLICATION_JSON_VALUE)
	public Object deleteHotel(@PathVariable @ApiParam(value = "酒店ID", required = true) int id) {
		merchantCommodityService.deleteHotel(id);
		return resultSuccess();
	}

	/**
	 * 5.31 上架酒店
	 */
	@RequestMapping( value = "/hotel/{id}/publish", method = RequestMethod.GET)
	@ApiOperation(value = "上架酒店", notes = "上架酒店", produces = MediaType.APPLICATION_JSON_VALUE)
	public Object publishHotel(@PathVariable @ApiParam(value = "酒店ID", required = true) int id) {
		merchantCommodityService.updateHotelStatus(id, 1);
		return resultSuccess();
	}

	/**
	 * 5.32 下架酒店
	 */
	@RequestMapping( value = "/hotel/{id}/close", method = RequestMethod.GET)
	@ApiOperation(value = "下架酒店", notes = "下架酒店", produces = MediaType.APPLICATION_JSON_VALUE)
	public Object closeHotel(@PathVariable @ApiParam(value = "酒店ID", required = true) int id) {
		merchantCommodityService.updateHotelStatus(id, 2);
		return resultSuccess();
	}


}
