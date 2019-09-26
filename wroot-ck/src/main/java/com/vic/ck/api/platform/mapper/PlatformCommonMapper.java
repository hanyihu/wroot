package com.vic.ck.api.platform.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vic.base.BaseApiLookup;
import com.vic.base.pager.Lookup;
import com.vic.ck.api.platform.lookup.CommodityLookup;
import com.vic.ck.entity.CommentPraise;
import com.vic.ck.entity.CustomerShareRecord;
import com.vic.ck.entity.PlatformFeedback;
import com.vic.ck.entity.PlatformMerchantCategory;
import com.vic.ck.vo.CommentVo;
import com.vic.ck.vo.SignVo;
import com.vic.wroot.common.annotation.MybatisMapper;

@MybatisMapper
public interface PlatformCommonMapper {

	/** 新增意见反馈 */
	int insertFeedback(PlatformFeedback feedback);

	/** 意见反馈列表 */
	List<PlatformFeedback> feedbacks(Lookup lookup);

	/** 商家分类 */
	List<PlatformMerchantCategory> merchantCategories();

	/** 用户评论列表 2条 并判断是否已经点赞 */
	List<CommentVo> merchantComments(@Param("merchantId") int merchantId, @Param("customerId") int customerId);

	/** 商家/团购评论列表 */
	List<CommentVo> comments(CommodityLookup lookup);

	/** 用户是否已经签到 */
	boolean isSign(@Param("customerId") int customerId, @Param("merchantId") int merchantId);

	/** 商家的签到列表 */
	List<SignVo> signs(BaseApiLookup lookup);

	/** 签到商家 */
	void sign(@Param("customerId") int customerId, @Param("merchantId") int merchantId);
	
	/**计算此商家的签到数量 */
	void calcViewNum(@Param("id") int id);

	/** 对评论是否已经点赞 */
	boolean isPraise(CommentPraise praise);

	/** 获得评论的id */
	Integer findPraiseId(CommentPraise praise);

	/** 对评论点赞 */
	int insertPraise(CommentPraise praise);
	
	/**删除点杂*/
	int deletePraise(@Param("id")int id);

	/**标记删除订单评论*/
	void removeComment(@Param("id") int id);

	void replyComment(@Param("id") int id, @Param("reply") String reply);

	/** 今日分享的次数 */
	int findSharedNum(@Param("customerId") int customerId);

	/** 新增分享 */
	int insertShare(CustomerShareRecord entity);

	

}
