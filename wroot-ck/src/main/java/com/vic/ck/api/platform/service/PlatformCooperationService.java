package com.vic.ck.api.platform.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.platform.mapper.PlatformCooperationMapper;
import com.vic.ck.entity.PlatformCooperation;
/**
 * 合作申请表
 *
 */
@Service
public class PlatformCooperationService extends BaseService{

	@Resource
	private PlatformCooperationMapper platformCooperationMapper;
	
	/**
    * 查询列表
    */
	public PageInfo<PlatformCooperation> list(Lookup lookup){
		startPage(lookup);
		List<PlatformCooperation> datas = platformCooperationMapper.list(lookup);
		return PageInfo.instance(datas, lookup);
	}
	
	/**
	 * 根据主键查询
	 */
	public PlatformCooperation findById(int id) {
		return platformCooperationMapper.findById(id);
	}
	
	/**
	 * 新增
	 */
	 public int insert(PlatformCooperation entity) {
		return platformCooperationMapper.insert(entity);
	}
	
	/**
	 * 修改
	 */
	  public int update(PlatformCooperation entity) {
		  return platformCooperationMapper.update(entity);
	  }
	  
	  /***
	   * 删除
	   */
	  public int delete(int...ids) {
		  return platformCooperationMapper.delete(ids);
	  }
    
}
