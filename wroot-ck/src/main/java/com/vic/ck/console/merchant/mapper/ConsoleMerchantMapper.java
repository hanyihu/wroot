package com.vic.ck.console.merchant.mapper;

import com.vic.base.BaseMapper;
import com.vic.ck.entity.ConsoleMerchant;
import com.vic.ck.entity.MerchantAuthentication;
import com.vic.wroot.common.annotation.MybatisMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 商户表-申请时产生数据
 * 
 * @author VIC
 */
@MybatisMapper
public interface ConsoleMerchantMapper extends BaseMapper<ConsoleMerchant> {

	/**修改用户类型*/
	void updateCustomerType(@Param("customerId")Integer customerId, @Param("type")int type);

	/**商家实名认证信息*/
	MerchantAuthentication findMerchantAuthentication(@Param("merchantId")int merchantId);
	/**批量删除商家信息*/
	int deleteAll(@Param("ids")int[] ids);

}
