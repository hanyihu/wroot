package com.vic.ck.task;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vic.ck.api.personal.service.CustomerDiscountTicketService;
import com.vic.ck.api.personal.service.CustomerGroupTicketService;

/**
 *
 * 团购券和优惠券是否过期检测
 * @author VIC
 *
 */
@Component
public class TicketTask {

	private Logger logger = LoggerFactory.getLogger(TicketTask.class);
	
	
	@Resource
	private CustomerGroupTicketService customerGroupTicketService;
	
	@Resource
	private CustomerDiscountTicketService customerDiscountTicketService;
	
	/**
	 * 团购券和优惠券是否过期检测
	 */
	@Scheduled(cron = "0 18 0 * * ?")
	public void processTask() {
		logger.info("团购券和优惠券是否过期检测------------start ");
		
		int groupTicketNum = customerGroupTicketService.overduedUpdate();
		int discountTicketNum = customerDiscountTicketService.overduedUpdate();
		
		logger.info("团购券{}和优惠券{}是否过期检测------------------end", groupTicketNum, discountTicketNum);
		
	}
	
	
	
}
