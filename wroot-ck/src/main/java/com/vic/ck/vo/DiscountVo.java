package com.vic.ck.vo;

import java.math.BigDecimal;

/**
 * 生成订单前的一些优惠的计算
 * @author VIC
 *
 */
public class DiscountVo {

	/**
	 * 总金额
	 */
	private BigDecimal totalFee;
	
	/**
	 * 应支付金额
	 */
	private BigDecimal amount;
	
	/**
	 * 优惠的金额
	 */
	private BigDecimal discountFee;
	
	/**
	 * 优惠的描述
	 */
	private String discountsDesc;

    /**
     * 数量
     */
    private int num;
	

	public DiscountVo() {
		super();
	}

    public DiscountVo(BigDecimal totalFee, BigDecimal amount, BigDecimal discountFee, String discountsDesc) {
        super();
        this.totalFee = totalFee;
        this.amount = amount;
        this.discountFee = discountFee;
        this.discountsDesc = discountsDesc;
    }


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

	public BigDecimal getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getDiscountFee() {
		return discountFee;
	}

	public void setDiscountFee(BigDecimal discountFee) {
		this.discountFee = discountFee;
	}

	public String getDiscountsDesc() {
		return discountsDesc;
	}

	public void setDiscountsDesc(String discountsDesc) {
		this.discountsDesc = discountsDesc;
	}

	
	
}
