package com.vic.ck.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author oyml
 * @Description platformactivitydiscountCoupon实体类
 * @date 2222-2-22
 */

public class PlatformActivityDiscountcoupon implements Serializable {
    private static final long serialVersionUID = 1L;


    private Integer id;


    private Integer type;


    private String title;


    private Integer threshold;

    //适用于店铺类型：1全部、2制定可用、
    private Integer apply;


    private Integer num;


    private String icon;


    private Integer content;


    private Date startdate;


    private Date enddate;


    private Integer enabled;


    private String rule;


    private Integer user;


    private Integer remindday;


    private Integer getnum;


    private Date createtime;


    private String rulereduce;


    private String rulediscount;


    private String rulerandom;


    private List<Integer> merchantid = new ArrayList<Integer>();


    public List<Integer> getMerchantid() {
        return merchantid;
    }


    public void setMerchantid(List<Integer> merchantid) {
        this.merchantid = merchantid;
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getType() {
        return type;
    }


    public void setType(Integer type) {
        this.type = type;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public Integer getThreshold() {
        return threshold;
    }


    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }


    public Integer getApply() {
        return apply;
    }


    public void setApply(Integer apply) {
        this.apply = apply;
    }


    public Integer getNum() {
        return num;
    }


    public void setNum(Integer num) {
        this.num = num;
    }


    public String getIcon() {
        return icon;
    }


    public void setIcon(String icon) {
        this.icon = icon;
    }


    public Integer getContent() {
        return content;
    }


    public void setContent(Integer content) {
        this.content = content;
    }


    public Date getStartdate() {
        return startdate;
    }


    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }


    public Date getEnddate() {
        return enddate;
    }


    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }


    public Integer getEnabled() {
        return enabled;
    }


    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }


    public String getRule() {
        return rule;
    }


    public void setRule(String rule) {
        this.rule = rule;
    }


    public Integer getUser() {
        return user;
    }


    public void setUser(Integer user) {
        this.user = user;
    }


    public Integer getRemindday() {
        return remindday;
    }


    public void setRemindday(Integer remindday) {
        this.remindday = remindday;
    }


    public Integer getGetnum() {
        return getnum;
    }


    public void setGetnum(Integer getnum) {
        this.getnum = getnum;
    }


    public Date getCreatetime() {
        return createtime;
    }


    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }


    public String getRulereduce() {
        return rulereduce;
    }


    public void setRulereduce(String rulereduce) {
        this.rulereduce = rulereduce;
    }


    public String getRulediscount() {
        return rulediscount;
    }


    public void setRulediscount(String rulediscount) {
        this.rulediscount = rulediscount;
    }


    public String getRulerandom() {
        return rulerandom;
    }


    public void setRulerandom(String rulerandom) {
        this.rulerandom = rulerandom;
    }


}

