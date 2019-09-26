package com.vic.common.test.redis;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

public class Test {

	public static void main(String[] args) throws ParseException {
		String format = "yyyy-MM-dd";
		String format2 = "yyyy-MM-dd HH:mm:ss";
		String pattern = " CALL financial_stakegroupMonthly({0},{1},{2},{3});";
		Date start = DateUtils.parseDate("2016-12", "yyyy-MM");
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(start);
		c2.setTime(start);
		
		System.err.println("-- ----------------------------------------------------------------------------");
		for (int i = 0; i < 16; i++) {
			c1.add(Calendar.MONTH, 1);
			c2.add(Calendar.MONTH, 1);
			c1.set(Calendar.DAY_OF_MONTH, c1.getActualMinimum(Calendar.DAY_OF_MONTH));
			c2.set(Calendar.DAY_OF_MONTH, c2.getActualMaximum(Calendar.DAY_OF_MONTH));
			String startDate = DateFormatUtils.format(c1, format) + " " + "00:00:00";
			String endDate = DateFormatUtils.format(c2, format) +  " " + "23:59:59";
			
//			System.out.println(startDate + "  " + endDate);
			String d1 = DateUtils.parseDate(startDate, format2).getTime() + "";
			String d2 = (DateUtils.parseDate(endDate, format2).getTime() + 999L ) + "";
			String year = c1.get(Calendar.YEAR) + "";
			String month = (c1.get(Calendar.MONTH) + 1) + "";
			System.out.println(MessageFormat.format(pattern, d1, d2, year, month));
			
		}
		System.err.println("-- ----------------------------------------------------------------------------");

	}

}
