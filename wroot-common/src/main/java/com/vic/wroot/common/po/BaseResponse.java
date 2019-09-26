package com.vic.wroot.common.po;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vic.wroot.common.tool.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回JSON的基本格式  
 * @author VIC
 *
 */
public final class BaseResponse<T> {

	private static final Logger logger = LoggerFactory.getLogger(BaseResponse.class);

	private static final String RESULT_MSG_SUCCESS = "操作成功";
	private static final String RESULT_MSG_ERROR = "操作失败";

	/**
	 * 状态码 0为正确
	 */
	private int code;

	/**
	 * 错误提示信息
	 */
	private String msg;

	/**
	 * 数据对象
	 */
    private T data;

	/** 构建操作成功的对象 */
	public static BaseResponse success(Object data) {
		return new BaseResponse(0, RESULT_MSG_SUCCESS, data);
	}

	/** 构建操作成功的对象 */
	public static BaseResponse success() {
		return new BaseResponse(0, RESULT_MSG_SUCCESS);
	}

	public static BaseResponse error() {
		return error(999, RESULT_MSG_ERROR);
	}
	
	/** 构建操作失败的对象 */
	public static BaseResponse error(String msg) {
		return error(999, msg);
	}

	/** 构建操作失败的对象 */
	public static BaseResponse error(int code, String msg) {
		return new BaseResponse(code, msg);
	}

	/**
	 * 转JSON
	 */
	public String toJson() {
		try {
			return mapper.writeValueAsString(this);
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return "{" + "\"code\":" + code + ",\"msg\":\"" + msg + "\",\"data\":\"" + data + "\"}";
	}

	/**
	 * 返回结果
	 *//*
	public String result() {
		if (FinalFiledParams.START_ENCRYPT) {
			return this.toAesJson();
		}
		return this.toJson();
	}*/

	/**
	 * 转JSON并加密
	 *//*
	public String toAesJson() {
		String result = null;
		try {
			result = AESUtil.encrypt(toJson());
		} catch (Exception e) {
			logger.error("返回json，加密异常", e);
		}
		return result;
	}*/

	@Override
	public String toString() {
		return this.toJson();
	}

	public BaseResponse() {
	}

	private BaseResponse(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

    private BaseResponse(int code, String msg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public BaseResponse appendMsg(String msg) {
		this.msg += msg;
		return this;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public BaseResponse setMsg(String msg) {
		this.msg = msg;
		return this;
	}

    public T getData() {
		return data;
	}

    public BaseResponse setData(T data) {
		this.data = data;
		return  this;
	}

	private static ObjectMapper mapper = new ObjectMapper();
	
	
	@SuppressWarnings("serial")
	public static void main(String[] args) throws Exception{
		final Map<String, Object> map = new HashMap<String, Object>(){{
			put("a", 12);
			put("b",0);
			put("c", true);
			put("d", "dd");
		}};
		Map<String, Object> map2 = new HashMap<String, Object>(){{
			put("g", 13);
			put("f",0);
			put("h", true);
			put("i", "dd");
			put("e", map);
			
		}};
		
		String json = Tools.writeObject2StringJson(map2);
		System.out.println(json);
	}
}
