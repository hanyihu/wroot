package com.vic.ck.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 系统启动页
 * 
 * @author VIC
 */
public class PlatformBootPage implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 图片
	 */
	private Integer image;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 状态 0-禁用 1-可用
	 */
	private Integer status;
	/**
	 * 
	 */
	@JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime;
	/**
	 * 排序
	 */
	private Integer sort;
	
	
	public String getStatusDesc() {
		return status == null ? "禁用" : (status == 1 ? "启用" :"禁用");
	}

	/**
	 * set：
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * get：
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * set：图片
	 */
	public void setImage(Integer image) {
		this.image = image;
	}

	/**
	 * get：图片
	 */
	public Integer getImage() {
		return image;
	}

	/**
	 * set：标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * get：标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * set：状态 0-禁用 1-可用
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * get：状态 0-禁用 1-可用
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * set：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * get：
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * set：排序
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * get：排序
	 */
	public Integer getSort() {
		return sort;
	}
}
