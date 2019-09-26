package com.vic.ck.api.platform.service;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vic.base.BaseApiLookup;
import com.vic.base.BaseService;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.constant.FinalFiledParams;
import com.vic.ck.api.merchant.service.MerchantService;
import com.vic.ck.api.personal.service.PersonalService;
import com.vic.ck.api.platform.lookup.CommodityLookup;
import com.vic.ck.api.platform.mapper.PlatformCommonMapper;
import com.vic.ck.api.system.service.AuthService;
import com.vic.ck.api.system.service.CustomerBalanceService;
import com.vic.ck.entity.CommentPraise;
import com.vic.ck.entity.Customer;
import com.vic.ck.entity.CustomerShareRecord;
import com.vic.ck.entity.Merchant;
import com.vic.ck.entity.Order;
import com.vic.ck.entity.PlatformFeedback;
import com.vic.ck.entity.PlatformMerchantCategory;
import com.vic.ck.vo.CommentVo;
import com.vic.ck.vo.SignVo;
import com.vic.wroot.common.attachment.service.AttachmentService;
import com.vic.wroot.common.util.CommonUtils;

@Service
public class PlatformCommonService extends BaseService{
	
	public static Logger logger = LoggerFactory.getLogger(PlatformCommonService.class);

	@Resource
	private PlatformCommonMapper platformCommonMapper;
	
	@Resource
	private CustomerBalanceService customerBalanceService;
	
	@Resource
	private AttachmentService attachmentService;
	
	@Resource 
	private AuthService authService;
	
	@Resource
	private MerchantService merchantService;
	
	@Resource
	private PersonalService personalService;
	
	@Resource
	private JpushMsgService jpushMsgService;
	
	
	/**新增意见反馈*/
	public int insertFeedback(PlatformFeedback feedback) {
		attachmentService.addAttachmengFromObj(feedback);
		return platformCommonMapper.insertFeedback(feedback);
	}
	
	/**
	 * 商家分类
	 */
	public List<PlatformMerchantCategory> merchantCategories(){
		return platformCommonMapper.merchantCategories();
	}
	/**
	 * 商家分类map 
	 * type--对应分类
	 * @return
	 */
	public Map<Integer, List<PlatformMerchantCategory>> merchantCategoriesTree(){
		List<PlatformMerchantCategory> all = merchantCategories();
		Map<Integer, List<PlatformMerchantCategory>> result = new HashMap<Integer, List<PlatformMerchantCategory>>();
		for(PlatformMerchantCategory p : all) {
			int type = p.getType();
			List<PlatformMerchantCategory> categorys = result.get(type);
			if(categorys == null) {
				categorys = new ArrayList<PlatformMerchantCategory>();
				result.put(type, categorys);
			}
			categorys.add(p);
		}
		
		return result;
	}
	
	/**当日是否签到*/
	public boolean isSign(int customerId, int merchantId) {
		return platformCommonMapper.isSign(customerId, merchantId);
	}
	
	/**用户评论列表 2条 并判断是否已经点赞---后来改为3条*/
	public List<CommentVo> merchantComments(int merchantId, int userId){
		return platformCommonMapper.merchantComments(merchantId, userId);
		
	}
	
	/**评论是否已经点赞*/
	public boolean isPraise(int commentId, int userId) {
		return platformCommonMapper.isPraise(new CommentPraise(userId, commentId, 1));
	}
	
	/**对象是否已经点赞*/
	public boolean isPraise(int commentId, int userId, int type) {
		return platformCommonMapper.isPraise(new CommentPraise(userId, commentId, type));
	}
	
	/**查询点赞的那个id*/
	public Integer findPraiseId(CommentPraise comment) {
		return platformCommonMapper.findPraiseId(comment);
	}
	
	/**点赞评论*/
	public int insertPraise(CommentPraise praise) {
		
		return platformCommonMapper.insertPraise(praise);
	}
	
	/**删除点赞*/
	public int deletePraise(int id){
		return platformCommonMapper.deletePraise(id);
	}

	/** 商家/团购评论列表*/
	public PageInfo<CommentVo> comments(CommodityLookup lookup) {
		startPage(lookup);
		List<CommentVo> datas = platformCommonMapper.comments(lookup);
		return PageInfo.instance(datas);
	}

	/**标记删除评论*/
	public void removeComment(int id) {
		platformCommonMapper.removeComment(id);
		
	}
	
	/**回复*/
	public void replyComment(int id, String reply) {
		platformCommonMapper.replyComment(id, reply);
	}
	
	/**商家的签到列表*/
	public PageInfo<SignVo> signs(BaseApiLookup lookup) {
		startPage(lookup);
		List<SignVo> datas = platformCommonMapper.signs(lookup);
		return PageInfo.instance(datas);
	}

	/**签到商家*/
	public void sign(int userId, int merchantId) {
		platformCommonMapper.sign(userId, merchantId);
		//计算这个商家的签到数量
		platformCommonMapper.calcViewNum(merchantId);
		//签到获取积分 同一天只有前5次送积分
		int signNum = personalService.findSignNum(userId, FinalFiledParams.SCORE_SIGN);
		if(signNum < 5 ) {
			personalService.addCustomerScoreRecord(FinalFiledParams.SCORE_SIGN_NUMBER, FinalFiledParams.SCORE_SIGN, userId);
		}
	}
	
	
	
	/**
	 * 今日分享次数
	 */
	public int findSharedNum(int customerId) {
		return platformCommonMapper.findSharedNum(customerId);
	}

	/**分享记录 并获得积分*/
	public int share(int customerId, int type, String target) {
		platformCommonMapper.insertShare(new CustomerShareRecord(customerId, type, target));
		personalService.addCustomerScoreRecord(FinalFiledParams.SCORE_SHARE_NUMBER, FinalFiledParams.SCORE_SHARE, customerId);
		return FinalFiledParams.SCORE_SHARE_NUMBER;
	}

	
	
	
	/*******************************************************************************/
	
	
	/**
	 * 返点结算
	 *  1 直接买单消费，等于是交易成功了，用户马上获得1%的返点
	 *	2 团购消费，只有等团购券号验证后，才算是交易成功，用户马上获得1%的返点
	 *	3 会员推荐用户注册，获得用户消费金额的0.5%返点
	 *	4 会员推荐商家注册，获得商家流水金额的1%返点
	 */
	@Transactional
	public void rebateCalculation(Order order) {
		logger.info("开始进行返点结算 订单：{} ", CommonUtils.toJson(order));
		//当前用户
		Customer c = authService.findCustomerById(order.getCustomerId());
		if(c == null) return;
		//1 消费人获得百分之一的返点
		BigDecimal cutomerRebate = order.getAmount().divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
		customerBalanceService.addBalance(cutomerRebate, FinalFiledParams.BALANCE_BUY, order.getCustomerId());
		logger.info("{}消费获得返点{}", c.getNickname(), cutomerRebate);
		
		//8 用户消费，立返奖励到账通知
		jpushMsgService.pushToCustomer(c.getId(), MessageFormat.format("消费立返奖励{0}元到账",  cutomerRebate), c.getMobileCode());
		
		//2 推荐用户注册 获得 0.5%的返点
		//当前人是谁推荐的：
		if(c.getRecommendId() != null) {
			Customer recommentCustomer = authService.findCustomerById(c.getRecommendId());
			if(recommentCustomer != null) {
				BigDecimal cutomerRecommentRebate = order.getAmount().divide(new BigDecimal(200)).setScale(2, BigDecimal.ROUND_HALF_UP);
				customerBalanceService.addBalance(cutomerRecommentRebate, FinalFiledParams.BALANCE_RECOMMENT_BUY, c.getRecommendId(),
						MessageFormat.format("用户{0}消费产生奖励{1}元", c.getNickname(), cutomerRecommentRebate));
				logger.info("{}推荐 {}消费 获得返点{}", new Object[] {recommentCustomer.getNickname(), c.getNickname(), cutomerRecommentRebate });
				
				//6 好友消费获得分润，分润到账通知
				jpushMsgService.pushToCustomer(c.getRecommendId(), MessageFormat.format("好友{0}消费获得分润，分润{1}元到账", c.getNickname(), cutomerRecommentRebate), recommentCustomer.getMobileCode());
			}
		}
		
		//3 推荐商家注册，获得商家流水金额的1%返点
		//当前商家是谁推荐的：
		Merchant merchant = merchantService.getMerchantById(order.getMerchantId());
		if(merchant !=null && merchant.getRecommendId() != null) {
			Customer recommentMerchantCustomer = authService.findCustomerById(merchant.getRecommendId());
			if(recommentMerchantCustomer != null) {
				BigDecimal merchantRecommentRebate = order.getAmount().divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
				customerBalanceService.addBalance(merchantRecommentRebate, FinalFiledParams.BALANCE_RECOMMENT_SELL, merchant.getRecommendId(), 
						MessageFormat.format("商家{0}盈利收入产生奖励{1}元", merchant.getName(), merchantRecommentRebate));
				logger.info("{}推荐 商家{} 获得返点{}", new Object[] {recommentMerchantCustomer.getNickname(), merchant.getName(), merchantRecommentRebate });
				
				//7 商家收入获得分润，分润到账通知
				jpushMsgService.pushToCustomer(merchant.getRecommendId(), MessageFormat.format("商家{0}收入获得分润，分润{1}元到账", merchant.getName(), merchantRecommentRebate), recommentMerchantCustomer.getMobileCode());
			}
			
		}
		
		
	}
	
	

	
	
}
