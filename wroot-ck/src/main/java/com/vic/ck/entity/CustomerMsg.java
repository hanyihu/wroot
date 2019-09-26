package com.vic.ck.entity;

import java.io.Serializable;
import java.util.Date;

/**
*@author oyml
*@Description customer_msg实体类
*@date 2222-2-22
*/

public class CustomerMsg implements Serializable{
	private static final long serialVersionUID = 1L;

	
	private Integer id;

	
	private Integer customer_id;

	
	private Integer role;

	
	private String content;

	
	private Date create_time;

	
	private Integer readed;

	
	private Integer type;

	
	private Integer msg_id;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getCustomer_id() {
		return customer_id;
	}


	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}


	public Integer getRole() {
		return role;
	}


	public void setRole(Integer role) {
		this.role = role;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Date getCreate_time() {
		return create_time;
	}


	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}


	public Integer getReaded() {
		return readed;
	}


	public void setReaded(Integer readed) {
		this.readed = readed;
	}


	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}


	public Integer getMsg_id() {
		return msg_id;
	}


	public void setMsg_id(Integer msg_id) {
		this.msg_id = msg_id;
	}

	
	
	
}

