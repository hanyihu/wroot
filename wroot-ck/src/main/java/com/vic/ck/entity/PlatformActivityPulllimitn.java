package com.vic.ck.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author oyml
 * @Description platform_activity_pullLimitn实体类
 * @date 2019-4-18
 */

public class PlatformActivityPulllimitn implements Serializable {
    private static final long serialVersionUID = 1L;


    private Integer id;


    private Integer type;


    private String title;


    private String icon;


    private Integer content;


    private Date startdate;


    private Date enddate;


    private Integer enabled;


    private String rule;


    private Date createtime;


    private String rulereduce;


    private String rulediscount;


    private String rulepiecereduce;


    private String rulepiecediscount;
    //店铺使用类型
    private Integer apply;
    //指定商品类型优惠
    private Integer commodity;

    private Integer merchantid;


    public Integer getMerchantid() {
        return merchantid;
    }


    public void setMerchant_id(Integer merchantid) {
        this.merchantid = merchantid;
    }


    public Integer getCommodity() {
        return commodity;
    }


    public void setCommodity(Integer commodity) {
        this.commodity = commodity;
    }


    public Integer getApply() {
        return apply;
    }


    public void setApply(Integer apply) {
        this.apply = apply;
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


    public String getRulepiecereduce() {
        return rulepiecereduce;
    }


    public void setRulepiecereduce(String rulepiecereduce) {
        this.rulepiecereduce = rulepiecereduce;
    }


    public String getRulepiecediscount() {
        return rulepiecediscount;
    }


    public void setRulepiecediscount(String rulepiecediscount) {
        this.rulepiecediscount = rulepiecediscount;
    }


}

