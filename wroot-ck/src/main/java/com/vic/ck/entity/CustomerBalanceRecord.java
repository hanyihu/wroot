package com.vic.ck.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vic.ck.api.constant.FinalFiledParams;

/**
 * 用户余额明细表
 * customer_balance_record
 * @author VIC
 *
 */
public class CustomerBalanceRecord {

	private int id;
	
	private int customerId;
	
	private BigDecimal money = new BigDecimal(0.00);
	
	@JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
	private Date createTime;
	
	private String describe;
	
	//明细类别		  1-推荐消费收入 2-下单收入 3-红包收入 4-推荐商户收入 5-活动收入 6-注册送现金  7-消费支出 8-提现 9- 商户退款  10-话费余额转入 11 推荐用户成为商户 12-提现失败退款 13-推荐用户注册
	private int type;
	
	public CustomerBalanceRecord(int customerId, BigDecimal money, int type) {
		super();
		this.customerId = customerId;
		this.money = money;
		this.type = type;
		this.describe = FinalFiledParams.BALANCE_TYPE_MAP.get(type);
	}
	
	public CustomerBalanceRecord(int customerId, BigDecimal money, int type, String describe) {
		super();
		this.customerId = customerId;
		this.money = money;
		this.type = type;
		this.describe = describe;
	}

	public CustomerBalanceRecord() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
}
