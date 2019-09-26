package com.vic.ck.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *  用户搜索记录表
 * @author VIC
 *
 */
public class SelectNoter   implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private Integer id;

    private String commodity;
    
    private Integer customer_id;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date select_time;

	//备注
    private String note;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCommodity() {
		return commodity;
	}

	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}

	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	public Date getSelect_time() {
		return select_time;
	}

	public void setSelect_time(Date select_time) {
		this.select_time = select_time;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	 

	
    
}