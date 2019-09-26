package com.vic.ck.console.mall.lookup;
import com.vic.base.pager.CommonLookup;

/**
 * 封装我的兑换-一般为积分兑换查询条件 
 * 
 * @author VIC
 */
public class CustomerSwapLookup extends CommonLookup {
	
	private String swapno;
	
	private String swapMallName;

	public String getSwapno() {
		return swapno;
	}

	public void setSwapno(String swapno) {
		this.swapno = swapno;
	}

	public String getSwapMallName() {
		return swapMallName;
	}

	public void setSwapMallName(String swapMallName) {
		this.swapMallName = swapMallName;
	}
	
	
	
}
