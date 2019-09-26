package com.vic.ck.entity;

import java.io.Serializable;
import java.util.Date;

/**
*@author oyml
*@Description swap_goods实体类
*@date 2222-2-22
*/

public class SwapGoods implements Serializable{
	private static final long serialVersionUID = 1L;

	
	private Integer id;

	
	private String name;

	
	private String summary;

	
	private Integer score;

	
	private Integer stock;

	
	private Integer exchange_num;

	
	private Integer icon;

	
	private String images;

	
	private Double price;

	
	private Integer enabled;

	
	private Date create_time;

	
	private String content;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSummary() {
		return summary;
	}


	public void setSummary(String summary) {
		this.summary = summary;
	}


	public Integer getScore() {
		return score;
	}


	public void setScore(Integer score) {
		this.score = score;
	}


	public Integer getStock() {
		return stock;
	}


	public void setStock(Integer stock) {
		this.stock = stock;
	}


	public Integer getExchange_num() {
		return exchange_num;
	}


	public void setExchange_num(Integer exchange_num) {
		this.exchange_num = exchange_num;
	}


	public Integer getIcon() {
		return icon;
	}


	public void setIcon(Integer icon) {
		this.icon = icon;
	}


	public String getImages() {
		return images;
	}


	public void setImages(String images) {
		this.images = images;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public Integer getEnabled() {
		return enabled;
	}


	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}


	public Date getCreate_time() {
		return create_time;
	}


	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}
	
	

}

