package com.vic.ck.console.customer.lookup;
import com.vic.base.pager.CommonLookup;

/**
 * 封装用户表查询条件 
 * 
 * @author VIC
 */
public class CustomerLookup extends CommonLookup {

	private String homeCityName;
	
	private String liveCityName;
	
	private Integer gender;

	public String getHomeCityName() {
		return homeCityName;
	}

	public void setHomeCityName(String homeCityName) {
		this.homeCityName = homeCityName;
	}

	public String getLiveCityName() {
		return liveCityName;
	}

	public void setLiveCityName(String liveCityName) {
		this.liveCityName = liveCityName;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}
	
	
}
