package com.vic.ck.console.merchant.service;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.community.service.PlatformActivityService;
import com.vic.ck.api.constant.FinalFiledParams;
import com.vic.ck.api.system.service.CustomerBalanceService;
import com.vic.ck.console.merchant.mapper.ConsoleMerchantMapper;
import com.vic.ck.entity.ConsoleMerchant;
import com.vic.ck.entity.Merchant;
import com.vic.ck.entity.MerchantAuthentication;
import com.vic.ck.entity.PlatformActivity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商户表-申请时产生数据
 *
 */
@Service
public class ConsoleMerchantService extends BaseService {

	@Resource
	private ConsoleMerchantMapper consoleMerchantMapper;
	
	@Resource
	private PlatformActivityService platformActivityService; 
	
	@Resource
	private CustomerBalanceService customerBalanceService;
	
	
	
	/**
	 * 查询列表
	 */
	public PageInfo<ConsoleMerchant> list(Lookup lookup) {
		startPage(lookup);
		List<ConsoleMerchant> datas = consoleMerchantMapper.list(lookup);
		return PageInfo.instance(datas, lookup);
	}

	/**
	 * 根据主键查询
	 */
	public ConsoleMerchant findById(int id) {
		return consoleMerchantMapper.findById(id);
	}

	/**
	 * 新增
	 */
	public int insert(ConsoleMerchant entity) {
		return consoleMerchantMapper.insert(entity);
	}

	/**
	 * 修改
	 */
	public int update(ConsoleMerchant entity) {
		return consoleMerchantMapper.update(entity);
	}

	/***
	 * 删除
	 */
	public int delete(int... ids) {
		return consoleMerchantMapper.delete(ids);
	}

	/**
	 * 批量删除
	 * @return
	 */
	public int deleteAll(int... ids){
		return consoleMerchantMapper.deleteAll(ids);
	}
	
	/**
	 * 商家实名认证信息
	 */
	public MerchantAuthentication findMerchantAuthentication(int merchantId){
		return consoleMerchantMapper.findMerchantAuthentication(merchantId);
	}

	/***
	 * 商家通过审核后的一些操作
	 * 1、判断活动 奖励推荐人
	 * 2、用户类型改为2
	 */
	public void afterThroughAudit(int id) {
		ConsoleMerchant m = findById(id);
		if(m ==null) return;
		//1 判断活动 奖励推荐人
		Integer recommentId  = m.getRecommendId();
		if(recommentId != null && recommentId != 0) {
			PlatformActivity a = platformActivityService.currentAcitivityByType(3);
			// 已经获得过奖励不再重复获得
			if(a != null && !customerBalanceService.existBalanceRecord(recommentId, FinalFiledParams.BALANCE_RECOMMENT_SUCCESS, null)) {
				customerBalanceService.addBalance(a.getRecommentRule(), FinalFiledParams.BALANCE_RECOMMENT_SUCCESS, recommentId);
			}
		}
		//2 修改用户类型为2
		consoleMerchantMapper.updateCustomerType(m.getCustomerId(),2);
		

	}

	/**
	 * 查询所有回收的商家
	 * @param state 状态
	 * @return
	 */
	public PageInfo<ConsoleMerchant> findByState(Lookup lookup,int state){
		startPage(lookup);
		List<ConsoleMerchant> states = consoleMerchantMapper.findByState(lookup, state);
		return PageInfo.instance(states,lookup);
	}

	/**
	 * 根据id查询当前商家信息
	 * @param id 商家id
	 * @return
	 */
	public Merchant findByIdAll(int id){
		return consoleMerchantMapper.findByIdAll(id);
	}

}
