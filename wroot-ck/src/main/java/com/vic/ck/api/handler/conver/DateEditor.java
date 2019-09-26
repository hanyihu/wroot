package com.vic.ck.api.handler.conver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.propertyeditors.PropertiesEditor;

/**
 * Controller的日期参数的统一妆化
 * 
 * @author VIC
 *
 */
public class DateEditor extends PropertiesEditor {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		
		SimpleDateFormat sdf = getFormat(text);
		Date date;
		try {
			date = sdf.parse(text);
			setValue(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private SimpleDateFormat getFormat(String source) {
		if (source.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
			
			return new SimpleDateFormat("yyyy-MM-dd");
			
		} else if (source.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$")) {
			
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
		} else if (source.matches("^\\d{2}:\\d{2}$")) {
			
			return new SimpleDateFormat("HH:mm");
			
		} else {
			throw new TypeMismatchException("不存在的日期格式", Date.class);
		}
	}

}
