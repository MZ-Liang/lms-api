package com.lms.api.core.exception;

import org.apache.shiro.authc.UnknownAccountException;

/**
 * 基础异常
 * @author Ming
 * @date 2018年11月1日
 */
public class BaseException extends UnknownAccountException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 错误码
	 */
	protected Integer code;
	/**
	 * 消息
	 */
	protected String message;
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 构造函数
	 * 
	 */
	public BaseException() {
		super();
	}

	/**
	 * 构造函数
	 * 
	 * @param message 错误信息
	 */
    public BaseException(String message) {
        super(message);
    }

	/**
	 * 构造函数
	 * 
	 * @param code 消息码
	 */
	public BaseException(Integer code) {
		super();
	}
	
	/**
	 * 构造函数
	 * 
	 * @param code 消息码
	 * @param message 错误信息
	 */
	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}

}
