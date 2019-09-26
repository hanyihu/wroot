package com.vic.wroot.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日志AOP注解
 * @author VIC
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface LogRecord {
	
	//如果一个属性 尽量使用value
	String value() default "";
	
	String name();
	
}
