package com.vic.ck.entity;

import com.vic.ck.util.CommonUtils;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 我的银行卡
 * @author hanyihu
 * @date 2019/4/22 13:37
 *
 */
public class CustomerBankCard implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    private Integer id;

    /**
     * 用户名id
     */
    @ApiModelProperty(value = "用户名id")
    private Integer customerId;

    /**
     * 开户名
     */
    @ApiModelProperty(value = "开户名")
    private String accountName;

    /**
     * 银行id
     */
    @ApiModelProperty(value = "银行id")
    private Integer bankId;

    /**
     * 银行名称
     */
    @ApiModelProperty(value = "银行名称")
    private String bankName;

    /**
     * 银行卡号
     */
    @ApiModelProperty(value = "银行卡号")
    private String bankCardno;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    private String idNumber;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;
    
    /**
     * 银行图标
     */
    @ApiModelProperty(value = "银行图标")
    private Integer bankIcon;

    /*卡号尾号*/
    @ApiModelProperty(value = "卡号尾号")
    private String lastNumber;

    /*银行卡类型（1储蓄卡  2信用卡）*/
    @ApiModelProperty(value = "银行卡类型（1储蓄卡  2信用卡  其它）")
    private Integer cardType;

    /*卡内余额*/
    @ApiModelProperty(value = "卡内余额")
    private BigDecimal balance;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCardType() {
        return (cardType == 1 ? "储蓄卡" : (cardType == 2 ? "信用卡" : "其它"));
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public String getLastNumber() {
        return lastNumber;
    }

    public void setLastNumber(String lastNumber) {
        this.lastNumber = lastNumber;
    }

    public String getBankIconUrl(){
    	return CommonUtils.getImageUrl(bankIcon);
    }

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
     * 用户名
     * @return customer_id 用户名
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * 用户名
     * @param customerId 用户名
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * 开户名
     * @return account_name 开户名
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * 开户名
     * @param accountName 开户名
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    /**
     * 
     * @return bank_id 
     */
    public Integer getBankId() {
        return bankId;
    }

    /**
     * 
     * @param bankId 
     */
    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    /**
     * 银行名称
     * @return bank_name 银行名称
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * 银行名称
     * @param bankName 银行名称
     */
    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    /**
     * 银行卡号
     * @return bank_cardno 银行卡号
     */
    public String getBankCardno() {
        return bankCardno;
    }

    /**
     * 银行卡号
     * @param bankCardno 银行卡号
     */
    public void setBankCardno(String bankCardno) {
        this.bankCardno = bankCardno == null ? null : bankCardno.trim();
    }

    /**
     * 身份证号
     * @return id_number 身份证号
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * 身份证号
     * @param idNumber 身份证号
     */
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber == null ? null : idNumber.trim();
    }

    /**
     * 手机号
     * @return mobile 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 手机号
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

	public Integer getBankIcon() {
		return bankIcon;
	}

	public void setBankIcon(Integer bankIcon) {
		this.bankIcon = bankIcon;
	}
}