package com.lms.api.core.exception;

import com.lms.api.constant.Code.ERROR;

/**
 * ip地址异常
 * @author Ming
 * @date 2018年11月30日
 */
public class IpAddressException extends BaseException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public IpAddressException() {
		super();
		this.code=ERROR.ERROR_CODE_108;
		this.message="ip地址异常";
	}

}
