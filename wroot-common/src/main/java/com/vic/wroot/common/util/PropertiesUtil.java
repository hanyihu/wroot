package com.vic.wroot.common.util;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
/**
 * 读取配置文件工具类
 * @author VIC
 */
public class PropertiesUtil {
  
	private static Properties properties = null;
	
	private static final String PROP_RESOURCE = "configuration.properties";
	
	public static Properties getPara(){
		properties = new Properties();
		try{
			InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(PROP_RESOURCE);
			properties.load(in);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	public static String getStringByKey(String key){
		if(null == properties) {
			PropertiesUtil.getPara();
		}
		try{
			return properties.getProperty(key);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static int getIntByKey(String key ){
		String val = getStringByKey(key);
		if(StringUtils.isNotBlank(val)){
			return Integer.parseInt(val);
		}else {
			return 0;
		}
	}
}
