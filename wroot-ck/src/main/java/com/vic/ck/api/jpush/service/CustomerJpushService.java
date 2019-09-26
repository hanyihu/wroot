package com.vic.ck.api.jpush.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.vic.wroot.third.jpush.IJpushBaseService;
import com.vic.wroot.third.jpush.JpusHandle;
import com.vic.wroot.third.jpush.JpusHandle.PushClient;

@Service
public class CustomerJpushService implements IJpushBaseService{

	@Value("${jpush.customer.appkey}")
	private String appkey;
	
	@Value("${jpush.customer.secret}")
	private String secret;
	
	@Value("${jpush.customer.production}")
	private boolean production;
	
	@Override
	public PushClient create(String title) {
		return new JpusHandle().create(title, appkey, secret, production).setSound("ckPush.wav");
	}

}
