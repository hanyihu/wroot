package com.vic.ck.api.personal.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.vic.base.BaseApiLookup;
import com.vic.base.BaseService;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.constant.FinalFiledParams;
import com.vic.ck.api.personal.mapper.LookNoterMapper;
import com.vic.ck.api.platform.mapper.CollectionMapper;
import com.vic.ck.console.mall.service.CustomerSwapService;
import com.vic.ck.entity.CustomerCollection;
import com.vic.ck.entity.CustomerSwap;
import com.vic.ck.entity.LookNoter;
import com.vic.ck.util.GeneratorNoUtils;

@Service
public class LookNoterService  extends BaseService {

	
	@Resource
	private LookNoterMapper lookNoterMapper;
	
	
	/**
	 * 浏览记录 
	 * @param lookup
	 * @return
	 */
	public PageInfo<LookNoter> lljlservice(BaseApiLookup lookup){
		Page<LookNoter> page = startPage(lookup);
		List<LookNoter> datas = lookNoterMapper.lljlList(lookup);
		return PageInfo.instance(page, datas, lookup);
	}

}
