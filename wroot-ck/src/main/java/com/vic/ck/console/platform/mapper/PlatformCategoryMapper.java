package com.vic.ck.console.platform.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vic.base.BaseMapper;
import com.vic.base.model.BaseModel;
import com.vic.base.pager.Lookup;
import com.vic.ck.entity.PlatformCategory;
import com.vic.ck.entity.PlatformCategoryMerchant;
import com.vic.wroot.common.annotation.MybatisMapper;

/**
 * 平台特色商家分类表
 * 
 * @author VIC
 */
@MybatisMapper
public interface PlatformCategoryMapper extends BaseMapper<PlatformCategory> {
	
	/**特色分类商家列表*/
	List<PlatformCategoryMerchant> relList(Lookup lookup);
	
	/**新增特色分类和商家关系*/
	int insertRel(PlatformCategoryMerchant entity);

	/**更新关系*/
	int updateRel(PlatformCategoryMerchant entity);
	
	/**删除关系*/
	int deleteRel(@Param("ids")int[] ids);
	/**获取当前城市下的分类*/
	List<BaseModel> findCityCategoryList(@Param("cityId")int cityId);

	PlatformCategoryMerchant findByIdRel(@Param("id")int id);
}
