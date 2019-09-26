package com.vic.ck.console.mall.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.mall.mapper.ScoreMallGoodsMapper;
import com.vic.ck.entity.ScoreMallGoods;
import com.vic.wroot.common.attachment.service.AttachmentService;

/**
 * 积分商城商品
 *
 */
@Service
public class ScoreMallGoodsService extends BaseService {

	@Resource
	private ScoreMallGoodsMapper scoreMallGoodsMapper;

	@Resource
	private AttachmentService attachmentService;

	/**
	 * 查询列表
	 */
	public PageInfo<ScoreMallGoods> list(Lookup lookup) {
		startPage(lookup);
		List<ScoreMallGoods> datas = scoreMallGoodsMapper.list(lookup);
		return PageInfo.instance(datas, lookup);
	}

	/**
	 * 根据主键查询
	 */
	public ScoreMallGoods findById(int id) {
		return scoreMallGoodsMapper.findById(id);
	}

	/**
	 * 新增
	 */
	@Transactional
	public int insert(ScoreMallGoods entity) {
		attachmentService.addAttachmengFromObj(entity);
		return scoreMallGoodsMapper.insert(entity);
	}

	/**
	 * 修改
	 */
	@Transactional
	public int update(ScoreMallGoods entity) {
		ScoreMallGoods old = findById(entity.getId());
		attachmentService.HandleOldAndNowAttachment(old, entity);
		return scoreMallGoodsMapper.update(entity);
	}
	
	public int updateStatus(ScoreMallGoods entity){
		return scoreMallGoodsMapper.update(entity);
	}

	/***
	 * 删除
	 */
	@Transactional
	public int delete(int... ids) {
		List<ScoreMallGoods> list = scoreMallGoodsMapper.findByIds(ids);
		attachmentService.deleteAttachmengFromObj(list);
		return scoreMallGoodsMapper.delete(ids);
	}

}
