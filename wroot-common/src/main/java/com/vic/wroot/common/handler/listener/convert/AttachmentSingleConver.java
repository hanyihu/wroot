package com.vic.wroot.common.handler.listener.convert;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.vic.wroot.common.tool.Tools;

import java.io.IOException;


/**
 * 附件id-->附件URL
 * use:@JsonSerialize(using=AttachmentSingleConver.class) 
 * @author VIC
 *
 */
public class AttachmentSingleConver extends JsonSerializer<Integer> {


	@Override
	public void serialize(Integer value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		jgen.writeString(Tools.getImageUrl(value));
	}

}