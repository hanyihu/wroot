package com.vic.wroot.third.sms;

import com.alibaba.fastjson.JSONObject;
import com.vic.wroot.common.craw.BaseCrawl;
import com.vic.wroot.common.exception.SmsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 利用聚合发送短信验证码.
 * https://www.juhe.cn/docs/api/id/54
 * @author VIC
 *
 */
@Service
public class SmsService extends BaseCrawl{
	
	private Logger logger = LoggerFactory.getLogger(SmsService.class); 

	@Value("#{conf['juhe.sms.key']}")
	private String key;

	@Value("${juhe.sms.url}")
	private String url;
	
	@Value("${juhe.sms.tpl_id}")
	private int tplId;


    public void sendSmscode(String mobile, String code) throws SmsException {
		try{
			String text = con(url)
					.data("key", key)
					.data("mobile", mobile)
					.data("tpl_id", String.valueOf(tplId))
					.data("tpl_value", String.format("#code#=%s", code))
					.getBodyText();
			logger.info("发送短信验证码结果:{}", text);
			JSONObject result = JSONObject.parseObject(text);
			if(result.getInteger("error_code") != 0){
				throw new SmsException(result.getString("reason"));
			}
		}catch(Exception e){
			throw new SmsException();
		}
		
		
	}
	
	
	@Override
	protected int getTimeout() {
		return 80000;
	}
}
