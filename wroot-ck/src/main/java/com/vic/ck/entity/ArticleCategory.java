package com.vic.ck.entity;

import java.io.Serializable;

/**
 * 文章分类表
 * 
 * @author VIC
 */
public class ArticleCategory implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 
	 */
	private String name;

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
	 * set：
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * get：
	 */
	public String getName() {
		return name;
	}
}
