package com.relyy.shop.backend.common;

import lombok.Data;

/**
 * @Description
 * @Created by cairuirui
 * @Date 2021/4/29
 */
@Data
public class ResponseResult<T> {

	private boolean success = true;
	private T data;
	private String code;
	private String msg;

	public ResponseResult() {

	}

	public ResponseResult(boolean success, String code,String msg) {
		this.success = success;
		this.data = null;
		this.code = code;
		this.msg = msg;
	}

	public ResponseResult(boolean success) {
		this.success = success;
	}


}
