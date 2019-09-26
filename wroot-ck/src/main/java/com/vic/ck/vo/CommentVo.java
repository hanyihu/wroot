package com.vic.ck.vo;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vic.ck.util.CommonUtils;

/**
 * 订单的品论
 * @author VIC
 *
 */
public class CommentVo {

	private Integer id;
	/**
	 * 订单号
	 */
	private String orderno;
	/**
	 * 用户
	 */
	private Integer customerId;
	
	/**
	 * 头像
	 */
	private int customerHeadpic;
	
	/**
	 * 姓名
	 */
	private String customerName;
	
	/**
	 * 图片附件ids
	 */
	private String images;
	/**
	 * 评论内容
	 */
	private String content;
	/**
	 * 总评分
	 */
	private double star;
	
	/**
	 * 商家回复
	 */
	private String reply;
	/**
	 * 点赞数
	 */
	private Integer praiseNum;
	
	/**
	 * 是否已经点赞
	 */
	private boolean praised;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date createTime;
	
	public List<String> getImageUrls(){
		return CommonUtils.getImageUrls(images);
	}
	
	public String getHeadpicUrl(){
		return CommonUtils.getImageUrl(customerHeadpic);
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public int getCustomerHeadpic() {
		return customerHeadpic;
	}

	public void setCustomerHeadpic(int customerHeadpic) {
		this.customerHeadpic = customerHeadpic;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public Integer getPraiseNum() {
		return praiseNum;
	}

	public void setPraiseNum(Integer praiseNum) {
		this.praiseNum = praiseNum;
	}

	public boolean isPraised() {
		return praised;
	}

	public void setPraised(boolean praised) {
		this.praised = praised;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public double getStar() {
		return star;
	}

	public void setStar(double star) {
		this.star = star;
	}

	
	
	
	
	
}
