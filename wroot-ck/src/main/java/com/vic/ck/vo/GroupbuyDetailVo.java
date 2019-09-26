package com.vic.ck.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vic.ck.util.CommonUtils;

/**
 * 团购详情
 * @author VIC
 *
 */
public class GroupbuyDetailVo {

	private int id;
	
	private String name;
	
	//购买须知 多个逗号分隔
	private String tips;
	
	//团购标签(政策) 多个逗号分隔
	private String labels;
	
	private String images;
	
	private double price;
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	private Date startTime;
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	private Date endTime;
	
	//是否24小时可消费
	private boolean consumedAllHours;
	@JsonFormat(pattern="HH:mm", timezone="GMT+8")
	private Date consumeStartTime;
	@JsonFormat(pattern="HH:mm", timezone="GMT+8")
	private Date consumeEndTime;
	
	
	private double star;
	
	private String describe;
	
	
	//是否收藏
	private boolean collected;
	

	/**好评率*/
	public String getPraiseRate() {
		//因为star最多是5星
		return new StringBuilder(new BigDecimal(star*20).setScale(0, BigDecimal.ROUND_HALF_UP).toString()).append("%").toString();
	}

	public List<String> getImageUrls(){
		return CommonUtils.getImageUrls(images);
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


	public String getTips() {
		return tips;
	}


	public void setTips(String tips) {
		this.tips = tips;
	}


	public String getLabels() {
		return labels;
	}


	public void setLabels(String labels) {
		this.labels = labels;
	}


	public String getImages() {
		return images;
	}


	public void setImages(String images) {
		this.images = images;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public Date getStartTime() {
		return startTime;
	}


	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}


	public Date getEndTime() {
		return endTime;
	}


	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	public boolean isConsumedAllHours() {
		return consumedAllHours;
	}


	public void setConsumedAllHours(boolean consumedAllHours) {
		this.consumedAllHours = consumedAllHours;
	}


	public Date getConsumeStartTime() {
		return consumeStartTime;
	}


	public void setConsumeStartTime(Date consumeStartTime) {
		this.consumeStartTime = consumeStartTime;
	}


	public Date getConsumeEndTime() {
		return consumeEndTime;
	}


	public void setConsumeEndTime(Date consumeEndTime) {
		this.consumeEndTime = consumeEndTime;
	}


	public double getStar() {
		return star;
	}


	public void setStar(double star) {
		this.star = star;
	}



	public String getDescribe() {
		return describe;
	}



	public void setDescribe(String describe) {
		this.describe = describe;
	}



	public boolean isCollected() {
		return collected;
	}



	public void setCollected(boolean collected) {
		this.collected = collected;
	}
	
	
}
