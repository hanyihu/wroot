package com.vic.wroot.common.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;


/**
 * 参数绑定异常
 * 
 * @author VIC
 *
 */
// @ResponseStatus(HttpStatus.BAD_REQUEST) 其实这是个400异常 但是这里不抛出 而是捕捉 并返回说明
public class BadParamException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	/**是否是HIBERNATE验证*/
	private boolean validated = false;

	public BadParamException() {
	
	}

	/**build  HIBENATE verify exception*/
	public static <T> BadParamException instanceHibernateVerity( Set<ConstraintViolation<T>> errors) {
		StringBuilder verifyError = new StringBuilder();
		for(ConstraintViolation<?> c : errors){
            verifyError.append(c.getMessage()).append("； ");
        }
		verifyError.deleteCharAt(verifyError.length()-1);
		BadParamException e = new BadParamException(verifyError.toString());
		e.validated = true;
		return e;
		
	}
	public BadParamException(String string) {
		super(string);
	}

	public BadParamException(String string, Throwable thrwbl) {
		super(string, thrwbl);
	}

	public BadParamException(Throwable thrwbl) {
		super(thrwbl);
	}

	public boolean isValidated() {
		return validated;
	}

	public void setValidated(boolean validated) {
		this.validated = validated;
	}


	

}
