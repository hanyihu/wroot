package com.vic.ck.vo;

import java.util.Date;

import com.vic.ck.util.CommonUtils;

/**
 * 签到列表
 * （其实是用户的 罗列）
 * @author VIC
 *
 */
public class SignVo {
	
	private int id;
	
	private String customerId;
	
	private String customerName;
	
	private int customerHeadpic;
	
	private Date createTime;
	
	
	public String getCustomerHeadPicUrl() {
		return CommonUtils.getImageUrl(customerHeadpic);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getCustomerHeadpic() {
		return customerHeadpic;
	}

	public void setCustomerHeadpic(int customerHeadpic) {
		this.customerHeadpic = customerHeadpic;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
		
}
