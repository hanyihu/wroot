package com.vic.wroot.common.tool;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.BooleanSerializer;
import com.vic.wroot.common.util.PropertiesUtil;
import com.vic.wroot.common.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统级别的工具类
 * 
 * @author VIC
 *
 */
public class Tools {

	public static final String QRCODE_PATH = PropertiesUtil.getStringByKey("qrcode.path");

	public static final String ATTACHMENT_PREFIX = PropertiesUtil.getStringByKey("attachment.prefix");

	public static final String ATTACHMENT_LOADING_PREFIX = PropertiesUtil.getStringByKey("attachment.loading.prefix");
    private static ObjectMapper mapper = new ObjectMapper();

	/**
	 * 获得当前线程中的request
     *
     * @exception ：当前线程中不存在
	 *                Request 上下文
	 */
	public static HttpServletRequest currentRequest() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (attr == null) {
			throw new IllegalStateException("当前线程中不存在 Request 上下文");
		}
		return attr.getRequest();
	}

	/**
	 * 获得当前线程中的session 不存在则返回null
     *
	 * @return
	 */
	public static HttpSession currentSession() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (attr == null) {
			return null;
		}
		return attr.getRequest().getSession(false);
	}

	/**
	 * 通过response输出JSON
     *
	 * @return
	 */
	public static void writeJson(Object obj, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(JSON.toJSONString(obj));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

    /**
     * 获得当前请求的URL
     */
	public static String getRequestUrl(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		String requestURI = request.getRequestURI();
		int i = requestURI.indexOf("?");
		if (i < 0) {
			i = requestURI.length();
		}
		return requestURI.substring(contextPath.length(), i);
	}

	/**
	 * 把java对象转json， 其中value转为String类型
	 * @throws Exception
	 */
	public static String writeObject2StringJson(Object obj) throws Exception {
		StringWriter stringWriter = new StringWriter();
		JsonFactory jsonFactory = new JsonFactory();
		JsonGenerator jsonGenerator = jsonFactory.createGenerator(stringWriter);
		jsonGenerator.enable(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS);

		SimpleModule simpleModule = new SimpleModule("BooleanModule");
		simpleModule.addSerializer(Boolean.class, new BooleanSerializer(true));
		simpleModule.addSerializer(Boolean.class, new JsonSerializer<Boolean>() {

			@Override
			public void serialize(Boolean value, JsonGenerator gen, SerializerProvider serializers)
					throws IOException, JsonProcessingException {
				gen.writeNumber(value ? "true" : "false");

			}
		});
		mapper.registerModule(simpleModule);
		mapper.writeValue(jsonGenerator, obj);
		return stringWriter.toString();
    }

    /**
     * 获得附件的url
     **/
    public static String getImageUrl(int image) {
        if (image > 0) {
			return ATTACHMENT_PREFIX + image;
		}
		return null;
    }

    /**
     * 获得附件的url
     **/
    public static String getImageUrl(Integer image) {
        if (image != null && image > 0) {
			return ATTACHMENT_PREFIX + image;
		}
		return null;
	}

	/**
	 *获得附件列表
	 */
	public static List<String> getImageUrls(String images) {
        if (StringUtils.isNotBlank(images)) {
			String[] arr = images.split(",");
			List<String> list = new ArrayList<String>();
            for (String str : arr) {
				try {
					int imageId = Integer.parseInt(str);//过滤掉非法
					list.add(ATTACHMENT_PREFIX + imageId);
                } catch (NumberFormatException e) {
				}
			}
			return list;
		}

		return null;
	}
}
