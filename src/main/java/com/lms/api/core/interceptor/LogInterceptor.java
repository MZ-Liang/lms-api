package com.lms.api.core.interceptor;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.lms.api.util.CalendarUtility;
import com.lms.api.util.HttpUtility;

import io.swagger.annotations.ApiOperation;

/**
 * 日志拦截处理切面
 * @author Ming
 * @date 2018年12月7日
 */
@Aspect
@Component
public class LogInterceptor {
	//设置切入点：这里直接拦截被@RestController注解的类
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void pointcut() {
        
    }
    
    /**
     * 切面方法,记录日志
     * @return
     * @throws Throwable 
     */
    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
    	System.out.println("--------------------------------");
        long beginTime = CalendarUtility.getSystemMillisecondTime();//1、开始时间 
        System.out.println("beginTime:"+beginTime);
        String uri = HttpUtility.getRequest().getRequestURI();
        System.out.println("uri:"+uri);
        //访问目标方法的参数 可动态改变参数值
        Object[] args = joinPoint.getArgs();
        System.out.println("参数："+ JSON.toJSONString(args));// 请求request，response不能为参，不然json解析报异常
        //方法名获取
        String methodName = joinPoint.getSignature().getName();
        System.out.println("方法名："+methodName);

        Signature signature = joinPoint.getSignature();
        //获取执行的方法
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        
        String[] strings = methodSignature.getParameterNames();
        System.out.println("参数名：");
        System.out.println(Arrays.toString(strings));
        
        
        
        //调用实际方法
        Object object = joinPoint.proceed();
        //获取ApiOperation注解对象
        ApiOperation operation = AnnotationUtils.getAnnotation(method, ApiOperation.class);
        if(operation == null) {
            return object;
        } 
        System.out.println("value:"+operation.value());
        System.out.println("code:"+operation.code());
        System.out.println("nickname:"+operation.nickname());
        long endTime = CalendarUtility.getSystemMillisecondTime();
        System.out.println("endTime:"+endTime);
        
        System.out.println("+++++++++++++++++++++++");
        return object;
    }
    
}

