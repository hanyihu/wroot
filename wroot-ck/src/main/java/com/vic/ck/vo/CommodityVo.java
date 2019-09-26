package com.vic.ck.vo;

import com.vic.ck.util.CommonUtils;

/**
 * 商家详情里的团购
 * @author VIC
 *
 */
public class CommodityVo {

	private int id;
	
	private String name;
	
	private int icon;
	
	//价格
	private double price;
	
	//销售数量
	private int sellNum;
	
	
	public String getIconUrl() {
		return CommonUtils.getImageUrl(icon);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getSellNum() {
		return sellNum;
	}

	public void setSellNum(int sellNum) {
		this.sellNum = sellNum;
	}
	
	
}
