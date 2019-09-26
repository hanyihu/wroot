package com.vic.ck.console.customer.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.customer.mapper.PhoneChargeFetchMoneyMapper;
import com.vic.ck.entity.PhoneChargeFetchMoney;
/**
 * 用户话费充值申请表
 *
 */
@Service
public class PhoneChargeFetchMoneyService extends BaseService{

	@Resource
	private PhoneChargeFetchMoneyMapper phoneChargeFetchMoneyMapper;
	
	/**
    * 查询列表
    */
	public PageInfo<PhoneChargeFetchMoney> list(Lookup lookup){
		startPage(lookup);
		List<PhoneChargeFetchMoney> datas = phoneChargeFetchMoneyMapper.list(lookup);
		return PageInfo.instance(datas, lookup);
	}
	
	/**
	 * 根据主键查询
	 */
	public PhoneChargeFetchMoney findById(int id) {
		return phoneChargeFetchMoneyMapper.findById(id);
	}
	
	/**
	 * 新增
	 */
	 public int insert(PhoneChargeFetchMoney entity) {
		return phoneChargeFetchMoneyMapper.insert(entity);
	}
	
	/**
	 * 修改
	 */
	  public int update(PhoneChargeFetchMoney entity) {
		  return phoneChargeFetchMoneyMapper.update(entity);
	  }
	  
	  /***
	   * 删除
	   */
	  public int delete(int...ids) {
		  return phoneChargeFetchMoneyMapper.delete(ids);
	  }
    
}
