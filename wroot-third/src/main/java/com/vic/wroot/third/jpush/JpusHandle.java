package com.vic.wroot.third.jpush;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

/**
 * 具体的推送操作.
 * 
 *注意:推送目标为设备号或者别名的时候一次推送目标不能超过1000，推送目标为tag的时候一次不能超过20个。<br/>
 * 这样的判断放在推送操作之前判断 或分批推送
 * 
 * @author VIC
 * 
 * {@link https://docs.jiguang.cn/jpush/server/push/rest_api_v3_push/}
 * {@link https://github.com/jpush/jpush-api-java-client}
 */
public class JpusHandle {
	
	public PushClient create(String title, String appkey, String secret, boolean production) {
		return new PushClient(title, appkey, secret, production);
	}
	
	/**
	 * 推送目标
	 */
	public enum AudienceEnum{
		//全部、标签、别名、设备号
		all,tag,alias,registration_id;
	}
	
	public class PushClient {
		/**
		 * 推送的标题
		 */
	    private String title;
	    
	    private final String appkey;
	    private final String secret;
	    private final boolean production;
	    
	    /**
	     * 音频文件名
	     */
	    private String sound;
	    //1 - 安卓 2-苹果  其他-全部
	    private Platform platform;
	    //要发送的数据
	    private final Map<String, Object> extras = new LinkedHashMap<String, Object>();

	    //推送的目标  默认 设备号
	    private AudienceEnum audienceEnum;
	    //发送给谁 可能是设备号 可能是标签 可能是别名 默认是设备号
	    private final Collection<String> to = new ArrayList<String>();
	    
	    
	    
	    private final Logger logger = LoggerFactory.getLogger(PushClient.class);

	    public PushClient(String title, String appkey, String secret, boolean production) {
	        this.title = title;
	        this.appkey = appkey;
	        this.secret = secret;
	        this.production = production;
	        //默认推送给全部平台
	        this.platform = Platform.all();
	        //默认按照设备号推送
	        this.audienceEnum = AudienceEnum.registration_id;
	    }
	    
	    public PushClient setSound(String sound){
	    	this.sound = sound;
	    	return this;
	    }
	    //推送的内容
	    public PushClient title(String title) {
	    	this.title = title;
	    	return this;
	    	
	    }
	    //指定要发送的数据
	    public PushClient data(String key, Object value) {
	        extras.put(key, value);
	        return this;
	    }
	    public PushClient data(Map<String, Object> data) {
	        extras.putAll(data);
	        return this;
	    }
	    /**
	     * 发送目标类型
	     */
	    public PushClient audience(AudienceEnum audienceEnum) {
	    	this.audienceEnum =audienceEnum;
	    	return this;
	    }
	    
	    //单独发送
	    public PushClient to(String target) {
	        to.add(target);
	        return this;
	    }
	    
	    //发送给个目标
	    public PushClient to(List<String> targets) {
			if(targets !=null){
				to.addAll(targets);
			}
			return this;
		}
	    //推送给android
	    public PushClient toAndroid() {
	       this.platform = Platform.android();
	        return this;
	    }
	    
	  //推送给ISO
	    public PushClient toIos() {
	       this.platform = Platform.ios();
	        return this;
	    }

	    /**
	     * 开始推送 
	     */
	    public boolean push() {
	        JPushClient jpush = new JPushClient(secret, appkey);
	        
	        Message.Builder message = Message.newBuilder().setMsgContent("extraMsg");
	        for (Map.Entry<String, Object> entrySet : extras.entrySet()) {
	            String key = entrySet.getKey();
	            Object value = entrySet.getValue();
	            if (value == null) {
	            } else if (value instanceof Boolean) {
	                message.addExtra(key, (Boolean) value);
	            } else if (value instanceof Number) {
	                message.addExtra(key, (Number) value);
	            } else if (value instanceof String) {
	                message.addExtra(key, (String) value);
	            } else {
	                message.addExtra(key, value.toString());
	            }
	        }
	        try {
	            Audience audience;
	            if (to.isEmpty()) {
	                audience = Audience.all();
	            } else {
	            	switch (audienceEnum) {
					case registration_id:
						audience = Audience.registrationId(to);
						break;
					case alias:
						audience = Audience.alias(to);
						break;
					case tag:
						audience = Audience.tag(to);
						break;
					default:
						audience = Audience.all();
						break;
					}
	            	
	            }
	            
	            PushPayload pushPayload = PushPayload
	                    .newBuilder()
	                    .setPlatform(platform)
	                    .setAudience(audience)
	                    .setNotification(
	                            Notification.newBuilder().setAlert(title)
	                            //此处为了传递声音
	                            .addPlatformNotification(IosNotification.newBuilder()
	                            		.setAlert(title)
	                            		//此项是指定此推送的badge自动加1
	                            		.incrBadge(1)
	                            		.setSound(sound)
	                            		.build()
	                            		)
	                            .build())
	                    .setMessage(message.build())
	                    .setOptions(Options.newBuilder().setApnsProduction(production)
	                            .setSendno(Integer.parseInt((new Date().getTime() / 1000) + "")).build()).build();
	            logger.debug("jpush:{}", pushPayload);
	            
	            PushResult result = jpush.sendPush(pushPayload);
	            
	            if (!result.isResultOK()) {
	                logger.warn("推送失败");
	            }
	            
	            return result.isResultOK();
	        } catch (Exception ex) {
	            logger.warn("推送失败", ex);
	            return false;
	        }
	    }
	}
}
