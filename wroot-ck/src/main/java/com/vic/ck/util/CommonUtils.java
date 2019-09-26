package com.vic.ck.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vic.ck.api.constant.FinalFiledParams;
import com.vic.wroot.common.util.StringUtils;

public class CommonUtils {
	
	private static Logger logger = LoggerFactory.getLogger(CommonUtils.class);

	/**获得附件的url**/
	public static String getImageUrl(int image){
		if(image > 0) {
			return FinalFiledParams.ATTACHMENT_PREFIX + image;
		}
		return null;
	}
	/**获得附件的url**/
	public static String getImageUrl(Integer image){
		if(image != null &&image > 0) {
			return FinalFiledParams.ATTACHMENT_PREFIX + image;
		}
		return null;
	}
	
	/**
	 *获得附件列表 
	 */
	public static List<String> getImageUrls(String images) {
		if(StringUtils.isNotBlank(images)) {
			String[] arr = images.split(",");
			List<String> list = new ArrayList<String>();
			for(String str : arr) {
				try {
					int imageId = Integer.parseInt(str);//过滤掉非法
					list.add(FinalFiledParams.ATTACHMENT_PREFIX + imageId);
				}catch (NumberFormatException  e) {
				}
			}
			return list;
		}
		
		return null;
	}
	
	/**
	 * 为富文本的图片在100%宽度 主要用户APP展示
	 */
	public static String ueditorImgWigth(String html){
		if(StringUtils.isEmpty(html)) {
			return html;
		}
		String replacement="$0 width=\"100%;\" ";
		return html.replaceAll("(<img)", replacement);
	}

	/**
	 * 
	 * @param distance 米
	 * @return
	 */
	public static String getDistanceDesc(BigDecimal distance) {
		if(distance !=null ) {
			// 大于 0 小于1000m
			if(distance.compareTo(new BigDecimal(0)) >=0 && distance.compareTo(new BigDecimal(1000)) <=0) {
				return distance.setScale(0, BigDecimal.ROUND_HALF_UP).toString() + "m";
			}
			if(distance.compareTo(new BigDecimal(1000)) >=0) {
				return distance.divide(new BigDecimal(1000)).setScale(2, BigDecimal.ROUND_HALF_UP).toString() + "km";
			}
			
		}
		return "未知距离";
	}
	
	private static ObjectMapper mapper = new ObjectMapper();
	public static String printObject2Json(Object obj) {
		String result = "";
		try {
			result = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return result;
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
		Date d1 = new Date();
		Test t = new Test(d1);
		printObject2Json(t);
		System.out.println(getDistanceDesc(new BigDecimal(800)));
	}
}

class Test {
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date d1;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date d2;
	
	@JsonFormat(pattern="HH:mm")
	private Date d3;
	
	private Date d4;
	
	

	public Test(Date d1) {
		super();
		this.d1 = d1;
		this.d2 = d1;
		this.d3 = d1;
		this.d4 = d1;
	}

	public Test() {
		super();
	}

	public Date getD1() {
		return d1;
	}

	public void setD1(Date d1) {
		this.d1 = d1;
	}

	public Date getD2() {
		return d2;
	}

	public void setD2(Date d2) {
		this.d2 = d2;
	}

	public Date getD3() {
		return d3;
	}

	public void setD3(Date d3) {
		this.d3 = d3;
	}

	public Date getD4() {
		return d4;
	}

	public void setD4(Date d4) {
		this.d4 = d4;
	}
	
	
}
