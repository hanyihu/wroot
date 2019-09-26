package com.vic.wroot.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils{
	
	private static Logger logger = LoggerFactory.getLogger(DateUtils.class);


	/**
	 * 两个时间之间的天数 (date2-date1)
	 * @param date1
	 * @param date2
	 * @return
	 */
	 public static int daysBetween(Date date1, Date date2) {
	        Long between_days = 0L;
	        try {
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	            date1 = sdf.parse(sdf.format(date1));
	            date2 = sdf.parse(sdf.format(date2));
	            Calendar cal = Calendar.getInstance();
	            cal.setTime(date1);
	            long time1 = cal.getTimeInMillis();
	            cal.setTime(date2);
	            long time2 = cal.getTimeInMillis();
	            between_days = (time2 - time1) / (1000 * 3600 * 24);
	        } catch (ParseException e) {
	        	logger.error(ExceptionUtils.getStackTrace(e));
	        }
	        return between_days.intValue();
	    }
	 
	 public static void main(String[] args) throws ParseException {
		String s2 = "2017-12-15 15:30:20"; 
		String s1 =  "2017-12-11 19:30:20"; 
		Date d1= DateUtils.parseDate(s1, "yyyy-MM-dd HH:mm:ss");
		Date d2 =  DateUtils.parseDate(s2, "yyyy-MM-dd HH:mm:ss");
		System.out.println(daysBetween(d1, d2));
		
	}
}
