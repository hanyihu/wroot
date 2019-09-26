package com.vic.ck.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 后台的消息提醒数量
 * 
 * @author VIC
 *
 */
public class ConsoleBellTip {

	/**
	 * 组装提醒 
	 */
	public List<UrlInfo> getTips() {
		List<UrlInfo> result = new ArrayList<UrlInfo>();
		if (customerFetch > 0) {
			result.add(new UrlInfo(TipFlag.customeFetch, "/console/customer/fetch/", "用户提现", customerFetch));
		}
		if (mallSwap > 0) {
			result.add(new UrlInfo(TipFlag.mallSwap, "/console/mall/swap/", "积分兑换", mallSwap));
		}
		if (phoneFetch > 0) {
			result.add(new UrlInfo(TipFlag.phoneFetch, "/console/activity/fetch/", "话费充值", phoneFetch));
		}
		return result;
	}
	
	/**
	 * 用户提现申请的未处理的数量
	 */
	private int customerFetch;

	/**
	 * 积分兑换的未处理的数量
	 */
	private int mallSwap;

	/**
	 * 话费充值的未处理的数量
	 */
	private int phoneFetch;

	public int getCustomerFetch() {
		return customerFetch;
	}

	public void setCustomerFetch(int customerFetch) {
		this.customerFetch = customerFetch;
	}

	public int getMallSwap() {
		return mallSwap;
	}

	public void setMallSwap(int mallSwap) {
		this.mallSwap = mallSwap;
	}

	public int getPhoneFetch() {
		return phoneFetch;
	}

	public void setPhoneFetch(int phoneFetch) {
		this.phoneFetch = phoneFetch;
	}

	public class UrlInfo {
		
		private TipFlag flag;
		
		private String url;

		private String name;

		private int num;

		public UrlInfo(TipFlag flag,String url, String name, int num) {
			super();
			this.flag = flag;
			this.url = url;
			this.name = name;
			this.num = num;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getNum() {
			return num;
		}

		public void setNum(int num) {
			this.num = num;
		}

		public TipFlag getFlag() {
			return flag;
		}

		public void setFlag(TipFlag flag) {
			this.flag = flag;
		}
		
	}
	public enum TipFlag{
		customeFetch, mallSwap, phoneFetch;
	}

}
