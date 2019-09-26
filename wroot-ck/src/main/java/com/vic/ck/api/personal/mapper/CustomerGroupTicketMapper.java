package com.vic.ck.api.personal.mapper;

import org.apache.ibatis.annotations.Param;

import com.vic.base.BaseMapper;
import com.vic.ck.entity.CustomerGroupTicket;
import com.vic.wroot.common.annotation.MybatisMapper;

/**
 * 用户团购券表
 * 
 * @author VIC
 */
@MybatisMapper
public interface CustomerGroupTicketMapper extends BaseMapper<CustomerGroupTicket> {

	/**修改团购券的二维码*/
	void updateQrcode(@Param("id")int id, @Param("qrcode")int qrcode);

	/**根据券码查询*/
	CustomerGroupTicket findByTicketNo(@Param("ticketNo")String ticketNo);

	void useGroupTicket(CustomerGroupTicket ticket);
	
	void merchantDelete(@Param("id")int id);

	/**团购券否过期检测*/
	int overduedUpdate();

	/**删除订单号对应的团购券*/
	int deleteByOrdeno(@Param("orderno")String orderno);
	
}
