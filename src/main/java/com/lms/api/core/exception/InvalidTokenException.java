package com.lms.api.core.exception;

import com.lms.api.constant.Code.ERROR;

/**
 * 无效token异常
 * @author Ming
 * @date 2018年11月30日
 */
public class InvalidTokenException extends BaseException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public InvalidTokenException() {
		super();
		this.code=ERROR.ERROR_CODE_107;
		this.message="无效token";
	}
	
}
