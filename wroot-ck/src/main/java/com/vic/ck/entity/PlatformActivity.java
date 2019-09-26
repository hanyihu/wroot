package com.vic.ck.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vic.ck.util.CommonUtils;
import com.vic.wroot.common.annotation.AttachmentFlag;
import com.vic.wroot.common.annotation.AttachmentFlag.AttachmenType;

/**
 * 平台活动表
 * 
 * @author VIC
 */
public class PlatformActivity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 1-抢红包 2-抢话费 3-推荐商家有奖
	 */
	private Integer type;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 图标
	 */
	@AttachmentFlag(AttachmenType.SIGN)
	private Integer icon;
	/**
	 * 活动内容
	 */
	@AttachmentFlag(AttachmenType.CONTENT)
	private String content;
	/**
	 * 开始时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	/**
	 * 结束时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	/**
	 * 是否启用
	 */
	private Integer enabled;
	/**
	 * 为红包类型时：设置的红包总总流水占比 ，如 1-->5%
	 */
	@JsonIgnore
	private Integer redpacketRule;
	/**
	 * 话费规则，总共分5级，邀请人数对应金额 3:50,6:100,10:150,20:200,40:300
	 */

	@JsonIgnore
	private String phoneChargeRule;
	

	/**
	 * type= 3时候  推荐的奖励金额
	 */
	
	@JsonIgnore
	private BigDecimal recommentRule;
	
	@JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime;
	
	/**
	 * 5个级别
	 */
	private List<PhoneChargeLevel> phoneChargeLevels;
	
	// 5个级别总共要邀请多少人，也即最大的级别的人数
	@JsonIgnore
	private int maxRecommendNum;
	
	
	public String getIconUrl() {
		return CommonUtils.getImageUrl(icon);
	}
	
	public String getTypeDesc() {
		return type == null ? "推荐商家有奖" :(type == 1?"抢红包":(type==2?"抢话费":"推荐商家有奖"));
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
	 * set：1-抢红包 2-抢话费
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * get：1-抢红包 2-抢话费
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * set：标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * get：标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * set：图标
	 */
	public void setIcon(Integer icon) {
		this.icon = icon;
	}

	/**
	 * get：图标
	 */
	public Integer getIcon() {
		return icon;
	}

	/**
	 * set：活动内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * get：活动内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * set：开始时间
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * get：开始时间
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * set：结束时间
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * get：结束时间
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * set：是否启用
	 */
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	/**
	 * get：是否启用
	 */
	public Integer getEnabled() {
		return enabled;
	}

	/**
	 * set：为红包类型时：设置的红包总总流水占比 ，如 1-->5%
	 */
	public void setRedpacketRule(Integer redpacketRule) {
		this.redpacketRule = redpacketRule;
	}

	/**
	 * get：为红包类型时：设置的红包总总流水占比 ，如 1-->5%
	 */
	public Integer getRedpacketRule() {
		return redpacketRule;
	}

	/**
	 * set：话费规则，总共分5级，邀请人数对应金额 3:50,6:100,10:150,20:200,40:300
	 */
	public void setPhoneChargeRule(String phoneChargeRule) {
		this.phoneChargeRule = phoneChargeRule;
	}

	/**
	 * get：话费规则，总共分5级，邀请人数对应金额 3:50,6:100,10:150,20:200,40:300
	 */
	public String getPhoneChargeRule() {
		return phoneChargeRule;
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
	
	
	public int getMaxRecommendNum() {
		if(maxRecommendNum > 0) {
			return maxRecommendNum;
		}else {
			List<PhoneChargeLevel> levels = getPhoneChargeLevels();
			
			return  levels.get(levels.size() - 1).getNumber();
		}
		
	
	}

	public void setMaxRecommendNum(int maxRecommendNum) {
		this.maxRecommendNum = maxRecommendNum;
	}

	public BigDecimal getRecommentRule() {
		return recommentRule;
	}

	public void setRecommentRule(BigDecimal recommentRule) {
		this.recommentRule = recommentRule;
	}

	public void setPhoneChargeLevels(List<PhoneChargeLevel> phoneChargeLevels) {
		this.phoneChargeLevels = phoneChargeLevels;
	}
	/**
	 * 活动当前抢话费活动的具体规则
	 * 话费规则，总共分5级，邀请人数对应金额 3:50,6:100,10:150,20:200,40:300
	 */
	public List<PhoneChargeLevel> getPhoneChargeLevels(){
		if(this.phoneChargeLevels!= null) {
			return phoneChargeLevels;
		}
		if( type != null && 2==type && StringUtils.isNotBlank(phoneChargeRule)) {
			List<PhoneChargeLevel> list = new ArrayList<PhoneChargeLevel>();
			String[] rules = this.phoneChargeRule.split(",");
			for(String rule : rules) {
				String[] rs = rule.split(":");
				if(rs.length == 2) {
					try {
						list.add(new PhoneChargeLevel(Integer.parseInt(rs[0]), Integer.parseInt(rs[1])));
					}catch (NumberFormatException e ) {
					}
				}
				
			}
			Collections.sort(list);
			
			for(int i=0; i<list.size();	 i++) {
				PhoneChargeLevel cur = list.get(i);
				cur.setLevel(i + 1);
			}
			//最多需要要请的人数 
			this.maxRecommendNum = list.get(list.size() - 1).getNumber();
			this.phoneChargeLevels = list;
			return list;
		}
		return null;
	}
	/**根据邀请的人数获取当前话费活动的话费规则*/
	public PhoneChargeLevel findPhoneChargeLevel(int number) {
		List<PhoneChargeLevel> rs = getPhoneChargeLevels();
		PhoneChargeLevel result = null;
		for(PhoneChargeLevel r : rs) {
			if(r.getNumber() <= number ) {
				result = r;
			}
		}
		return result;
	}
	
	/**
	 * 当前邀请人数符合的红包级别
	 */
	public int canOpenLevel(int number){
		PhoneChargeLevel level = findPhoneChargeLevel(number);
		return level == null ? 0 : level.getLevel();
	}
	/**
	 * 用户是否可以打开这个红包
	 */
	public void setCanOpen(int currentLevel) {
		if(phoneChargeLevels == null || currentLevel==0) return;
		for(PhoneChargeLevel l : phoneChargeLevels) {
			if(l.getLevel() <= currentLevel) {
				l.setCanOpen(true);
			}
		}
	}
	
	/**
	 * 初始化 用户可以打开的红包 以及对应的红包的次数 
	 * @param number 邀请到的人数
	 */
	public void initCanOpened(int number){
		int max = getMaxRecommendNum();
		if(number > max) {
			int time = number / max; //可以打开几次
			addCanOpendTime(time, 5);
			number = number % max; // 剩余的邀请人数
		}
		int canOpenLevel = canOpenLevel(number);
		if(canOpenLevel > 0) {
			addCanOpendTime(1,canOpenLevel);
		}
	}
	
	/**
	 * 增加小于等于当前级别的红包的可打开次数
	 */
	public void addCanOpendTime(int time, int level){
		 List<PhoneChargeLevel> list = getPhoneChargeLevels();
		 if(list != null) {
			 for(PhoneChargeLevel l : list) {
				 if(l.getLevel() <= level){
					 l.setCanOpendTime(l.getCanOpendTime() + time);
				 }
			 }
		 }
	}
	
	public class PhoneChargeLevel implements Comparable<PhoneChargeLevel> {
		
		

		//需要邀请的人数
		private int number;

		// 对应的金额
		private int amount;
		
		//对应的级别
		private int level;
		
		//用户是否可以打开这个红包
		private boolean canOpen;
		
		//这个红包是否被打开过 
		@Deprecated
		private boolean opened;
		
		//打开的次数 因为后续需求修改为   抢话费为循环打开红包 即打开到最高级别的时候可以循环从低到高再次打开(只要邀请的人数最够) 所以opened 作废了
		private int openedTime;
		
		//可以打开多少次
		private int canOpendTime;
		
		

		public PhoneChargeLevel(int number, int amount) {
			super();
			this.number = number;
			this.amount = amount;
		}


		public PhoneChargeLevel(int number, int amount, int level) {
			super();
			this.number = number;
			this.amount = amount;
			this.level = level;
		}


		public PhoneChargeLevel() {
			super();
		}
		


		public int getOpenedTime() {
			return openedTime;
		}

		/**
		 * 前提为设置了可打开次数	会判断当前红包是否可打开
		 */
		public void setOpenedTime(int openedTime) {
			this.openedTime = openedTime;
			if(this.canOpendTime > this.openedTime) {
				this.canOpen = true;
			}else {
				this.canOpen = false;
			}
		}


		@Override
		public int compareTo(PhoneChargeLevel o) {
			if (this.number < o.getNumber()) {
				return -1;
			} else if (this.number > o.getNumber()) {
				return 1;
			}
			return 0;
		}


		public int getNumber() {
			return number;
		}


		public void setNumber(int number) {
			this.number = number;
		}


		public int getAmount() {
			return amount;
		}


		public void setAmount(int amount) {
			this.amount = amount;
		}


		public int getLevel() {
			return level;
		}


		public void setLevel(int level) {
			this.level = level;
		}


		
		
		public boolean isCanOpen() {
			return canOpen;
		}


		public void setCanOpen(boolean canOpen) {
			this.canOpen = canOpen;
		}


		public boolean isOpened() {
			return opened;
		}


		public void setOpened(boolean opened) {
			this.opened = opened;
		}

		public int getCanOpendTime() {
			return canOpendTime;
		}


		public void setCanOpendTime(int canOpendTime) {
			this.canOpendTime = canOpendTime;
		}


		@Override
		public String toString() {
			return "PhoneChargeLevel [number=" + number + ", amount=" + amount + ", level=" + level + ", canOpen="
					+ canOpen + ", opened=" + opened + ", openedTime=" + openedTime + ", canOpendTime=" + canOpendTime
					+ "]";
		}


		
		

	}
	public static void main(String[] args) {
		PlatformActivity p = new PlatformActivity();
		p.setType(2);
		p.setPhoneChargeRule("3:50,6:100,10:150,20:200,40:300");
		List<PhoneChargeLevel> l = p.getPhoneChargeLevels();
		for(PhoneChargeLevel t : l) {
			System.out.println(t);
		}
		PhoneChargeLevel t = p.findPhoneChargeLevel(21);
		System.out.println();
		System.out.println(t);
		System.out.println(p.getMaxRecommendNum());
		
		p.initCanOpened(52);
		for(PhoneChargeLevel i : l) {
			i.setOpenedTime(1);
		}
		
		for(PhoneChargeLevel i : l) {
			System.out.println(i);
		}
		
	}

	
	

}
