package com.vic.ck.console.merchant.service;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.merchant.mapper.MerchantGroupbuyMapper;
import com.vic.ck.entity.MerchantGroupBuy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
/**
 * 商户团购商品表
 *
 */
@Service
public class MerchantGroupbuyService extends BaseService{

	@Resource
	private MerchantGroupbuyMapper merchantGroupbuyMapper;
	
	/**
    * 查询列表
    */
	public PageInfo<MerchantGroupBuy> list(Lookup lookup){
		startPage(lookup);
		List<MerchantGroupBuy> datas = merchantGroupbuyMapper.list(lookup);
		return PageInfo.instance(datas, lookup);
	}
	
	/**
	 * 根据主键查询
	 */
	public MerchantGroupBuy findById(int id) {
		return merchantGroupbuyMapper.findById(id);
	}
	
	/**
	 * 新增
	 */
	 public int insert(MerchantGroupBuy entity) {
		return merchantGroupbuyMapper.insert(entity);
	}
	
	/**
	 * 修改
	 */
	  public int update(MerchantGroupBuy entity) {
		  return merchantGroupbuyMapper.update(entity);
	  }
	  
	  /***
	   * 删除
	   */
	  public int delete(int...ids) {
		  return merchantGroupbuyMapper.delete(ids);
	  }
    
}
