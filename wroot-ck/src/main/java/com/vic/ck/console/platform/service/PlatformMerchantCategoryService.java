package com.vic.ck.console.platform.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.platform.mapper.PlatformMerchantCategoryMapper;
import com.vic.ck.entity.PlatformMerchantCategory;
import com.vic.wroot.common.attachment.service.AttachmentService;

/**
 * 商家分类
 *
 */
@Service
public class PlatformMerchantCategoryService extends BaseService {

	@Resource
	private PlatformMerchantCategoryMapper platformMerchantCategoryMapper;
	
	@Resource
	private AttachmentService attachmentService;

	/**
	 * 查询列表
	 */
	public PageInfo<PlatformMerchantCategory> list(Lookup lookup) {
		startPage(lookup);
		List<PlatformMerchantCategory> datas = platformMerchantCategoryMapper.list(lookup);
		return PageInfo.instance(datas, lookup);
	}

	/**
	 * 根据主键查询
	 */
	public PlatformMerchantCategory findById(int id) {
		return platformMerchantCategoryMapper.findById(id);
	}

	/**
	 * 新增
	 */
	@Transactional
	public int insert(PlatformMerchantCategory entity) {
		attachmentService.updateTemporary(false, entity.getIcon());
		return platformMerchantCategoryMapper.insert(entity);
	}

	/**
	 * 修改
	 */
	@Transactional
	public int update(PlatformMerchantCategory entity) {
		PlatformMerchantCategory old = findById(entity.getId());
		if (old.getIcon() != entity.getIcon()) {
			attachmentService.updateTemporary(true, old.getIcon());
			attachmentService.updateTemporary(false, entity.getIcon());
		}
		return platformMerchantCategoryMapper.update(entity);
	}

	/***
	 * 删除
	 */
	public int delete(int... ids) {
		for(int id :ids){//暂时for循环写
			PlatformMerchantCategory old = findById(id);
			attachmentService.updateTemporary(true, old.getIcon());
		}
		return platformMerchantCategoryMapper.delete(ids);
	}

}
