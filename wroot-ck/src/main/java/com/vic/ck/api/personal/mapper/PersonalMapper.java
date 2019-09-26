package com.vic.ck.api.personal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vic.base.BaseApiLookup;
import com.vic.ck.entity.CustomerBankCard;
import com.vic.ck.entity.CustomerDeliveryAddress;
import com.vic.ck.entity.CustomerFetchMoney;
import com.vic.ck.entity.CustomerMsg;
import com.vic.ck.entity.CustomerScoreRecord;
import com.vic.ck.entity.SwapGoods;
import com.vic.ck.vo.RecommentSurveyVo;
import com.vic.ck.vo.RecommentVo;
import com.vic.wroot.common.annotation.MybatisMapper;

@MybatisMapper
public interface PersonalMapper {

	/**收货地址列表*/
	List<CustomerDeliveryAddress> deliveries(BaseApiLookup lookup);

	void addDelivery(CustomerDeliveryAddress address);
	
	/**修改用户收货地址*/
	void updateDelivery(CustomerDeliveryAddress address);
	
	void deleteDelivery(@Param("id") int id);
	
	CustomerDeliveryAddress deliverieDetail(@Param("id") int id);
	

	/**我绑定的银行卡*/
	List<CustomerBankCard> bankCards(BaseApiLookup lookup);
	
	 void addBankCards(CustomerBankCard bankCard);
	 
	 void deleteBankCards(@Param("id") int id);
	 
	 /**现金提现申请*/
	 void fetch(CustomerFetchMoney money);

	CustomerBankCard getBankCard(@Param("cardId") int cardId);

	/**我的积分明细*/
	List<CustomerScoreRecord> scores(BaseApiLookup lookup);

	/**新增用户积分记录*/
	void addCustomerScoreRecord(CustomerScoreRecord customerScoreRecord);

	/**我的推荐概况*/
	RecommentSurveyVo recommentSurvey(@Param("userId")int userId);

	//推荐的商家列表
	List<RecommentVo> merchantRecomments(BaseApiLookup lookup);

	//推荐的用户列表
	List<RecommentVo> customerRecomments(BaseApiLookup lookup);

	/**
	 * 当天当前type的获得积分的次数
	 */
	int findSignNum(@Param("type")int type, @Param("customerId")int customerId);

	//积分兑换
	List<SwapGoods> integralCommodity(BaseApiLookup lookup);
	
	//个人消息查询
	List<CustomerMsg> xxtx(BaseApiLookup lookup);

	

}
