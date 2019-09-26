package com.vic.ck.console.platform.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.platform.mapper.PlatformAdPositionMapper;
import com.vic.ck.entity.PlatformAdPosition;
import com.vic.wroot.common.attachment.service.AttachmentService;

/**
 * 平台广告位表
 *
 */
@Service
public class PlatformAdPositionService extends BaseService {

	@Resource
	private PlatformAdPositionMapper platformAdPositionMapper;

	@Resource
	private AttachmentService attachmentService;

	/**
	 * 查询列表
	 */
	public PageInfo<PlatformAdPosition> list(Lookup lookup) {
		startPage(lookup);
		List<PlatformAdPosition> datas = platformAdPositionMapper.list(lookup);
		return PageInfo.instance(datas, lookup);
	}

	/**
	 * 根据主键查询
	 */
	public PlatformAdPosition findById(int id) {
		return platformAdPositionMapper.findById(id);
	}

	/**
	 * 新增
	 */
	@Transactional
	public int insert(PlatformAdPosition entity) {
		attachmentService.updateTemporary(false, entity.getIcon());
		return platformAdPositionMapper.insert(entity);
	}

	/**
	 * 修改
	 */
	@Transactional
	public int update(PlatformAdPosition entity) {
		PlatformAdPosition old = platformAdPositionMapper.findById(entity.getId());
		if(old.getIcon() != entity.getIcon()) {
			attachmentService.updateTemporary(true, old.getIcon());
			attachmentService.updateTemporary(false, entity.getIcon());
		}
		return platformAdPositionMapper.update(entity);
	}

	/***
	 * 删除
	 */
	public int delete(int... ids) {
		List<PlatformAdPosition> datas = platformAdPositionMapper.findByIds(ids);
		attachmentService.deleteAttachmengFromObj(datas);
		return platformAdPositionMapper.delete(ids);
	}

}
