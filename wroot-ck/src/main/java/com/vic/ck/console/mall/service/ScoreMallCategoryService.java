package com.vic.ck.console.mall.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.mall.mapper.ScoreMallCategoryMapper;
import com.vic.ck.entity.ScoreMallCategory;
import com.vic.wroot.common.attachment.service.AttachmentService;
/**
 * 积分商城分类
 *
 */
@Service
public class ScoreMallCategoryService extends BaseService{

	@Resource
	private ScoreMallCategoryMapper scoreMallCategoryMapper;
	
	@Resource
	private AttachmentService attachmentService;
	
	/**
    * 查询列表
    */
	public PageInfo<ScoreMallCategory> list(Lookup lookup){
		startPage(lookup);
		List<ScoreMallCategory> datas = scoreMallCategoryMapper.list(lookup);
		return PageInfo.instance(datas, lookup);
	}
	
	/**
    *  列表
    */
	public List<ScoreMallCategory> categorys(Lookup lookup){
		List<ScoreMallCategory> datas = scoreMallCategoryMapper.list(lookup);
		return datas;
	}
	
	/**
	 * 根据主键查询
	 */
	public ScoreMallCategory findById(int id) {
		return scoreMallCategoryMapper.findById(id);
	}
	
	/**
	 * 新增
	 */
	 public int insert(ScoreMallCategory entity) {
		 attachmentService.updateTemporary(false, entity.getIcon());
		return scoreMallCategoryMapper.insert(entity);
	}
	
	/**
	 * 修改
	 */
	  public int update(ScoreMallCategory entity) {
		  attachmentService.HandleOldAndNowAttachment(findById(entity.getId()), entity);
		  return scoreMallCategoryMapper.update(entity);
	  }
	  
	  /***
	   * 删除
	   */
	  public int delete(int...ids) {
		  attachmentService.deleteAttachmengFromObj(scoreMallCategoryMapper.findByIds(ids));
		  return scoreMallCategoryMapper.delete(ids);
	  }
    
}
