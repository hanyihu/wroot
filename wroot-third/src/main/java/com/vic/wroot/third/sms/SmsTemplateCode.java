package com.vic.wroot.third.sms;

public enum SmsTemplateCode {
	/**
	 * 通用
	 */
	DEFAULT(0, "SMS_160305254"),
	
	/**
	 * 注册
	 */
	REGISTER(1, "SMS_157650016"),
	/**
	 * 登录
	 */
	LOGIN(2, "SMS_157650018"),
	/**
	 * 身份验证
	 */
	VALIDATE(3, "SMS_157650019"),
	
	/**
	 * 登录异常
	 */
	LOGIN_ABNORMAL(4, "SMS_157650017"),
	
	/**
	 * 修改密码
	 */
	CHANGE_PASS(5, "SMS_157650015"),
	
	/**
	 * 修改信息
	 */
	MODIFY_INFO(6, "SMS_157650014"),
    /**
     * 商家入驻
     */
	LOCATE(6, "SMS_157453770");

    private final int value;

    private final String code;

    SmsTemplateCode(int value, String code) {
        this.value = value;
        this.code = code;
    }

    public int getValue() {
        return value;
    }

    public String getCode() {
        return code;
    }
	
//	/**
//	 * 注册
//	 */
//	public final static String REGISTER_TEMPLATE_CODE = "SMS_157650016";
//	
//	/**
//	 * 登录
//	 */
//	public final static String LOGIN_TEMPLATE_CODE = "SMS_157650018";
//	
//	/**
//	 * 身份验证
//	 */
//	public final static String VALIDATE_TEMPLATE_CODE = "SMS_157650019";
//	
//	/**
//	 * 登录异常
//	 */
//	public final static String LOGIN_ABNORMAL_TEMPLATE_CODE = "SMS_157650017";
//	
//	/**
//	 * 修改密码
//	 */
//	public final static String CHANGE_PASS_TEMPLATE_CODE = "SMS_157650015";
//	
//	/**
//	 * 信息变更
//	 */
//	public final static String MODIFY_INFO_TEMPLATE_CODE = "SMS_157650014";
//	
//	
//	/**
//	 * 商家入驻
//	 */
//	public final static String LOCATE_TEMPLATE_CODE = "SMS_157453770";
//	
//	/**
//	 * 通用
//	 */
//	public final static String COMMON_TEMPLATE_CODE = "SMS_160305254";

}
