package com.vic.ck.api.merchant.service;

import com.vic.base.BaseApiLookup;
import com.vic.base.BaseService;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.merchant.mapper.MerchantActivityMapper;
import com.vic.ck.entity.MerchantActivity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MerchantActivityService extends BaseService{

	@Resource
	private MerchantActivityMapper merchantActivityMapper;
	
	/**
	 * 活动列表
	 * @param lookup
	 * @return
	 */
	public PageInfo<MerchantActivity> activities(BaseApiLookup lookup) {
		startPage(lookup);
		List<MerchantActivity> datas = merchantActivityMapper.activities(lookup);
		return PageInfo.instance(datas);
	}
	
	
	public MerchantActivity findActivityById(int id) {
		return merchantActivityMapper.findMerchantActivityById(id);
	}

	/**插入活动*/
	@Transactional
	public void insertActivity(MerchantActivity activity) {
		merchantActivityMapper.insertActivity(activity);
		updateMerchantCurrentActivity(activity.getMerchantId());
	}
	
	/**修改活动*/
	@Transactional
	public void updateActivity(MerchantActivity activity) {
		merchantActivityMapper.updateActivity(activity);
		updateMerchantCurrentActivity(activity.getMerchantId());
	}
	
	/**删除活动*/
	@Transactional
	public void deleteActivity(int id) {
		merchantActivityMapper.deleteMerchantActivity(id);
		updateMerchantCurrentActivity(null);//就不查这活动的商家id了
	}

	/**发布或关闭活动*/
	@Transactional
	public void updateStatus(int id, int status) {
		merchantActivityMapper.updateStatus(id, status);
		if(status == 1){//关闭其他同类型活动
			merchantActivityMapper.closeOthers(id);
		}
		updateMerchantCurrentActivity(null);//就不查这活动的商家id了
	}
	
	
	/**商家当前活动 每种最多取一个*/
	public List<MerchantActivity> currentActivities(int merchantId){
		return merchantActivityMapper.currentActivities(merchantId);
	}
	
	/**商家当前类型的活动 */
	public MerchantActivity currentTypeActivitie(int merchantId, int types) {
		return merchantActivityMapper.currentTypeActivitie(merchantId, types);
	}
	

	/**
	 * 更新商家活动是否过期
	 */
	public int autoUpdateStatus(){
		return merchantActivityMapper.autoUpdateStatus();
	}
	/**
	 * 根据当前商家的活动更新商家表里的当前活动字段 传空 则全量更新
	 */
	public int updateMerchantCurrentActivity(Integer merchantId){
		return merchantActivityMapper.updateMerchantCurrentActivity(merchantId);
	}

	/**减少库存 -1*/
	public int minusStock(int id) {
		return merchantActivityMapper.minusStock(id);
	}



	

	
}
