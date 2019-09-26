package com.vic.ck.api.personal.controller;

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
import com.vic.ck.api.personal.service.SwapService;
import com.vic.ck.api.system.service.AuthService;
import com.vic.ck.console.mall.lookup.CustomerSwapLookup;
import com.vic.ck.console.mall.service.CustomerSwapService;
import com.vic.ck.console.mall.service.ScoreMallCategoryService;
import com.vic.ck.console.mall.service.ScoreMallGoodsService;
import com.vic.ck.entity.Customer;
import com.vic.ck.entity.CustomerSwap;
import com.vic.ck.entity.ScoreMallCategory;
import com.vic.ck.entity.ScoreMallGoods;
import com.vic.ck.util.CommonUtils;

/**
 * 积分兑换相关  score
 * 
 * @author VIC
 *
 */
@RestController
@RequestMapping("/api/personal/swap")
public class SwapController extends BaseApiController {

	@Resource
	private ScoreMallCategoryService scoreMallCategoryService;

	@Resource
	private ScoreMallGoodsService scoreMallGoodsService;
	
	
	@Resource
	private CustomerSwapService customerSwapService;
	
	@Resource
	private AuthService authService;
	
	@Resource
	private SwapService swapService;

	/*
	 *我的兑换商品 我的兑换商品详情
	 */

	/**
	 * 2.5-01积分商品分类
	 */
	@RequestMapping(value = "/categorys", method = RequestMethod.GET)
	public Object categorys(BaseApiLookup lookup) {
		List<ScoreMallCategory> data = scoreMallCategoryService.categorys(lookup);
		return resultSuccess(data);
	}

	/**
	 * 2.5-02 积分兑换商品列表
	 * 
	 * @param lookup
	 * @return
	 */
	@RequestMapping(value = "/malls", method = RequestMethod.GET)
	public Object malls(BaseApiLookup lookup) {
		lookup.setEnabled(1);
		PageInfo<ScoreMallGoods> data = scoreMallGoodsService.list(lookup);
		return resultSuccess(data);
	}
	
	/***
	 * 2.5-03 积分商品详情 
	 */
	@RequestMapping(value = "/mall/{id}", method = RequestMethod.GET)
	public Object malls(@PathVariable int id) {
		ScoreMallGoods data = scoreMallGoodsService.findById(id);
		if(data != null) {
			data.setDescribe(CommonUtils.ueditorImgWigth(data.getDescribe()));
		}
		return resultSuccess(data);
	}
	
	/**
	 * 2.5-04 兑换商品
	 * @throws AuthenticationException 
	 */
	@RequestMapping(value = "/exchange", method = RequestMethod.POST)
	public Object exchange(int userId, int mallId, int quantity, int addressId,@RequestParam(defaultValue="") String remark
			) throws AuthenticationException {
		checkLoggin(userId);
		Customer c =authService.findCustomerById(userId);
		ScoreMallGoods goods = scoreMallGoodsService.findById(mallId);
		if(goods == null) {
			return resultError(ResultMsgEnum.NOTEXISTED_MALL_GOODS);
		}
		
		if(quantity < 1) return resultError(ResultMsgEnum.PARAM_ERROR);//这个
		
		int needScore = goods.getScore() * quantity;
		
		int score = authService.getScore(userId);
		
		if(score < needScore) {
			return resultError(ResultMsgEnum.SCORE_NOT_ENOUGH);
		}
		CustomerSwap swap = new CustomerSwap(userId, mallId, quantity, goods.getScore(), needScore, addressId, 0, goods.getMerchantName(), goods.getIcon(),remark,
				c.getNickname(), c.getMobile(), goods.getName());
		swapService.exchange(swap);
		return resultSuccess(customerSwapService.findById(swap.getId()));
	}
	
	
	/**
	 *  2.5-05 我的兑换列表
	 * @throws AuthenticationException 
	 */
	@RequestMapping(value = "/exchanges", method = RequestMethod.GET)
	public Object exchanges(CustomerSwapLookup lookup) throws AuthenticationException {
		checkLoggin(lookup.getUserId());
		PageInfo<CustomerSwap> data = customerSwapService.list(lookup);
		return resultSuccess(data);
	}
	
	/**
	 *  2.5-06 我的兑换详情
	 * @throws AuthenticationException 
	 */
	@RequestMapping(value = "/exchange/{id}", method = RequestMethod.GET)
	public Object exchanges(@PathVariable int id) {
		CustomerSwap data = customerSwapService.findById(id);
		return resultSuccess(data);
	}
	
	
	
	

}
