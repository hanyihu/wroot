package com.vic.ck.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vic.ck.util.CommonUtils;

/**
 * 文章评论表
 * 
 * @author VIC
 */
public class ArticleComment implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer id;
	/**
	 * article id
	 */
	private Integer articleId;
	/**
	 * 用户
	 */
	private Integer customerId;
	/**
	 * 用户名
	 */
	private String customerName;
	/**
	 * 用户头像
	 */
	private Integer headpic;
	/**
	 * 图片附件ids
	 */
	private String images;
	/**
	 * 评论内容
	 */
	private String content;
	/**
	 * 平台回复
	 */
	private String reply;
	/**
	 * 点赞数
	 */
	private Integer praise;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone="GMT+8")
	private Date createTime;

	public List<String> getImageUrls() {
		return CommonUtils.getImageUrls(images);
	}
	
	public String getCustomerHeadpicUrl(){
		return CommonUtils.getImageUrl(headpic);
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
	 * set：artcle id
	 */
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	/**
	 * get：artcle id
	 */
	public Integer getArticleId() {
		return articleId;
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
	 * set：平台回复
	 */
	public void setReply(String reply) {
		this.reply = reply;
	}

	/**
	 * get：平台回复
	 */
	public String getReply() {
		return reply;
	}

	/**
	 * set：点赞数
	 */
	public void setPraise(Integer praise) {
		this.praise = praise;
	}

	/**
	 * get：点赞数
	 */
	public Integer getPraise() {
		return praise;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getHeadpic() {
		return headpic;
	}

	public void setHeadpic(Integer headpic) {
		this.headpic = headpic;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
}
