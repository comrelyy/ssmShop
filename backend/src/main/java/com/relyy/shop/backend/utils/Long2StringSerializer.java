package com.relyy.shop.backend.utils;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @Description 序列时将Long 转为字符串
 * @Created by Reeve Cai
 * @Date 2021/12/8
 */
public class Long2StringSerializer extends JsonSerializer<Long> {
	@Override
	public void serialize(Long value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		if (null != value) {
			jsonGenerator.writeString(value + "");
		}else {
			jsonGenerator.writeNull();
		}
	}
}
