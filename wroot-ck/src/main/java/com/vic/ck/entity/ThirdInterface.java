package com.vic.ck.entity;

import java.io.Serializable;

/**
 * 第三方接口相关表
 */
public class ThirdInterface implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 第三方接口名称
     */
    private String interface_name;
    /**
     * 第三方接口类型
     */
    private String interface_type;
    /**
     * 第三方接口id
     */
    private String interface_id;
    /**
     * 第三方接口公钥
     */
    private String public_key;
    /**
     * 第三方接口私钥
     */
    private String private_key;
    /**
     * 第三方接口app_id
     */
    private String app_id;
    /**
     * 第三方接口app_key;
     */
    private String app_key;
    /**
     * 第三方接口app_secret;
     */
    private String app_secret;
    /**
     * 第三方接口AccessKeyId
     */
    private String access_key_id;
    /**
     * 第三方接口AccessKeySecret
     */
    private String access_key_secret;
    /**
     * 第三方接口签名
     */
    private String sign_name;
    /**
     * 第三方接口模板编码
     */
    private String template_code;
    /**
     * 第三方接口模板参数
     */
    private String template_param;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInterface_name() {
        return interface_name;
    }

    public void setInterface_name(String interface_name) {
        this.interface_name = interface_name;
    }

    public String getInterface_type() {
        return interface_type;
    }

    public void setInterface_type(String interface_type) {
        this.interface_type = interface_type;
    }

    public String getInterface_id() {
        return interface_id;
    }

    public void setInterface_id(String interface_id) {
        this.interface_id = interface_id;
    }

    public String getPublic_key() {
        return public_key;
    }

    public void setPublic_key(String public_key) {
        this.public_key = public_key;
    }

    public String getPrivate_key() {
        return private_key;
    }

    public void setPrivate_key(String private_key) {
        this.private_key = private_key;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getApp_key() {
        return app_key;
    }

    public void setApp_key(String app_key) {
        this.app_key = app_key;
    }

    public String getApp_secret() {
        return app_secret;
    }

    public void setApp_secret(String app_secret) {
        this.app_secret = app_secret;
    }

    public String getAccess_key_id() {
        return access_key_id;
    }

    public void setAccess_key_id(String access_key_id) {
        this.access_key_id = access_key_id;
    }

    public String getAccess_key_secret() {
        return access_key_secret;
    }

    public void setAccess_key_secret(String access_key_secret) {
        this.access_key_secret = access_key_secret;
    }

    public String getSign_name() {
        return sign_name;
    }

    public void setSign_name(String sign_name) {
        this.sign_name = sign_name;
    }

    public String getTemplate_code() {
        return template_code;
    }

    public void setTemplate_code(String template_code) {
        this.template_code = template_code;
    }

    public String getTemplate_param() {
        return template_param;
    }

    public void setTemplate_param(String template_param) {
        this.template_param = template_param;
    }
}
