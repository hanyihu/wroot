package com.vic.ck.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vic.wroot.common.annotation.AttachmentFlag;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * 骑手人员
 */
public class RiderPersonnel {

    private Integer id;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 1-男 2-女 0-保密
     */
    private Integer gender;

    /**
     * 生日
     */
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthday;

    /**
     * 家乡-省id
     */
    private Integer homeProvince;

    private String homeProvinceName;

    /**
     * 家乡-市id
     */
    private Integer homeCity;

    private String homeCityName;
    /**
     * 常住地-省id
     */
    private Integer liveProvince;

    private String liveProvinceName;
    /**
     * 常住地-市id
     */
    private Integer liveCity;

    private String liveCityName;

    /**
     * 头像-对应附件表id
     */
    @AttachmentFlag(AttachmentFlag.AttachmenType.SIGN)
    private Integer headpic;

    /**
     * 第三方登陆方式 1-微信 2-qq 3-支付宝
     */
    private Integer thirdPartyType;

    /**
     * 第三方登陆id
     */
    private String thirdPartyUid;

    /**
     * 第三方登陆头像
     */
    private String thirdPartyHeadpic;

    /**
     * 我的推荐二维码
     */
    private Integer recommendQcodePath ;

    /**
     * 我的推荐地址
     */
    private String recommendUrl;

    /**
     * 我是被谁推荐的
     */
    private Integer recommendId;

    /**
     * 我的邀请码
     */
    private String  recommendCode;

    /**
     * 是否可用 0 不可用 1 可用
     */
    private Integer enabled;

    /**
     * 创建时间
     */
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 手机设备号-用户端
     */
    @JsonIgnore
    private String mobileCode;

    /**
     * 手机设备号-商家端
     */
    private String merchantMobileCode;

    /**
     * 1-用户 2-骑手(骑手必定是用户)
     */
    private Integer type;

    /**
     * 用户积分
     */
    private Integer score;

    /**
     * 配送区域
     */
    private String sendarea;

    /**
     * 是否是vip 0 ：不是 1： 是
     */
    private Integer viped;

    /**
     * 支付宝账号
     */
    private String aliAccount;
    /**
     * 微信账号
     */
    private String wechatAccount;
    /**
     * 淘宝账号
     */
    private String taobaoAccount;
    /**
     * 是否登录过 0 ：无 1： 有
     */
    private Integer logged;
    /**
     * 健康证
     */
    private Integer healthCard;
    /**
     * 健康证有效期
     */
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date healthValidity;
    /**
     * 身份证
     */
    private Integer idCard;
    /**
     * 身份证有效期
     */
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date idValidity;
    /**
     * 骑手紧急联系人
     */
    private String emergency;
    /**
     * 骑手紧急联系人电话
     */
    private String emergencyPhone;
    /**
     * 婚姻状况 0 为结婚 1 已结婚
     */
    private Integer marriage;
    /**
     * 学历
     */
    private String education;
    /**
     * 邀请收益
     */
    private Double invitationFee;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getHomeProvince() {
        return homeProvince;
    }

    public void setHomeProvince(Integer homeProvince) {
        this.homeProvince = homeProvince;
    }

    public String getHomeProvinceName() {
        return homeProvinceName;
    }

    public void setHomeProvinceName(String homeProvinceName) {
        this.homeProvinceName = homeProvinceName;
    }

    public Integer getHomeCity() {
        return homeCity;
    }

    public void setHomeCity(Integer homeCity) {
        this.homeCity = homeCity;
    }

    public String getHomeCityName() {
        return homeCityName;
    }

    public void setHomeCityName(String homeCityName) {
        this.homeCityName = homeCityName;
    }

    public Integer getLiveProvince() {
        return liveProvince;
    }

    public void setLiveProvince(Integer liveProvince) {
        this.liveProvince = liveProvince;
    }

    public String getLiveProvinceName() {
        return liveProvinceName;
    }

    public void setLiveProvinceName(String liveProvinceName) {
        this.liveProvinceName = liveProvinceName;
    }

    public Integer getLiveCity() {
        return liveCity;
    }

    public void setLiveCity(Integer liveCity) {
        this.liveCity = liveCity;
    }

    public String getLiveCityName() {
        return liveCityName;
    }

    public void setLiveCityName(String liveCityName) {
        this.liveCityName = liveCityName;
    }

    public Integer getHeadpic() {
        return headpic;
    }

    public void setHeadpic(Integer headpic) {
        this.headpic = headpic;
    }

    public Integer getThirdPartyType() {
        return thirdPartyType;
    }

    public void setThirdPartyType(Integer thirdPartyType) {
        this.thirdPartyType = thirdPartyType;
    }

    public String getThirdPartyUid() {
        return thirdPartyUid;
    }

    public void setThirdPartyUid(String thirdPartyUid) {
        this.thirdPartyUid = thirdPartyUid;
    }

    public String getThirdPartyHeadpic() {
        return thirdPartyHeadpic;
    }

    public void setThirdPartyHeadpic(String thirdPartyHeadpic) {
        this.thirdPartyHeadpic = thirdPartyHeadpic;
    }

    public Integer getRecommendQcodePath() {
        return recommendQcodePath;
    }

    public void setRecommendQcodePath(Integer recommendQcodePath) {
        this.recommendQcodePath = recommendQcodePath;
    }

    public String getRecommendUrl() {
        return recommendUrl;
    }

    public void setRecommendUrl(String recommendUrl) {
        this.recommendUrl = recommendUrl;
    }

    public Integer getRecommendId() {
        return recommendId;
    }

    public void setRecommendId(Integer recommendId) {
        this.recommendId = recommendId;
    }

    public Integer isEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMobileCode() {
        return mobileCode;
    }

    public void setMobileCode(String mobileCode) {
        this.mobileCode = mobileCode;
    }

    public String getMerchantMobileCode() {
        return merchantMobileCode;
    }

    public void setMerchantMobileCode(String merchantMobileCode) {
        this.merchantMobileCode = merchantMobileCode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getSendarea() {
        return sendarea;
    }

    public void setSendarea(String sendarea) {
        this.sendarea = sendarea;
    }

    public Integer getViped() {
        return viped;
    }

    public void setViped(Integer viped) {
        this.viped = viped;
    }

    public String getAliAccount() {
        return aliAccount;
    }

    public void setAliAccount(String aliAccount) {
        this.aliAccount = aliAccount;
    }

    public String getWechatAccount() {
        return wechatAccount;
    }

    public void setWechatAccount(String wechatAccount) {
        this.wechatAccount = wechatAccount;
    }

    public String getRecommendCode() {
        return recommendCode;
    }

    public void setRecommendCode(String recommendCode) {
        this.recommendCode = recommendCode;
    }

    public String getTaobaoAccount() {
        return taobaoAccount;
    }

    public void setTaobaoAccount(String taobaoAccount) {
        this.taobaoAccount = taobaoAccount;
    }

    public Integer getLogged() {
        return logged;
    }

    public void setLogged(Integer logged) {
        this.logged = logged;
    }

    public Integer getHealthCard() {
        return healthCard;
    }

    public void setHealthCard(Integer healthCard) {
        this.healthCard = healthCard;
    }

    public Date getHealthValidity() {
        return healthValidity;
    }

    public void setHealthValidity(Date healthValidity) {
        this.healthValidity = healthValidity;
    }

    public Integer getIdCard() {
        return idCard;
    }

    public void setIdCard(Integer idCard) {
        this.idCard = idCard;
    }

    public Date getIdValidity() {
        return idValidity;
    }

    public void setIdValidity(Date idValidity) {
        this.idValidity = idValidity;
    }

    public String getEmergency() {
        return emergency;
    }

    public void setEmergency(String emergency) {
        this.emergency = emergency;
    }

    public String getEmergencyPhone() {
        return emergencyPhone;
    }

    public void setEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }

    public Integer getMarriage() {
        return marriage;
    }

    public void setMarriage(Integer marriage) {
        this.marriage = marriage;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Double getInvitationFee() {
        return invitationFee;
    }

    public void setInvitationFee(Double invitationFee) {
        this.invitationFee = invitationFee;
    }
}
