package com.vic.ck.other.recommend;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vic.base.BaseApiController;
import com.vic.ck.api.jpush.service.CustomerJpushService;
import com.vic.ck.api.jpush.service.MerchantJpushService;
import com.vic.wroot.third.jpush.IJpushBaseService;
import com.vic.wroot.third.jpush.JpusHandle.AudienceEnum;
import com.vic.wroot.third.jpush.JpusHandle.PushClient;

/**
 * 推荐注册相关
 * @author VIC
 *
 */
@RestController
@RequestMapping("/api/test/")
public class TestController extends BaseApiController{

	@Resource
	private MerchantJpushService merchantJpushService;
	
	@Resource
	private CustomerJpushService customerJpushService;
	
	/**
	 * 测试推送接口
	 * role 1-客户端 2-商家端
	 * targetType 1-全平台 2-城市 3-某个设备号
	 */
	@RequestMapping(value = "/jpush", method = RequestMethod.GET)
	public Object recommend(@RequestParam(defaultValue="1") int role, @RequestParam(defaultValue="1") int targetType, 
			@RequestParam(defaultValue="") String target, @RequestParam(defaultValue="") String data) {
		String content = "接口测试推送-" + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		IJpushBaseService service = role == 1 ? customerJpushService : merchantJpushService;
		PushClient client = service.create(content);
		if(targetType == 1) {
			client.audience(AudienceEnum.all);
		}else if(targetType ==2) {
			client.audience(AudienceEnum.tag);
			client.to(target);
		}else if(targetType ==3) {
			client.to(target);
		}
		client.data("data", data);	
		return resultSuccess(client.push());
	}
	


}