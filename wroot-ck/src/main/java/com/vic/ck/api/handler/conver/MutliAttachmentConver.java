package com.vic.ck.api.handler.conver;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.vic.ck.util.CommonUtils;


/**
 * 多附件IDs-->附件URLs
 * use:@JsonDeserialize(using=SingleAttachmentConver.class) 
 * @author VIC
 *
 */
public class MutliAttachmentConver extends JsonSerializer<String> {


	@Override
	public void serialize(String value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		jgen.writeObject(CommonUtils.getImageUrls(value));;
	}

}