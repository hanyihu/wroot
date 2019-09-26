package com.vic.ck.api.platform.mapper;

import org.apache.ibatis.annotations.Param;

import com.vic.base.BaseMapper;
import com.vic.ck.entity.OrderComment;
import com.vic.wroot.common.annotation.MybatisMapper;

/**
 * 订单评论表
 * 
 * @author VIC
 */
@MybatisMapper
public interface OrderCommentMapper extends BaseMapper<OrderComment> {

	/**评论商家之后的计算评星*/
	int afterComment(@Param("merchantId")Integer merchantId);

	/**计算团购的评星*/
	int updateGroupBuyComment(@Param("id")Integer id);

	OrderComment findByOrderno(@Param("orderno")String orderno);

	int calcPraiseNum(@Param("id")int id);
	
}
