package com.vic.ck.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vic.ck.util.CommonUtils;
import com.vic.wroot.common.annotation.AttachmentFlag;
import com.vic.wroot.common.annotation.AttachmentFlag.AttachmenType;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 注册用户
 *
 * @author VIC
 */
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private Integer id;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

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
    @ApiModelProperty(value = "生日")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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
    @ApiModelProperty(value = "头像")
    @AttachmentFlag(AttachmenType.SIGN)
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
    private Integer recommendQcodePath;

    /**
     * 我的推荐地址
     */
    private String recommendUrl;

    /**
     * 我是被谁推荐的
     */
    private Integer recommendId;

    /**
     * 是否可用
     */
    private boolean enabled;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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
     * 1-用户 2-商家(商家必定是用户)
     */
    private Integer type;

    /**
     * 用户积分
     */
    private Integer score;

    /*是否骑手  0否  1是*/
    private Integer isRider;

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    private Double lng;

    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度")
    private Double lat;


    /*骑手健康证*/
    @ApiModelProperty(value = "骑手健康证")
    @AttachmentFlag(AttachmentFlag.AttachmenType.SIGNS)
    private String healthCard;


    /*骑手健康证号*/
    @ApiModelProperty(value = "骑手健康证号")
    private String healthCardNo;


    /*健康证有效期*/
   @ApiModelProperty(value = "健康证有效期")
    private String healthValidity;

    /*骑手配送城市*/
    @ApiModelProperty(value = "骑手配送城市")
    private String sendCity;

    /*骑手配送区域*/
    @ApiModelProperty(value = "骑手配送区域")
    private String sendArea;

    /*骑手身份证*/
    @ApiModelProperty(value = "骑手身份证")
    @AttachmentFlag(AttachmentFlag.AttachmenType.SIGNS)
    private String idCard;


    /*身份证有效期*/
    @ApiModelProperty(value = "身份证有效期")
    private String idValidity;



    /*骑手姓名*/
    @ApiModelProperty(value = "骑手姓名")
    private String name;

    /*骑手紧急联系人*/
    @ApiModelProperty(value = "骑手紧急联系人")
    private String emergency;

    /*紧急联系人电话*/
    @ApiModelProperty(value = "紧急联系人电话")
    private String emergencyPhone;

	/*骑手交通工具*/
    @ApiModelProperty(value = "骑手交通工具(1电动车  2自行车  3摩托车  4汽车)")
	private Integer vehicle;

	/*骑手婚姻状况*/
    @ApiModelProperty(value = "骑手婚姻状况 1未婚 2已婚")
    private Integer marriage;

    /*骑手学历*/
    @ApiModelProperty(value = "骑手学历（1博士  2硕士  3本科  4专科  5其他）")
	private Integer education;

	/*开通骑手的手机号*/
    @ApiModelProperty(value = "开通骑手的手机号 ")
	private  String ttlAccounts;

    /*骑手年龄*/
    private int age;

    /*骑手是否空闲  0否 1是*/
    private Integer isfree;

    /*骑手名单状态 0普通状态    1黑名单  2白名单*/
    private Integer listStatus;

    /*骑手距离商家的距离*/
    private Double diatance;

    public List<String> getHealthImage() {
        if ((healthCard == null)) {
            return null;
        }
        return CommonUtils.getImageUrls(healthCard);
    }


    public List<String> getIdCardImage() {
        if ((idCard == null)) {
            return null;
        }
        return CommonUtils.getImageUrls(idCard);
    }

    public String getHealthCardNo() {
        return healthCardNo;
    }

    public void setHealthCardNo(String healthCardNo) {
        this.healthCardNo = healthCardNo;
    }

    public Double getDiatance() {
		return diatance;
	}

    public void setDiatance(Double diatance) {
        this.diatance = diatance;
    }

    public Integer getIsfree() {
        return isfree;
    }

    public void setIsfree(Integer isfree) {
        this.isfree = isfree;
    }

    public Integer getListStatus() {
        return listStatus;
    }

    public void setListStatus(Integer listStatus) {
        this.listStatus = listStatus;
    }

    public Integer getIsRider() {
        return isRider;
    }

    public void setIsRider(Integer isRider) {
        this.isRider = isRider;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }


	public String getRecommendQcodePathUrl() {
    	return CommonUtils.getImageUrl(recommendQcodePath);
    }

    /**
     * 单设备登录,token
     */
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Customer() {
        super();
    }

    public Customer(int id) {
        this.id = id;
    }


    public Customer(Integer id, Integer recommendQcodePath, String recommendUrl) {
        super();
        this.id = id;
        this.recommendQcodePath = recommendQcodePath;
        this.recommendUrl = recommendUrl;
    }

    public Customer(String nickname, Integer thirdPartyType, String thirdPartyUid, String thirdPartyHeadpic) {
        super();
        this.nickname = nickname;
        this.thirdPartyType = thirdPartyType;
        this.thirdPartyUid = thirdPartyUid;
        this.thirdPartyHeadpic = thirdPartyHeadpic;
    }

    public Customer(String mobile, String password, String nickname) {
        super();
        this.mobile = mobile;
        this.password = password;
        this.nickname = nickname;
    }


    public Customer(Integer id, Integer recommendId) {
        super();
        this.id = id;
        this.recommendId = recommendId;
    }

    public String getHeadpicUrl() {
        if ((headpic == null || headpic == 0) && StringUtils.isNotEmpty(thirdPartyHeadpic)) {
            return thirdPartyHeadpic;
        }
        return CommonUtils.getImageUrl(headpic);
    }

    public Integer getId() {
        return id;
    }

    public Customer setId(Integer id) {
        this.id = id;
        return this;
    }

    public int getAge() {
		return age;
	}

    public void setAge(int age) {
        this.age = age;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Integer getHomeCity() {
        return homeCity;
    }

    public void setHomeCity(Integer homeCity) {
        this.homeCity = homeCity;
    }

    public Integer getLiveProvince() {
        return liveProvince;
    }

    public void setLiveProvince(Integer liveProvince) {
        this.liveProvince = liveProvince;
    }

    public Integer getLiveCity() {
        return liveCity;
    }

    public void setLiveCity(Integer liveCity) {
        this.liveCity = liveCity;
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

    public Customer setRecommendId(Integer recommendId) {
        this.recommendId = recommendId;
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getScore() {
        return score;
    }

    public Customer setScore(Integer score) {
        this.score = score;
        return this;
    }

    public String getHomeProvinceName() {
        return homeProvinceName;
    }

    public void setHomeProvinceName(String homeProvinceName) {
        this.homeProvinceName = homeProvinceName;
    }

    public String getHomeCityName() {
        return homeCityName;
    }

    public void setHomeCityName(String homeCityName) {
        this.homeCityName = homeCityName;
    }

    public String getLiveProvinceName() {
        return liveProvinceName;
    }

    public void setLiveProvinceName(String liveProvinceName) {
        this.liveProvinceName = liveProvinceName;
    }

    public String getLiveCityName() {
        return liveCityName;
    }

    public void setLiveCityName(String liveCityName) {
        this.liveCityName = liveCityName;
    }

    public String getMerchantMobileCode() {
        return merchantMobileCode;
    }

    public void setMerchantMobileCode(String merchantMobileCode) {
        this.merchantMobileCode = merchantMobileCode;
    }

    public String getThirdPartyHeadpic() {
        return thirdPartyHeadpic;
    }

    public void setThirdPartyHeadpic(String thirdPartyHeadpic) {
        this.thirdPartyHeadpic = thirdPartyHeadpic;
    }


    public String getHealthValidity() {
        return healthValidity;
    }

    public void setHealthValidity(String healthValidity) {
        this.healthValidity = healthValidity;
    }

    public String getSendCity() {
        return sendCity;
    }

    public void setSendCity(String sendCity) {
        this.sendCity = sendCity;
    }

    public String getSendArea() {
        return sendArea;
    }

    public void setSendArea(String sendArea) {
        this.sendArea = sendArea;
    }

    public String getIdValidity() {
        return idValidity;
    }

    public void setIdValidity(String idValidity) {
        this.idValidity = idValidity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

	public Integer getVehicle() {
		return vehicle;
	}

	public void setVehicle(Integer vehicle) {
		this.vehicle = vehicle;
	}

    public Integer getMarriage() {
        return marriage;
    }

    public void setMarriage(Integer marriage) {
        this.marriage = marriage;
    }

	public Integer getEducation() {
		return education;
	}

	public void setEducation(Integer education) {
		this.education = education;
	}

    public String getTtlAccounts() {
        return ttlAccounts;
    }

    public void setTtlAccounts(String ttlAccounts) {
        this.ttlAccounts = ttlAccounts;
    }

    public String getHealthCard() {
        return healthCard;
    }

    public void setHealthCard(String healthCard) {
        this.healthCard = healthCard;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }


}
