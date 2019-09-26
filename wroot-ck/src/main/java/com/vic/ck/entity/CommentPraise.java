package com.vic.ck.entity;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 评论点赞表
 * 
 * @author VIC
 */
public class CommentPraise implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
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
	/**
	 * 
	 */
	@JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime;
	
	
	

	public CommentPraise(Integer customerId, Integer targetId, Integer type) {
		super();
		this.customerId = customerId;
		this.targetId = targetId;
		this.type = type;
	}

	public CommentPraise() {
		super();
	}

	/**
	 * set：
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * get：
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * set：操作人
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	/**
	 * get：操作人
	 */
	public Integer getCustomerId() {
		return customerId;
	}

	/**
	 * set：目标id
	 */
	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}

	/**
	 * get：目标id
	 */
	public Integer getTargetId() {
		return targetId;
	}

	/**
	 * set：1-商品相关评论点赞
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * get：1-商品相关评论点赞
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * set：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * get：
	 */
	public Date getCreateTime() {
		return createTime;
	}
}
