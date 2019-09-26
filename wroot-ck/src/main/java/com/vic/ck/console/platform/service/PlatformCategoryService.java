package com.vic.ck.console.platform.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vic.base.BaseService;
import com.vic.base.model.BaseModel;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.platform.mapper.PlatformCategoryMapper;
import com.vic.ck.entity.PlatformCategory;
import com.vic.ck.entity.PlatformCategoryMerchant;
import com.vic.wroot.common.attachment.service.AttachmentService;

/**
 * 平台特色商家分类表
 *
 */
@Service
public class PlatformCategoryService extends BaseService {

	@Resource
	private PlatformCategoryMapper platformCategoryMapper;
	
	@Resource
	private AttachmentService attachmentService;
	

	/**
	 * 查询列表
	 */
	public PageInfo<PlatformCategory> list(Lookup lookup) {
		startPage(lookup);
		List<PlatformCategory> datas = platformCategoryMapper.list(lookup);
		return PageInfo.instance(datas, lookup);
	}

	/**
	 * 根据主键查询
	 */
	public PlatformCategory findById(int id) {
		return platformCategoryMapper.findById(id);
	}

	/**
	 * 新增
	 */
	@Transactional
	public int insert(PlatformCategory entity) {
		attachmentService.updateTemporary(false, entity.getIcon());
		return platformCategoryMapper.insert(entity);
	}

	/**
	 * 修改
	 */
	@Transactional
	public int update(PlatformCategory entity) {
		PlatformCategory old = findById(entity.getId());
		if(old.getIcon() != entity.getIcon()) {
			attachmentService.updateTemporary(true, old.getIcon());
			attachmentService.updateTemporary(false, entity.getIcon());
		}
		return platformCategoryMapper.update(entity);
	}

	/***
	 * 删除
	 */
	@Transactional
	public int delete(int... ids) {
		List<PlatformCategory> list = platformCategoryMapper.findByIds(ids);
		List<Integer> iconIds = new ArrayList<Integer>();
		for(PlatformCategory p :list) {
			iconIds.add(p.getIcon());
		}
		attachmentService.updateTemporary(true, iconIds.toArray(new Integer[0]));
		return platformCategoryMapper.delete(ids);
	}

	/**
	 * 获取当前城市下的分类
	 */
	public List<BaseModel> findCityCategoryList(int cityId) {
		return platformCategoryMapper.findCityCategoryList(cityId);
	}
	
	/**************** 特色分类和商家关系 ↓ ↓ ↓ ************************************/

	/** 特色分类商家列表 */
	public PageInfo<PlatformCategoryMerchant> relList(Lookup lookup) {
		startPage(lookup);
		List<PlatformCategoryMerchant> datas = platformCategoryMapper.relList(lookup);
		return PageInfo.instance(datas, lookup);
	}

	public PlatformCategoryMerchant findByIdRel(int id) {
		return platformCategoryMapper.findByIdRel(id);
	}

	/** 新增特色分类和商家关系 */
	public int insertRel(PlatformCategoryMerchant entity) {
		return platformCategoryMapper.insertRel(entity);
	}

	/** 更新关系 */
	public int updateRel(PlatformCategoryMerchant entity) {
		return platformCategoryMapper.updateRel(entity);
	}

	/** 删除关系 */
	public int deleteRel(int...ids) {
		return platformCategoryMapper.deleteRel(ids);
	}


}
