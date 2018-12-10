package com.lms.api.core.exception;

import com.lms.api.constant.Code.ERROR;

/**
 * 用户不存在异常
 * @author Ming
 * @date 2018年11月1日
 */
public class NoUserException extends BaseException{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public NoUserException() {
		super();
		setCode(ERROR.ERROR_CODE_102);
		setMessage("用户不存在");
	}

	public NoUserException(Integer code) {
		super(code);
		// TODO Auto-generated constructor stub
	}


	public NoUserException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NoUserException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NoUserException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
