package com.vic.ck.console.platform.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.platform.mapper.PlatformBannerMapper;
import com.vic.ck.entity.PlatformBanner;
import com.vic.wroot.common.attachment.service.AttachmentService;

/**
 * banner图
 *
 */
@Service
public class PlatformBannerService extends BaseService {

	@Resource
	private PlatformBannerMapper platformBannerMapper;
	
	@Resource
	private AttachmentService attachmentService;

	/**
	 * 查询列表
	 */
	public PageInfo<PlatformBanner> list(Lookup lookup) {
		startPage(lookup);
		List<PlatformBanner> datas = platformBannerMapper.list(lookup);
		return PageInfo.instance(datas, lookup);
	}

	/**
	 * 根据主键查询
	 */
	public PlatformBanner findById(int id) {
		return platformBannerMapper.findById(id);
	}

	/**
	 * 新增
	 */
	@Transactional
	public int insert(PlatformBanner entity) {
		attachmentService.updateTemporary(false, entity.getImage());
		return platformBannerMapper.insert(entity);
	}

	/**
	 * 修改
	 */
	@Transactional
	public int update(PlatformBanner entity) {
		PlatformBanner old = findById(entity.getId());
		if(old.getImage() != entity.getImage()) {
			attachmentService.updateTemporary(true, old.getImage());
			attachmentService.updateTemporary(false, entity.getImage());
		}
		return platformBannerMapper.update(entity);
	}

	/***
	 * 删除
	 */
	public int delete(int... ids) {
		for(int id :ids){//暂时for循环写
			PlatformBanner old = findById(id);
			attachmentService.updateTemporary(true, old.getImage());
		}
		return platformBannerMapper.delete(ids);
	}

}
