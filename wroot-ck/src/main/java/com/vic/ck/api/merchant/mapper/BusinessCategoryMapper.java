package com.vic.ck.api.merchant.mapper;

import com.vic.base.BaseMapper;
import com.vic.ck.entity.BusinessCategory;
import com.vic.wroot.common.annotation.MybatisMapper;

import java.util.List;

@MybatisMapper
public interface BusinessCategoryMapper extends BaseMapper<BusinessCategory> {
	
	//查询经营品类
    List<BusinessCategory> queryCategory();

}
