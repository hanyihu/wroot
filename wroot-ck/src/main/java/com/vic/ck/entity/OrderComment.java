package com.vic.ck.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vic.wroot.common.annotation.AttachmentFlag;
import com.vic.wroot.common.annotation.AttachmentFlag.AttachmenType;

/**
 * 订单评论表
 * 
 * @author VIC
 */
public class OrderComment implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 订单号
	 */
	@NotNull( message= "订单号不能为空")
	private String orderno;
	
	/**
	 * 1-商家 2-团购 3-酒店
	 */
	private Integer type;
	/**
	 * 用户
	 */
	private Integer customerId;
	/**
	 * 评论的商家
	 */
	@NotNull( message= "评论的商家不能为空")
	private Integer merchantId;
	/**
	 * 团购或酒店房间id
	 */
	private Integer commodityId;
	/**
	 * 图片附件ids
	 */
	@AttachmentFlag(AttachmenType.SIGNS)
	private String images;
	/**
	 * 评论内容
	 */
	@NotNull( message= "评论内容不能为空")
	private String content;
	/**
	 * 总评分
	 */
	private Double star;
	/**
	 * 环境评分
	 */
	private Double environmentStar;
	/**
	 * 口味评分-美食才有
	 */
	private Double flavorStar;
	/**
	 * 服务评价
	 */
	private Double serviceStar;
	/**
	 * 商家回复
	 */
	private String reply;
	/**
	 * 点赞数
	 */
	private Integer praiseNum;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm" ,timezone = "GMT+8")
	private Date createTime;
	
	@JsonIgnore
	private Integer isDelete;

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
	 * set：订单号
	 */
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	/**
	 * get：订单号
	 */
	public String getOrderno() {
		return orderno;
	}

	/**
	 * set：用户
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	/**
	 * get：用户
	 */
	public Integer getCustomerId() {
		return customerId;
	}

	/**
	 * set：评论的商家
	 */
	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	/**
	 * get：评论的商家
	 */
	public Integer getMerchantId() {
		return merchantId;
	}

	
	public Integer getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}

	/**
	 * set： 图片附件ids
	 */
	public void setImages(String images) {
		this.images = images;
	}

	/**
	 * get： 图片附件ids
	 */
	public String getImages() {
		return images;
	}

	/**
	 * set：评论内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * get：评论内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * set：总评分
	 */
	public void setStar(Double star) {
		this.star = star;
	}

	/**
	 * get：总评分
	 */
	public Double getStar() {
		return star;
	}

	/**
	 * set：环境评分
	 */
	public void setEnvironmentStar(Double environmentStar) {
		this.environmentStar = environmentStar;
	}

	/**
	 * get：环境评分
	 */
	public Double getEnvironmentStar() {
		return environmentStar;
	}

	/**
	 * set：口味评分-美食才有
	 */
	public void setFlavorStar(Double flavorStar) {
		this.flavorStar = flavorStar;
	}

	/**
	 * get：口味评分-美食才有
	 */
	public Double getFlavorStar() {
		return flavorStar;
	}

	/**
	 * set：服务评价
	 */
	public void setServiceStar(Double serviceStar) {
		this.serviceStar = serviceStar;
	}

	/**
	 * get：服务评价
	 */
	public Double getServiceStar() {
		return serviceStar;
	}

	/**
	 * set：商家回复
	 */
	public void setReply(String reply) {
		this.reply = reply;
	}

	/**
	 * get：商家回复
	 */
	public String getReply() {
		return reply;
	}

	/**
	 * set：点赞数
	 */
	public void setPraiseNum(Integer praiseNum) {
		this.praiseNum = praiseNum;
	}

	/**
	 * get：点赞数
	 */
	public Integer getPraiseNum() {
		return praiseNum;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	
	
}
