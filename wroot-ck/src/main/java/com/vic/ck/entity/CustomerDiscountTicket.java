package com.vic.ck.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户优惠券表
 * 
 * @author VIC
 */
public class CustomerDiscountTicket implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 用属于用户
	 */
	private Integer customerId;
	/**
	 * 所属商家
	 */
	private Integer merchantId;
	/**
	 * 所属商家活动
	 */
	private Integer activityId;
	/**
	 * 商家名称
	 */
	private String merchantName;
	/**
	 * 折扣
	 */
    private String discount;
    /**
     * 满减券金额
     */
    private String minAmount;

    /**
     * 随机数上线
     */
    private String random;

	/**
	 * 领用时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime;
	/**
	 * 生效时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	/**
	 * 失效时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endTime;
	/**
	 * 使用时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date usedTime;
	/**
	 * 0-未使用 1-已使用 2-已过期 3-已删除
	 */
	private Integer status;

    //优惠券类型
    private Integer type;

    public CustomerDiscountTicket(Integer customerId, Integer activityId, Integer type,
                                  Date startDate, Date endTime, String minAmount, String discount, String random) {
        super();
        this.customerId = customerId;
        this.activityId = activityId;
        this.type = type;
        this.discount = discount;
        this.startDate = startDate;
        this.endTime = endTime;
        this.status = 0;
        this.random = random;

    }

    public CustomerDiscountTicket() {
        super();
    }

    //优惠券类型 1 2 3
    public Integer getType() {
        return type;
    }

//	public CustomerDiscountTicket(Integer customerId, Integer merchantId, Integer activityId,
//			String merchantName, String discount, String minAmount, Date startDate, Date endTime) {
//		super();
//		this.customerId = customerId;
//		this.merchantId = merchantId;
//		this.activityId = activityId;
//		this.merchantName = merchantName;
//		this.discount = discount;
//		this.minAmount = minAmount;
//		this.startDate = startDate;
//		this.endTime = endTime;
//		this.status = 0;
//		
//	}  

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
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
	 * set：用属于用户
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	/**
	 * get：用属于用户
	 */
	public Integer getCustomerId() {
		return customerId;
	}

	/**
	 * set：所属商家
	 */
	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	/**
	 * get：所属商家
	 */
	public Integer getMerchantId() {
		return merchantId;
	}

	/**
	 * set：所属商家活动
	 */
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	/**
	 * get：所属商家活动
	 */
	public Integer getActivityId() {
		return activityId;
	}

	/**
	 * set：商家名称
	 */
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	/**
	 * get：商家名称
	 */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * get：折扣
     */
    public String getDiscount() {
        return discount;
    }

    /**
	 * set：折扣
     */
    public void setDiscount(String discount) {
        this.discount = discount;
    }

    /**
     * get：满减
     */
    public String getMinAmount() {
        return minAmount;
    }

    /**
     * set：满减
     */
    public void setMinAmount(String minAmount) {
        this.minAmount = minAmount;
    }

	/**
	 * set：领用时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * get：领用时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * set：生效时间
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * get：生效时间
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * set：生效时间
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * get：生效时间
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * set：使用时间
	 */
	public void setUsedTime(Date usedTime) {
		this.usedTime = usedTime;
	}

	/**
	 * get：使用时间
	 */
	public Date getUsedTime() {
		return usedTime;
	}

	/**
	 * set：0-未使用 1-已使用 2-已过期 3-已删除
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * get：0-未使用 1-已使用 2-已过期 3-已删除
	 */
	public Integer getStatus() {
		return status;
	}
}
