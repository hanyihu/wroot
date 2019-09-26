package com.vic.wroot.common.attachment.service;

import com.vic.wroot.common.annotation.AttachmentFlag;
import com.vic.wroot.common.annotation.AttachmentFlag.AttachmenType;

public class Test {

	@AttachmentFlag(AttachmenType.SIGN)
	private int icon ;
	@AttachmentFlag(AttachmenType.SIGN)
	private Integer image;
	@AttachmentFlag(AttachmenType.SIGNS)
	private String images;
	
	@AttachmentFlag(AttachmenType.CONTENT)
	private String context;
	
	private int id;

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public Integer getImage() {
		return image;
	}

	public void setImage(Integer image) {
		this.image = image;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Test() {
		
	}

	public Test(int icon, Integer image, String images, String context) {
		super();
		this.icon = icon;
		this.image = image;
		this.images = images;
		this.context = context;
	}
	
	
	
	
}
