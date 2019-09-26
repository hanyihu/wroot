package com.vic.ck.console.platform.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.platform.mapper.PlatformBankMapper;
import com.vic.ck.entity.PlatformBank;
import com.vic.wroot.common.attachment.service.AttachmentService;

/**
 * 银行表
 *
 */
@Service
public class PlatformBankService extends BaseService {

	@Resource
	private PlatformBankMapper platformBankMapper;

	@Resource
	private AttachmentService attachmentService;

	/**
	 * 查询列表
	 */
	public PageInfo<PlatformBank> list(Lookup lookup) {
		startPage(lookup);
		List<PlatformBank> datas = platformBankMapper.list(lookup);
		return PageInfo.instance(datas, lookup);
	}

	/**
	 * 根据主键查询
	 */
	public PlatformBank findById(int id) {
		return platformBankMapper.findById(id);
	}

	/**
	 * 新增
	 */
	@Transactional
	public int insert(PlatformBank entity) {
		attachmentService.updateTemporary(false, entity.getIcon());
		return platformBankMapper.insert(entity);
	}

	/**
	 * 修改
	 */
	@Transactional
	public int update(PlatformBank entity) {
		attachmentService.HandleOldAndNowAttachment(findById(entity.getId()), entity);
		return platformBankMapper.update(entity);
	}

	/***
	 * 删除
	 */
	@Transactional
	public int delete(int... ids) {
		attachmentService.deleteAttachmengFromObj(platformBankMapper.findByIds(ids));
		return platformBankMapper.delete(ids);
	}

}
