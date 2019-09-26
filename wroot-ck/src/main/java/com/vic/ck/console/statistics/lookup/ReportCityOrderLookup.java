package com.vic.ck.console.statistics.lookup;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.vic.base.pager.CommonLookup;

/**
 * 封装城市订单流水查询条件 
 * 
 * @author VIC
 */
public class ReportCityOrderLookup extends CommonLookup {
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startDate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endDate;
	
	private boolean isFirst = true;

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

	public boolean isFirst() {
		return isFirst;
	}

	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}
	
	

	
}
