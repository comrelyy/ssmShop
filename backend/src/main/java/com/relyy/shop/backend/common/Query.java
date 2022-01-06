package com.relyy.shop.backend.common;

import lombok.Data;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查询参数
 */
@Data
public class Query<T>{
	private static final long serialVersionUID = 1L;
	// 
	private Long offset;
	// 每页条数
	private Long limit;
	private Long page;

	private T condition;

	public Query(Map<String, Object> params) {
		// 分页参数
		this.offset = Long.valueOf(params.get("offset").toString());
		this.limit = Long.valueOf(params.get("limit").toString());
		this.page = offset / limit + 1;
	}

	public Query(Map<String, Object> params,Class<T> tClass) throws InstantiationException,IllegalAccessException{
		// 分页参数
		this.offset = Long.valueOf(params.get("offset").toString());
		this.limit = Long.valueOf(params.get("limit").toString());
		this.page = offset / limit + 1;
		params.remove("offset");
		params.remove("limit");
		if (!params.isEmpty()){
			condition = tClass.newInstance();
			params.forEach((k,v) -> {
				try {
					Field declaredField = tClass.getDeclaredField(k);
					declaredField.setAccessible(true);
					declaredField.set(condition,v);
//					Class<?> fieldType = declaredField.getType();
//					String methodName = "set" + WordUtils.capitalizeFully(k);
//					Method method = tClass.getMethod(methodName, fieldType);
//					tClass.
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			});
		}
	}
}
