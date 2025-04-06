package com.example.crud.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

//    // Pointcut for all methods in the service layer
//    @Around("execution(* com.example.crud.service.*.*(..))")
//    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
//        long startTime = System.currentTimeMillis();  // Capture start time
//        Object proceed = joinPoint.proceed();  // Execute the method
//        long timeTaken = System.currentTimeMillis() - startTime;  // Calculate execution time
//        System.out.println("Method " + joinPoint.getSignature() + " executed in " + timeTaken + " ms");
//
//        return proceed;  // Return the result of the method execution
//    }

    @Around("@annotation(com.example.crud.aspect.LogExecutionTime)")  // Pointcut for methods with @LogExecutionTime
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();  // Start time
        Object proceed = joinPoint.proceed();  // Execute the method
        long timeTaken = System.currentTimeMillis() - startTime;  // Calculate execution time

        System.out.println("Method " + joinPoint.getSignature() + " executed in " + timeTaken + " ms");

        return proceed;  // Return method's original result
    }
}
