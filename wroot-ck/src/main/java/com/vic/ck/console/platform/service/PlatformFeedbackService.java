package com.vic.ck.console.platform.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.platform.mapper.PlatformFeedbackMapper;
import com.vic.ck.entity.PlatformFeedback;
/**
 * 意见反馈表
 *
 */
@Service
public class PlatformFeedbackService extends BaseService{

	@Resource
	private PlatformFeedbackMapper platformFeedbackMapper;
	
	/**
    * 查询列表
    */
	public PageInfo<PlatformFeedback> list(Lookup lookup){
		startPage(lookup);
		List<PlatformFeedback> datas = platformFeedbackMapper.list(lookup);
		return PageInfo.instance(datas, lookup);
	}
	
	/**
	 * 根据主键查询
	 */
	public PlatformFeedback findById(int id) {
		return platformFeedbackMapper.findById(id);
	}
	
	/**
	 * 新增
	 */
	 public int insert(PlatformFeedback entity) {
		return platformFeedbackMapper.insert(entity);
	}
	
	/**
	 * 修改
	 */
	  public int update(PlatformFeedback entity) {
		  return platformFeedbackMapper.update(entity);
	  }
	  
	  /***
	   * 删除
	   */
	  public int delete(int...ids) {
		  return platformFeedbackMapper.delete(ids);
	  }
    
}
