package com.vic.ck.console.platform.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.platform.mapper.PlatformCategoryAdPositionMapper;
import com.vic.ck.entity.PlatformCategoryAdPosition;
import com.vic.wroot.common.attachment.service.AttachmentService;

/**
 * 平台商品分类商家广告位
 *
 */
@Service
public class PlatformCategoryAdPositionService extends BaseService {

	@Resource
	private PlatformCategoryAdPositionMapper platformCategoryAdPositionMapper;

	@Resource
	private AttachmentService attachmentService;

	/**
	 * 查询列表
	 */
	public PageInfo<PlatformCategoryAdPosition> list(Lookup lookup) {
		startPage(lookup);
		List<PlatformCategoryAdPosition> datas = platformCategoryAdPositionMapper.list(lookup);
		return PageInfo.instance(datas, lookup);
	}

	/**
	 * 根据主键查询
	 */
	public PlatformCategoryAdPosition findById(int id) {
		return platformCategoryAdPositionMapper.findById(id);
	}

	/**
	 * 新增
	 */
	@Transactional
	public int insert(PlatformCategoryAdPosition entity) {
		attachmentService.updateTemporary(false, entity.getIcon());
		return platformCategoryAdPositionMapper.insert(entity);
	}

	/**
	 * 修改
	 */
	@Transactional
	public int update(PlatformCategoryAdPosition entity) {
		PlatformCategoryAdPosition old = findById(entity.getId());
		if(old.getIcon() != entity.getIcon()) {
			attachmentService.updateTemporary(true, old.getIcon());
			attachmentService.updateTemporary(false, entity.getIcon());
		}
		return platformCategoryAdPositionMapper.update(entity);
	}

	/***
	 * 删除
	 */
	@Transactional
	public int delete(int... ids) {
		List<PlatformCategoryAdPosition> list = platformCategoryAdPositionMapper.findByIds(ids);
		attachmentService.deleteAttachmengFromObj(list);
		return platformCategoryAdPositionMapper.delete(ids);
	}

}
