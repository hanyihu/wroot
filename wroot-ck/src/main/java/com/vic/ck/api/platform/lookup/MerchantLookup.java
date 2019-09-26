package com.vic.ck.api.platform.lookup;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;

import com.vic.base.BaseApiLookup;

public class MerchantLookup extends BaseApiLookup{
	

	//商家id
	private Integer id;
	
	
    /**
     * 经度
     */
	@NotNull(message = "需传入当前经度")
    private BigDecimal longitude;

    /**
     * 纬度
     */
	@NotNull(message = "需传入当前纬度")
    private BigDecimal latitude;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}
	
	
	
}
