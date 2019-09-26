package com.vic.ck.entity;

import com.vic.ck.api.constant.FinalFiledParams;

/**
 * 系统启动页
 * @author VIC
 *
 */
public class BootPage {

	private  int id;
	
	private int image;
	
	private String title;
	
	public String getImageUrl(){
		if(image !=0) {
			return FinalFiledParams.ATTACHMENT_PREFIX + image;
		}
		return null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
}
