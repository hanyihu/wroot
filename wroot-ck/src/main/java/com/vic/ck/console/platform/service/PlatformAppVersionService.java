package com.vic.ck.console.platform.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.platform.mapper.PlatformAppVersionMapper;
import com.vic.ck.entity.PlatformAppVersion;
import com.vic.wroot.common.attachment.service.AttachmentService;

/**
 * app版本管理
 *
 * 
 */
@Service
public class PlatformAppVersionService extends BaseService {

	@Resource
	private PlatformAppVersionMapper platformAppVersionMapper;

	@Resource
	private AttachmentService attachmentService;

	/**
	 * 查询列表
	 */
	public PageInfo<PlatformAppVersion> list(Lookup lookup) {
		startPage(lookup);
		List<PlatformAppVersion> datas = platformAppVersionMapper.list(lookup);
		return PageInfo.instance(datas, lookup);
	}

	/**
	 * 根据主键查询
	 */
	public PlatformAppVersion findById(int id) {
		return platformAppVersionMapper.findById(id);
	}

	/**
	 * 新增
	 */
	@Transactional
	public int insert(PlatformAppVersion entity) {
		attachmentService.updateTemporary(false, entity.getAndroidUrl());
		return platformAppVersionMapper.insert(entity);
	}

	/**
	 * 修改
	 */
	@Transactional
	public int update(PlatformAppVersion entity) {
		attachmentService.HandleOldAndNowAttachment(findById(entity.getId()), entity);
		return platformAppVersionMapper.update(entity);
	}

	/***
	 * 删除
	 */
	@Transactional
	public int delete(int... ids) {
		List<PlatformAppVersion> list = platformAppVersionMapper.findByIds(ids);
		attachmentService.deleteAttachmengFromObj(list);
		return platformAppVersionMapper.delete(ids);
	}

	/** 当前最新版本 */
	public PlatformAppVersion currentVersion(int appType, int machineType) {
		return platformAppVersionMapper.currentVersion(appType, machineType);
	}

}
