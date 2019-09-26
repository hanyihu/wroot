package com.vic.wroot.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 附件标识注解
 * @author VIC
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface AttachmentFlag {
	
	//如果一个属性 尽量使用value  类型
	AttachmenType value() default AttachmenType.SIGN;
	
	
	
	public enum AttachmenType {
		/** 1 单附件 2 附件ids 3存在内容中的附件*/
		SIGN,SIGNS, CONTENT;
	}
}
