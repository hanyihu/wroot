package com.vic.ck.console.system.area.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vic.ck.console.system.area.mapper.SysAreaMapper;
import com.vic.ck.entity.SysArea;
import com.vic.base.BaseService;
import com.vic.base.model.BaseModel;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import java.util.List;

/**
 * 区域
 *
 */
@Service
public class SysAreaService extends BaseService {

	@Resource
	private SysAreaMapper sysAreaMapper;

	/**
	 * 查询列表
	 */
	public PageInfo<SysArea> list(Lookup lookup) {
		startPage(lookup);
		List<SysArea> datas = sysAreaMapper.list(lookup);
		return PageInfo.instance(datas, lookup);
	}

	/**
	 * 根据主键查询
	 */
	public SysArea findById(int id) {
		return sysAreaMapper.findById(id);
	}

	/**
	 * 新增
	 */
	public int insert(SysArea entity) {
		return sysAreaMapper.insert(entity);
	}

	/**
	 * 修改
	 */
	public int update(SysArea entity) {
		return sysAreaMapper.update(entity);
	}

	/***
	 * 删除
	 */
	public int delete(int... ids) {
		return sysAreaMapper.delete(ids);
	}

	/** 全部的省 */
	public List<BaseModel> provinces() {
		return sysAreaMapper.provinces();
	}
	
	/**
	 * 获得子区域
	 */
	public List<BaseModel> findChildren(int parentId){
		return sysAreaMapper.findChildren(parentId);
	}
	/**全部开发的城市*/
	public List<BaseModel> opendCityList() {
		return sysAreaMapper.opendCityList();
	}

}
