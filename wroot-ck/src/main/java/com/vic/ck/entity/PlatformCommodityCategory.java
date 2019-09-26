package com.vic.ck.entity;

import java.io.Serializable;
import java.util.Date;

/*
* 商品分类
* */

/**
 * @author hanyihu
 * @date 2019/4/20 13:37
 */
public class PlatformCommodityCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;

    /*分类名称*/
    private String name;

    /*图标*/
    private String icon;

    /*排序*/
    private int sort;

    /*是否启用*/
    private Boolean enabled;

    /*创建时间*/
    private Date createTime;

    /*上级id*/
    private Integer pid;

    /*
     * 二级分类的点击次数
     * */
    private int clickNum;

    /*
     * 商品分类名称
     * */
    private String categoryName;

    /*第一级分类名称*/
    private String businessName;

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public int getClickNum() {
        return clickNum;
    }

    public void setClickNum(int clickNum) {
        this.clickNum = clickNum;
    }


}
