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

	public ResponseResult(boolean success, T data,String code,String msg) {
		this.success = success;
		this.data = data;
		this.code = code;
		this.msg = msg;
	}
	public ResponseResult(boolean success, String code,String msg) {
		this(success, null,code,msg);
	}

	public ResponseResult(boolean success,String msg) {
		this(success, null,null,msg);
	}

	public ResponseResult(boolean success,T data) {
		this(success, data,null,null);
	}

	public ResponseResult(boolean success) {
		this.success = success;
	}

	public static ResponseResult ok() {
		return new ResponseResult(true,null,"0","ok");
	}

	public static ResponseResult error() {
		return new ResponseResult(false);
	}

	public static ResponseResult ok(String s) {
		return new ResponseResult(true,s);
	}

	public static ResponseResult error(String s) {
		return new ResponseResult(false,s);
	}
}
