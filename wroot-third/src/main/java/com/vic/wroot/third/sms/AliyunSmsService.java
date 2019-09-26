package com.vic.wroot.third.sms;

import org.springframework.stereotype.Service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.vic.wroot.common.exception.SmsException;

@Service("aliyunSmsService")
public class AliyunSmsService {
	
	public final static String REGIN_ID = "cn-hangzhou";
	public final static String ACCESS_KEY_ID = "LTAILBxvVIIivDbH";
	public final static String ACESS_KEY_SECRET = "ct61ZpE4icli9ocpHrxJsN5pBvLH4y";
	public final static String SIGN_NAME = "镗镗锣";
	
	/**
	 * 发送短信
	 * 默认模板
	 * @param mobile
	 * @param code
	 * @throws SmsException
	 */
	public void sendSmsCode(String mobile, String code) throws SmsException {
		sendSmsCode(mobile, code, SmsTemplateCode.DEFAULT);
	}
	
	/**
	 * 注册
	 * @param mobile
	 * @param code
	 * @throws SmsException
	 */
	public void sendRegisterSmsCode(String mobile, String code) throws SmsException {
		sendSmsCode(mobile, code, SmsTemplateCode.REGISTER);
	}
	
	/**
	 * 入驻申请
	 * @param mobile
	 * @param code
	 * @throws SmsException
	 */
	public void sendLocateSmsCode(String mobile, String code) throws SmsException {
		sendSmsCode(mobile, code, SmsTemplateCode.LOCATE);
	}
	
	/**
	 * 登录
	 * @param mobile
	 * @param code
	 * @throws SmsException
	 */
	public void sendLoginSmsCode(String mobile, String code) throws SmsException {
		sendSmsCode(mobile, code, SmsTemplateCode.LOGIN);
	}
	
	/**
	 * 身份验证
	 * @param mobile
	 * @param code
	 * @throws SmsException
	 */
	public void sendValidateSmsCode(String mobile, String code) throws SmsException {
		sendSmsCode(mobile, code, SmsTemplateCode.VALIDATE);
	}
	
	/**
	 * 异常登录
	 * @param mobile
	 * @param code
	 * @throws SmsException
	 */
	public void sendLoginAbnormalSmsCode(String mobile, String code) throws SmsException {
		sendSmsCode(mobile, code, SmsTemplateCode.LOGIN_ABNORMAL);
	}
	
	/**
	 * 修改密码
	 * @param mobile
	 * @param code
	 * @throws SmsException
	 */
	public void sendChangePassSmsCode(String mobile, String code) throws SmsException {
		sendSmsCode(mobile, code, SmsTemplateCode.CHANGE_PASS);
	}
	
	/**
	 * 修改信息
	 * @param mobile
	 * @param code
	 * @throws SmsException
	 */
	public void sendModifyInfoSmsCode(String mobile, String code) throws SmsException {
		sendSmsCode(mobile, code, SmsTemplateCode.MODIFY_INFO);
	}
	
	
	public void sendSmsCode(String mobile, String code, SmsTemplateCode template) throws SmsException {
		
		DefaultProfile profile = DefaultProfile.getProfile(REGIN_ID, ACCESS_KEY_ID, ACESS_KEY_SECRET);
		
		IAcsClient client = new DefaultAcsClient(profile);

		SendSmsRequest request = new SendSmsRequest();
		
        request.setPhoneNumbers(mobile);
        request.setSignName(SIGN_NAME);
        request.setTemplateCode(template.getCode());
        request.setTemplateParam("{\"code\":\""+ code +"\"}");

        try {
        	SendSmsResponse response = client.getAcsResponse(request);
            System.out.println(response.getMessage());
        } catch (ServerException e) {
            e.printStackTrace();
            throw new SmsException();
        } catch (ClientException e) {
            e.printStackTrace();
            throw new SmsException();
        }
	}
	
	public static void main(String[] args) {
		AliyunSmsService smsSeverice = new AliyunSmsService();
		try {
//			smsSeverice.sendRegisterSmsCode("18253713672", "123456");
			smsSeverice.sendChangePassSmsCode("18253713672", "125226");
		} catch (SmsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
