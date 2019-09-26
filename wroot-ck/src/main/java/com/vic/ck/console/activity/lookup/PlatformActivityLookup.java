package com.vic.ck.console.activity.lookup;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.vic.base.pager.CommonLookup;

/**
 * 封装平台活动表查询条件 
 * 
 * @author VIC
 */
public class PlatformActivityLookup extends CommonLookup {
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startDate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endDate;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
}
