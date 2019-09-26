package com.vic.ck.api.platform.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.platform.mapper.OrderCommentMapper;
import com.vic.ck.entity.OrderComment;
import com.vic.wroot.common.attachment.service.AttachmentService;

/**
 * 订单评论表
 *
 */
@Service
public class OrderCommentService extends BaseService {

	@Resource
	private OrderCommentMapper orderCommentMapper;
	
	@Resource
	private AttachmentService attachmentService;

	/**
	 * 查询列表
	 */
	public PageInfo<OrderComment> list(Lookup lookup) {
		startPage(lookup);
		List<OrderComment> datas = orderCommentMapper.list(lookup);
		return PageInfo.instance(datas, lookup);
	}

	/**
	 * 根据主键查询
	 */
	public OrderComment findById(int id) {
		return orderCommentMapper.findById(id);
	}
	
	public OrderComment findByOrderno(String orderno) {
		return orderCommentMapper.findByOrderno(orderno);
	}

	/**
	 * 新增
	 */
	public int insert(OrderComment entity) {
		attachmentService.addAttachmengFromObj(entity);
		return orderCommentMapper.insert(entity);
	}

	/**
	 * 修改
	 */
	public int update(OrderComment entity) {
		return orderCommentMapper.update(entity);
	}

	/***
	 * 删除
	 */
	public int delete(int... ids) {
		return orderCommentMapper.delete(ids);
	}
	
	/**计算点赞数*/
	public int calcPraiseNum(int id){
		return orderCommentMapper.calcPraiseNum(id);
	}

	/**
	 * 评论商家之后的计算评星
	 */
	public void afterComment(OrderComment comment) {
		
		if (comment.getType() != null && comment.getCommodityId() != null) {
			switch (comment.getType()) {
			case 1://商家 总会计算 因为一个评论肯定是针对一个商家

				break;
			case 2://团购 评星 和评论数
				orderCommentMapper.updateGroupBuyComment(comment.getCommodityId());
				break;
			case 3: //酒店 暂时没有评论的需求
				
				break;
			default:
				break;
			}
		}
		// 评论商家之后的计算评星
		orderCommentMapper.afterComment(comment.getMerchantId());
		

	}

}
