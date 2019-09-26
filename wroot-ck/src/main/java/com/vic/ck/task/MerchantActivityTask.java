package com.vic.ck.task;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vic.ck.api.merchant.service.MerchantActivityService;

/**
 * 商家活动的定时器
 * @author VIC
 *
 */
@Component
public class MerchantActivityTask {
	
	private Logger logger = LoggerFactory.getLogger(MerchantActivityTask.class);
	
	@Resource
	private MerchantActivityService merchantActivityService;
	
	
	
	@Scheduled(cron = "0 2 0 * * ?")
	public void processTask() {
		logger.info("商家活动定时任务---------------------------start");
		/**
		 * 1 更新商家活动是否过期
		 */
		merchantActivityService.autoUpdateStatus();
		/**
		 * 2 根据当前商家的活动更新商家表里的当前活动字段 全量更新
		 */
		merchantActivityService.updateMerchantCurrentActivity(null);
		logger.info("商家活动定时任务---------------------------end");

	}

}
