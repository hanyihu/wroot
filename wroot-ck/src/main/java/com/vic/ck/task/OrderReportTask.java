package com.vic.ck.task;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vic.ck.console.statistics.service.ReportOrderService;
import com.vic.ck.entity.ReportOrder;

/**
 * 订单的一些数据的统计
 * 
 * @author VIC
 *
 */
@Component
public class OrderReportTask {

	private Logger logger = LoggerFactory.getLogger(OrderReportTask.class);

	@Resource
	private ReportOrderService reportOrderService;
	
	/**
	 * 订单的一些数据的统计 被生成红包统计信息所依赖 故此定时器应比之早些 @See RedpacketTask
	 */
	@Scheduled(cron = "0 5 0 * * ?")
	public void process() {
		Date yestoday = DateUtils.addDays(new Date(), -1);
		staticticsCurrentDayOrder(yestoday);
	}

	/**
	 * 对这一天的订单进行统计
	 */
	public void staticticsCurrentDayOrder(Date date) {
		String day = DateFormatUtils.format(date, "yyyy-MM-dd");
		logger.info("{}订单的一些数据的统计----------------------satrt", day);
		// 判断要统计的日期的数据是否已经存在
		if (reportOrderService.existCurrentDay(day)) {
			logger.info("{}的订单的一些数据的统计已经生成", date);
			return;
		}
		// 新增统计的日期
		ReportOrder entity = new ReportOrder().setAtDate(date);;
		reportOrderService.insert(entity);
		Integer id = entity.getId();
		
		//查询出统计的数据插入统计表
		ReportOrder reportOrder = reportOrderService.staticticsCurrentDayOrder(day).setId(id);
		reportOrderService.update(reportOrder);

		logger.info("{}订单的一些数据的统计----------------------end", day);
	}
}
