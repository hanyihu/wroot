package com.vic.ck.entity;

import java.util.ArrayList;
import java.util.List;

public class BusinessCategory {

	private Integer id;

	private Integer parentId;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 层级
	 */
	private Integer level;
	/**
	 * 父层级名称
	 */
	private String parentName;
	/**
	 * 类目编码
	 */
	private Integer categoryCode;

	/**
	 * 首字母
	 */
	private String firstChar;

	private List<BusinessCategory> categorys = new ArrayList<BusinessCategory>();

	private Integer opened;

	private Integer hot;

	public List<BusinessCategory> getCategorys() {
		return categorys;
	}

	public void setCategorys(List<BusinessCategory> categorys) {
		this.categorys = categorys;
	}

	public Integer getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(Integer categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getFirstChar() {
		return firstChar;
	}

	public void setFirstChar(String firstChar) {
		this.firstChar = firstChar;
	}

	public Integer getOpened() {
		return opened;
	}

	public void setOpened(Integer opened) {
		this.opened = opened;
	}

	public Integer getHot() {
		return hot;
	}

	public void setHot(Integer hot) {
		this.hot = hot;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

}
