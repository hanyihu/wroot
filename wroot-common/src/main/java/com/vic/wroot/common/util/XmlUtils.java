package com.vic.wroot.common.util;


import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * XML的工具类
 * @author VIC
 *
 */
public class XmlUtils {
	
	private static Logger logger = LoggerFactory.getLogger(XmlUtils.class);

	/**
     * 把request请求中的XML解析成map
     * @param request
     * @return
     * @throws IOException 
     * @throws DocumentException 
     */
    public static Map<String, String> getXmlRequestParamsMap(HttpServletRequest request) throws IOException, DocumentException{
    	Map<String, String> map = new HashMap<>();
    	//读取输入流
    	InputStream inputStream = request.getInputStream();
    	SAXReader reader = new SAXReader();
    	Document document = reader.read(inputStream);
    	//获得xml根元素
    	Element root = document.getRootElement();
    	//获得根元素的所有子节点
    	@SuppressWarnings("unchecked")
		List<Element> documentList = root.elements();
    	for(Element e :documentList) {
    		map.put(e.getName(), e.getText());
    	}
    	inputStream.close();
    	inputStream = null;
    	return map;
    }
    
    /**
     * map 转为xml
     * @return
     */
    public static String convertMap2Xml(Map<Object,Object> paraMap){
    	StringBuffer xmlStr = new StringBuffer();
    	if (paraMap != null) {
    		xmlStr.append("<xml>");
            Set<Object> keySet = paraMap.keySet();
            Iterator<Object> keyIte = keySet.iterator();
            while (keyIte.hasNext()) {
                String key = (String) keyIte.next();
                String val = String.valueOf(paraMap.get(key));
                xmlStr.append("<");
                xmlStr.append(key);
                xmlStr.append(">");
                xmlStr.append(val);
                xmlStr.append("</");
                xmlStr.append(key);
                xmlStr.append(">");
            }
            xmlStr.append("</xml>");
        }
    	return xmlStr.toString();
    }
    
    public static Object xmlStrToBean(String xmlStr, Class<?> clazz) {
		Object obj = null;
		try {
			// 将xml格式的数据转换成Map对象
			Map<String, Object> map = xmlStrToMap(xmlStr);
			// 将map对象的数据转换成Bean对象
			obj = mapToBean(map, clazz);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return obj;
	}

	/**
	 * 将xml格式的字符串转换成Map对象
	 * 
	 * @param xmlStr
	 *            xml格式的字符串
	 * @return Map对象
	 * @throws Exception
	 *             异常
	 */
	public static Map<String, Object> xmlStrToMap(String xmlStr) throws Exception {
		if (StringUtils.isEmpty(xmlStr)) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		// 将xml格式的字符串转换成Document对象
		Document doc = DocumentHelper.parseText(xmlStr);
		// 获取根节点
		Element root = doc.getRootElement();
		// 获取根节点下的所有元素
		List<?> children = root.elements();
		// 循环所有子元素
		if (children != null && children.size() > 0) {
			for (int i = 0; i < children.size(); i++) {
				Element child = (Element) children.get(i);
				map.put(child.getName(), child.getTextTrim());
			}
		}
		return map;
	}

	/**
	 * 将xml格式字符串转换成Bean对象
	 * 多级子节点递归遍历
	 * @param xmlStr
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public static Object xmlStrToJavaBean(String xmlStr, Class<?> clazz) {
		if (StringUtils.isEmpty(xmlStr)) {
			return null;
		}
		Object obj = null;
		Map<String, Object> map = new HashMap<String, Object>();
		// 将xml格式的字符串转换成Document对象
		Document doc;
		try {
			doc = DocumentHelper.parseText(xmlStr);

			// 获取根节点
			Element root = doc.getRootElement();
			map = elementToMap(root, map);
			// 将map对象的数据转换成Bean对象
			obj = mapToBean(map, clazz);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return obj;
	}

	/**
	 * 递归遍历xml子节点，转换Map
	 * 
	 * @param element
	 * @param map
	 * @return
	 */
	public static Map<String, Object> elementToMap(Element element, Map<String, Object> map) {
		if (element == null || map == null)
			return null;
		List<?> children = element.elements();
		if (children != null && children.size() > 0) {
			for (int i = 0; i < children.size(); i++) {
				Element child = (Element) children.get(i);
				if (child.elements() != null && child.elements().size() > 0)
					elementToMap(child, map);
				else
					map.put(child.getName(), child.getTextTrim());
			}
		}
		return map;
	}

	/**
	 * 将Map对象通过反射机制转换成Bean对象
	 * 
	 * @param map
	 *            存放数据的map对象
	 * @param clazz
	 *            待转换的class
	 * @return 转换后的Bean对象
	 * @throws Exception
	 *             异常
	 */
	public static Object mapToBean(Map<String, Object> map, Class<?> clazz) throws Exception {
		Object obj = clazz.newInstance();
		if (map != null && map.size() > 0) {
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				String propertyName = entry.getKey();
				Object value = entry.getValue();
				String setMethodName = "set" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
				Field field = getClassField(clazz, propertyName);
				if (field != null) {
					Class<?> fieldTypeClass = field.getType();
					value = convertValType(value, fieldTypeClass);
					clazz.getMethod(setMethodName, field.getType()).invoke(obj, value);
				}
			}
		}
		return obj;
	}

	/**
	 * 将Object类型的值，转换成bean对象属性里对应的类型值
	 * 
	 * @param value
	 *            Object对象值
	 * @param fieldTypeClass
	 *            属性的类型
	 * @return 转换后的值
	 */
	private static Object convertValType(Object value, Class<?> fieldTypeClass) {
		Object retVal = null;
		if (Long.class.getName().equals(fieldTypeClass.getName())
				|| long.class.getName().equals(fieldTypeClass.getName())) {
			retVal = Long.parseLong(value.toString());
		} else if (Integer.class.getName().equals(fieldTypeClass.getName())
				|| int.class.getName().equals(fieldTypeClass.getName())) {
			retVal = Integer.parseInt(value.toString());
		} else if (Float.class.getName().equals(fieldTypeClass.getName())
				|| float.class.getName().equals(fieldTypeClass.getName())) {
			retVal = Float.parseFloat(value.toString());
		} else if (Double.class.getName().equals(fieldTypeClass.getName())
				|| double.class.getName().equals(fieldTypeClass.getName())) {
			retVal = Double.parseDouble(value.toString());
		} else {
			retVal = value;
		}
		return retVal;
	}

	/**
	 * 获取指定字段名称查找在class中的对应的Field对象(包括查找父类)
	 * 
	 * @param clazz
	 *            指定的class
	 * @param fieldName
	 *            字段名称
	 * @return Field对象
	 */
	private static Field getClassField(Class<?> clazz, String fieldName) {
		if (Object.class.getName().equals(clazz.getName())) {
			return null;
		}
		Field[] declaredFields = clazz.getDeclaredFields();
		for (Field field : declaredFields) {
			if (field.getName().equals(fieldName)) {
				return field;
			}
		}

		Class<?> superClass = clazz.getSuperclass();
		if (superClass != null) {// 简单的递归一下
			return getClassField(superClass, fieldName);
		}
		return null;
	}
}
