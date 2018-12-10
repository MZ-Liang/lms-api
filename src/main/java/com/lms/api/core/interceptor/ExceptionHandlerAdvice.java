package com.lms.api.core.interceptor;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lms.api.constant.Code;
import com.lms.api.constant.Code.ERROR;
import com.lms.api.core.exception.BaseException;
import com.lms.api.model.ReturnMsg;

/**
   * 全局异常处理
 * @author Ming
 * @date 2018年11月7日
 */
@ControllerAdvice
@ResponseBody
@SuppressWarnings("rawtypes")
public class ExceptionHandlerAdvice {
	
	@ExceptionHandler(Exception.class)
    public ReturnMsg handleException(Exception e) {
        e.printStackTrace();
        return new ReturnMsg(Code.ERROR, e.getMessage());
    }
	
	@ExceptionHandler(BaseException.class)
	public ReturnMsg handleNoUserException(BaseException e) {
		e.printStackTrace();
		
		return new ReturnMsg(e.getCode(), e.getMessage());
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ReturnMsg handleNullPointerException(NullPointerException e) {
		e.printStackTrace();
		return new ReturnMsg(ERROR.ERROR_CODE_103, "空数据异常");
	}
	
}
