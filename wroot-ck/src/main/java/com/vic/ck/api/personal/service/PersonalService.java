package com.vic.ck.api.personal.service;

import com.github.pagehelper.Page;
import com.vic.base.BaseApiLookup;
import com.vic.base.BaseService;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.constant.FinalFiledParams;
import com.vic.ck.api.elasticSearch.service.ElasticSearchService;
import com.vic.ck.api.personal.mapper.PersonalMapper;
import com.vic.ck.api.system.mapper.AuthMapper;
import com.vic.ck.api.system.service.CustomerBalanceService;
import com.vic.ck.entity.*;
import com.vic.ck.vo.RecommentSurveyVo;
import com.vic.ck.vo.RecommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class PersonalService extends BaseService {

	@Resource
	private PersonalMapper personalMapper;

	@Resource
	private CustomerBalanceService customerBalanceService;

	@Resource
	private AuthMapper authMapper;

    @Autowired
    private ElasticSearchService elasticSearchService;

	/**
	 * 收货地址列表
	 * 
	 * @param lookup
	 * @return
	 */
	public PageInfo<CustomerDeliveryAddress> deliveries(BaseApiLookup lookup) {
		Page<CustomerDeliveryAddress> page = startPage(lookup);
		List<CustomerDeliveryAddress> datas = personalMapper.deliveries(lookup);
		return PageInfo.instance(page, datas, lookup);
	}

	/** 地址详情 */
	public CustomerDeliveryAddress deliverieDetail(int id) {
		return personalMapper.deliverieDetail(id);
	}

	/**
	 * 新增用户收货地址
	 * 
	 * @param address
	 */
	public void addDelivery(CustomerDeliveryAddress address) {
		personalMapper.addDelivery(address);
	}
	
	/**
	 * 修改用户收货地址
	 * @param address
	 */
	public void updateDelivery(CustomerDeliveryAddress address) {
		personalMapper.updateDelivery(address);
	}

	/** 删除用户收货地址 */
	public void deleteDelivery(int id) {
		personalMapper.deleteDelivery(id);

	}

	/**
	 * 我的余额+我的佣金
	 * 
	 * @param userId
	 * @return
	 */
	public BigDecimal balance(int userId) {
		CustomerBalance ba = customerBalanceService.getCustomerBalance(userId);
		if (ba != null) {
			return ba.getBalance();
		}
		return new BigDecimal(0.00);
	}
 
	/** 我绑定的银行卡 */
	public PageInfo<CustomerBankCard> bankCards(BaseApiLookup lookup) {
		Page<CustomerBankCard> page = startPage(lookup);
		List<CustomerBankCard> datas = personalMapper.bankCards(lookup);
		return PageInfo.instance(page, datas, lookup);
	}

	/**
	 * 新增用户银行卡
	 */
	public void addBankCard(CustomerBankCard bankCard) {
		personalMapper.addBankCards(bankCard);
	}

	/**
	 * 删除用户银行卡
	 */
	public void deleteBankCard(int id) {
		personalMapper.deleteBankCards(id);
	}

	/** 现金提现申请 REPEATABLE_READ 就可以了*/
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public void fetch(CustomerFetchMoney money) {
		//减少余额 并记录
		customerBalanceService.addBalance(money.getMoney().negate(), FinalFiledParams.BALANCE_FETCH, money.getCustomerId());
		// 插入申请记录
		personalMapper.fetch(money);
	}

	/** 判断用户的银行卡 */
	public boolean judgeCustomerCard(CustomerFetchMoney money) {
		CustomerBankCard c = getBankCard(money.getCardId());
		if (c != null && c.getCustomerId() == money.getCustomerId()) {
			return true;
		}
		return false;

	}

	public CustomerBankCard getBankCard(int cardId) {
		return personalMapper.getBankCard(cardId);
	}

	/**
	 * 我的积分明细 列表
	 * 
	 * @param lookup
	 * @return
	 */
	public PageInfo<CustomerScoreRecord> scores(BaseApiLookup lookup) {
		Page<CustomerScoreRecord> page = startPage(lookup);
		List<CustomerScoreRecord> datas = personalMapper.scores(lookup);
		return PageInfo.instance(page, datas);
	}

	/**
	 * 新增我的积分明细 + 我的积分
	 */
	@Transactional
	public boolean addCustomerScoreRecord(int score, int type, int customerId) {
		if(score == 0) {
			return false;
		}
		personalMapper.addCustomerScoreRecord(new CustomerScoreRecord(customerId, score, type));
		authMapper.updateScore(new Customer(customerId).setScore(score));
		return true;
	}
	
	/**
	 * 当天当前type的获得积分的次数
	 */
	public int findSignNum(int type, int customerId){
		return personalMapper.findSignNum(type, customerId);
	}

	/** 我的推荐概况 */
	public RecommentSurveyVo recommentSurvey(int userId) {
		return personalMapper.recommentSurvey(userId);
	}

	/**
	 * 我的推荐列表 -商家或用户
	 * 
	 * @param lookup
	 * @return
	 */
	public PageInfo<RecommentVo> recomments(BaseApiLookup lookup) {
		if (lookup.getType() == 1) {// 用户
			return customerRecomments(lookup);
		} else if (lookup.getType() == 2) {// 商家
			return merchantRecomments(lookup);
		}
		return null;
	}

	/**
	 * 我的推荐列表-商家
	 */
	public PageInfo<RecommentVo> merchantRecomments(BaseApiLookup lookup) {
		startPage(lookup);
		List<RecommentVo> datas = personalMapper.merchantRecomments(lookup);
		return PageInfo.instance(datas);
	}

	/**
	 * 我的推荐列表-用户
	 */
	public PageInfo<RecommentVo> customerRecomments(BaseApiLookup lookup) {
		startPage(lookup);
		List<RecommentVo> datas = personalMapper.customerRecomments(lookup);
		return PageInfo.instance(datas);
	}
	
	
	/**
	 * 我的 积分兑换商品
	 */
	public PageInfo<SwapGoods> jfdhService(BaseApiLookup lookup) {
		startPage(lookup);
		List<SwapGoods> datas = personalMapper.integralCommodity(lookup);
		return PageInfo.instance(datas);
	}
	
	/**
	 * 我的 积分兑换商品
	 */
	public PageInfo<CustomerMsg> xxtxService(BaseApiLookup lookup) {
		startPage(lookup);
		List<CustomerMsg> datas = personalMapper.xxtx(lookup);
		return PageInfo.instance(datas);
	}
}
