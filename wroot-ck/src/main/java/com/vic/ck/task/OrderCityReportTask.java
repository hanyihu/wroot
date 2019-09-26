package com.vic.ck.task;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vic.ck.console.statistics.service.ReportCityOrderService;

/**
 * 城市流水统计
 * 
 * @author VIC
 *
 */
@Component
public class OrderCityReportTask {

	private Logger logger = LoggerFactory.getLogger(OrderCityReportTask.class);

	@Resource
	private ReportCityOrderService reportCityOrderService;
	
	/**
	 * 城市流水统计
	 */
	@Scheduled(cron = "0 20 0 * * ?")
	public void process() {
		Date yestoday = DateUtils.addDays(new Date(), -1);
		staticticsCurrentDayOrder(yestoday);
	}

	/**
	 * 对这一天的订单流水进行统计.
	 * @see com.vic.ck.entity.ReportCityOrder
	 */
	public void staticticsCurrentDayOrder(Date date) {
		String day = DateFormatUtils.format(date, "yyyy-MM-dd");
		logger.info("{}城市流水统计----------------------satrt", day);
		reportCityOrderService.staticticsCityOrder(day);
		reportCityOrderService.updateRedundant(day);
		reportCityOrderService.staticticsPlatformOrder(day);
		logger.info("{}城市流水统计----------------------end", day);
	}
	
}
