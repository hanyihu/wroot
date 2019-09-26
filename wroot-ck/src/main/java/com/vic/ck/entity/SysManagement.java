package com.vic.ck.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class SysManagement implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 系统管理类型
     */
    private String type;

    /**
     * 骑手端-邀请是否开启：1.开启 0.不开启
     */
    @ApiModelProperty(value = "骑手端-邀请是否开启：1.开启 0.不开启")
    private Integer rider_invite;

    /**
     * 骑手端-骑手服务标准
     */
    @ApiModelProperty(value = "骑手端-骑手服务标准")
    private String rider_service_standard;


    /**
     * 生活服务管理-内容管理
     */
    @ApiModelProperty(value = "生活服务管理-内容管理")
    private String life_content;

    /**
     * 积分管理-积分类型
     */
    private Integer integral_type;
    /**
     * 积分管理-是否开启
     */
    private Integer integral_open;
    /**
     * 积分管理-规则x
     */
    private Integer integral_x;
    /**
     * 积分管理-规则x
     */
    private Integer integral_y;
    /**
     * 积分管理-规则x
     */
    private Integer integral_z;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getRider_invite() {
        return rider_invite;
    }

    public void setRider_invite(Integer rider_invite) {
        this.rider_invite = rider_invite;
    }

    public String getRider_service_standard() {
        return rider_service_standard;
    }

    public void setRider_service_standard(String rider_service_standard) {
        this.rider_service_standard = rider_service_standard;
    }

    public String getLife_content() {
        return life_content;
    }

    public void setLife_content(String life_content) {
        this.life_content = life_content;
    }

    public Integer getIntegral_type() {
        return integral_type;
    }

    public void setIntegral_type(Integer integral_type) {
        this.integral_type = integral_type;
    }

    public Integer getIntegral_open() {
        return integral_open;
    }

    public void setIntegral_open(Integer integral_open) {
        this.integral_open = integral_open;
    }

    public Integer getIntegral_x() {
        return integral_x;
    }

    public void setIntegral_x(Integer integral_x) {
        this.integral_x = integral_x;
    }

    public Integer getIntegral_y() {
        return integral_y;
    }

    public void setIntegral_y(Integer integral_y) {
        this.integral_y = integral_y;
    }

    public Integer getIntegral_z() {
        return integral_z;
    }

    public void setIntegral_z(Integer integral_z) {
        this.integral_z = integral_z;
    }
}
