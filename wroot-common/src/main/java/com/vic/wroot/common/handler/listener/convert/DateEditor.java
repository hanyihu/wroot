package com.vic.wroot.common.handler.listener.convert;

import com.vic.wroot.common.util.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.propertyeditors.PropertiesEditor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Controller的日期参数的统一转化
 * 
 * @author VIC
 * 
 * 使用方法：Controller中加入如下代码
 	@InitBinder
	protected void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Date.class, new DateEditor());
	}
 *
 */
public class DateEditor extends PropertiesEditor {
	
	private Logger logger = LoggerFactory.getLogger(DateEditor.class);

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(StringUtils.isEmpty(text)) {
			setValue(null);
			return;
		}
		
		try {
			SimpleDateFormat sdf = getFormat(text);
			Date date;
			date = sdf.parse(text);
			setValue(date);
		} catch (ParseException e) {
			logger.error("日期格式错误 无法转换{}\n{}",text, ExceptionUtils.getStackTrace(e));
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
