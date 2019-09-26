package com.vic.ck.entity;

import java.io.Serializable;

/**
 * 区域
 * 
 * @author VIC
 */
public class SysArea implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private Integer id;
	/**
	 * pid
	 */
	private Integer parentId;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 简称
	 */
	private String shortName;
	/**
	 * 层级:0国家级别，1省级，2地市级，3区县级
	 */
	private Integer levelType;
	/**
	 * 父层级名称
	 */
	private String mergerName;
	/**
	 * 父层级简称
	 */
	private String mergerShortName;
	/**
	 * 城市编码
	 */
	private String areaCode;
	/**
	 * 邮政编码
	 */
	private String postalCode;
	/**
	 * 拼音
	 */
	private String pinyin;
	/**
	 * 简拼
	 */
	private String jianpin;
	/**
	 * 首字母
	 */
	private String firstChar;
	/**
	 * 经度
	 */
	private Double lng;
	/**
	 * 纬度
	 */
	private Double lat;
	/**
	 * 是否开放
	 */
	private Integer opened;
	/**
	 * 是否热门城市
	 */
	private Integer hot;
	
	
	public String getOpenedStr() {
		return 1== opened ? "是":"否";
	}
	
	public String getHotStr() {
		return 1== hot ? "是":"否";
	}

	/**
	 * set：id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * get：id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * set：pid
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	/**
	 * get：pid
	 */
	public Integer getParentId() {
		return parentId;
	}

	/**
	 * set：名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * get：名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * set：简称
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	/**
	 * get：简称
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * set：层级:0国家级别，1省级，2地市级，3区县级
	 */
	public void setLevelType(Integer levelType) {
		this.levelType = levelType;
	}

	/**
	 * get：层级:0国家级别，1省级，2地市级，3区县级
	 */
	public Integer getLevelType() {
		return levelType;
	}

	/**
	 * set：父层级名称
	 */
	public void setMergerName(String mergerName) {
		this.mergerName = mergerName;
	}

	/**
	 * get：父层级名称
	 */
	public String getMergerName() {
		return mergerName;
	}

	/**
	 * set：父层级简称
	 */
	public void setMergerShortName(String mergerShortName) {
		this.mergerShortName = mergerShortName;
	}

	/**
	 * get：父层级简称
	 */
	public String getMergerShortName() {
		return mergerShortName;
	}

	/**
	 * set：城市编码
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * get：城市编码
	 */
	public String getAreaCode() {
		return areaCode;
	}

	/**
	 * set：邮政编码
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * get：邮政编码
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * set：拼音
	 */
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	/**
	 * get：拼音
	 */
	public String getPinyin() {
		return pinyin;
	}

	/**
	 * set：简拼
	 */
	public void setJianpin(String jianpin) {
		this.jianpin = jianpin;
	}

	/**
	 * get：简拼
	 */
	public String getJianpin() {
		return jianpin;
	}

	/**
	 * set：首字母
	 */
	public void setFirstChar(String firstChar) {
		this.firstChar = firstChar;
	}

	/**
	 * get：首字母
	 */
	public String getFirstChar() {
		return firstChar;
	}

	/**
	 * set：经度
	 */
	public void setLng(Double lng) {
		this.lng = lng;
	}

	/**
	 * get：经度
	 */
	public Double getLng() {
		return lng;
	}

	/**
	 * set：纬度
	 */
	public void setLat(Double lat) {
		this.lat = lat;
	}

	/**
	 * get：纬度
	 */
	public Double getLat() {
		return lat;
	}

	/**
	 * set：是否开放
	 */
	public void setOpened(Integer opened) {
		this.opened = opened;
	}

	/**
	 * get：是否开放
	 */
	public Integer getOpened() {
		return opened;
	}

	/**
	 * set：是否热门城市
	 */
	public void setHot(Integer hot) {
		this.hot = hot;
	}

	/**
	 * get：是否热门城市
	 */
	public Integer getHot() {
		return hot;
	}
}
