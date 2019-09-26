package com.vic.ck.console.entity;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

public class BaseEntity {
	
	protected static final String PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	protected Integer id;
	
	protected boolean isDelete;
	
	protected Date createTime;

	
	public String getCreateTimeStr(){
		return createTime != null? DateFormatUtils.format(createTime, PATTERN) : "";
	}
	
	public Integer getId() {
		return id == null? 0 : id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
