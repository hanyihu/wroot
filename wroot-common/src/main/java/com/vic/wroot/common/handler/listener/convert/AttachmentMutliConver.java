package com.vic.wroot.common.handler.listener.convert;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.vic.wroot.common.tool.Tools;

import java.io.IOException;


/**
 * 多附件IDs-->附件URLs
 * use:@JsonSerialize(using=AttachmentMutliConver.class) 
 * @author VIC
 *
 */
public class AttachmentMutliConver extends JsonSerializer<String> {


	@Override
	public void serialize(String value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
        jgen.writeObject(Tools.getImageUrls(value));
	}

}