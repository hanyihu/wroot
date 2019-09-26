package com.vic.ck.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vic.ck.util.CommonUtils;
import com.vic.wroot.common.annotation.AttachmentFlag;
import com.vic.wroot.common.annotation.AttachmentFlag.AttachmenType;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 文章表
 *
 * @author VIC
 */
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 标题
	 */
    @ApiModelProperty(value = "标题")
	private String title;
	/**
	 * 简介
	 */
    @ApiModelProperty(value = "简介")
	private String summary;
	/**
	 * 
	 */
    @ApiModelProperty(value = "图标")
	@AttachmentFlag(AttachmenType.SIGN)
	private Integer icon;
	/**
	 * 所属城市
	 */
	private Integer cityId;
	
	private String cityName;
	/**
	 * 所属分类
	 */
	private Integer categoryId;
	
	private String categoryName;
	/**
	 * 内容
	 */
    @ApiModelProperty(value = "内容")
	@AttachmentFlag(AttachmenType.CONTENT)
	private String content;
	/**
	 * 创建时间
	 */
    @ApiModelProperty(value = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm" ,timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date createTime;
	/**
	 * 是否显示
	 */
	private Integer enabled;
	/**
	 * 是否开启评论
	 */
	private Integer openComment;
	/**
	 * 点赞数
	 */
	private Integer praiseNum;
	/**
	 * 评论数
	 */
	private Integer commentNum;
	
	/**
	 * 当前用户是否点赞
	 */
	private Boolean isPraised;
	
	
	private List<ArticleComment> comments;

    /*骑手管理细则是否显示*/
    @ApiModelProperty(value = "是否显示")
    private boolean riderDetailShow;

    /**
     * 文章浏览量
     */
    private Integer viewNum;

    public Integer getViewNum() {
        return viewNum;
    }

    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
    }

	public String getIconUrl() {
		return CommonUtils.getImageUrl(icon);
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
     * get：标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * set：标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * get：简介
     */
    public String getSummary() {
        return summary;
    }

    /**
     * set：简介
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * get：
     */
    public Integer getIcon() {
        return icon;
    }

    /**
     * set：
     */
    public void setIcon(Integer icon) {
        this.icon = icon;
    }

    /**
     * get：所属城市
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     * set：所属城市
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     * get：所属分类
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * set：所属分类
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * get：内容
     */
    public String getContent() {
        return content;
    }

    /**
     * set：内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * get：创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * set：创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * get：是否显示
     */
    public Integer getEnabled() {
        return enabled;
    }

	/**
	 * get：是否开启评论
	 */
	public Integer getOpenComment() {
		return openComment;
	}

	/**
	 * set：是否开启评论
	 */
	public void setOpenComment(Integer openComment) {
		this.openComment = openComment;
	}

	/**
	 * set：点赞数
	 */
	public void setPraiseNum(Integer praiseNum) {
		this.praiseNum = praiseNum;
	}
    /**
     * set：是否显示
     */
    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    /**
     * get：点赞数
     */
    public Integer getPraiseNum() {
        return praiseNum;
    }


    /**
     * get：评论数
     */
    public Integer getCommentNum() {
        return commentNum;
    }

    /**
     * set：评论数
     */
    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Boolean getIsPraised() {
        return isPraised;
    }

    public void setIsPraised(Boolean isPraised) {
        this.isPraised = isPraised;
    }

    public List<ArticleComment> getComments() {
        return comments;
    }

    public void setComments(List<ArticleComment> comments) {
        this.comments = comments;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public boolean isRiderDetailShow() {
        return riderDetailShow;
    }

    public void setRiderDetailShow(boolean riderDetailShow) {
        this.riderDetailShow = riderDetailShow;
    }
}
