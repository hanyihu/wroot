package com.vic.ck.api.personal.mapper;

import org.apache.ibatis.annotations.Param;

import com.vic.base.BaseMapper;
import com.vic.ck.entity.CustomerDiscountTicket;
import com.vic.wroot.common.annotation.MybatisMapper;

/**
 * 用户优惠券表
 * 
 * @author VIC
 */
@MybatisMapper
public interface CustomerDiscountTicketMapper extends BaseMapper<CustomerDiscountTicket> {
	
	/**修改我的折扣券状态*/
	int updateStatus(@Param("id")int id, @Param("status")int status);

	/**优惠券是否过期*/
	int overduedUpdate();

}
