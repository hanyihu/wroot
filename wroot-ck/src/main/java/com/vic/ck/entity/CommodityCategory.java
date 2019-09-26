package com.vic.ck.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.vic.ck.util.CommonUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品分类表
 * @author VIC
 *
 */
public class CommodityCategory implements Serializable  {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
     * 
     */
    private Integer id;

    
	 /**
     * 分类名称
     */
    private String name;
    
    
	 /**
     * 图标
     */
    private Integer icon;
    
    
	 /**
     * 排序
     */
    private Integer sort;
    
	 /**
     * 启用
     */
    private Integer enabled;
    
	 /**
     * 创建时间
     */
	@JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
	private Date create_time;
    
	 /**
     * 上级分类ID
     */
    private Integer pid;
    
	 /**
     * 上级分类ID
     */
    private Integer click_num;
    
    
	 /**
     * 标题
     */
    private String title;
    
    
	 /**
     * 1爱生活-2品牌精选
     */
    private Integer module;

    public String getIconUrl() {
        return CommonUtils.getImageUrl(icon);
    }

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getIcon() {
		return icon;
	}


	public void setIcon(Integer icon) {
		this.icon = icon;
	}


	public Integer getSort() {
		return sort;
	}


	public void setSort(Integer sort) {
		this.sort = sort;
	}


	public Integer getEnabled() {
		return enabled;
	}


	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}


	public Date getCreate_time() {
		return create_time;
	}


	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}


	public Integer getPid() {
		return pid;
	}


	public void setPid(Integer pid) {
		this.pid = pid;
	}


	public Integer getClick_num() {
		return click_num;
	}


	public void setClick_num(Integer click_num) {
		this.click_num = click_num;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Integer getModule() {
		return module;
	}


	public void setModule(Integer module) {
		this.module = module;
	}

    
    
    
 
    
    
    
    
    
    
    
    
    
    
	
}
