
package com.vic.ck.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础配置表 basic_config
 * @author houhaoran
 */
public class BasicConfig implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Integer id;
    /**
     * 所属模块
     */
    private String type;
    /**
     * 系统信息-系统名称
     */
    private String name;
    /**
     * 系统信息-IPC备案
     */
    private String icp_record;
    /**
     * 系统信息-网站关键字
     */
    private String web_keyword;
    /**
     * 提示音管理-提示音类型
     */
    private Integer prompt_type;
    /**
     * 提示音管理-提示音名称
     */
    private String prompt_name;
    /**
     * 提示音管理-提示音附件ID
     */
    private Integer prompt_attachId;
    /**
     * 提现管理-提现类型：1.商家 2.骑手 3.用户
     */
    private Integer fetch_type;
    /**
     * 提现管理-是否开启：1.开启 0.不开启
     */
    private Integer fetch_open;
    /**
     * 提现管理-提现开始时间
     */
    private String fetch_startTime;
    /**
     * 提现管理-提现结束时间
     */
    private String fetch_endTime;
    /**
     * 提现管理-提现规则
     */
    private Integer fetch_rule;
    /**
     * 提现管理-提现方式：1.微信 2.支付宝 3.银行卡
     */
    private Integer fetch_way;
    /**
     * 提现管理-提现手续费：百分比(%)
     */
    private Integer fetch_poundage;
    /**
     * 分销管理-佣金类型
     */
    private Integer commission_type;
    /**
     * 分销管理-是否开启
     */
    private Integer commission_open;
    /**
     * 分销管理-佣金数目
     */
    private String commission_amount;
    /**
     * 小程序管理-小程序名称
     */
    private String app_name;
    /**
     * 小程序管理-appid
     */
    private String app_appid;
    /**
     * 小程序管理-appsecret
     */
    private String app_appsecret;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcp_record() {
        return icp_record;
    }

    public void setIcp_record(String icp_record) {
        this.icp_record = icp_record;
    }

    public String getWeb_keyword() {
        return web_keyword;
    }

    public void setWeb_keyword(String web_keyword) {
        this.web_keyword = web_keyword;
    }

    public Integer getPrompt_type() {
        return prompt_type;
    }

    public void setPrompt_type(Integer prompt_type) {
        this.prompt_type = prompt_type;
    }

    public String getPrompt_name() {
        return prompt_name;
    }

    public void setPrompt_name(String prompt_name) {
        this.prompt_name = prompt_name;
    }

    public Integer getPrompt_attachId() {
        return prompt_attachId;
    }

    public void setPrompt_attachId(Integer prompt_attachId) {
        this.prompt_attachId = prompt_attachId;
    }

    public Integer getFetch_type() {
        return fetch_type;
    }

    public void setFetch_type(Integer fetch_type) {
        this.fetch_type = fetch_type;
    }

    public Integer getFetch_open() {
        return fetch_open;
    }

    public void setFetch_open(Integer fetch_open) {
        this.fetch_open = fetch_open;
    }

    public String getFetch_startTime() {
        return fetch_startTime;
    }

    public void setFetch_startTime(String fetch_startTime) {
        this.fetch_startTime = fetch_startTime;
    }

    public String getFetch_endTime() {
        return fetch_endTime;
    }

    public void setFetch_endTime(String fetch_endTime) {
        this.fetch_endTime = fetch_endTime;
    }

    public Integer getFetch_rule() {
        return fetch_rule;
    }

    public void setFetch_rule(Integer fetch_rule) {
        this.fetch_rule = fetch_rule;
    }

    public Integer getFetch_way() {
        return fetch_way;
    }

    public void setFetch_way(Integer fetch_way) {
        this.fetch_way = fetch_way;
    }

    public Integer getFetch_poundage() {
        return fetch_poundage;
    }

    public void setFetch_poundage(Integer fetch_poundage) {
        this.fetch_poundage = fetch_poundage;
    }

    public Integer getCommission_type() {
        return commission_type;
    }

    public void setCommission_type(Integer commission_type) {
        this.commission_type = commission_type;
    }

    public Integer getCommission_open() {
        return commission_open;
    }

    public void setCommission_open(Integer commission_open) {
        this.commission_open = commission_open;
    }

    public String getCommission_amount() {
        return commission_amount;
    }

    public void setCommission_amount(String commission_amount) {
        this.commission_amount = commission_amount;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getApp_appid() {
        return app_appid;
    }

    public void setApp_appid(String app_appid) {
        this.app_appid = app_appid;
    }

    public String getApp_appsecret() {
        return app_appsecret;
    }

    public void setApp_appsecret(String app_appsecret) {
        this.app_appsecret = app_appsecret;
    }
}