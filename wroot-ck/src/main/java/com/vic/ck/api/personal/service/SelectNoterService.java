package com.vic.ck.api.personal.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.vic.base.BaseApiLookup;
import com.vic.base.BaseService;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.personal.mapper.SelectNoterMapper;
import com.vic.ck.entity.SelectNoter;

@Service
public class SelectNoterService  extends BaseService {

	
	@Resource
	private SelectNoterMapper SelectNoterMapper;
	
	
	/**
	 * 搜索记录 
	 * @param lookup
	 * @return
	 */
	public PageInfo<SelectNoter> lljlservice(BaseApiLookup lookup){
		Page<SelectNoter> page = startPage(lookup);
		List<SelectNoter> datas = SelectNoterMapper.ssjlList(lookup);
		return PageInfo.instance(page, datas, lookup);
	}

}
