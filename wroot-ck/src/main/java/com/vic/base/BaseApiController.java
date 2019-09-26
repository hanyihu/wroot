package com.vic.base;

import com.vic.ck.api.constant.ResultMsgEnum;
import com.vic.ck.api.handler.conver.DateEditor;
import com.vic.ck.entity.Customer;
import com.vic.wroot.common.po.BaseResponse;
import com.vic.wroot.common.util.HibernateValidatorUtils;
import com.vic.wroot.common.util.JedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpSession;
import java.util.Date;

public abstract class BaseApiController {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	// 把登录用户存在REDIS 时候的key前缀
	protected static final String CUSTOMER_CACHE_PREFIX = "cutomser_";

	protected boolean checkLoggin(BaseApiLookup lookup) throws AuthenticationException {
		if (lookup.getUserId() <= 0) {
			throw new AuthenticationException();
		}
		return checkLoggin(lookup.getUserId());
	}

	protected boolean checkLoggin(int userId) throws AuthenticationException {
		if (userId <= 0) {
			throw new AuthenticationException();
		}
		Customer c = (Customer) cacheFromRedis(CUSTOMER_CACHE_PREFIX + userId);
		if (c == null) {
			// 暂时不通过缓存验证登陆
			// throw new AuthenticationException();
		}
		return true;
	}

    /**
     * 缓存登录用户 7 天
     */
    protected void cacheCustomer(Customer customer) {
        cache2Redis(CUSTOMER_CACHE_PREFIX + customer.getId(), customer, 7 * 24 * 60);
    }

	/**验证参数
	 * @throws
	 */
	public void vaildateModel(Object model){
		HibernateValidatorUtils.vaildate(model);
	}

	/**
	 * 缓存到REDIS
	 * 
	 * @param key
	 * @param value
	 * @param min
	 */
	public void cache2Redis(String key, Object value, int min) {
		JedisUtil.setObject(key, value, min * 60);
	}

	/** 从redis中根据key获得缓存 */
	public Object cacheFromRedis(String key) {
		return JedisUtil.getObject(key);
	}

	/** 根据key删除redis缓存 */
	public void delRedisCache(String key) {
		JedisUtil.del(key);
	}

	/**
	 * 返回正确的对象
	 * 
	 * @param data
	 * @return
	 */
	protected BaseResponse resultSuccess(Object data) {
		return result(BaseResponse.success(data));
	}

	/**
	 * 返回正确的对象
	 * 
	 * @param
	 * @return
	 */
	protected BaseResponse resultSuccess() {
		return result(BaseResponse.success());
	}

	/**
	 * 返回错误的对象
	 * 
	 * @param msg
	 * @return
	 */
	protected BaseResponse resultError(ResultMsgEnum msg) {
		return result(BaseResponse.error(msg.getCode(), msg.getMsg()));
	}
	
	/**返回错误的对象*/
	protected Object resultError(int code, String msg) {
		return result(BaseResponse.error(code, msg));
	}

	protected BaseResponse result(BaseResponse response) {
		if (response == null) {
			return null;
		}
		/*if (FinalFiledParams.START_ENCRYPT) {
			String result = null;
			try {
				result = AESUtil.encrypt(response.toJson(), FinalFiledParams.AES_KEY);
			} catch (Exception e) {
				logger.error("返回json，加密异常", e);
			}
			*//*return result;*//*
		}*/
		return response;

	}

	protected String getRealPath() {
		HttpSession session = currentSession();
		if (session != null) {
			return session.getServletContext().getRealPath("/");
		}
		return "";
	}

	protected HttpSession currentSession() {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (attrs == null) {
			return null;
		}
		return attrs.getRequest().getSession(false);
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Date.class, new DateEditor());
	}
}
