package com.vic.wroot.common.handler.listener;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * TOMCAT关闭应用时的清理工作(1): JDBC Driver
 * http://hongjiang.info/tomcat-jdbc-leak-prevention/
 * @author VIC
 *
 */
public class DriverListener implements ServletContextListener {

	private Logger logger = LoggerFactory.getLogger(DriverListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		Enumeration<Driver> drivers = DriverManager.getDrivers();
		logger.info("DriverListener-->contextDestroyed {}", drivers.hasMoreElements());
		
		while (drivers.hasMoreElements()) {
			Driver driver = drivers.nextElement();
			try {
				DriverManager.deregisterDriver(driver);
				logger.info(String.format("手动 deregistering jdbc driver: %s", driver));
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error(String.format("手动 deregistering jdbc driver: %s", driver));
			}
		}
	}
}
