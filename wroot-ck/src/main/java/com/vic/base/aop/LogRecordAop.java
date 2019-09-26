package com.vic.base.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.vic.wroot.common.annotation.LogRecord;

@Aspect
@Component
public class LogRecordAop {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	

	/**
	 * 切入点，在添加@LogRecord注解的方法上切入
	 * @return
	 */
	@Pointcut("@annotation(com.vic.wroot.common.annotation.LogRecord)")
	public void point(){
		logger.info("this is a point");
	}
	
	
	@Before("point()")
	public void beforeExce(JoinPoint joinPoint){
		

	}
	
	@After("@annotation(com.vic.wroot.common.annotation.LogRecord)")
	public void afterExec(JoinPoint joinPoint) {
		print(joinPoint);
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		if(method.isAnnotationPresent(LogRecord.class)){
			LogRecord annotation = method.getAnnotation(LogRecord.class);
			if( annotation != null ) {
				System.out.println(annotation.name());
				System.out.println(annotation.value());
			}
		}
	}
	
	
	@Around("@annotation(com.vic.wroot.common.annotation.LogRecord)")
    public void aroundExec(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println("我是Around，来打酱油的");
        pjp.proceed();
    }

	
	private void print(JoinPoint joinPoint){
		System.out.println("--------------------------------------------------");
        System.out.println("Kind:\t"+joinPoint.getKind());
        System.out.println("Target:\t"+joinPoint.getTarget().toString());
        Object[] os=joinPoint.getArgs();
        System.out.println("Args:");
        for(int i=0;i<os.length;i++){
            System.out.println("\t==>参数["+i+"]:\t"+os[i].toString());
        }
        System.out.println("Signature:\t"+joinPoint.getSignature());
        System.out.println("SourceLocation:\t"+joinPoint.getSourceLocation());
        System.out.println("StaticPart:\t"+joinPoint.getStaticPart());
        System.out.println("--------------------------------------------------");

	}
	
	
	
}
