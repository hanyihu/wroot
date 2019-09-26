package com.vic.ck.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论点赞表
 *
 * @author jiachonggao
 */
public class ArticlePraise implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer id;
    /**
     * 操作人
     */
    private Integer customerId;
    /**
     * 目标id
     */
    private Integer targetId;
    /**
     * 1-商品相关评论点赞 2-文章点赞 3-文章的评论点赞(暂无 原型上没看到)
     */
    private Integer type;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    private Integer articleId;


    public ArticlePraise(Integer customerId, Integer articleId, Integer type) {
        this.customerId = customerId;
        this.type = type;
        this.articleId = articleId;
    }

    public ArticlePraise() {
        super();
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    /**
     * get：
     */
    public Integer getId() {
        return id;
    }

    /**
     * set：
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * get：操作人
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * set：操作人
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * get：目标id
     */
    public Integer getTargetId() {
        return targetId;
    }

    /**
     * set：目标id
     */
    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    /**
     * get：1-商品相关评论点赞
     */
    public Integer getType() {
        return type;
    }

    /**
     * set：1-商品相关评论点赞
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * get：
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * set：
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ArticlePraise{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", targetId=" + targetId +
                ", type=" + type +
                ", createTime=" + createTime +
                ", articleId=" + articleId +
                '}';
    }
}
