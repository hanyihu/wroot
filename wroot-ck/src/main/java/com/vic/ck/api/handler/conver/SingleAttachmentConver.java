package com.vic.ck.api.handler.conver;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.vic.ck.util.CommonUtils;


/**
 * 附件id-->附件URL
 * use:@JsonSerialize(using=SingleAttachmentConver.class) 
 * @author VIC
 *
 */
public class SingleAttachmentConver extends JsonSerializer<Integer> {


	@Override
	public void serialize(Integer value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		jgen.writeString(CommonUtils.getImageUrl(value));
	}

}