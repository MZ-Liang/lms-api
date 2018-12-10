package com.lms.api.core.interceptor;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.lms.api.core.exception.NoLoginExceprion;

/**
 * Controller层 不登录拦截
 * 
 * @author Ming
 * @date 2018年11月9日
 */
@Aspect
@Component
public class ControllerLoginInterceptor {
	
	/**
	 * 未登录处理切入点
	 */
	@Pointcut("execution(public com.lms.api.model.ReturnMsg  com.lms.api.controller..*(..))")
	public void noLoginPointcut() {
	}

	/**
	 * 未登录处理
	 * 
	 * @throws Exception
	 */
	@Before("noLoginPointcut()")
	public void before() throws Exception {
		// 判断登录
		if (null == SecurityUtils.getSubject().getPrincipal()) {
			// 未登录，抛出异常
			throw new NoLoginExceprion();
		}
	}

}
