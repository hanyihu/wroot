package com.vic.ck.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vic.ck.util.CommonUtils;
import com.vic.wroot.common.annotation.AttachmentFlag;
import com.vic.wroot.common.annotation.AttachmentFlag.AttachmenType;
import com.vic.wroot.common.attachment.service.AttachmentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 商户表
 *
 * @author VIC
 */
public class Merchant {
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

    /**
     * 商户名称
     */
    private String name;

    /**
     * 省
     */
    private Integer provinceId;

    private String provinceName;

    /**
     * 市
     */
    private Integer cityId;

    private String cityName;

    /**
     * 区
     */
    private Integer areaId;

    private String areaName;

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
    private BigDecimal longitude;

    /**
     * 纬度
     */
    private BigDecimal latitude;

    /**
     * 类型 1-美食 2-酒店预订 3-其他
     */
    private Integer type;

    /**
     * 商家分类的id
     */
    private Integer categoryId;

    private String categoryName;

    /**
     * 联系电话
     */
    private String mobile;

    /**
     * 外景照 附件表id
     */
    @AttachmentFlag(AttachmenType.SIGN)
    private Integer outPic;

    /**
     * 外景照 附件表ids
     */
    @AttachmentFlag(AttachmenType.SIGNS)
    private String inPic;

    /**
     * 营业执照 图片
     */
    @AttachmentFlag(AttachmenType.SIGNS)
    private Integer licensePic;

    /**
     * 营业执照 是否长期有效
     */
    private Boolean licensePersistent;

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
    @AttachmentFlag(AttachmenType.SIGN)
    private Integer permitPic;

    /**
     * 许可证是否长期有效
     */
    private Boolean permitPersistent;

    /**
     * 营业许可证有效期
     */
    private String permitDate;

    /**
     * 其他证明图片 附件ids
     */
    @AttachmentFlag(AttachmenType.SIGNS)
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
     * 状态 1-审核中 2-通过 3-拒绝 4 -尚未提交审核资料
     */
    private Integer status;

    /**
     * 商家设施，多个逗号分隔 1-可刷卡 2-停车位 3-WIFI 4-无烟区 5-吸烟区  6-包厢
     */
    private String facility;

    /**
     * 营业日
     */
    private String businessDay;

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
     * 查看人数
     */
    private Integer viewNum;

    /**
     * 评论数
     */
    private Integer commentNum;

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
    @JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
    @DateTimeFormat(pattern = "HH:mm")
    private Date hotelInTime;

    /**
     * 酒店-离店时间
     */
    @JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
    @DateTimeFormat(pattern = "HH:mm")
    private Date hotelOutTime;

    /**
     * 酒店-可否加床
     */
    private Boolean hotelCanAddBed;

    /**
     * 酒店-加床价格
     */
    private BigDecimal hotelAddBedPrice;

    /**
     * 酒店-是否接待外宾
     */
    private Boolean hotelForeigned;

    /**
     * 酒店-可否携带宠物
     */
    private Boolean hotelPeted;

    /**
     * 酒店-是否需要押金
     */
    private Boolean hotelPledge;

    /**
     * 酒店星级 1-客栈 2-公寓 3-经济舒适型 4-高档豪华型 5-经济连锁 6-二星酒店 7-三星酒店 8-四星 9-
     */
    private Integer hotelLevel;

    /**
     * 是否镗镗锣精选
     */
    @JsonIgnore
    private Boolean wellChosened;

    /**
     * 镗镗锣精选排序
     */
    private Integer wellChosenSort;

    /**
     * 商家描述
     */
    //详情里的附件单独处理
    //@AttachmentFlag(AttachmenType.CONTENT)
    private String describe;

    /**
     * 商家收款二维码
     * 存储的信息如：{"merchantId":1,"merchantName":"张三"}
     */
    private Integer qrcode;


    public Customer customer;


    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 一级分类
     */
    private Integer firstCategoryId;

    /**
     * 二级分类
     */
    private Integer twoCategoryId;

    /**
     * 三级分类
     */
    private Integer threeCategoryId;

    /**
     * 门店商标
     */
    @AttachmentFlag(AttachmenType.SIGN)
    private Integer shopTrademark;

    /**
     * 门店地址
     */
    private String shopAddress;

    /**
     * 营业执照法定代表人
     */
    private String licenseRepresentative;

    /**
     * 营业执照经营场所
     */
    private String licensePlace;

    /**
     * 营业执照注册时间
     */
    private String licenseRegistrationTime;

    /**
     * 营业执照登记机关
     */
    private String licenseRegistrationAuthority;

    /**
     * 营业执照核准日期
     */
    private String licenseApprovalDate;

    /**
     * 经营范围 (非必填)
     */
    private String licenseScope;

    /**
     * 注册资本(非必填)
     */
    private String licenseCapital;

    /**
     * 身份证正面
     */
    @AttachmentFlag(AttachmenType.SIGN)
    private Integer identityPositive;

    /**
     * 身份证反面
     */
    @AttachmentFlag(AttachmenType.SIGN)
    private Integer identityBack;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 民族
     */
    private String nation;

    /**
     * 出生日期
     */
    private String dateBirth;

    /**
     * 身份证地址
     */
    private String identityAddress;

    /**
     * 身份证签证机关
     */
    private String identityAuthority;

    /**
     * 身份证有效期
     */
    private String identityValidity;

    /**
     * 许可证经营者名称
     */
    private String permitName;

    /**
     * 许可证法定代表人
     */
    private String permitRepresentative;

    /**
     * 许可证编号
     */
    private String permitNumber;

    /**
     * 许可证门店地址
     */
    private String permitAddress;

    /**
     * 许可证有效期
     */
    private String permitValidityPeriod;

    /**
     * 商家状态 1:可用 2:不可用
     */
    private Integer state;


    /******************************************************************/


    public String getQrcodeUrl() {
        return CommonUtils.getImageUrl(qrcode);
    }

    public String getOutPicUrl() {
        return CommonUtils.getImageUrl(outPic);
    }

    public List<String> getInPicUrls() {
        return CommonUtils.getImageUrls(inPic);
    }

    public String getLicensePicUrl() {//营业执照
        return CommonUtils.getImageUrl(licensePic);
    }

    public String getPermitPicUrl() {//营业许可证
        return CommonUtils.getImageUrl(permitPic);
    }

    public List<String> getOtherProvePicUrls() {
        return CommonUtils.getImageUrls(otherProvePic);
    }

    public String getShopTrademarkUrl() {//商家商标
        return CommonUtils.getImageUrl(shopTrademark);
    }

    public String getIdentityPositiveUrl() {//身份证正面
        return CommonUtils.getImageUrl(identityPositive);
    }

    public String getIdentityBackUrl() {//身份证背面
        return CommonUtils.getImageUrl(identityBack);
    }

    /****************************************************************/

    public Merchant() {
    }

    public static void main(String[] args) {
        Merchant a = new Merchant();
        a.setOutPic(1);
        a.setInPic("3,4,5");
        a.setLicensePic(6);
        a.setPermitPic(8);
        a.setOtherProvePic("9,10");
        ;
        for (int i : a.findAllImages()) {
            System.out.print(i + "\t");
        }

    }

    /**
     * 获得全部的图片ids
     */
    public Integer[] findAllImages() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(getOutPic());
        list.add(getLicensePic());

        list.add(getPermitPic());
        if (getInPic() != null) {
            list.addAll(com.vic.wroot.common.util.CommonUtils.toIntList(getInPic().split(",")));

        }
        if (getOtherProvePic() != null) {
            list.addAll(com.vic.wroot.common.util.CommonUtils.toIntList(getOtherProvePic().split(",")));

        }

        //商家描述里也是有图的
        if (StringUtils.isNotEmpty(getDescribe())) {
            Integer[] ids = AttachmentService.getAttachmentIds(getDescribe());
            if (ids != null && ids.length > 0) {
                list.addAll(new ArrayList<Integer>(Arrays.asList(ids)));
            }
        }
        return list.toArray(new Integer[0]);
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
     * 所属用户
     *
     * @return customer_id 所属用户
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * 所属用户
     *
     * @param customerId 所属用户
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * 推荐人id
     *
     * @return recommend_id 推荐人id
     */
    public Integer getRecommendId() {
        return recommendId;
    }

    /**
     * 推荐人id
     *
     * @param recommendId 推荐人id
     */
    public void setRecommendId(Integer recommendId) {
        this.recommendId = recommendId;
    }

    /**
     * 商户名称
     *
     * @return name 商户名称
     */
    public String getName() {
        return name;
    }

    /**
     * 商户名称
     *
     * @param name 商户名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 省
     *
     * @return province_id 省
     */
    public Integer getProvinceId() {
        return provinceId;
    }

    /**
     * 省
     *
     * @param provinceId 省
     */
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * 市
     *
     * @return city_id 市
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     * 市
     *
     * @param cityId 市
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     * 区
     *
     * @return area_id 区
     */
    public Integer getAreaId() {
        return areaId;
    }

    /**
     * 区
     *
     * @param areaId 区
     */
    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    /**
     * 详细地址
     *
     * @return address 详细地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 详细地址
     *
     * @param address 详细地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 附件地标
     *
     * @return landmarks 附件地标
     */
    public String getLandmarks() {
        return landmarks;
    }

    /**
     * 附件地标
     *
     * @param landmarks 附件地标
     */
    public void setLandmarks(String landmarks) {
        this.landmarks = landmarks == null ? null : landmarks.trim();
    }

    /**
     * 经度
     *
     * @return longitude 经度
     */
    public BigDecimal getLongitude() {
        return longitude;
    }

    /**
     * 经度
     *
     * @param longitude 经度
     */
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    /**
     * 纬度
     *
     * @return latitude 纬度
     */
    public BigDecimal getLatitude() {
        return latitude;
    }

    /**
     * 纬度
     *
     * @param latitude 纬度
     */
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    /**
     * 类型 1-美食 2-酒店预订 3-其他
     *
     * @return type 类型 1-美食 2-酒店预订 3-其他
     */
    public Integer getType() {
        return type;
    }

    /**
     * 类型 1-美食 2-酒店预订 3-其他
     *
     * @param type 类型 1-美食 2-酒店预订 3-其他
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 商家分类的id
     *
     * @return category_id 商家分类的id
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * 商家分类的id
     *
     * @param categoryId 商家分类的id
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 联系电话
     *
     * @return mobile 联系电话
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 联系电话
     *
     * @param mobile 联系电话
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 外景照 附件表id
     *
     * @return out_pic 外景照 附件表id
     */
    public Integer getOutPic() {
        return outPic;
    }

    /**
     * 外景照 附件表id
     *
     * @param outPic 外景照 附件表id
     */
    public void setOutPic(Integer outPic) {
        this.outPic = outPic;
    }

    /**
     * 外景照 附件表ids
     *
     * @return in_pic 外景照 附件表ids
     */
    public String getInPic() {
        return inPic;
    }

    /**
     * 外景照 附件表ids
     *
     * @param inPic 外景照 附件表ids
     */
    public void setInPic(String inPic) {
        this.inPic = inPic == null ? null : inPic.trim();
    }

    /**
     * 营业执照 图片
     *
     * @return license_pic 营业执照 图片
     */
    public Integer getLicensePic() {
        return licensePic;
    }

    /**
     * 营业执照 图片
     *
     * @param licensePic 营业执照 图片
     */
    public void setLicensePic(Integer licensePic) {
        this.licensePic = licensePic;
    }

    /**
     * 营业执照 是否长期有效
     *
     * @return license_persistent 营业执照 是否长期有效
     */
    public Boolean getLicensePersistent() {
        return licensePersistent;
    }

    /**
     * 营业执照 是否长期有效
     *
     * @param licensePersistent 营业执照 是否长期有效
     */
    public void setLicensePersistent(Boolean licensePersistent) {
        this.licensePersistent = licensePersistent;
    }

    /**
     * 营业执照有效期
     *
     * @return license_date 营业执照有效期
     */
    public String getLicenseDate() {
        return licenseDate;
    }

    /**
     * 营业执照有效期
     *
     * @param licenseDate 营业执照有效期
     */
    public void setLicenseDate(String licenseDate) {
        this.licenseDate = licenseDate == null ? null : licenseDate.trim();
    }

    /**
     * 营业执照名称
     *
     * @return license_name 营业执照名称
     */
    public String getLicenseName() {
        return licenseName;
    }

    /**
     * 营业执照名称
     *
     * @param licenseName 营业执照名称
     */
    public void setLicenseName(String licenseName) {
        this.licenseName = licenseName == null ? null : licenseName.trim();
    }

    /**
     * 营业执照注册号
     *
     * @return license_regcode 营业执照注册号
     */
    public String getLicenseRegcode() {
        return licenseRegcode;
    }

    /**
     * 营业执照注册号
     *
     * @param licenseRegcode 营业执照注册号
     */
    public void setLicenseRegcode(String licenseRegcode) {
        this.licenseRegcode = licenseRegcode == null ? null : licenseRegcode.trim();
    }

    /**
     * 营业许可证图片
     *
     * @return permit_pic 营业许可证图片
     */
    public Integer getPermitPic() {
        return permitPic;
    }

    /**
     * 营业许可证图片
     *
     * @param permitPic 营业许可证图片
     */
    public void setPermitPic(Integer permitPic) {
        this.permitPic = permitPic;
    }

    /**
     * 许可证是否长期有效
     *
     * @return permit_persistent 许可证是否长期有效
     */
    public Boolean getPermitPersistent() {
        return permitPersistent;
    }

    /**
     * 许可证是否长期有效
     *
     * @param permitPersistent 许可证是否长期有效
     */
    public void setPermitPersistent(Boolean permitPersistent) {
        this.permitPersistent = permitPersistent;
    }

    /**
     * 营业许可证有效期
     *
     * @return permit_date 营业许可证有效期
     */
    public String getPermitDate() {
        return permitDate;
    }

    /**
     * 营业许可证有效期
     *
     * @param permitDate 营业许可证有效期
     */
    public void setPermitDate(String permitDate) {
        this.permitDate = permitDate == null ? null : permitDate.trim();
    }

    /**
     * 其他证明图片 附件ids
     *
     * @return other_prove_pic 其他证明图片 附件ids
     */
    public String getOtherProvePic() {
        return otherProvePic;
    }

    /**
     * 其他证明图片 附件ids
     *
     * @param otherProvePic 其他证明图片 附件ids
     */
    public void setOtherProvePic(String otherProvePic) {
        this.otherProvePic = otherProvePic == null ? null : otherProvePic.trim();
    }

    /**
     * 账户名
     *
     * @return account_name 账户名
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * 账户名
     *
     * @param accountName 账户名
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    /**
     * 银行账号
     *
     * @return bank_account 银行账号
     */
    public String getBankAccount() {
        return bankAccount;
    }

    /**
     * 银行账号
     *
     * @param bankAccount 银行账号
     */
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount == null ? null : bankAccount.trim();
    }

    /**
     * 开户银行
     *
     * @return bank_name 开户银行
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * 开户银行
     *
     * @param bankName 开户银行
     */
    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    /**
     * 状态 1-审核中 2-通过 3-拒绝
     *
     * @return status 状态 1-审核中 2-通过 3-拒绝
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态 1-审核中 2-通过 3-拒绝
     *
     * @param status 状态 1-审核中 2-通过 3-拒绝 4-尚未提交审核资料
     */
    public Merchant setStatus(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * 商家设施，多个逗号分隔 1-可刷卡 2-停车位 3-WIFI 4-无烟区 5-吸烟区  6-包厢
     *
     * @return facility 商家设施，多个逗号分隔 1-可刷卡 2-停车位 3-WIFI 4-无烟区 5-吸烟区  6-包厢
     */
    public String getFacility() {
        return facility;
    }

    /**
     * 商家设施，多个逗号分隔 1-可刷卡 2-停车位 3-WIFI 4-无烟区 5-吸烟区  6-包厢
     *
     * @param facility 商家设施，多个逗号分隔 1-可刷卡 2-停车位 3-WIFI 4-无烟区 5-吸烟区  6-包厢
     */
    public void setFacility(String facility) {
        this.facility = facility == null ? null : facility.trim();
    }

    /**
     * 营业时间
     *
     * @return business hours 营业时间
     */
    public String getBusinessHours() {
        return businessHours;
    }

    /**
     * 营业时间
     *
     * @param businessHours 营业时间
     */
    public void setBusinessHours(String businessHours) {
        this.businessHours = businessHours == null ? null : businessHours.trim();
    }

    /**
     * 最低消费金额
     *
     * @return price 最低消费金额
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 最低消费金额
     *
     * @param price 最低消费金额
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 服务评分
     *
     * @return service_star 服务评分
     */
    public Double getServiceStar() {
        return serviceStar;
    }

    /**
     * 服务评分
     *
     * @param serviceStar 服务评分
     */
    public void setServiceStar(Double serviceStar) {
        this.serviceStar = serviceStar;
    }

    /**
     * 环境评星
     *
     * @return environment_star 环境评星
     */
    public Double getEnvironmentStar() {
        return environmentStar;
    }

    /**
     * 环境评星
     *
     * @param environmentStar 环境评星
     */
    public void setEnvironmentStar(Double environmentStar) {
        this.environmentStar = environmentStar;
    }

    /**
     * 口味评星
     *
     * @return flavor_star 口味评星
     */
    public Double getFlavorStar() {
        return flavorStar;
    }

    /**
     * 口味评星
     *
     * @param flavorStar 口味评星
     */
    public void setFlavorStar(Double flavorStar) {
        this.flavorStar = flavorStar;
    }

    /**
     * 总评价 对多10星 1.5 ，2 ，2.5 ，3....
     *
     * @return star 总评价 对多10星 1.5 ，2 ，2.5 ，3....
     */
    public Double getStar() {
        return star;
    }

    /**
     * 总评价 对多10星 1.5 ，2 ，2.5 ，3....
     *
     * @param star 总评价 对多10星 1.5 ，2 ，2.5 ，3....
     */
    public void setStar(Double star) {
        this.star = star;
    }

    /**
     * 查看人数
     *
     * @return view_num 查看人数
     */
    public Integer getViewNum() {
        return viewNum;
    }

    /**
     * 查看人数
     *
     * @param viewNum 查看人数
     */
    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
    }

    /**
     * 评论数
     *
     * @return comment_num 评论数
     */
    public Integer getCommentNum() {
        return commentNum;
    }

    /**
     * 评论数
     *
     * @param commentNum 评论数
     */
    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    /**
     * 销售数量
     *
     * @return sell_num 销售数量
     */
    public Integer getSellNum() {
        return sellNum;
    }

    /**
     * 销售数量
     *
     * @param sellNum 销售数量
     */
    public void setSellNum(Integer sellNum) {
        this.sellNum = sellNum;
    }

    /**
     * 当前活动 1-满减 2-优惠券，都有则逗号分隔
     *
     * @return current_activity 当前活动 1-满减 2-优惠券，都有则逗号分隔
     */
    public String getCurrentActivity() {
        return currentActivity;
    }

    /**
     * 当前活动 1-满减 2-优惠券，都有则逗号分隔
     *
     * @param currentActivity 当前活动 1-满减 2-优惠券，都有则逗号分隔
     */
    public void setCurrentActivity(String currentActivity) {
        this.currentActivity = currentActivity == null ? null : currentActivity.trim();
    }

    /**
     * 酒店-入店时间
     *
     * @return hotel_in_time 酒店-入店时间
     */
    public Date getHotelInTime() {
        return hotelInTime;
    }

    /**
     * 酒店-入店时间
     *
     * @param hotelInTime 酒店-入店时间
     */
    public void setHotelInTime(Date hotelInTime) {
        this.hotelInTime = hotelInTime;
    }

    /**
     * 酒店-离店时间
     *
     * @return hotel_out_time 酒店-离店时间
     */
    public Date getHotelOutTime() {
        return hotelOutTime;
    }

    /**
     * 酒店-离店时间
     *
     * @param hotelOutTime 酒店-离店时间
     */
    public void setHotelOutTime(Date hotelOutTime) {
        this.hotelOutTime = hotelOutTime;
    }

    /**
     * 酒店-可否加床
     *
     * @return hotel_can_add_bed 酒店-可否加床
     */
    public Boolean getHotelCanAddBed() {
        return hotelCanAddBed;
    }

    /**
     * 酒店-可否加床
     *
     * @param hotelCanAddBed 酒店-可否加床
     */
    public void setHotelCanAddBed(Boolean hotelCanAddBed) {
        this.hotelCanAddBed = hotelCanAddBed;
    }

    /**
     * 酒店-加床价格
     *
     * @return hotel_add_bed_price 酒店-加床价格
     */
    public BigDecimal getHotelAddBedPrice() {
        return hotelAddBedPrice;
    }

    /**
     * 酒店-加床价格
     *
     * @param hotelAddBedPrice 酒店-加床价格
     */
    public void setHotelAddBedPrice(BigDecimal hotelAddBedPrice) {
        this.hotelAddBedPrice = hotelAddBedPrice;
    }

    /**
     * 酒店-是否接待外宾
     *
     * @return hotel_foreigned 酒店-是否接待外宾
     */
    public Boolean getHotelForeigned() {
        return hotelForeigned;
    }

    /**
     * 酒店-是否接待外宾
     *
     * @param hotelForeigned 酒店-是否接待外宾
     */
    public void setHotelForeigned(Boolean hotelForeigned) {
        this.hotelForeigned = hotelForeigned;
    }

    /**
     * 酒店-可否携带宠物
     *
     * @return hotel_peted 酒店-可否携带宠物
     */
    public Boolean getHotelPeted() {
        return hotelPeted;
    }

    /**
     * 酒店-可否携带宠物
     *
     * @param hotelPeted 酒店-可否携带宠物
     */
    public void setHotelPeted(Boolean hotelPeted) {
        this.hotelPeted = hotelPeted;
    }

    /**
     * 酒店-是否需要押金
     *
     * @return hotel_pledge 酒店-是否需要押金
     */
    public Boolean getHotelPledge() {
        return hotelPledge;
    }

    /**
     * 酒店-是否需要押金
     *
     * @param hotelPledge 酒店-是否需要押金
     */
    public void setHotelPledge(Boolean hotelPledge) {
        this.hotelPledge = hotelPledge;
    }

    /**
     * 酒店星级 1-客栈 2-公寓 3-经济舒适型 4-高档豪华型 5-经济连锁 6-二星酒店 7-三星酒店 8-四星 9-
     *
     * @return hotel_level 酒店星级 1-客栈 2-公寓 3-经济舒适型 4-高档豪华型 5-经济连锁 6-二星酒店 7-三星酒店 8-四星 9-
     */
    public Integer getHotelLevel() {
        return hotelLevel;
    }

    /**
     * 酒店星级 1-客栈 2-公寓 3-经济舒适型 4-高档豪华型 5-经济连锁 6-二星酒店 7-三星酒店 8-四星 9-
     *
     * @param hotelLevel 酒店星级 1-客栈 2-公寓 3-经济舒适型 4-高档豪华型 5-经济连锁 6-二星酒店 7-三星酒店 8-四星 9-
     */
    public void setHotelLevel(Integer hotelLevel) {
        this.hotelLevel = hotelLevel;
    }

    /**
     * 是否镗镗锣精选
     *
     * @return well_chosened 是否镗镗锣精选
     */
    public Boolean getWellChosened() {
        return wellChosened;
    }

    /**
     * 是否镗镗锣精选
     *
     * @param wellChosened 是否镗镗锣精选
     */
    public void setWellChosened(Boolean wellChosened) {
        this.wellChosened = wellChosened;
    }

    /**
     * 镗镗锣精选排序
     *
     * @return well_chosen_sort 镗镗锣精选排序
     */
    public Integer getWellChosenSort() {
        return wellChosenSort;
    }

    /**
     * 镗镗锣精选排序
     *
     * @param wellChosenSort 镗镗锣精选排序
     */
    public void setWellChosenSort(Integer wellChosenSort) {
        this.wellChosenSort = wellChosenSort;
    }

    /**
     * 商家描述
     *
     * @return describe 商家描述
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * 商家描述
     *
     * @param describe 商家描述
     */
    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public Customer getCustomer() {
        return customer;
    }

    public Merchant setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public Integer getQrcode() {
        return qrcode;
    }

    public void setQrcode(Integer qrcode) {
        this.qrcode = qrcode;
    }

    public String getBusinessDay() {
        return businessDay;
    }

    public void setBusinessDay(String businessDay) {
        this.businessDay = businessDay;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public Integer getFirstCategoryId() {
        return firstCategoryId;
    }

    public void setFirstCategoryId(Integer firstCategoryId) {
        this.firstCategoryId = firstCategoryId;
    }

    public Integer getTwoCategoryId() {
        return twoCategoryId;
    }

    public void setTwoCategoryId(Integer twoCategoryId) {
        this.twoCategoryId = twoCategoryId;
    }

    public Integer getThreeCategoryId() {
        return threeCategoryId;
    }

    public void setThreeCategoryId(Integer threeCategoryId) {
        this.threeCategoryId = threeCategoryId;
    }

    public Integer getShopTrademark() {
        return shopTrademark;
    }

    public void setShopTrademark(Integer shopTrademark) {
        this.shopTrademark = shopTrademark;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getLicenseRepresentative() {
        return licenseRepresentative;
    }

    public void setLicenseRepresentative(String licenseRepresentative) {
        this.licenseRepresentative = licenseRepresentative;
    }

    public String getLicensePlace() {
        return licensePlace;
    }

    public void setLicensePlace(String licensePlace) {
        this.licensePlace = licensePlace;
    }

    public String getLicenseRegistrationTime() {
        return licenseRegistrationTime;
    }

    public void setLicenseRegistrationTime(String licenseRegistrationTime) {
        this.licenseRegistrationTime = licenseRegistrationTime;
    }

    public String getLicenseRegistrationAuthority() {
        return licenseRegistrationAuthority;
    }

    public void setLicenseRegistrationAuthority(String licenseRegistrationAuthority) {
        this.licenseRegistrationAuthority = licenseRegistrationAuthority;
    }

    public String getLicenseApprovalDate() {
        return licenseApprovalDate;
    }

    public void setLicenseApprovalDate(String licenseApprovalDate) {
        this.licenseApprovalDate = licenseApprovalDate;
    }

    public String getLicenseScope() {
        return licenseScope;
    }

    public void setLicenseScope(String licenseScope) {
        this.licenseScope = licenseScope;
    }

    public String getLicenseCapital() {
        return licenseCapital;
    }

    public void setLicenseCapital(String licenseCapital) {
        this.licenseCapital = licenseCapital;
    }

    public Integer getIdentityPositive() {
        return identityPositive;
    }

    public void setIdentityPositive(Integer identityPositive) {
        this.identityPositive = identityPositive;
    }

    public Integer getIdentityBack() {
        return identityBack;
    }

    public void setIdentityBack(Integer identityBack) {
        this.identityBack = identityBack;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getIdentityAddress() {
        return identityAddress;
    }

    public void setIdentityAddress(String identityAddress) {
        this.identityAddress = identityAddress;
    }

    public String getIdentityAuthority() {
        return identityAuthority;
    }

    public void setIdentityAuthority(String identityAuthority) {
        this.identityAuthority = identityAuthority;
    }

    public String getIdentityValidity() {
        return identityValidity;
    }

    public void setIdentityValidity(String identityValidity) {
        this.identityValidity = identityValidity;
    }

    public String getPermitName() {
        return permitName;
    }

    public void setPermitName(String permitName) {
        this.permitName = permitName;
    }

    public String getPermitRepresentative() {
        return permitRepresentative;
    }

    public void setPermitRepresentative(String permitRepresentative) {
        this.permitRepresentative = permitRepresentative;
    }

    public String getPermitNumber() {
        return permitNumber;
    }

    public void setPermitNumber(String permitNumber) {
        this.permitNumber = permitNumber;
    }

    public String getPermitAddress() {
        return permitAddress;
    }

    public void setPermitAddress(String permitAddress) {
        this.permitAddress = permitAddress;
    }

    public String getPermitValidityPeriod() {
        return permitValidityPeriod;
    }

    public void setPermitValidityPeriod(String permitValidityPeriod) {
        this.permitValidityPeriod = permitValidityPeriod;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}