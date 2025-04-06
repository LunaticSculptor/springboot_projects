package com.example.myPractice.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    @Around("execution(* com.example.myPractice.service.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();  // Capture start time
        Object proceed = joinPoint.proceed();  // Execute the method
        long timeTaken = System.currentTimeMillis() - startTime;  // Calculate execution time
        System.out.println("Method " + joinPoint.getSignature() + " executed in " + timeTaken + " ms");

        return proceed;  // Return the result of the method execution
    }
}
