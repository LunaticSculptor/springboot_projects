package com.example.book_manager.aspect;

import org.apache.commons.logging.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.example.book_manager.service.*.*(..))")
    public Object LogExecutionTime (ProceedingJoinPoint proceedingJoinPoint)
            throws Throwable {
        long startTime = System.currentTimeMillis();
        Object proceed = proceedingJoinPoint.proceed();
        long stopTime = System.currentTimeMillis();

        long timeTake = stopTime - startTime;
//        System.out.println(proceedingJoinPoint.getSignature() + " executed in " +
//                timeTake + " ms");

        logger.info("{} executed in {} ms", proceedingJoinPoint.getSignature(), timeTake);
        return proceed;
    }
}
