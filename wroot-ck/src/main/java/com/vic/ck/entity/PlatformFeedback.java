package com.vic.ck.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vic.ck.util.CommonUtils;
import com.vic.wroot.common.annotation.AttachmentFlag;
import com.vic.wroot.common.annotation.AttachmentFlag.AttachmenType;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.List;

/**
 * 意见反馈表
 *
 * @author VIC
 */
public class PlatformFeedback {

    private Integer id;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "反馈人id")
    private Integer customerId;

    /**
     * 反馈人
     */
    @ApiModelProperty(value = "反馈人")
    private String customerName;

    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    private String content;

    /**
     * 1-用户 2-商家
     */
    @ApiModelProperty(value = " 1-用户 2-商家")
    private Integer type;

    /**
     * 附件表ids
     */
    @ApiModelProperty(value = "附件表ids")
    @AttachmentFlag(AttachmenType.SIGNS)
    private String images;

    /**
     * 提交时间
     */
    @ApiModelProperty(value = "提交时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 0-初始 1-已经查看
     */
    private Integer status;

    /**
     * 回复内容
     */
    @ApiModelProperty(value = "回复内容")
    private String reply;

    /**
     * 电话号码
     */
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<String> getImageUrls() {
        return CommonUtils.getImageUrls(images);
    }


    public String getTypeDesc() {
        return type == null ? "" : (type == 1 ? "用户" : "商家");
    }

    public String getStatusDesc() {
        return status == null ? "" : (status == 0 ? "未查看" : "已查看");
    }

    public String getCreateTimeDesc() {
        return DateFormatUtils.format(createTime, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return customer_id
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * 反馈人
     *
     * @return cusstomer_name 反馈人
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * 反馈人
     *
     * @param customerName 反馈人
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    /**
     * 内容
     *
     * @return content 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 1-用户 2-商家
     *
     * @return type 1-用户 2-商家
     */
    public Integer getType() {
        return type;
    }

    /**
     * 1-用户 2-商家
     *
     * @param type 1-用户 2-商家
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 附件表ids
     *
     * @return images 附件表ids
     */
    public String getImages() {
        return images;
    }

    /**
     * 附件表ids
     *
     * @param images 附件表ids
     */
    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

    /**
     * 提交时间
     *
     * @return create_time 提交时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 提交时间
     *
     * @param createTime 提交时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 0-初始 1-已经查看
     *
     * @return status 0-初始 1-已经查看
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 0-初始 1-已经查看
     *
     * @param status 0-初始 1-已经查看
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 回复内容
     *
     * @return reply 回复内容
     */
    public String getReply() {
        return reply;
    }

    /**
     * 回复内容
     *
     * @param reply 回复内容
     */
    public void setReply(String reply) {
        this.reply = reply == null ? null : reply.trim();
    }
}