package com.vic.ck.task;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vic.ck.api.community.service.PlatformActivityService;
import com.vic.ck.console.statistics.service.ActivitySurveyRedpacketService;
import com.vic.ck.console.statistics.service.ReportOrderService;
import com.vic.ck.entity.ActivitySurveyRedpacket;
import com.vic.ck.entity.PlatformActivity;
import com.vic.ck.entity.ReportOrder;
import com.vic.wroot.common.util.CommonUtils;

/**
 *
 * 根据平台流水和活动生成红包
 * @author VIC
 *
 */
@Component
public class RedpacketTask {

	private Logger logger = LoggerFactory.getLogger(RedpacketTask.class);
	
	@Resource
	private PlatformActivityService platformActivityService;
	
	@Resource
	private ReportOrderService reportOrderService; 
	
	@Resource
	private ActivitySurveyRedpacketService activitySurveyRedpacketService;
	
	/**
	 * 根据平台流水和活动生成红包 依赖对订单的统计  因此比之时间上迟一些 @See OrderReportTask
	 */
	@Scheduled(cron = "0 15 0 * * ?")
	public void processTask() {
		logger.info("根据平台流水和活动生成红包 依赖对订单的统计------------start ");
		List<PlatformActivity> list = platformActivityService.currentAcitivities();
		if(CollectionUtils.isEmpty(list)) {
			logger.info("当前没有活动");
			return;
		}
		PlatformActivity a = list.stream().filter( cur -> cur.getType() == 1).findFirst().get();
		
		if(a == null) {
			logger.info("当前没有活动");
			return;
		}
		ReportOrder order = reportOrderService.findByday(DateFormatUtils.format(DateUtils.addDays(new Date(), -1), "yyyy-MM-dd"));
		if(order == null || order.getTotalAmount() == null || order.getTotalAmount().compareTo(BigDecimal.ZERO) <=0) {
			logger.info("没有平台流水");
			return;
		}
		
		BigDecimal totalAmount = order.getTotalAmount().multiply(new BigDecimal(a.getRedpacketRule()))
				.divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_DOWN);
					
		ActivitySurveyRedpacket entity = new ActivitySurveyRedpacket(order.getAtDate(), totalAmount, a.getId(), order.getTotalNumber(), 1);
		activitySurveyRedpacketService.insert(entity);
		
		logger.info("根据平台流水和活动生成红包 依赖对订单的统计------------------end \n{}", CommonUtils.toJson(entity));
		
	}
	
	
	
}
