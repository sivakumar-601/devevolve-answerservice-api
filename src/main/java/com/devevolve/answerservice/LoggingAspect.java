package com.devevolve.answerservice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	@Pointcut("execution(* com.devevolve.answerservice..*(..))")
	public void applicationPackagePointcut() {}
	
	// Before method execution
    @Before("applicationPackagePointcut()")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Started: {} with arguments: {}", 
                     joinPoint.getSignature(), 
                     joinPoint.getArgs());
    }

   // After method execution
    @AfterReturning(pointcut = "applicationPackagePointcut()", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        logger.info("Completed: {} with return value: {}", 
                     joinPoint.getSignature(), 
                     result);
    }
    
    // Log execution time
    @Around("applicationPackagePointcut()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        
        logger.info("Executed: {} in {} ms", 
                     joinPoint.getSignature(), 
                     executionTime);
        return proceed;
    }
}
