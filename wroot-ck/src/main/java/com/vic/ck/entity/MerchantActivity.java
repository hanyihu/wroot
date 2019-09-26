package com.vic.ck.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vic.ck.api.handler.conver.Date2yyyyMMdd;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 商家活动
 * @author VIC
 *
 */
public class MerchantActivity {

	private Integer id;
	
	private Integer merchantId;

	// 优惠券类型 1 2 3
	private Integer type;
	//满减活动类型 1普通优惠 2多级优惠
	private Integer types;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using=Date2yyyyMMdd.class) 
	private Date startDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using=Date2yyyyMMdd.class) 
	private Date endDate;
	//满减：满多少减多少:格式为100:5,200:15,
	private String rules;
	
	//立减 ：减少的金额
	private BigDecimal minus;
	
	//折扣券是否限制数量
	private Boolean limited;
	
	//折扣最大的发放数量
	private Integer limitNum;
	
	//折扣券最低消费金额
	private BigDecimal minAmount;
	
	//折扣券折扣
	private BigDecimal discount;

	//0-未使用 1-已使用 2-已过期 3-已删除
	private Integer status;
	//0-初始 1-开启 2-关闭
	private Integer statuss;
	
	@JsonIgnore
	private String merchantName;


	public List<RuleDesc> getRuleDescs() {
		
		if(type == 1 && StringUtils.isNotBlank(rules)) {
			List<RuleDesc> list = new ArrayList<RuleDesc>();
			String[] rules = this.rules.split(",");
			for(String rule : rules) {
				String[] rs = rule.split(":");
				if(rs.length == 2) {
					try {
						list.add(new RuleDesc(Integer.parseInt(rs[0]), Integer.parseInt(rs[1])));
					}catch (NumberFormatException e ) {
					}
				}
				
			}
			Collections.sort(list);
			return list;
		}
		return null;
	}


	public Integer getTypes() {
		return types;
	}


	public void setTypes(Integer types) {
		this.types = types;
	}


	public Integer getStatuss() {
		return statuss;
	}


	public void setStatuss(Integer statuss) {
		this.statuss = statuss;
	}


	public void setType(Integer type) {
		this.type = type;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

	public Boolean getLimited() {
		return limited;
	}

	public void setLimited(Boolean limited) {
		this.limited = limited;
	}

	public Integer getLimitNum() {
		return limitNum;
	}

	public void setLimitNum(Integer limitNum) {
		this.limitNum = limitNum;
	}

	public BigDecimal getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(BigDecimal minAmount) {
		this.minAmount = minAmount;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	
	/**获取当前金额满足哪一档次的满减*/
	public RuleDesc findRuleDesc(BigDecimal money) {
		List<RuleDesc> rs = getRuleDescs();
		RuleDesc result = null;
		for(RuleDesc r : rs) {
			if(new BigDecimal(r.getFill()).compareTo(money) <=0 ) {
				result = r;
			}
		}
		return result;
	}

	/**
	 * 获取当前商品数量满足哪一档次的满减
	 */
	public RuleDesc findRuleDesc(int num) {
		List<RuleDesc> rs = getRuleDescs();
		RuleDesc result = null;
		for (RuleDesc r : rs) {
			if (r.getFill() <= num) {
				result = r;
			}
		}
		return result;
	}
	
	public BigDecimal getMinus() {
		return minus;
	}

	public void setMinus(BigDecimal minus) {
		this.minus = minus;
	}


	public class RuleDesc implements Comparable<RuleDesc>{
		
		//满多少元
		private int fill;
		
		//减多少元
		private int minus;

		//折扣 0.8
		private int discount;

		//数量
		private int num;

		public RuleDesc(int fill, int minus) {
			super();
			this.fill = fill;
			this.minus = minus;
		}


		public int getNum() {
			return num;
		}


		public void setNum(int num) {
			this.num = num;
		}


		public int getDiscount() {
			return discount;
		}

		public void setDiscount(int discount) {
			this.discount = discount;
		}

		public int getFill() {
			return fill;
		}

		public void setFill(int fill) {
			this.fill = fill;
		}

		public int getMinus() {
			return minus;
		}

		public void setMinus(int minus) {
			this.minus = minus;
		}


		@Override
		public int compareTo(RuleDesc o) {
			if(this.fill < o.getFill()) {
				return -1;
			}else if(this.fill > o.getFill()) {
				return 1;
			}
			return 0;
		}

		@Override
		public String toString() {
			return "RuleDesc [fill=" + fill + ", minus=" + minus + "]";
		}
		
		
	}
	
	
}


