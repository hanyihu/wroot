package com.vic.ck.console.customer.lookup;
import com.vic.base.pager.CommonLookup;

/**
 * 封装用户余额表查询条件 
 * 
 * @author VIC
 */
public class CustomerBalanceLookup extends CommonLookup {
	
	private Integer type;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	
	
}
