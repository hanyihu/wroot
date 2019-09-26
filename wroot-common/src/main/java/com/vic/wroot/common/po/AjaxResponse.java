package com.vic.wroot.common.po;
/**
 * AJAX  返回JSON的对象 一般用于文件上传 
 * 
 * @author VIC
 *
 */
public class AjaxResponse {

	private int code;//0 表示成功
	
	private String message;
	
	private int id;//附件id
	
	private String value;//附件本项目中的地址
	
	private String url;
	
	private String fileName;
	
	private long size;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
