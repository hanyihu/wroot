package com.vic.ck.console.platform.service;

import com.vic.base.BaseService;
import com.vic.base.model.BaseModel;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.platform.mapper.PlatformAdPositionMapper;
import com.vic.ck.console.platform.mapper.PlatformCommodityCategoryMapper;
import com.vic.ck.entity.PlatformAdPosition;
import com.vic.ck.entity.PlatformCommodityCategory;
import com.vic.wroot.common.attachment.service.AttachmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品分类
 *
 */
@Service
public class PlatformCommodityCategoryService extends BaseService {

	@Resource
	private AttachmentService attachmentService;

	@Resource
	PlatformCommodityCategoryMapper platformCommodityCategoryMapper;
	/**
	 * 查询列表
	 */
	public PageInfo<PlatformCommodityCategory> list(Lookup lookup) {
		startPage(lookup);
		List<PlatformCommodityCategory> datas = platformCommodityCategoryMapper.list(lookup);
		return PageInfo.instance(datas, lookup);
	}

	/**
	 * 根据主键查询
	 */
	public PlatformCommodityCategory findById(int id) {
		return platformCommodityCategoryMapper.findById(id);
	}

	/**
	 * 新增
	 */
	@Transactional
	public int insert(PlatformCommodityCategory entity) {
		attachmentService.updateTemporary(false, entity.getIcon());
		return platformCommodityCategoryMapper.insert(entity);
	}

	/**
	 * 修改
	 */
	@Transactional
	public int update(PlatformCommodityCategory entity) {
		PlatformCommodityCategory old = platformCommodityCategoryMapper.findById(entity.getId());
		if(old.getIcon() != entity.getIcon()) {
			attachmentService.updateTemporary(true, old.getIcon());
			attachmentService.updateTemporary(false, entity.getIcon());
		}
		return platformCommodityCategoryMapper.update(entity);
	}

	/***
	 * 删除
	 */
	public int delete(int... ids) {
		List<PlatformCommodityCategory> datas = platformCommodityCategoryMapper.findByIds(ids);
		attachmentService.deleteAttachmengFromObj(datas);
		return platformCommodityCategoryMapper.delete(ids);
	}

	/*
	* 查询上级分类
	* */
	public List<BaseModel> categoryList(Integer pid){
        return platformCommodityCategoryMapper.categoryList(pid);
	}


}
