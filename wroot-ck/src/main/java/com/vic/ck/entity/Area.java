package com.vic.ck.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 区域
 * @author VIC
 *
 */
public class Area {

	private int id;
	
	private int parentId;
	
	private String name;
	
	//层级:0国家级别，1省级，2地市级，3区县级
	private int levelType; 
	
	private String firstChar;//首字母
	
	private List<Area> area = new ArrayList<Area>();

	//opened
	private boolean opened;
	
	//是否热门城市
	private boolean hot;

	public List<Area> getArea() {
		return area;
	}

	public void setArea(List<Area> area) {
		this.area = area;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevelType() {
		return levelType;
	}

	public void setLevelType(int levelType) {
		this.levelType = levelType;
	}

	public String getFirstChar() {
		return firstChar;
	}

	public void setFirstChar(String firstChar) {
		this.firstChar = firstChar;
	}

	public boolean isOpened() {
		return opened;
	}

	public void setOpened(boolean opened) {
		this.opened = opened;
	}

	public boolean isHot() {
		return hot;
	}

	public void setHot(boolean hot) {
		this.hot = hot;
	}
	
	
	
}
