package com.vic.ck.api.merchant.service;

import com.vic.ck.api.merchant.mapper.BusinessCategoryMapper;
import com.vic.ck.entity.BusinessCategory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BusinessCategoryService {
	
	@Resource
	private BusinessCategoryMapper businessCategoryMapper;
	
	//查询经营品类
    public List<BusinessCategory> selectAllCategory() {
        return businessCategoryMapper.queryCategory();
	}

}
