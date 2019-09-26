package com.vic.wroot.common.util.excel;

public class ExcelHeader {

	private String name;
	private String width;
	
	public static class Builder{
		private String name;
		private String width;
		
        public Builder(){
        	super();
        }
        public Builder name(String name){
        	this.name = name;
        	return this;
        }
        public Builder width(String width){
        	this.width = width;
        	return this;
        }
        public ExcelHeader bulid(){
        	return new ExcelHeader(this);
        }
	}
	
	public ExcelHeader(Builder builder){
		name = builder.name;
		width = builder.width;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	
}
