package com.vic.wroot.common.exception;

/**
 * 发送短信验证码的异常
 * @author VIC
 *
 */
public class SmsException extends Exception {

	private static final long serialVersionUID = 1L;

	public SmsException() {
    }

    public SmsException(String string) {
        super(string);
    }

    public SmsException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public SmsException(Throwable thrwbl) {
        super(thrwbl);
    }
}
