package com.vic.ck.entity;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import com.vic.wroot.common.annotation.AttachmentFlag;
import com.vic.wroot.common.annotation.AttachmentFlag.AttachmenType;

/**
 *  对应系统配置表sys_config
 * @author VIC
 *
 */
public class SysConfig {

	private String code;
	
	private String name;
	
	@AttachmentFlag(AttachmenType.CONTENT)
	private String content;
	
	private String remark;
	/**
	 * 数据类型 1-input 2 textarea 3 富文本
	 */
	private int type;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	public int getIntContent(){
		if(StringUtils.isNotBlank(content)) {
			try{
				return Integer.parseInt(content.trim());
				
			}catch(Exception e){
				//NumberFormatException
			}
		}
		return 0;
	}
	
	public double getDoubleContent(){
		if(StringUtils.isNotBlank(content)) {
			try{
				return Double.parseDouble(content.trim());
				
			}catch(Exception e){
				//NumberFormatException
			}
		}
		return 0.0;
	}
	
	public BigDecimal getBigDecimalContent(){
		if(StringUtils.isNotBlank(content)) {
			try{
				return new BigDecimal(content.trim()).setScale(2, BigDecimal.ROUND_HALF_UP);
				
			}catch(Exception e){
			}
		}
		return  BigDecimal.ZERO;
	}
	
	
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	
	
}
