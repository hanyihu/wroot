package com.vic.wroot.common.handler.listener.convert;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 转JSON的时候把null转为""
 * @author VIC
 *
 */
public class JsonObjectMapper extends ObjectMapper { 
    private static final long serialVersionUID = 1L; 
   
    public JsonObjectMapper() { 
        super(); 
        // 空值处理为空串 
        this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() { 
            @Override 
            public void serialize(Object value, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException { 
                jg.writeString(""); 
            } 
        }); 
    } 
}
/*
 * <mvc:annotation-driven> 
    <mvc:message-converters> 
        <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter"> 
            <property name="objectMapper"> 
                <bean class="com.vic.ck.api.handler.conver.JsonObjectMapper"></bean> 
            </property> 
        </bean> 
    </mvc:message-converters> 
</mvc:annotation-driven> 
*/
