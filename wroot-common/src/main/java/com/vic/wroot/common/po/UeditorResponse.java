package com.vic.wroot.common.po;

/**
 * 百度富文本返回的json
 * @author VIC
 *
 */
public class UeditorResponse {
	/*
	 {
		"state": "SUCCESS",
		"original": "80px - \u526f\u672c (2).jpg",
		"size": "13252",
		"title": "1465731377326075274.jpg",
		"type": ".jpg",
		"url": "/ueditor/jsp/upload/image/20160612/1465731377326075274.jpg"
	}
	 */

	private String state;
	
	private String original;
	private long size;
	
	private String title;
	
	private String type;
	
	private String url;
	
	
	public UeditorResponse success(){
		this.state = "SUCCESS";
		return this;
	}
	
	public UeditorResponse error(){
		this.state = "ERROR";
		return this;
	}
	
	
	public UeditorResponse(){}
	
	public static  UeditorResponse toUeditor(AjaxResponse response){
		String type = response.getFileName().substring( response.getFileName().indexOf("."));
		UeditorResponse ueditor = new UeditorResponse(response.getFileName(), response.getSize(), response.getFileName(),type, response.getUrl());
		if(response.getCode() == 0) {
			ueditor.success();
		}else {
			ueditor.error();
		}
		
		return ueditor;
	}
	
	public UeditorResponse(String original, long size, String title, String type, String url) {
		super();
		this.original = original;
		this.size = size;
		this.title = title;
		this.type = type;
		this.url = url;
	}

	public UeditorResponse(String state, String original, long size, String title, String type, String url) {
		super();
		this.state = state;
		this.original = original;
		this.size = size;
		this.title = title;
		this.type = type;
		this.url = url;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
	
}
