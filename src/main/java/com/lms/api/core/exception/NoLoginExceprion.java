package com.lms.api.core.exception;

import com.lms.api.constant.Code.ERROR;

/**
   *  未登录异常
 * @author Ming
 * @date 2018年11月9日
 */
public class NoLoginExceprion extends BaseException{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public NoLoginExceprion() {
		super();
		setCode(ERROR.ERROR_CODE_104);
		setMessage("未登录");
	}

	public NoLoginExceprion(Integer code) {
		super(code);
		// TODO Auto-generated constructor stub
	}

	public NoLoginExceprion(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NoLoginExceprion(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NoLoginExceprion(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
