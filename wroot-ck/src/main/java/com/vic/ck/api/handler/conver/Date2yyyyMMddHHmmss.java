package com.vic.ck.api.handler.conver;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;


/**
 * date-->format String
 * use:@JsonDeserialize(using=Date2yyyyMMdd.class) 
 * @author VIC
 *
 */
public class Date2yyyyMMddHHmmss extends JsonSerializer<Date> {


	@Override
	public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		jgen.writeString(DateFormatUtils.format(value, "yyyy-MM-dd"));
	}

}