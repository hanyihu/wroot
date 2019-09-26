package com.vic.ck.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vic.ck.util.CommonUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 商户表-申请时产生数据
 *
 * @author VIC
 */
public class ConsoleMerchant implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     *
	 */
	private Integer id;
	/**
	 * 所属用户
	 */
	private Integer customerId;
	/**
	 * 推荐人id
	 */
	private Integer recommendId;

	//推荐人
	private String recommendName;
	/**
	 * 商户名称
	 */
	private String name;
	/**
	 * 省
	 */
	private Integer provinceId;
	/**
	 * 市
	 */
	private Integer cityId;
	/**
	 * 区
	 */
	private Integer areaId;
	/**
	 * 详细地址
	 */
	private String address;
	/**
	 * 附近地标
	 */
	private String landmarks;
	/**
	 * 经度
	 */
	private Double longitude;
	/**
	 * 纬度
	 */
	private Double latitude;
	/**
	 * 类型 1-美食 2-酒店预订 3-其他
	 */
	private Integer type;
	/**
	 * 商家分类的id
	 */
	private Integer categoryId;
	/**
	 * 联系电话
	 */
	private String mobile;
	/**
	 * 外景照 附件表id
	 */
	private Integer outPic;
	/**
	 * 内景照 附件表ids
	 */
	private String inPic;
	/**
	 * 营业执照 图片
	 */
	private Integer licensePic;
	/**
	 * 营业执照 是否长期有效
	 */
	private Integer licensePersistent;
	/**
	 * 营业执照有效期
	 */
	private String licenseDate;
	/**
	 * 营业执照名称
	 */
	private String licenseName;
	/**
	 * 营业执照注册号
	 */
	private String licenseRegcode;
	/**
	 * 营业许可证图片
	 */
	private Integer permitPic;
	/**
	 * 许可证是否长期有效
	 */
	private Integer permitPersistent;
	/**
	 * 营业许可证有效期
	 */
	private String permitDate;
	/**
	 * 其他证明图片 附件ids
	 */
	private String otherProvePic;
	/**
	 * 账户名
	 */
	private String accountName;
	/**
	 * 银行账号
	 */
	private String bankAccount;
	/**
	 * 开户银行
	 */
	private String bankName;
	/**
	 * 状态 1-审核中 2-通过 3-拒绝
	 */
	private Integer status;
	/**
	 * 商家设施，多个逗号分隔 1-可刷卡 2-停车位 3-WIFI 4-无烟区 5-吸烟区 6-包厢
	 */
	private String facility;
	/**
	 * 商家描述
	 */
	private String describe;
	/**
	 * 营业时间
	 */
	private String businessHours;
	/**
	 * 最低消费金额
	 */
	private Double price;
	/**
	 * 服务评分
	 */
	private Double serviceStar;
	/**
	 * 环境评星
	 */
	private Double environmentStar;
	/**
	 * 口味评星
	 */
	private Double flavorStar;
	/**
	 * 总评价 对多10星 1.5 ，2 ，2.5 ，3....
	 */
	private Double star;
	/**
	 * 查看人数 -签到人数
	 */
	private Integer viewNum;
	/**
	 * 评论数
	 */
	private Integer commentNum;
	/**
	 * 好评数 4星以上为好评
	 */
	private Integer niceCommentNum;
	/**
	 * 销售数量
	 */
	private Integer sellNum;
	/**
	 * 当前活动 1-满减 2-优惠券，都有则逗号分隔
	 */
	private String currentActivity;
	/**
	 * 酒店-入店时间
	 */
	private Date hotelInTime;
	/**
	 * 酒店-离店时间
	 */
	private Date hotelOutTime;
	/**
	 * 酒店-可否加床
	 */
	private Integer hotelCanAddBed;
	/**
	 * 酒店-加床价格
	 */
	private BigDecimal hotelAddBedPrice;
	/**
	 * 酒店-是否接待外宾
	 */
	private Integer hotelForeigned;
	/**
	 * 酒店-可否携带宠物
	 */
	private Integer hotelPeted;
	/**
	 * 酒店-是否需要押金
	 */
	private Integer hotelPledge;
	/**
	 * 酒店星级 1-客栈 2-公寓 3-经济舒适型 4-高档豪华型 5-经济连锁 6-二星酒店 7-三星酒店 8-四星 9-五星
	 */
	private Integer hotelLevel;
	/**
	 * 是否镗镗锣精选
	 */
	private Integer wellChosened;
	/**
	 * 镗镗锣精选排序
	 */
	private Integer wellChosenSort;
	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime;
	/**
	 * 二维码 信息如 {"merchantId":1,"merchantName":"张三"}
	 */
	private Integer qrcode;
	/**
	 * 商家状态 1:可用 2:不可用
	 */
	private Integer state;
    /*
     * SELECT a.`id`, a.`name`,b.`nickname` AS customerName, c.`merger_name` AS areaName, a.`type`, d.`name` AS categoryName,a.`out_pic` AS outPic, a.`create_time` AS createTime,
        a.`status`, a.`price` , a.`qrcode`, a.`well_chosened` AS wellChosened, a.`well_chosen_sort` AS wellChosenSort
     */
    /*
     * 所属用户
     */
    private String customerName;
    @JsonIgnore
    private String cityName;
    /*
     * 分类
     */
    private String categoryName;

    public String getQrcodeUrl() {
        return CommonUtils.getImageUrl(qrcode);
    }

    public String getOutPicUrl() {
        return CommonUtils.getImageUrl(outPic);
    }

    public List<String> getInPicUrls() {
        return CommonUtils.getImageUrls(inPic);
    }

	//1-审核中 2-通过 3-拒绝
	public String getStatusDesc() {
		return status == null ?"unknow" :(1==status ? "待审核":( 2==status ? "通过" : "不通过"));
	}

	// 1-美食 2-酒店预订 3-其他
	public String getTypeDesc() {
		return type == null ?"unknow" :(1==type ? "美食":(2==type ? "酒店预订" : "其他"));
	}

    public String getLicensePicUrl() {//营业执照
        return CommonUtils.getImageUrl(licensePic);
    }

	/*
	 * 地区
	 */
	private String areaName;

    public String getPermitPicUrl() {//营业许可证
        return CommonUtils.getImageUrl(permitPic);
    }

    public List<String> getOtherProvePicUrls() {//其他证明图片
        return CommonUtils.getImageUrls(otherProvePic);
    }

    public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
	 * set：所属用户
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	/**
	 * get：所属用户
	 */
	public Integer getCustomerId() {
		return customerId;
	}

	/**
	 * set：推荐人id
	 */
	public void setRecommendId(Integer recommendId) {
		this.recommendId = recommendId;
	}

	/**
	 * get：推荐人id
	 */
	public Integer getRecommendId() {
		return recommendId;
	}

	/**
	 * set：商户名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * get：商户名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * set：省
	 */
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	/**
	 * get：省
	 */
	public Integer getProvinceId() {
		return provinceId;
	}

	/**
	 * set：市
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	/**
	 * get：市
	 */
	public Integer getCityId() {
		return cityId;
	}

	/**
	 * set：区
	 */
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	/**
	 * get：区
	 */
	public Integer getAreaId() {
		return areaId;
	}

	/**
	 * set：详细地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * get：详细地址
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * set：附近地标
	 */
	public void setLandmarks(String landmarks) {
		this.landmarks = landmarks;
	}

	/**
	 * get：附近地标
	 */
	public String getLandmarks() {
		return landmarks;
	}

	/**
	 * get：经度
	 */
	public Double getLongitude() {
		return longitude;
	}

	/**
     * set：经度
	 */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
	}

	/**
	 * get：纬度
	 */
	public Double getLatitude() {
		return latitude;
	}

    /**
     * set：纬度
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

	/**
	 * set：类型 1-美食 2-酒店预订 3-其他
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * get：类型 1-美食 2-酒店预订 3-其他
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * set：商家分类的id
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * get：商家分类的id
	 */
	public Integer getCategoryId() {
		return categoryId;
	}

	/**
	 * set：联系电话
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * get：联系电话
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * set：外景照 附件表id
	 */
	public void setOutPic(Integer outPic) {
		this.outPic = outPic;
	}

	/**
	 * get：外景照 附件表id
	 */
	public Integer getOutPic() {
		return outPic;
	}

	/**
	 * set：内景照 附件表ids
	 */
	public void setInPic(String inPic) {
		this.inPic = inPic;
	}

	/**
	 * get：内景照 附件表ids
	 */
	public String getInPic() {
		return inPic;
	}

	/**
	 * set：营业执照 图片
	 */
	public void setLicensePic(Integer licensePic) {
		this.licensePic = licensePic;
	}

	/**
	 * get：营业执照 图片
	 */
	public Integer getLicensePic() {
		return licensePic;
	}

	/**
	 * set：营业执照 是否长期有效
	 */
	public void setLicensePersistent(Integer licensePersistent) {
		this.licensePersistent = licensePersistent;
	}

	/**
	 * get：营业执照 是否长期有效
	 */
	public Integer getLicensePersistent() {
		return licensePersistent;
	}

	/**
	 * set：营业执照有效期
	 */
	public void setLicenseDate(String licenseDate) {
		this.licenseDate = licenseDate;
	}

	/**
	 * get：营业执照有效期
	 */
	public String getLicenseDate() {
		return licenseDate;
	}

	/**
	 * set：营业执照名称
	 */
	public void setLicenseName(String licenseName) {
		this.licenseName = licenseName;
	}

	/**
	 * get：营业执照名称
	 */
	public String getLicenseName() {
		return licenseName;
	}

	/**
	 * set：营业执照注册号
	 */
	public void setLicenseRegcode(String licenseRegcode) {
		this.licenseRegcode = licenseRegcode;
	}

	/**
	 * get：营业执照注册号
	 */
	public String getLicenseRegcode() {
		return licenseRegcode;
	}

	/**
	 * set：营业许可证图片
	 */
	public void setPermitPic(Integer permitPic) {
		this.permitPic = permitPic;
	}

	/**
	 * get：营业许可证图片
	 */
	public Integer getPermitPic() {
		return permitPic;
	}

	/**
	 * set：许可证是否长期有效
	 */
	public void setPermitPersistent(Integer permitPersistent) {
		this.permitPersistent = permitPersistent;
	}

	/**
	 * get：许可证是否长期有效
	 */
	public Integer getPermitPersistent() {
		return permitPersistent;
	}

	/**
	 * set：营业许可证有效期
	 */
	public void setPermitDate(String permitDate) {
		this.permitDate = permitDate;
	}

	/**
	 * get：营业许可证有效期
	 */
	public String getPermitDate() {
		return permitDate;
	}

	/**
	 * set：其他证明图片 附件ids
	 */
	public void setOtherProvePic(String otherProvePic) {
		this.otherProvePic = otherProvePic;
	}

	/**
	 * get：其他证明图片 附件ids
	 */
	public String getOtherProvePic() {
		return otherProvePic;
	}

	/**
	 * set：账户名
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	/**
	 * get：账户名
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * set：银行账号
	 */
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	/**
	 * get：银行账号
	 */
	public String getBankAccount() {
		return bankAccount;
	}

	/**
	 * set：开户银行
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * get：开户银行
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * set：状态 1-审核中 2-通过 3-拒绝
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * get：状态 1-审核中 2-通过 3-拒绝
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * set：商家设施，多个逗号分隔 1-可刷卡 2-停车位 3-WIFI 4-无烟区 5-吸烟区 6-包厢
	 */
	public void setFacility(String facility) {
		this.facility = facility;
	}

	/**
	 * get：商家设施，多个逗号分隔 1-可刷卡 2-停车位 3-WIFI 4-无烟区 5-吸烟区 6-包厢
	 */
	public String getFacility() {
		return facility;
	}

	/**
	 * set：商家描述
	 */
	public void setDescribe(String describe) {
		this.describe = describe;
	}

	/**
	 * get：商家描述
	 */
	public String getDescribe() {
		return describe;
	}

	/**
	 * set：营业时间
	 */
	public void setBusinessHours(String businessHours) {
		this.businessHours = businessHours;
	}

	/**
	 * get：营业时间
	 */
	public String getBusinessHours() {
		return businessHours;
	}

	/**
	 * set：最低消费金额
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * get：最低消费金额
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * set：服务评分
	 */
	public void setServiceStar(Double serviceStar) {
		this.serviceStar = serviceStar;
	}

	/**
	 * get：服务评分
	 */
	public Double getServiceStar() {
		return serviceStar;
	}

	/**
	 * set：环境评星
	 */
	public void setEnvironmentStar(Double environmentStar) {
		this.environmentStar = environmentStar;
	}

	/**
	 * get：环境评星
	 */
	public Double getEnvironmentStar() {
		return environmentStar;
	}

	/**
	 * set：口味评星
	 */
	public void setFlavorStar(Double flavorStar) {
		this.flavorStar = flavorStar;
	}

	/**
	 * get：口味评星
	 */
	public Double getFlavorStar() {
		return flavorStar;
	}

	/**
	 * set：总评价 对多10星 1.5 ，2 ，2.5 ，3....
	 */
	public void setStar(Double star) {
		this.star = star;
	}

	/**
	 * get：总评价 对多10星 1.5 ，2 ，2.5 ，3....
	 */
	public Double getStar() {
		return star;
	}

	/**
	 * set：查看人数 -签到人数
	 */
	public void setViewNum(Integer viewNum) {
		this.viewNum = viewNum;
	}

	/**
	 * get：查看人数 -签到人数
	 */
	public Integer getViewNum() {
		return viewNum;
	}

	/**
	 * set：评论数
	 */
	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	/**
	 * get：评论数
	 */
	public Integer getCommentNum() {
		return commentNum;
	}

	/**
	 * set：好评数 4星以上为好评
	 */
	public void setNiceCommentNum(Integer niceCommentNum) {
		this.niceCommentNum = niceCommentNum;
	}

	/**
	 * get：好评数 4星以上为好评
	 */
	public Integer getNiceCommentNum() {
		return niceCommentNum;
	}

	/**
	 * set：销售数量
	 */
	public void setSellNum(Integer sellNum) {
		this.sellNum = sellNum;
	}

	/**
	 * get：销售数量
	 */
	public Integer getSellNum() {
		return sellNum;
	}

	/**
	 * set：当前活动 1-满减 2-优惠券，都有则逗号分隔
	 */
	public void setCurrentActivity(String currentActivity) {
		this.currentActivity = currentActivity;
	}

	/**
	 * get：当前活动 1-满减 2-优惠券，都有则逗号分隔
	 */
	public String getCurrentActivity() {
		return currentActivity;
	}

	/**
	 * set：酒店-入店时间
	 */
	public void setHotelInTime(Date hotelInTime) {
		this.hotelInTime = hotelInTime;
	}

	/**
	 * get：酒店-入店时间
	 */
	public Date getHotelInTime() {
		return hotelInTime;
	}

	/**
	 * set：酒店-离店时间
	 */
	public void setHotelOutTime(Date hotelOutTime) {
		this.hotelOutTime = hotelOutTime;
	}

	/**
	 * get：酒店-离店时间
	 */
	public Date getHotelOutTime() {
		return hotelOutTime;
	}

	/**
	 * set：酒店-可否加床
	 */
	public void setHotelCanAddBed(Integer hotelCanAddBed) {
		this.hotelCanAddBed = hotelCanAddBed;
	}

	/**
	 * get：酒店-可否加床
	 */
	public Integer getHotelCanAddBed() {
		return hotelCanAddBed;
	}

	/**
	 * set：酒店-加床价格
	 */
	public void setHotelAddBedPrice(BigDecimal hotelAddBedPrice) {
		this.hotelAddBedPrice = hotelAddBedPrice;
	}

	/**
	 * get：酒店-加床价格
	 */
	public BigDecimal getHotelAddBedPrice() {
		return hotelAddBedPrice;
	}

	/**
	 * set：酒店-是否接待外宾
	 */
	public void setHotelForeigned(Integer hotelForeigned) {
		this.hotelForeigned = hotelForeigned;
	}

	/**
	 * get：酒店-是否接待外宾
	 */
	public Integer getHotelForeigned() {
		return hotelForeigned;
	}

	/**
	 * set：酒店-可否携带宠物
	 */
	public void setHotelPeted(Integer hotelPeted) {
		this.hotelPeted = hotelPeted;
	}

	/**
	 * get：酒店-可否携带宠物
	 */
	public Integer getHotelPeted() {
		return hotelPeted;
	}

	/**
	 * set：酒店-是否需要押金
	 */
	public void setHotelPledge(Integer hotelPledge) {
		this.hotelPledge = hotelPledge;
	}

	/**
	 * get：酒店-是否需要押金
	 */
	public Integer getHotelPledge() {
		return hotelPledge;
	}

	/**
	 * set：酒店星级 1-客栈 2-公寓 3-经济舒适型 4-高档豪华型 5-经济连锁 6-二星酒店 7-三星酒店 8-四星 9-五星
	 */
	public void setHotelLevel(Integer hotelLevel) {
		this.hotelLevel = hotelLevel;
	}

	/**
	 * get：酒店星级 1-客栈 2-公寓 3-经济舒适型 4-高档豪华型 5-经济连锁 6-二星酒店 7-三星酒店 8-四星 9-五星
	 */
	public Integer getHotelLevel() {
		return hotelLevel;
	}

	/**
	 * set：是否镗镗锣精选
	 */
	public void setWellChosened(Integer wellChosened) {
		this.wellChosened = wellChosened;
	}

	/**
	 * get：是否镗镗锣精选
	 */
	public Integer getWellChosened() {
		return wellChosened;
	}

	/**
	 * set：镗镗锣精选排序
	 */
	public void setWellChosenSort(Integer wellChosenSort) {
		this.wellChosenSort = wellChosenSort;
	}

	/**
	 * get：镗镗锣精选排序
	 */
	public Integer getWellChosenSort() {
		return wellChosenSort;
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

	/**
	 * set：二维码 信息如 {"merchantId":1,"merchantName":"张三"}
	 */
	public void setQrcode(Integer qrcode) {
		this.qrcode = qrcode;
	}

	/**
	 * get：二维码 信息如 {"merchantId":1,"merchantName":"张三"}
	 */
	public Integer getQrcode() {
		return qrcode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getRecommendName() {
		return recommendName;
	}

	public void setRecommendName(String recommendName) {
		this.recommendName = recommendName;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}
