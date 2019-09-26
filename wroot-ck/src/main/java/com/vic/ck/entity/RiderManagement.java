package com.vic.ck.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vic.ck.util.CommonUtils;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ：hanyihu
 * @date ：2019/5/8 9:06
 */
public class RiderManagement implements Serializable {

    private static final long serialVersionUID = 1L;

    /*id*/
    private Integer id;

    /*标题*/
    @ApiModelProperty(value = "标题")
    private String title;

    /*图标*/
    @ApiModelProperty(value = "图标")
    private Integer icon;

    /*内容*/
    @ApiModelProperty(value = "内容")
    private String content;

    /*创建时间*/
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /*是否显示*/
    @ApiModelProperty(value = "是否显示")
    private boolean isShow;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
        this.icon = icon;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public String getIconUrl() {
        return CommonUtils.getImageUrl(icon);
    }
}
