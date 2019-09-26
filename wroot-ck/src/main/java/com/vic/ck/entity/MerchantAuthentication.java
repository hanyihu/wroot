package com.vic.ck.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 商家认证
 * @author VIC
 *
 */
public class MerchantAuthentication {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer merchantId;

    /**
     * 身份证
     */
    private String idNumber;

    /**
     * 姓名
     */
    private String readName;

    /**
     * 1-男 2-女 0-保密
     */
    private int gender = 0;

    /**
     * 民族
     */
    private String nation;

    /**
     * 出生年月
     */
    @DateTimeFormat( pattern = "yyyy-MM-dd")
    private Date birthday;

    /**
     * 注册地地址
     */
    private String houseAddress;

    /**
     * 失效日期
     */
    private Date expiryDate;

    /**
     * 0-待审核 1-审核人通过 2-不通过
     */
    private int statsu = 0;

    /**
     * 审核说明
     */
    private String auditRemark;

    /**
     * 
     */
    private Date createTime;

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
     * 
     * @return merchant_id 
     */
    public Integer getMerchantId() {
        return merchantId;
    }

    /**
     * 
     * @param merchantId 
     */
    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * 身份证
     * @return id_number 身份证
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * 身份证
     * @param idNumber 身份证
     */
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber == null ? null : idNumber.trim();
    }

    /**
     * 姓名
     * @return read_name 姓名
     */
    public String getReadName() {
        return readName;
    }

    /**
     * 姓名
     * @param readName 姓名
     */
    public void setReadName(String readName) {
        this.readName = readName == null ? null : readName.trim();
    }

    /**
     * 1-男 2-女 0-保密
     * @return gender 1-男 2-女 0-保密
     */
    public int getGender() {
        return gender;
    }

    /**
     * 1-男 2-女 0-保密
     * @param gender 1-男 2-女 0-保密
     */
    public void setGender(int gender) {
        this.gender = gender;
    }

    /**
     * 民族
     * @return nation 民族
     */
    public String getNation() {
        return nation;
    }

    /**
     * 民族
     * @param nation 民族
     */
    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }

    /**
     * 出生年月
     * @return birthday 出生年月
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 出生年月
     * @param birthday 出生年月
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 注册地地址
     * @return house_address 注册地地址
     */
    public String getHouseAddress() {
        return houseAddress;
    }

    /**
     * 注册地地址
     * @param houseAddress 注册地地址
     */
    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress == null ? null : houseAddress.trim();
    }

    /**
     * 失效日期
     * @return expiry _date 失效日期
     */
    public Date getExpiryDate() {
        return expiryDate;
    }

    /**
     * 失效日期
     * @param expiryDate 失效日期
     */
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * 0-待审核 1-审核人通过 2-不通过
     * @return statsu 0-待审核 1-审核人通过 2-不通过
     */
    public int getStatsu() {
        return statsu;
    }

    /**
     * 0-待审核 1-审核人通过 2-不通过
     * @param statsu 0-待审核 1-审核人通过 2-不通过
     */
    public void setStatsu(int statsu) {
        this.statsu = statsu;
    }

    /**
     * 审核说明
     * @return audit_remark 审核说明
     */
    public String getAuditRemark() {
        return auditRemark;
    }

    /**
     * 审核说明
     * @param auditRemark 审核说明
     */
    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark == null ? null : auditRemark.trim();
    }

    /**
     * 
     * @return create_time 
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 
     * @param createTime 
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}