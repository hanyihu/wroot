package com.vic.ck.console.platform.service;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.platform.mapper.CommodityManageMapper;
import com.vic.ck.entity.Commodity;
import com.vic.wroot.common.attachment.service.AttachmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品表
 *
 */
@Service
public class CommodityManageService extends BaseService {

	@Resource
    private CommodityManageMapper commodityManageMapper;

	@Resource
	private AttachmentService attachmentService;

	/**
	 * 查询列表
	 */
	public PageInfo<Commodity> list(Lookup lookup) {
		startPage(lookup);
        List<Commodity> datas = commodityManageMapper.list(lookup);
		return PageInfo.instance(datas, lookup);
	}
	/**
	 * 根据主键查询
	 */
	@Transactional
	public Commodity findById(int id) {
        return commodityManageMapper.findById(id);
	}

	/**
	 * 新增
	 */
	@Transactional
	public int insert(Commodity entity) {
		attachmentService.addAttachmengFromObj(entity);
        return commodityManageMapper.insert(entity);
	}

	/**
	 * 修改
	 */
	@Transactional
	public int update(Commodity entity) {
		Commodity old = findById(entity.getId());
		attachmentService.HandleOldAndNowAttachment(old, entity);
        return commodityManageMapper.update(entity);
	}

	/***
	 * 删除
	 */
	@Transactional
	public int delete(int... ids) {
        attachmentService.deleteAttachmengFromObj(commodityManageMapper.findByIds(ids));
        return commodityManageMapper.delete(ids);
	}


}
