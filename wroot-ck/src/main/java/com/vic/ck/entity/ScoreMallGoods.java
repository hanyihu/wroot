package com.vic.ck.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vic.ck.util.CommonUtils;
import com.vic.wroot.common.annotation.AttachmentFlag;
import com.vic.wroot.common.annotation.AttachmentFlag.AttachmenType;

/**
 * 积分商城商品
 * 
 * @author VIC
 */
public class ScoreMallGoods implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 兑换所需积分
	 */
	private Integer score;
	/**
	 * 商品价格
	 */
	private BigDecimal price;
	/**
	 * 规格描述，多个用逗号分隔
	 */
	private String norms;
	/**
	 * 图标
	 */
	@AttachmentFlag(AttachmenType.SIGN)
	private Integer icon;
	/**
	 * 商户名称
	 */
	private String merchantName;
	/**
	 * 商户热线
	 */
	private String merchantTel;
	/**
	 * 商户服务时间
	 */
	private String merchantServiceTime;
	/**
	 * 已兑换数量
	 */
	private Integer swapedNum;
	/**
	 * 图文描述
	 */
	@AttachmentFlag(AttachmenType.CONTENT)
	private String describe;
	
	private Integer categoryId;
	
	private String categoryName;
	
	/**
	 *  上下架
	 */
	private Integer enabled;
	/**
	 * 
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime;
	
	public String getIconUul() {
		return CommonUtils.getImageUrl(icon);
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
	 * set：名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * get：名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * set：兑换所需积分
	 */
	public void setScore(Integer score) {
		this.score = score;
	}

	/**
	 * get：兑换所需积分
	 */
	public Integer getScore() {
		return score;
	}

	/**
	 * set：商品价格
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * get：商品价格
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * set：规格描述，多个用逗号分隔
	 */
	public void setNorms(String norms) {
		this.norms = norms;
	}

	/**
	 * get：规格描述，多个用逗号分隔
	 */
	public String getNorms() {
		return norms;
	}

	/**
	 * set：图标
	 */
	public void setIcon(Integer icon) {
		this.icon = icon;
	}

	/**
	 * get：图标
	 */
	public Integer getIcon() {
		return icon;
	}

	/**
	 * set：商户名称
	 */
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	/**
	 * get：商户名称
	 */
	public String getMerchantName() {
		return merchantName;
	}

	/**
	 * set：商户热线
	 */
	public void setMerchantTel(String merchantTel) {
		this.merchantTel = merchantTel;
	}

	/**
	 * get：商户热线
	 */
	public String getMerchantTel() {
		return merchantTel;
	}

	/**
	 * set：商户服务时间
	 */
	public void setMerchantServiceTime(String merchantServiceTime) {
		this.merchantServiceTime = merchantServiceTime;
	}

	/**
	 * get：商户服务时间
	 */
	public String getMerchantServiceTime() {
		return merchantServiceTime;
	}

	/**
	 * set：已兑换数量
	 */
	public void setSwapedNum(Integer swapedNum) {
		this.swapedNum = swapedNum;
	}

	/**
	 * get：已兑换数量
	 */
	public Integer getSwapedNum() {
		return swapedNum;
	}

	/**
	 * set：图文描述
	 */
	public void setDescribe(String describe) {
		this.describe = describe;
	}

	/**
	 * get：图文描述
	 */
	public String getDescribe() {
		return describe;
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

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
	
}
