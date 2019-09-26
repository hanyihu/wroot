package com.vic.ck.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品评价表
 */
public class CommodityEvaluation implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 商品id
     */
    private Integer commodity_id;
    /**
     * 商品名称
     */
    private String commodity_name;
    /**
     * 评价人id
     */
    private Integer customer_id;
    /**
     * 评价人姓名
     */
    private String customer_name;
    /**
     * 评价内容
     */
    private String content;
    /**
     * 评价时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date evaluation_time;
    /**
     * 是否包含违禁词汇
     */
    private Integer is_illegal_word;
    /**
     * 后台处理状态 0.未读 1.已读 2.已处理 3.不处理
     */
    private Integer status;

    public String getEvaluationTimeDesc() {
        return DateFormatUtils.format(evaluation_time, "yyyy-MM-dd HH:mm:ss");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(Integer commodity_id) {
        this.commodity_id = commodity_id;
    }

    public String getCommodity_name() {
        return commodity_name;
    }

    public void setCommodity_name(String commodity_name) {
        this.commodity_name = commodity_name;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getEvaluation_time() {
        return evaluation_time;
    }

    public void setEvaluation_time(Date evaluation_time) {
        this.evaluation_time = evaluation_time;
    }

    public Integer getIs_illegal_word() {
        return is_illegal_word;
    }

    public void setIs_illegal_word(Integer is_illegal_word) {
        this.is_illegal_word = is_illegal_word;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
