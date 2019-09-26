package com.vic.base.aop;

import org.springframework.stereotype.Component;

import com.vic.wroot.common.annotation.LogRecord;

/**
 * 测试AOP
 * @author VIC
 *
 */
@Component
public class TestAopService {
	

	@LogRecord(name = "查询xx操作",value="123" )
	public boolean testAop(String name, int age){
		return true;
	}


}
