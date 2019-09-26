package com.vic.ck.entity;

import java.io.Serializable;
import java.util.Date;

public class Notice implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 公告id
     */
    private Integer id;
    /**
     * 公告名称
     */
    private String title;
    /**
     * 公告内容
     */
    private String content;
    /**
     * 发布人id
     */
    private Integer issue_user_id;
    /**
     * 发布人姓名
     */
    private String issue_user_name;
    /**
     * 发布的城市
     */
    private Integer issue_city_id;
    /**
     * 发布时间
     */
    private Date issue_time;
    /**
     * 公告类型
     */
    private Integer notice_type;
    /**
     * 发送给个人时的手机号
     */
    private String to_user_mobile;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getIssue_user_id() {
        return issue_user_id;
    }

    public void setIssue_user_id(Integer issue_user_id) {
        this.issue_user_id = issue_user_id;
    }

    public String getIssue_user_name() {
        return issue_user_name;
    }

    public void setIssue_user_name(String issue_user_name) {
        this.issue_user_name = issue_user_name;
    }

    public Integer getIssue_city_id() {
        return issue_city_id;
    }

    public void setIssue_city_id(Integer issue_city_id) {
        this.issue_city_id = issue_city_id;
    }

    public Date getIssue_time() {
        return issue_time;
    }

    public void setIssue_time(Date issue_time) {
        this.issue_time = issue_time;
    }

    public Integer getNotice_type() {
        return notice_type;
    }

    public void setNotice_type(Integer notice_type) {
        this.notice_type = notice_type;
    }

    public String getTo_user_mobile() {
        return to_user_mobile;
    }

    public void setTo_user_mobile(String to_user_mobile) {
        this.to_user_mobile = to_user_mobile;
    }
}
