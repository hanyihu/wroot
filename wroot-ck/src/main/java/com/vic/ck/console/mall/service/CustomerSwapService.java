package com.vic.ck.console.mall.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.personal.service.PersonalService;
import com.vic.ck.console.mall.mapper.CustomerSwapMapper;
import com.vic.ck.entity.CustomerSwap;
/**
 * 我的兑换-一般为积分兑换
 *
 */
@Service
public class CustomerSwapService extends BaseService{

	@Resource
	private CustomerSwapMapper customerSwapMapper;
	
	@Resource
	private PersonalService personalService;
	
	/**
    * 查询列表
    */
	public PageInfo<CustomerSwap> list(Lookup lookup){
		startPage(lookup);
		List<CustomerSwap> datas = customerSwapMapper.list(lookup);
		return PageInfo.instance(datas, lookup);
	}
	
	/**
	 * 根据主键查询
	 */
	public CustomerSwap findById(int id) {
		CustomerSwap swap= customerSwapMapper.findById(id);
		//此处懒得配置association
		if(swap != null && swap.getAddressId() != null) {
			swap.setAddress(personalService.deliverieDetail(swap.getAddressId()));
		}
		return swap;
	}
	
	/**
	 * 新增
	 */
	 public int insert(CustomerSwap entity) {
		return customerSwapMapper.insert(entity);
	}
	
	/**
	 * 修改
	 */
	  public int update(CustomerSwap entity) {
		  return customerSwapMapper.update(entity);
	  }
	  
	  /***
	   * 删除
	   */
	  public int delete(int...ids) {
		  return customerSwapMapper.delete(ids);
	  }
    
}
