package com.vic.ck.entity;

import com.vic.ck.util.CommonUtils;

/**
 * 商家图片
 * @author VIC
 *
 */
public class MerchantPhoto {

	private int id;
	
	private int merchantId;
	
	//附件表id
	private int image;
	
	//1-外景 2-内景 3-其他 4-酒店
	private int type;
	
	
	public MerchantPhoto(int merchantId, int image, int type) {
		super();
		this.merchantId = merchantId;
		this.image = image;
		this.type = type;
	}

	public MerchantPhoto() {
		super();
	}

	public String getImageUrl() {
		return CommonUtils.getImageUrl(image);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}

	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
}
