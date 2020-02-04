package com.code.jms.utils;

import java.io.Serializable;
import java.util.Map;
public class HttpResponseBody<T> implements Serializable {

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -5406928126690333502L;
	
	private String code;//成功或失败的响应码，自己定义常量 如：0000，0001，0002等
	
	private String message;//响应消息，自己定义常量，如：用户名已存在
	
	private T data;//具体的业务数据
	
	public HttpResponseBody(String code, String message, T data){
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
}

