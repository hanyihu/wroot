package com.vic.ck.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vic.ck.api.constant.FinalFiledParams;

/**
 * 我的积分明细
 * @author VIC
 *
 */
public class CustomerScoreRecord {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer customerId;

    /**
     * 积分
     */
    private Integer score;

    /**
     * 创建时间
     */
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createTime;

    /**
     * 类型 1-评论积分 2-消费积分 3-签到积分 4-分享积分 5-邀请好友积分
     */
    private int type;

    /**
     * 简单说明
     */
    private String describe;
    
    

    public CustomerScoreRecord() {
		super();
	}
    
    

	public CustomerScoreRecord(Integer customerId, Integer score, int type) {
		super();
		this.customerId = customerId;
		this.score = score;
		this.type = type;
		this.describe = FinalFiledParams.SCORE_TYPE_MAP.get(type) ;
	}



	/** 
     * 
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return customer_id 
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * 
     * @param customerId 
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * 积分
     * @return score 积分
     */
    public Integer getScore() {
        return score;
    }

    /**
     * 积分
     * @param score 积分
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 类型 1-评论积分 2-消费积分 3-签到积分 4-分享积分 5-邀请好友积分
     * @return type 类型 1-评论积分 2-消费积分 3-签到积分 4-分享积分 5-邀请好友积分
     */
    public int getType() {
        return type;
    }

    /**
     * 类型 1-评论积分 2-消费积分 3-签到积分 4-分享积分 5-邀请好友积分
     * @param type 类型 1-评论积分 2-消费积分 3-签到积分 4-分享积分 5-邀请好友积分
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * 简单说明
     * @return describe 简单说明
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * 简单说明
     * @param describe 简单说明
     */
    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }
}