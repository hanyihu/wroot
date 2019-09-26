package com.vic.ck.api.platform.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vic.base.BaseApiLookup;
import com.vic.ck.entity.CustomerCollection;
import com.vic.wroot.common.annotation.MybatisMapper;

@MybatisMapper
public interface CollectionMapper {
	
	List<CustomerCollection> collections(BaseApiLookup lookup);

	void deleteCollection(@Param("id")int id);

	void deleteCollectionByCondition(@Param("type")int type, @Param("targetId")int targetId, @Param("customerId")int customerId);

	void addCollection(CustomerCollection collection);

	/**是否已经收藏*/
	boolean isCollected(CustomerCollection customerCollection);
	
}
