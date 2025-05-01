package com.becoder.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

//	@Before("execution(* com.becoder.controller..*(..))")
//	public void beforeController(JoinPoint joinPoint)
//	{
//		Signature signature = joinPoint.getSignature();
//		String className = signature.getDeclaringType().getSimpleName();
//		String methodName = signature.getName();
//		log.info("Calling :: {} :: {}()",className,methodName);
//	}
//	
//	@After("execution(* com.becoder.controller..*(..))")
//	public void afterController(JoinPoint joinPoint)
//	{
//		Signature signature = joinPoint.getSignature();
//		String className = signature.getDeclaringType().getSimpleName();
//		String methodName = signature.getName();
//		log.info("End Calling :: {} :: {}()",className,methodName);
//	}
	
	@Around("execution(* com.becoder.controller..*(..))")
	public Object jointPointController(ProceedingJoinPoint joinPoint) throws Throwable
	{
		Signature signature = joinPoint.getSignature();
		String className = signature.getDeclaringType().getSimpleName();
		String methodName = signature.getName();
		log.info("Calling :: {} :: {}()",className,methodName);
		long start = System.currentTimeMillis();
		Object result = joinPoint.proceed();
		long duration = System.currentTimeMillis()-start;
		log.info("Exceution End :: {} :: {}() ::{} ms",className,methodName,duration);
		return result;
	}
	
	@Around("execution(* com.becoder.service..*(..))")
	public Object jointPointService(ProceedingJoinPoint joinPoint) throws Throwable
	{
		Signature signature = joinPoint.getSignature();
		String className = signature.getDeclaringType().getSimpleName();
		String methodName = signature.getName();
		log.info("Calling :: {} :: {}()",className,methodName);
		long start = System.currentTimeMillis();
		Object result = joinPoint.proceed();
		long duration = System.currentTimeMillis()-start;
		log.info("Exceution End :: {} :: {}() ::{} ms",className,methodName,duration);
		return result;
	}
	
	
}
