package com.vic.ck.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vic.ck.api.handler.conver.Date2yyyyMMdd;
import com.vic.ck.util.CommonUtils;
import com.vic.wroot.common.annotation.AttachmentFlag;
import com.vic.wroot.common.annotation.AttachmentFlag.AttachmenType;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 商家团购
 * @author VIC
 *
 */
public class MerchantGroupBuy {
    /**
     * 
     */
    private Integer id;

    /**
     * 商户id
     */
    private Integer merchantId;
    
    @JsonIgnore
    private String merchantName;

    @JsonIgnore
    private String merchantMobile;
    /**
     * 团购名称
     */
    private String name;

    /**
     * 团购价格
     */
    private BigDecimal price;

    /**
     * 开始时间
     */
    @DateTimeFormat( pattern = "yyyy-MM-dd")
    @JsonSerialize(using = Date2yyyyMMdd.class)
    private Date startTime;

    /**
     * 结束时间
     */
    @DateTimeFormat( pattern = "yyyy-MM-dd")
    @JsonSerialize(using = Date2yyyyMMdd.class)
    private Date endTime;

    /**
     * 是否24小时可消费
     */
    private Boolean consumedAllHours;

    /**
     * 消费开始时间
     */
    @DateTimeFormat( pattern = "HH:mm")
    @JsonFormat( pattern = "HH:mm" ,timezone = "GMT+8")
    private Date consumeStartTime;

    /**
     * 消费结束时间
     */
    @JsonFormat( pattern = "HH:mm" ,timezone = "GMT+8")
    @DateTimeFormat( pattern = "HH:mm")
    private Date consumeEndTime;

    /**
     * 团购标签(政策) 多个逗号分隔
     */
    private String labels;

    /**
     * 购买须知 多个逗号分隔
     */
    private String tips;

    /**
     * 团购文字介绍
     */
    private String describe;

    /**
     * 团购图片
     */
    @AttachmentFlag(AttachmenType.SIGNS)
    private String images;

    /**
     * 0-待上线 1-已上线 2-已下线 
     */
    private int status;

    /**
     * 销售数量
     */
    private Integer sellNum;

    /**
     * 评论数
     */
    private Integer commentNum;
    
    @JsonIgnore
    private Integer isDelete;
    
    @JsonIgnore
    private Date createTime;
    
    @JsonIgnore
    private BigDecimal star;

    /**
     * 团购图标
     */
    @AttachmentFlag(AttachmenType.SIGN)
    private Integer icon;

    
    public String getIconUrl() {
    	return CommonUtils.getImageUrl(icon);
    }
    
    public List<String> getImageUrls() {
    	return CommonUtils.getImageUrls(images);
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
     * 商户id
     * @return merchant_id 商户id
     */
    public Integer getMerchantId() {
        return merchantId;
    }

    /**
     * 商户id
     * @param merchantId 商户id
     */
    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * 团购名称
     * @return name 团购名称
     */
    public String getName() {
        return name;
    }

    /**
     * 团购名称
     * @param name 团购名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 团购价格
     * @return price 团购价格
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 团购价格
     * @param price 团购价格
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 开始时间
     * @return start_time 开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 开始时间
     * @param startTime 开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 结束时间
     * @return end_time 结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 结束时间
     * @param endTime 结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 是否24小时可消费
     * @return consumed_all_hours 是否24小时可消费
     */
    public Boolean getConsumedAllHours() {
        return consumedAllHours;
    }

    /**
     * 是否24小时可消费
     * @param consumedAllHours 是否24小时可消费
     */
    public void setConsumedAllHours(Boolean consumedAllHours) {
        this.consumedAllHours = consumedAllHours;
    }

    /**
     * 消费开始时间
     * @return consume_start_time 消费开始时间
     */
    public Date getConsumeStartTime() {
        return consumeStartTime;
    }

    /**
     * 消费开始时间
     * @param consumeStartTime 消费开始时间
     */
    public void setConsumeStartTime(Date consumeStartTime) {
        this.consumeStartTime = consumeStartTime;
    }

    /**
     * 消费结束时间
     * @return consume_end_time 消费结束时间
     */
    public Date getConsumeEndTime() {
        return consumeEndTime;
    }

    /**
     * 消费结束时间
     * @param consumeEndTime 消费结束时间
     */
    public void setConsumeEndTime(Date consumeEndTime) {
        this.consumeEndTime = consumeEndTime;
    }

    /**
     * 团购标签(政策) 多个逗号分隔
     * @return labels 团购标签(政策) 多个逗号分隔
     */
    public String getLabels() {
        return labels;
    }

    /**
     * 团购标签(政策) 多个逗号分隔
     * @param labels 团购标签(政策) 多个逗号分隔
     */
    public void setLabels(String labels) {
        this.labels = labels == null ? null : labels.trim();
    }

    /**
     * 购买须知 多个逗号分隔
     * @return tips 购买须知 多个逗号分隔
     */
    public String getTips() {
        return tips;
    }

    /**
     * 购买须知 多个逗号分隔
     * @param tips 购买须知 多个逗号分隔
     */
    public void setTips(String tips) {
        this.tips = tips == null ? null : tips.trim();
    }

    /**
     * 团购文字介绍
     * @return describe 团购文字介绍
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * 团购文字介绍
     * @param describe 团购文字介绍
     */
    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    /**
     * 团购图片
     * @return images 团购图片
     */
    public String getImages() {
        return images;
    }

    /**
     * 团购图片
     * @param images 团购图片
     */
    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

    /**
     * 0-初始 1-上架 2-下架- 3-删除
     * @return status 0-初始 1-上架 2-下架- 3-删除
     */
    public int getStatus() {
        return status;
    }

    /**
     * 0-初始 1-上架 2-下架- 3-删除
     * @param status 0-初始 1-上架 2-下架- 3-删除
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * 销售数量
     * @return sell_num 销售数量
     */
    public Integer getSellNum() {
        return sellNum;
    }

    /**
     * 销售数量
     * @param sellNum 销售数量
     */
    public void setSellNum(Integer sellNum) {
        this.sellNum = sellNum;
    }

    /**
     * 评论数
     * @return comment_num 评论数
     */
    public Integer getCommentNum() {
        return commentNum;
    }

    /**
     * 评论数
     * @param commentNum 评论数
     */
    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    /**
     * 团购图标
     * @return icon 团购图标
     */
    public Integer getIcon() {
        return icon;
    }

    /**
     * 团购图标
     * @param icon 团购图标
     */
    public void setIcon(Integer icon) {
        this.icon = icon;
    }

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getMerchantMobile() {
		return merchantMobile;
	}

	public void setMerchantMobile(String merchantMobile) {
		this.merchantMobile = merchantMobile;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getStar() {
		return star;
	}

	public void setStar(BigDecimal star) {
		this.star = star;
	}
    
    

}