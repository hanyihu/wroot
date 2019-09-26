package com.vic.wroot.third.jpush;

import com.vic.wroot.third.jpush.JpusHandle.PushClient;

/**
 * 项目中的JpushService请实现此接口.
 *
 *实现类形如：
 * <pre>
 *  @Override
    public PushClient create(String title) {
    	return new JpusHandle().create(title, appkey, secret, production);
	}
 * </pre>
 * 
 * @author VIC
 *
 */
public interface IJpushBaseService {
	
	public PushClient create(String title);
	
	

}
