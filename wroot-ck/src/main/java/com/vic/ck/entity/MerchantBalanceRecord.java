package com.vic.ck.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vic.ck.api.constant.FinalFiledParams;

/**
 * 商家余额明细表
 * merchant_balance_record
 * @author VIC
 *
 */
public class MerchantBalanceRecord {

	private int id;
	
	private int merchantId;
	
	private BigDecimal money = new BigDecimal(0.00);
	
	@JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
	private Date createTime;
	
	private String describe;
	
	//明细类别明细类别1-用户付款 2-团购销售收入 3-给用户退款 4-提款 5 酒店预订收入
	private int type;
	
	public MerchantBalanceRecord(int merchantId, BigDecimal money, int type) {
		super();
		this.merchantId = merchantId;
		this.money = money;
		this.type = type;
		this.describe = FinalFiledParams.MERCHANT_BALANCE_TYPE_MAP.get(type);
	}

	public MerchantBalanceRecord() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}
	
	
}
