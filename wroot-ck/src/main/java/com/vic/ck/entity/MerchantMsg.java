package com.vic.ck.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 商家留言表
 */
public class MerchantMsg implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 商家id
     */
    private Integer merchant_id;
    /**
     * 商家名称
     */
    private String merchant_name;
    /**
     * 留言人id
     */
    private Integer customer_id;
    /**
     * 留言人姓名
     */
    private String customer_name;
    /**
     * 留言内容
     */
    private String content;
    /**
     * 留言时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date msg_time;
    /**
     * 是否包含违禁词汇
     */
    private Integer is_illegal_word;
    /**
     * 后台处理状态 0.未读 1.已读 2.已处理 3.不处理
     */
    private Integer status;

    public String getMsgTimeDesc() {
        return DateFormatUtils.format(msg_time, "yyyy-MM-dd HH:mm:ss");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(Integer merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getMerchant_name() {
        return merchant_name;
    }

    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name;
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

    public Date getMsg_time() {
        return msg_time;
    }

    public void setMsg_time(Date msg_time) {
        this.msg_time = msg_time;
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
