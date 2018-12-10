package com.lms.api.core.exception;

import com.lms.api.constant.Code.ERROR;

/**
 * 密码错误异常
 * @author Ming
 * @date 2018年11月1日
 */
public class PasswordErrorException extends BaseException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public PasswordErrorException() {
		super();
		setCode(ERROR.ERROR_CODE_101);
		setMessage("密码错误");
	}

	public PasswordErrorException(Integer code) {
		super(code);
		// TODO Auto-generated constructor stub
	}

	public PasswordErrorException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public PasswordErrorException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public PasswordErrorException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
