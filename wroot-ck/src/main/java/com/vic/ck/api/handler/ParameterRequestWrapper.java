package com.vic.ck.api.handler;

import com.alibaba.fastjson.JSONObject;
import com.vic.ck.api.constant.FinalFiledParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;
import java.util.Map.Entry;
/**
 * 参数包装类
 * @author QiuDong
 *
 */
public class ParameterRequestWrapper extends HttpServletRequestWrapper {
	
	Logger logger = LoggerFactory.getLogger(ParameterRequestWrapper.class);

	private Map<String, String[]> params =  new HashMap<String, String[]>();

	public ParameterRequestWrapper(HttpServletRequest request, Map<String, String[]> newParams) {
		super(request);
		this.params = newParams;
		renewParameterMap(request);
	}
	
	public ParameterRequestWrapper(HttpServletRequest request, JSONObject jsonObject) {
		super(request);
		if(jsonObject != null) {
			Map<String, String[]> map = new HashMap<String, String[]>();
			 Set<Entry<String, Object>> set = jsonObject.entrySet();  
			 for(Entry<String, Object> entry : set) {
				 logger.info(entry.getKey() + "  =  " + entry.getValue());
				 map.put(entry.getKey(), new String[]{entry.getValue() + ""});
			 }
			 this.params = map;
		}
		renewParameterMap(request);
	}

	@Override
	public String getParameter(String name) {
		String result = "";

		Object v = params.get(name);
		if (v == null) {
			result = null;
		} else if (v instanceof String[]) {
			String[] strArr = (String[]) v;
			if (strArr.length > 0) {
				result = strArr[0];
			} else {
				result = null;
			}
		} else if (v instanceof String) {
			result = (String) v;
		} else {
			result = v.toString();
		}

		return result;
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		return params;
	}

	@Override
	public Enumeration<String> getParameterNames() {
		return new Vector<String>(params.keySet()).elements();
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] result = null;

		Object v = params.get(name);
		if (v == null) {
			result = null;
		} else if (v instanceof String[]) {
			result = (String[]) v;
		} else if (v instanceof String) {
			result = new String[] { (String) v };
		} else {
			result = new String[] { v.toString() };
		}

		return result;
	}

	private void renewParameterMap(HttpServletRequest req) {

		String queryString = req.getQueryString();

		if (queryString != null && queryString.trim().length() > 0) {
			String[] params = queryString.split("&");

			for (int i = 0; i < params.length; i++) {
				int splitIndex = params[i].indexOf("=");
				if (splitIndex == -1) {
					continue;
				}

				String key = params[i].substring(0, splitIndex);
				//删除request中原本的参数 防止其他地方勿用  比如 log日志统一处理
                if (FinalFiledParams.REQUEST_PARAM.equals(key)) {
                    continue;
                }
				if (!this.params.containsKey(key)) {
					if (splitIndex < params[i].length()) {
						String value = params[i].substring(splitIndex + 1);
						this.params.put(key, new String[] { value });
					}
				}
			}
		}
	}

}