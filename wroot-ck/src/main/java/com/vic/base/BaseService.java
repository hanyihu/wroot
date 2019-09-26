package com.vic.base;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vic.base.pager.Lookup;
/**
 * base service  for PageHelper
 * @author VIC
 *
 */
public abstract class BaseService {
	

	protected <T> Page<T> startPage(Lookup lookup) {
		return  PageHelper.startPage(lookup.getPage(), lookup.getSize()); // 核心分页代码  
	}
}
