package com.lms.api.core.exception;

import com.lms.api.constant.Code.ERROR;

/**
   * 无权限异常
 * @author Ming
 * @date 2018年11月9日
 */
public class NoPermissionException extends BaseException{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public NoPermissionException() {
		super();
		setCode(ERROR.ERROR_CODE_105);
		setMessage("无权限");
	}

	public NoPermissionException(Integer code) {
		super(code);
		// TODO Auto-generated constructor stub
	}

	public NoPermissionException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NoPermissionException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NoPermissionException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
