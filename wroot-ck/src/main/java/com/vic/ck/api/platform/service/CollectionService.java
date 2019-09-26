package com.vic.ck.api.platform.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.vic.base.BaseApiLookup;
import com.vic.base.BaseService;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.platform.mapper.CollectionMapper;
import com.vic.ck.entity.CustomerCollection;

/**
 * 收藏相关的
 * @author VIC
 *
 */
@Service
public class CollectionService extends BaseService{
	
	
	@Resource
	private CollectionMapper collectionMapper;
	
	
	/**
	 * 我的收藏列表
	 * @param lookup
	 * @return
	 */
	public PageInfo<CustomerCollection> collections(BaseApiLookup lookup){
		Page<CustomerCollection> page = startPage(lookup);
		List<CustomerCollection> datas = collectionMapper.collections(lookup);
		return PageInfo.instance(page, datas, lookup);
	}
	
	/**删除我的收藏*/
	public void deleteCollection(int id) {
		collectionMapper.deleteCollection(id);
	}
	/**删除收藏*/
	public void deleteCollection(int type, int targetId, int customerId) {
		collectionMapper.deleteCollectionByCondition(type, targetId, customerId);
	}
	
	/**新增我的收藏*/
	public void addCollection(CustomerCollection collection){
		collectionMapper.addCollection(collection);
	}
	
	/**判断当前用户是否收藏
	 * type  1-商家 2-团购 
	 * */
	public boolean isCollected(int type, int targetId, int customerId) {
		return collectionMapper.isCollected(new CustomerCollection(customerId, type, targetId));
	}

	
	
	
}
