package com.vic.wroot.third.login;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.vic.wroot.common.util.CommonUtils;
import com.vic.wroot.common.util.PropertiesUtil;

/**
 * 
 * App支付宝登录API列表:
 * 
 * @link https://docs.open.alipay.com/218/105328/
 * @author VIC
 *
 */
public class AlipayLogin {

	private static Logger logger = LoggerFactory.getLogger(AlipayLogin.class);

	private static final String ALIPAY_URL = "https://openapi.alipay.com/gateway.do";

	private static final String APP_ID = PropertiesUtil.getStringByKey("pay.alipay.appid");
	// 商户收款账号
	protected static final String SELLER = PropertiesUtil.getStringByKey("pay.alipay.seller");
	// 商户私钥，pkcs8格式
	private static final String RSA_PRIVATE = PropertiesUtil.getStringByKey("pay.alipay.rsa_private");
	// 支付宝公钥
	private static final String RSA_PUBLIC = PropertiesUtil.getStringByKey("pay.alipay.rsa_public");

	private static final String INPUT_CHARSET = "utf-8";

	/**
	 * 换取授权访问令牌
	 * 
	 * @param code
	 * @throws AlipayApiException
	 */
	public static AlipaySystemOauthTokenResponse getToken(String code) throws AlipayApiException {
		AlipayClient alipayClient = new DefaultAlipayClient(ALIPAY_URL, APP_ID, RSA_PRIVATE, "json", INPUT_CHARSET,
				RSA_PUBLIC, "RSA2");
		AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
		request.setGrantType("authorization_code");
		request.setCode(code);
		AlipaySystemOauthTokenResponse response = alipayClient.execute(request);
		if (response.isSuccess()) {
			logger.info("换取支付宝授权访问令牌{}成功", response.getAccessToken());
		} else {
			logger.info("换取支付宝授权访问令牌失败");
		}

		return response;
	}

	/**
	 * 用户信息共享接口
	 * 
	 * @throws AlipayApiException
	 */
	public static AlipayUserInfoShareResponse getUserInfoShare(String accessToken) throws AlipayApiException {
		AlipayClient alipayClient = new DefaultAlipayClient(ALIPAY_URL, APP_ID,RSA_PRIVATE, "json", INPUT_CHARSET, RSA_PUBLIC, "RSA2");
		AlipayUserInfoShareRequest request = new AlipayUserInfoShareRequest();
		AlipayUserInfoShareResponse response = alipayClient.execute(request, accessToken);
		if (response.isSuccess()) {
			logger.info("用户信息共享接口调用成功 {}",CommonUtils.toJson(response));
		} else {
			logger.info("用户信息共享接口调用失败失败");
		}
		return response;
		
		/*
		 * {
	    "alipay_user_info_share_response": {
	        "code": "10000",
	        "msg": "Success",
	        "user_id": "2088102104794936",
	        "avatar": "http://tfsimg.alipay.com/images/partner/T1uIxXXbpXXXXXXXX",
	        "province": "安徽省",
	        "city": "安庆",
	        "nick_name": "支付宝小二",
	        "is_student_certified": "T",
	        "user_type": "1",
	        "user_status": "T",
	        "is_certified": "T",
	        "gender": "F"
	    },
	    "sign": "ERITJKEIJKJHKKKKKKKHJEREEEEEEEEEEE"
		 */
	}
	public static void main(String[] args) throws AlipayApiException {
		try{
			String code = "147e770cb4254a0baf38eb7a37a3SC77";
			AlipaySystemOauthTokenResponse toekenResponse = AlipayLogin.getToken(code);
			String accessToken = toekenResponse.getAccessToken();
			AlipayUserInfoShareResponse userInfo = AlipayLogin.getUserInfoShare(accessToken);
			String a = CommonUtils.toJson(userInfo);
			System.out.println(a);
		}catch(Exception e){
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	
	}
}
