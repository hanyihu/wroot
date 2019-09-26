package com.vic.wroot.common.web.interceptor;

import com.vic.wroot.common.util.JedisUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public class PhoneToken {

	// 存储在REDIS中的 token : TOKEN_PREFIX + phone = token （token = phone + "-" +
	// UUID()）
	public static final String TOKEN_PREFIX = "phone_token_";

	private static final String SEPARATE = "-";

    public static final String UID_PREFIX = "uid_";

	/**
	 * 获得PhoneToken并缓存到REDIS.
	 * 
	 * 登录时候调用此方法
	 * 
	 * @return
	 */
	public static String cachePhoneToken(String phone) {
		String uuid = UUID.randomUUID().toString().replace(SEPARATE, "");
		String token = phone + SEPARATE + uuid;
		String key = TOKEN_PREFIX + phone;
		//保存7天吧先
		int cacheSeconds = 7 * 24 * 3600;
		JedisUtil.set(key, token, cacheSeconds);
		return token;
	}

    public static String cachePhoneUid(String phone, Integer uid) {
        String token = phone + SEPARATE + uid;
        String key = UID_PREFIX + phone;
        //保存7天吧先
        int cacheSeconds = 7 * 24 * 3600;
        JedisUtil.set(key, token, cacheSeconds);
        return token;
    }
	
	public static void deletePhoneToken(String phone){
		JedisUtil.del(TOKEN_PREFIX + phone);
	}

	/**
	 * 当前token是否有效
	 * 
	 * @param token
	 * @return
	 */
	public static boolean isValidToken(String token) {
		if (StringUtils.isNotEmpty(token) && token.split(SEPARATE).length == 2) {
			String phone = token.split("-")[0];
			String redisKey = TOKEN_PREFIX + phone;
			String redisToken = JedisUtil.get(redisKey);
			if (StringUtils.equals(token, redisToken)) {
				return true;
			}

		}
		return false;

	}

	public static void main(String[] args) throws InterruptedException {
		// 在这测试别忘了copy配置文件到本项目
		String p = "15056920791";
		String token = cachePhoneToken(p);
		System.out.println("第一次token " + token);
		Thread.sleep(3000);
		System.out.println("验证第一次的token " + isValidToken(token));
		String token2 = cachePhoneToken(p);
		System.out.println("第二次token " + token);
		Thread.sleep(3000);
		System.out.println("验证第一次的token " + isValidToken(token));
		System.out.println("验证第二次的token " + isValidToken(token2));

	}
}
