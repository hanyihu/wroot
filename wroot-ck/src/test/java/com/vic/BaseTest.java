package com.vic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * 使用spring-test测试的基本测试类
 * 在测试的时候请继承此类注入要测试的bean
 * @author VIC
 */
//使用注解 或者 extends SpringJUnit4ClassRunner
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/springmvc-servlet.xml", "file:src/main/webapp/WEB-INF/spring-config.xml"})
@ContextConfiguration(locations = {"classpath:/spring-servlet.xml", "classpath:/spring-config.xml"})
public abstract class BaseTest {
	protected Logger logger = LoggerFactory.getLogger(BaseTest.class);
	
	@Test
	public  void test(){
		System.out.println(123);
	}
}