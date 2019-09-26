package com.vic.wroot.common.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vic.wroot.common.po.BaseResponse;

public class CommonUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);

	private static final ObjectMapper jsonUtil = new ObjectMapper();
	

	private static final String WORDS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	/**对象转JSON*/
	public static String toJson(Object obj) {
		try {
			return jsonUtil.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "{}";
		}
	}
	
	/**
	 * 根据文件名获得mimeType
	 */
	public static String guessContentTypeFromName(String fileName) {
		 String mimeType = URLConnection.guessContentTypeFromName(fileName);
		 if(mimeType == null) {
			 mimeType = "application/octet-stream";
		 }
		 return mimeType;
	}
	
	/**把数字转为字母 1->A， 2->B*/
	public static String numToWord(int num){
		if(num > 26 || num < 1) {
			return "";
		}
		return "" + WORDS.charAt(num -1 );
	}
	/**
	 * 字符串数组转Integer类型的list
	 * @param arr
	 * @return
	 */
	public static List<Integer> toIntList(String[] arr){
		List<Integer> list = new ArrayList<Integer>();
		if(arr != null && arr.length > 0) {
			for(String obj : arr) {
				try{
					Integer cur = Integer.parseInt(obj);
					list.add(cur);
				}catch(NumberFormatException e){}
			}
		}
		return list;
	}
	
	/**删除int数组中的null*/
	public static Integer[] deleteNullInArr(Integer[] arr) {
		if(arr == null || arr.length ==0) {
			return new Integer[] {};
		}
		List<Integer> list = new ArrayList<Integer>();
		for(Integer i : arr) {
			if(i != null && i != 0) {
				list.add(i);
			}
		}
		return list.toArray(new Integer[] {list.size()});
	}
	
	/**
	 * 把字符串首字母大写
	 * @return
	 */
	public static String first(String str){
		if(StringUtils.isBlank(str)){
			return str;
		}
		return Character.toString(str.charAt(0)).toUpperCase() + str.substring(1, str.length());
	}
	
	/**
	 * 删除字符串前后的分隔符或者子字符串
	 * 2016年12月12日 by VIC
	 * @return
	 */
	public static String deleteAroundSep(String str, String separator){
		if(StringUtils.isBlank(str) || StringUtils.isBlank(separator)){
			return null;
		}
		int sepLen =  separator.length();
		if(str.startsWith(separator)) {
			str = str.substring(sepLen, str.length());
		}
		if(str.endsWith(separator)) {
			str = str.substring(0,  str.length()- sepLen);
		}
		return str;
	}
	/**
	 * 把树形结构list数据转化为树
	 * @param list  
	 * @param idField  id 属性的名称
	 * @param pidField pid 属性的名称
	 * @param childrenField children属性的名称    必须是List集合
	 * 
	 * @return
	 */
	public static <T>List<T> buildTree(List<T> list, String idField, String pidField , String childrenField){
		Map<Object, T> map = new LinkedHashMap<Object, T>();
		List<T> result = new ArrayList<T>();
		try {
			for(T t :list){
				Class<? extends Object> clazz = t.getClass();
				PropertyDescriptor idPd = new PropertyDescriptor(idField, clazz);
				Method getIdMethod = idPd.getReadMethod();//获得get方法  
				Object id = getIdMethod.invoke(t);
				map.put(id, t);
			}
			
			for(Object key :map.keySet()) {
				T cur = map.get(key);
				Class<? extends Object> clazz =cur.getClass();
				PropertyDescriptor pidPd = new PropertyDescriptor(pidField, clazz);
				Method getPidMethod = pidPd.getReadMethod();
				Object pid = getPidMethod.invoke(cur);
				T parent = map.get(pid);
				if(parent != null) {
					Class<? extends Object> pclazz =cur.getClass();
					PropertyDescriptor childrenPd = new PropertyDescriptor(childrenField, pclazz);
					Method getChildrenMethod = childrenPd.getReadMethod();
					@SuppressWarnings("unchecked")
					List<T> children = (List<T>) getChildrenMethod.invoke(parent);
					if(children == null) {
						children = new ArrayList<T>();
					}
					children.add(cur);
				}else {
					result.add(cur);
				}
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	/**bead --> map*/
	public static Map<String, Object> transBean2Map(Object obj) {
		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);
					map.put(key, value);
				}
			}
		} catch (Exception e) {
			logger.error("transBean2Map Error " + e);
		}
		return map;
	}
	
	
	/**从request中获得IP*/
	public static String getRemoteIp(HttpServletRequest request){
	    String ip = request.getHeader("x-forwarded-for");
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getRemoteAddr();
	    }
	    return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}

	public static final String[] BOOTSTRAP_CLASSES = {"info", "warning", "primary", "success", "default",  "danger"};
	
	/**
	 *随机获取bootstrap的样式 
	 */
	public static String randomBootstrapClass(){
		int index = new Random().nextInt(5);
		return randomBootstrapClass(index);
	}
	
	public static String randomBootstrapClass(int index){
		index = index % 6;
		return BOOTSTRAP_CLASSES[index];
	}
	
	
	
	public static void main(String[] args) {
		/*
		System.out.println(deleteAroundSep("/aa/ss/ss/", "/"));
		
		Integer[] arr = new Integer[]{1,2,3,null,3,null,88};
		arr = deleteNullInArr(arr);
		for(Integer i: arr) {
			System.out.print(i + "\t");
		}
		*/
		BaseResponse b = BaseResponse.error(1, "aa");
		System.out.println(transBean2Map(b));
		
		
	}
}
