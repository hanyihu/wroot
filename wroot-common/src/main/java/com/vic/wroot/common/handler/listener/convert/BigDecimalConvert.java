package com.vic.wroot.common.handler.listener.convert;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;


/**
 * 转JSON时候去掉BigDecimal 后面的0
 * use:@JsonSerialize(using=BigDecimalConvert.class) 
 * @author VIC
 *
 */
public class BigDecimalConvert extends JsonSerializer<BigDecimal> {


	@Override
	public void serialize(BigDecimal value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		jgen.writeString(value.stripTrailingZeros().toPlainString());
//		jgen.writeNumber(value.stripTrailingZeros());
	}

	
}