package com.vic.wroot.common.util;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;


import com.vic.wroot.common.exception.BadParamException;

public class HibernateValidatorUtils {
	private static final  Validator  validator = Validation.buildDefaultValidatorFactory().getValidator();
	
	public  static <T>  void vaildate(T o) throws BadParamException{
		if(o == null)
			throw new IllegalArgumentException();
		 Set<ConstraintViolation<T>>  constraintViolation= validator.validate(o);
		 if(constraintViolation.size()!=0)
			 throw BadParamException.instanceHibernateVerity(constraintViolation);
	}
	
	public  static  <T> void vaildate(T o,Class<?> cls) throws BadParamException{
		if(o == null)
			throw new IllegalArgumentException();
		Set<ConstraintViolation<T>>  constraintViolation= validator.validate(o,cls);
		 if(constraintViolation.size()!=0)
			 throw BadParamException.instanceHibernateVerity(constraintViolation);
	}
}
