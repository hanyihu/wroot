package com.vic.ck.console.platform.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.platform.mapper.PlatformBootPageMapper;
import com.vic.ck.entity.PlatformBootPage;
import com.vic.wroot.common.attachment.service.AttachmentService;
/**
 * 系统启动页
 *
 */
@Service
public class PlatformBootPageService extends BaseService{

	@Resource
	private PlatformBootPageMapper platformBootPageMapper;
	
	@Resource
	private AttachmentService attachmentService;
	
	/**
    * 查询列表
    */
	public PageInfo<PlatformBootPage> list(Lookup lookup){
		startPage(lookup);
		List<PlatformBootPage> datas = platformBootPageMapper.list(lookup);
		return PageInfo.instance(datas, lookup);
	}
	
	/**
	 * 根据主键查询
	 */
	public PlatformBootPage findById(int id) {
		return platformBootPageMapper.findById(id);
	}
	
	/**
	 * 新增
	 */
	 public int insert(PlatformBootPage entity) {
		 attachmentService.updateTemporary(false, entity.getImage());
		return platformBootPageMapper.insert(entity);
	}
	
	/**
	 * 修改
	 */
	  public int update(PlatformBootPage entity) {
		  PlatformBootPage old = findById(entity.getId());
			if(old.getImage() != entity.getImage()) {
				attachmentService.updateTemporary(true, old.getImage());
				attachmentService.updateTemporary(false, entity.getImage());
			}
		  return platformBootPageMapper.update(entity);
	  }
	  
	  /***
	   * 删除
	   */
	  public int delete(int...ids) {
		  for(int id :ids){//暂时for循环写
			  PlatformBootPage old = findById(id);
				attachmentService.updateTemporary(true, old.getImage());
			}
		  return platformBootPageMapper.delete(ids);
	  }
    
}
