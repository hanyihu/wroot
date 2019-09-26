package com.vic.ck.console.merchant.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.merchant.mapper.MerchantHotelMapper;
import com.vic.ck.entity.MerchantHotel;
/**
 * 酒店表
 *
 */
@Service
public class MerchantHotelService extends BaseService{

	@Resource
	private MerchantHotelMapper merchantHotelMapper;
	
	/**
    * 查询列表
    */
	public PageInfo<MerchantHotel> list(Lookup lookup){
		startPage(lookup);
		List<MerchantHotel> datas = merchantHotelMapper.list(lookup);
		return PageInfo.instance(datas, lookup);
	}
	
	/**
	 * 根据主键查询
	 */
	public MerchantHotel findById(int id) {
		return merchantHotelMapper.findById(id);
	}
	
	/**
	 * 新增
	 */
	 public int insert(MerchantHotel entity) {
		return merchantHotelMapper.insert(entity);
	}
	
	/**
	 * 修改
	 */
	  public int update(MerchantHotel entity) {
		  return merchantHotelMapper.update(entity);
	  }
	  
	  /***
	   * 删除
	   */
	  public int delete(int...ids) {
		  return merchantHotelMapper.delete(ids);
	  }
    
}
