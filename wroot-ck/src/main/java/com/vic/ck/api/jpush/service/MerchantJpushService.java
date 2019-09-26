package com.vic.ck.api.jpush.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.vic.wroot.third.jpush.IJpushBaseService;
import com.vic.wroot.third.jpush.JpusHandle;
import com.vic.wroot.third.jpush.JpusHandle.PushClient;

@Service
public class MerchantJpushService implements IJpushBaseService{

	@Value("${jpush.merchant.appkey}")
	private String appkey;
	
	@Value("${jpush.merchant.secret}")
	private String secret;
	
	@Value("${jpush.merchant.production}")
	private boolean production;
	
	@Override
	public PushClient create(String title) {
		return new JpusHandle().create(title, appkey, secret, production).setSound("ckPush.mp3");
	}

}
